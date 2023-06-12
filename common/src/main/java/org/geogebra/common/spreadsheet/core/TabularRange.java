package org.geogebra.common.spreadsheet.core;

public class TabularRange {

	final int fromRow;
	final int toRow;
	final int fromCol;
	final int toCol;

	public TabularRange(int fromRow, int toRow, int fromCol, int toCol) {
		this.fromRow = fromRow;
		this.toRow = toRow;
		this.fromCol = fromCol;
		this.toCol = toCol;
	}

	public boolean isEmpty() {
		return (toRow - fromRow <= 0 && toRow >= 0) || (toCol - fromCol <= 0 && toCol >= 0);
	}

	public TabularRange merge(TabularRange range) {
		if (fromCol == range.fromCol && toCol == range.toCol) {
			if ((range.fromRow >= fromRow && range.fromRow <= toRow + 1)
					|| (fromRow >= range.fromRow && fromRow <= range.toRow + 1)) {
				return new TabularRange(Math.min(fromRow, range.fromRow),
						Math.max(toRow, range.toRow), fromCol, toCol);
			}
		}
		return null;
	}
}
