package ninefoo.lib;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public abstract class FormPanel extends JPanel{
	
	// Define components
	protected JPanel fixedPanel;
	protected GridBagConstraints gc;
	protected TitledBorder titledBorder;
	
	// Constructor
	public FormPanel() {
		
		// Initialize components
		this.fixedPanel = new JPanel();
		this.gc = new GridBagConstraints();
		
		// Set layout
		this.setLayout(new GridBagLayout());
		fixedPanel.setLayout(new GridBagLayout());
		
		// Set Border for fixedPanel
		titledBorder = BorderFactory.createTitledBorder("");
//		titledBorder.setTitleFont(new Font(titledBorder.getTitleFont().getFontName(),Font.PLAIN, 20));
		fixedPanel.setBorder(BorderFactory.createCompoundBorder(titledBorder, BorderFactory.createEmptyBorder(10,10,10,10)));
		
		// Default configuration gc
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(5, 5, 5, 5); // Spacing between components
		
		// Configure this JPanel
		this.setVisible(false);
	}
	
	/**
	 * Specify the grid position
	 * @param row Row index
	 * @param col Column index
	 * @param mergedCells Number of merged columns for the current row
	 */
	protected void gcGrid(int row, int col, int mergedCells){
		this.gc.gridx = col;
		this.gc.gridy = row;
		this.gc.gridwidth = mergedCells;
	}
}
