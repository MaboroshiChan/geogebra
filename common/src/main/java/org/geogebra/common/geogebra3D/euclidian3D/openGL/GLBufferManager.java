package org.geogebra.common.geogebra3D.euclidian3D.openGL;

import java.util.ArrayList;
import java.util.TreeMap;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.geogebra3D.euclidian3D.openGL.Manager.Type;

/**
 * manager for packing buffers
 */
public class GLBufferManager {

	private Index currentIndex;
	private BufferSegment currentBufferSegment, currentBufferSegmentForIndices;
	private GLBuffer vertexBuffer, normalBuffer, textureBuffer, colorBuffer;
	private GLBufferIndices curvesIndices;
	private int totalLength, indicesLength;
	private TreeMap<Index, BufferSegment> bufferSegments, bufferSegmentsForIndices;
	private int indicesIndex;

	private static class Index implements Comparable<Index> {
		private int indexForSet, indexForGeometry;

		/**
		 * simple constructor
		 */
		public Index() {
			// nothing done
		}

		/**
		 * create a copy
		 * 
		 * @param index
		 *            index
		 */
		public Index(Index index) {
			this.indexForSet = index.indexForSet;
			this.indexForGeometry = index.indexForGeometry;
		}

		/**
		 * set indices
		 * 
		 * @param forSet
		 *            index for set
		 * @param forGeometry
		 *            index for geometry
		 */
		public void set(int forSet, int forGeometry) {
			this.indexForSet = forSet;
			this.indexForGeometry = forGeometry;
		}

		@Override
		public int compareTo(Index o) {
			if (indexForSet < o.indexForSet) {
				return -1;
			}
			if (indexForSet > o.indexForSet) {
				return 1;
			}
			if (indexForGeometry < o.indexForGeometry) {
				return -1;
			}
			if (indexForGeometry > o.indexForGeometry) {
				return 1;
			}
			return 0;
		}

		public String toString() {
			return indexForSet + ", " + indexForGeometry;
		}
	}

	private static class BufferSegment {
		public int offset;
		public int length;

		public BufferSegment(int offset, int length) {
			this.offset = offset;
			this.length = length;
		}
	}

	/**
	 * constructor
	 */
	public GLBufferManager() {
		currentIndex = new Index();
		vertexBuffer = GLFactory.getPrototype().newBuffer();
		normalBuffer = GLFactory.getPrototype().newBuffer();
		textureBuffer = GLFactory.getPrototype().newBuffer();
		colorBuffer = GLFactory.getPrototype().newBuffer();
		curvesIndices = GLFactory.getPrototype().newBufferIndices();

		int size1 = Short.MAX_VALUE, size2 = size1 * 3;
		vertexBuffer.allocate(size1 * 3);
		normalBuffer.allocate(size1 * 3);
		textureBuffer.allocate(size1 * 2);
		colorBuffer.allocate(size1 * 4);
		curvesIndices.allocate(size2);


		bufferSegments = new TreeMap<Index, GLBufferManager.BufferSegment>();
		bufferSegmentsForIndices = new TreeMap<Index, GLBufferManager.BufferSegment>();
	}

	/**
	 * set current geometry set and geometry indices
	 * 
	 * @param index
	 *            geometry set index
	 * @param geometryIndex
	 *            geometry index
	 */
	public void setCurrentIndex(int index, int geometryIndex) {
		currentIndex.set(index, geometryIndex);
	}

	/**
	 * 
	 * @param array
	 *            array
	 * @param length
	 *            length to set
	 */
	public void setVertexBuffer(ArrayList<Double> array, int length) {
		currentBufferSegment = bufferSegments.get(currentIndex);
		if (currentBufferSegment == null) {
			int l = length / 3;
			currentBufferSegment = new BufferSegment(totalLength, l);
			bufferSegments.put(new Index(currentIndex), currentBufferSegment);
			totalLength += l;
			vertexBuffer.setLimit(totalLength * 3);
			normalBuffer.setLimit(totalLength * 3);
			textureBuffer.setLimit(totalLength * 2);
			colorBuffer.setLimit(totalLength * 4);
		}
		vertexBuffer.set(array, currentBufferSegment.offset * 3, currentBufferSegment.length * 3);
	}

