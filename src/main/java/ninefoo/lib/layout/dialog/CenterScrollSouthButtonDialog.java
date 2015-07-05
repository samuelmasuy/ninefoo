package ninefoo.lib.layout.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ninefoo.lib.lang.LanguageText;

/**
 * This class must be inherited by dialog boxes that are composed of two parts:
 *  - Scroll in the center
 *  - Buttons at the bottom
 *  
 *  +-----------------------+
 *  |                       |
 *  |                       |
 *  |                       |
 *  |         SCROLL        |
 *  |                       |
 *  |                       |
 *  |                       |
 *  |-----------------------|
 *  |        BUTTONS        |
 *  +-----------------------+
 *  
 * @author Amir El Bawab
 */
public abstract class CenterScrollSouthButtonDialog extends JDialog{

	private static final long serialVersionUID = 6124363441606459436L;
	
	protected JPanel southPanel;
	protected FormDialog centerPanel;
	protected JScrollPane centerPanelScroll;
	
	/**
	 * Constructor
	 */
	public CenterScrollSouthButtonDialog() {
		
		// Set layout
		this.setLayout(new BorderLayout());
				
		// Initialize components
		this.southPanel = new JPanel();
		
		// Configure south panel
		this.southPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		// Add components to dialog
		this.add(southPanel, BorderLayout.SOUTH);
		
		// Disable clicking on background windows
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}
	
	/**
	 * Set center panel
	 * @param centerPanel
	 */
	public void setCenterPanel(FormDialog centerPanel){
		
		// Set center panel
		this.centerPanel = centerPanel;
		
		// Call place form
		this.centerPanel.placeForm();
		
		// Configure scroll panel
		this.centerPanel.setBorder(null);
		
		// Set Scroll pane
		this.centerPanelScroll = new JScrollPane(this.centerPanel);
		
		// Set border
		this.centerPanelScroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder("")));
		
		// Add it
		this.add(this.centerPanelScroll, BorderLayout.CENTER);
	}
	
	/**
	 * Get center panel
	 * @return center panel
	 */
	public JPanel getCenterPanel(){
		return this.centerPanel;
	}
	
	/**
	 * Get center panel scroll panel
	 * @return center panel scroll pane or <code>null</code> if no center panel was set
	 */
	public JScrollPane getCenterPanelScrollPane(){
		return this.centerPanelScroll;
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
}
