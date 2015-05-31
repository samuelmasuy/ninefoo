package ninefoo.lib;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public abstract class FormPanel extends JPanel{
	
	private static final long serialVersionUID = 8991855477647061617L;
	
	// Define components
	protected JPanel fixedPanel;
	protected GridBagConstraints gc;
	protected TitledBorder titledBorder;
	protected JLabel errorMessage;
	
	// Constructor
	public FormPanel() {
		
		// Initialize components
		this.fixedPanel = new JPanel();
		this.gc = new GridBagConstraints();
		this.errorMessage = new JLabel();
		
		// Set layout
		this.setLayout(new GridBagLayout());
		fixedPanel.setLayout(new GridBagLayout());
		
		// Configure error message
		this.errorMessage.setFont(new Font(this.errorMessage.getFont().getFontName(), Font.PLAIN, 12));
		this.errorMessage.setForeground(Color.RED);
		
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
	
	
	/**
	 * Set the error message
	 * @param msg Message to be displayed
	 */
	public void setErrorMessage(String msg){
		this.errorMessage.setText(String .format("<html>%s</html>", msg));
	}
}
