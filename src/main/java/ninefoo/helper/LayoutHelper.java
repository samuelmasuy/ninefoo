package ninefoo.helper;

import java.awt.GridBagConstraints;

public class LayoutHelper {
	
	/**
	 * Specify the grid position
	 * @param row Row index
	 * @param col Column index
	 * @param mergedCells Number of merged columns for the current row
	 */
	public static void gcGrid(GridBagConstraints gc, int row, int col, int mergedCells){
		gc.gridx = col;
		gc.gridy = row;
		gc.gridwidth = mergedCells;
	}
}
