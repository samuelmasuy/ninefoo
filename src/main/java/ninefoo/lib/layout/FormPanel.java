package ninefoo.lib.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ninefoo.lib.lang.LanguageText;

/**
 * Template for form panels.
 * @author Amir EL Bawab
 */
public abstract class FormPanel extends JPanel{
	
	private static final long serialVersionUID = 8991855477647061617L;
	
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
	 * Set the error message as a prompt
	 * @param msg Message to be displayed
	 */
	public void setErrorMessage(String msg){
		JOptionPane.showMessageDialog(this, String .format("<html>%s</html>", msg), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
	}
}
