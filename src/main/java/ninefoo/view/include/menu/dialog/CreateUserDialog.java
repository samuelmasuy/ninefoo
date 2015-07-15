package ninefoo.view.include.menu.dialog;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ninefoo.config.RoleNames;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

/**
 * Dialog that shows a form to create a new user
 * @author Sebouh Bardakjian
 */

public class CreateUserDialog extends CenterFormSouthButtonDialog{

	// Define components
	private PMButton createButton;
	private JTextField firstName, lastName, usernameField;
	private JPasswordField passwordField;
	private JComboBox<String> roleBox;
	
	/**
	 *  Constructor
	 */
	public CreateUserDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.createButton = new PMButton(LanguageText.getConstant("CREATE_ADD_PRO"));
		this.firstName = new JTextField(10);
		this.lastName = new JTextField(10);
		this.usernameField = new JTextField(10);
		this.passwordField = new JPasswordField(10);
		this.roleBox = new JComboBox<>(RoleNames.ROLES);
		
		// Set border title
		this.setTitle(LanguageText.getConstant("NEW_ACCOUNT"));
		
		// Add button listener
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.createUser(firstName.getText(), lastName.getText(), usernameField.getText(), new String(passwordField.getPassword()));
			}
		});
		
		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			@Override
			public void placeForm() {
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("NEW_ACCOUNT"));
				
				// Set input border
				firstName.setBorder(BorderFactory.createCompoundBorder(firstName.getBorder(), inputPadding ));
				lastName.setBorder(BorderFactory.createCompoundBorder(lastName.getBorder(), inputPadding));
				usernameField.setBorder(BorderFactory.createCompoundBorder(usernameField.getBorder(), inputPadding));
				passwordField.setBorder(BorderFactory.createCompoundBorder(passwordField.getBorder(), inputPadding));
				roleBox.setBorder(BorderFactory.createCompoundBorder(roleBox.getBorder(), inputPadding));
				
				// Add components
				this.table.put(new PMLabel(LanguageText.getConstant("FIRST_NAME")));
				this.table.put(firstName);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("LAST_NAME")));
				this.table.put(lastName);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("USERNAME")));
				this.table.put(usernameField);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("PASSWORD")));
				this.table.put(passwordField);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("ROLE")));
				this.table.put(roleBox);
				
			}
		});
		
		// Add component to south panel
		this.southPanel.add(createButton);
		
		// Configure dialog
		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
