package ninefoo.lib.layout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ninefoo.lib.lang.LanguageText;

/**
 * Template for form panels.
 * @author Amir EL Bawab
 */
public abstract class FormDialog extends JPanel{
	
	private static final long serialVersionUID = 8991855477647061617L;
	
	// Define components
	protected JPanel fixedPanel;
	protected GridBagConstraints gc;
	protected TitledBorder titledBorder;
	protected Border inputPadding;
	protected int row;
	
	// Constructor
	public FormDialog() {
		
		// Initialize components
		this.fixedPanel = new JPanel();
		this.gc = new GridBagConstraints();
		this.inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		this.row = 0;
		
		// Set layout
		this.setLayout(new GridBagLayout());
		fixedPanel.setLayout(new GridBagLayout());
		
		// Set Border for fixedPanel
		titledBorder = BorderFactory.createTitledBorder("");
		fixedPanel.setBorder(BorderFactory.createCompoundBorder(titledBorder, BorderFactory.createEmptyBorder(10,10,10,10)));
		
		// Default configuration gc
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(5, 5, 5, 5); // Spacing between components
	}
	
	/**
	 * Set the error message as a prompt
	 * @param msg Message to be displayed
	 */
	public void setErrorMessage(String msg){
		JOptionPane.showMessageDialog(this, String .format("<html>%s</html>", msg), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Set the success message as a prompt
	 * @param msg Message to be displayed
	 */
	public void setSuccessMessage(String msg){
		JOptionPane.showMessageDialog(this, String .format("<html>%s</html>", msg), LanguageText.getConstant("OPERATION_SUCCESSFUL"), JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * The class that implements this method must add the form components here inside the grid bag layout
	 */
	public abstract void placeForm();
}
