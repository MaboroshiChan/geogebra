package org.geogebra.desktop.spreadsheet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import org.geogebra.common.spreadsheet.core.Spreadsheet;
import org.geogebra.common.util.shape.Rectangle;
import org.geogebra.desktop.awt.GGraphics2DD;

public class SpreadsheetDemo {

	/**
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame("spreadsheet");
			Dimension preferredSize = new Dimension(800, 600);
			frame.setPreferredSize(preferredSize);
			Spreadsheet spreadsheet = new Spreadsheet(100, 200,
					(i, j) -> i + " x " + j);
			spreadsheet.getLayout().setWidthForColumns(60, IntStream.range(0, 10).toArray());
			spreadsheet.getLayout().setHeightForRows(20, IntStream.range(0, 10).toArray());
			SpreadsheetPanel sp = new SpreadsheetPanel(spreadsheet);
			sp.setPreferredSize(preferredSize);
			initParentPanel(frame, sp);
			spreadsheet.setViewport(sp.getViewport());

			frame.setVisible(true);
			frame.setSize(preferredSize);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static void initParentPanel(JFrame frame, SpreadsheetPanel sp) {
		JScrollBar vertical = new JScrollBar();
		JScrollBar horizontal = new JScrollBar(JScrollBar.HORIZONTAL);
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		top.add(sp);
		top.add(vertical);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(top);
		contentPane.add(horizontal);
		vertical.addAdjustmentListener(evt -> {
			sp.scrollY = evt.getValue() * 10;
			sp.spreadsheet.setViewport(sp.getViewport());
			frame.repaint();
		});
		horizontal.addAdjustmentListener(evt -> {
			sp.scrollX = evt.getValue() * 10;
			sp.spreadsheet.setViewport(sp.getViewport());
			frame.repaint();
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private static class SpreadsheetPanel extends JPanel {
		private final Spreadsheet spreadsheet;
		private int scrollX;
		private int scrollY;

		public SpreadsheetPanel(Spreadsheet spreadsheet) {
			this.spreadsheet = spreadsheet;
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					spreadsheet.getEventDispatcher().handlePointerUp(event.getX(), event.getY(),
							event.isControlDown() ? 1 : 0);
					repaint();
				}
			});
		}

		public Rectangle getViewport() {
			return new Rectangle(scrollX, scrollX + 500, scrollY, scrollY + 400);
		}

		public void paint(Graphics g) {
			((Graphics2D) g).setPaint(Color.YELLOW);
			g.fillRect(0, 0, 800, 600);
			((Graphics2D) g).setPaint(Color.BLUE);

			spreadsheet.draw(new GGraphics2DD((Graphics2D) g));
		}
	}
}