	/**
	 * 
	 * @param array
	 *            array
	 * @param length
	 *            length to set
	 */
	public void setNormalBuffer(ArrayList<Double> array, int length) {
		normalBuffer.set(array, currentBufferSegment.offset * 3, currentBufferSegment.length * 3);
	}

	/**
	 * 
	 * @param array
	 *            array
	 * @param length
	 *            length to set
	 */
	public void setTextureBuffer(ArrayList<Double> array, int length) {
		textureBuffer.set(array, currentBufferSegment.offset * 2, currentBufferSegment.length * 2);
	}

	/**
	 * set colors buffer
	 * 
	 * @param color
	 *            color
	 */
	public void setColorBuffer(GColor color) {
		int offset = currentBufferSegment.offset * 4;
		colorBuffer.set((float) color.getRed() / 255, offset, currentBufferSegment.length, 4);
		offset++;
		colorBuffer.set((float) color.getGreen() / 255, offset, currentBufferSegment.length, 4);
		offset++;
		colorBuffer.set((float) color.getBlue() / 255, offset, currentBufferSegment.length, 4);
		offset++;
		colorBuffer.set((float) color.getAlpha() / 255, offset, currentBufferSegment.length, 4);
	}

	/**
	 * set indices
	 * 
	 * @param size
	 *            size to set
	 */
	public void setIndices(int size) {
		currentBufferSegmentForIndices = bufferSegmentsForIndices.get(currentIndex);
		if (currentBufferSegmentForIndices == null) {
			int length = 3 * 2 * size * PlotterBrush.LATITUDES;
			currentBufferSegmentForIndices = new BufferSegment(indicesLength, length);
			bufferSegmentsForIndices.put(new Index(currentIndex), currentBufferSegmentForIndices);
			indicesLength += length;
			indicesIndex = currentBufferSegmentForIndices.offset;
			curvesIndices.setLimit(indicesLength);
			for (int k = 0; k < size; k++) {
				for (int i = 0; i < PlotterBrush.LATITUDES; i++) {
					int iNext = (i + 1) % PlotterBrush.LATITUDES;
					// first triangle
					putToIndices(i + k * PlotterBrush.LATITUDES);
					putToIndices(i + (k + 1) * PlotterBrush.LATITUDES);
					putToIndices(iNext + (k + 1) * PlotterBrush.LATITUDES);
					// second triangle
					putToIndices(i + k * PlotterBrush.LATITUDES);
					putToIndices(iNext + (k + 1) * PlotterBrush.LATITUDES);
					putToIndices(iNext + k * PlotterBrush.LATITUDES);
				}
			}
		}
	}

	private void putToIndices(int index) {
		curvesIndices.put(indicesIndex, (short) (currentBufferSegment.offset + index));
		indicesIndex++;
	}

	/**
	 * draw
	 * 
	 * @param r
	 *            renderer
	 * @param hidden
	 *            if hidden
	 */
	public void draw(RendererShadersInterface r, boolean hidden) {

		if (totalLength == 0) {
			return;
		}

		vertexBuffer.rewind();
		normalBuffer.rewind();
		curvesIndices.rewind();

		((TexturesShaders) r.getTextures()).setPackedDash();
		r.setDashTexture(hidden ? Textures.DASH_PACKED_HIDDEN : Textures.DASH_PACKED);

		r.loadVertexBuffer(vertexBuffer, totalLength * 3);
		r.loadNormalBuffer(normalBuffer, totalLength * 3);
		r.loadColorBuffer(colorBuffer, totalLength * 4);
		if (r.areTexturesEnabled()) {
			r.loadTextureBuffer(textureBuffer, totalLength * 2);
		} else {
			r.disableTextureBuffer();
		}
		r.loadIndicesBuffer(curvesIndices, indicesLength);
		r.draw(Type.TRIANGLES, indicesLength);

	}
}
