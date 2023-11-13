package org.geogebra.common.spreadsheet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.IntStream;

import org.geogebra.common.BaseUnitTest;
import org.geogebra.common.awt.GFont;
import org.geogebra.common.spreadsheet.core.CellRenderableFactory;
import org.geogebra.common.spreadsheet.core.Spreadsheet;
import org.geogebra.common.spreadsheet.rendering.SelfRenderable;
import org.geogebra.common.spreadsheet.rendering.StringRenderer;
import org.geogebra.common.spreadsheet.style.CellFormat;
import org.geogebra.common.spreadsheet.style.SpreadsheetStyle;
import org.geogebra.common.util.shape.Rectangle;
import org.junit.Test;

public class SpreadsheetTest extends BaseUnitTest {

	@Test
	public void testTextDataRendering() {
		TestTabularData tabularData = new TestTabularData();
		Spreadsheet spreadsheet = new Spreadsheet(tabularData,
				new TestCellRenderableFactory());
		spreadsheet.setHeightForRows(20, IntStream.range(0, 5).toArray());
		StringCapturingGraphics graphics = new StringCapturingGraphics();
		tabularData.setContent(0, 0, "foo");
		tabularData.setContent(0, 1, "bar");
		spreadsheet.setViewport(new Rectangle(0, 100, 0, 100));
		spreadsheet.draw(graphics);
		assertThat(graphics.toString(), equalTo("col0,col1,1,foo,bar,2,3,4,5"));
	}

	private static class TestCellRenderableFactory implements CellRenderableFactory {
		@Override
		public SelfRenderable getRenderable(Object data, SpreadsheetStyle style,
				int row, int column) {
			return data == null ? null : new SelfRenderable(new StringRenderer(),
					GFont.PLAIN, CellFormat.ALIGN_LEFT, data);
		}
	}
}
