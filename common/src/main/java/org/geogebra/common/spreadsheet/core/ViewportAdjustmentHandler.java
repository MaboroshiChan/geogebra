package org.geogebra.common.spreadsheet.core;

public interface ViewportAdjustmentHandler {

	// TODO I think all UI positions/sizes/rects should be float-valued
	void setScrollPosition(int x, int y);

	/**
	 * Retrieves the width of the scrollbar used for dragging content with the left mouse button
	 * @return The width of the scrollbar
	 */
	int getScrollBarWidth();
}