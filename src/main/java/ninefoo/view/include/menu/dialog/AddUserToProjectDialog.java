package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ninefoo.config.RoleNames;
import ninefoo.lib.autocompleteComboBox.AutocompleteComboBox;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;
import ninefoo.model.object.Member;


/**
 * Dialog that shows lists of users to be added as members or managers
 * @author Sebouh Bardakjian
 */

public class AddUserToProjectDialog extends CenterFormSouthButtonDialog{

	private static final long serialVersionUID = 4530177660846739513L;
	// Define components
	private JButton addButton;
	private AutocompleteComboBox memberBox;
	private JComboBox<String> roleBox;
	private ArrayList<Member> users;
	
	/** 
	 *  Constructor
	 */
	public AddUserToProjectDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.addButton = new JButton(LanguageText.getConstant("ADD"));
		this.memberBox = new AutocompleteComboBox();
		this.roleBox = new JComboBox<>(RoleNames.ROLES);
		
		this.setTitle(LanguageText.getConstant("ADD_USER_PRO"));
		
		// Add button listener
		this.addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.addUserToProject();
			}
		});
		
		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = 206877090385767375L;

			@Override
			public void placeForm() {
				// Set border title
				this.titledBorder.setTitle(String.format("%s >> %s", LanguageText.getConstant("USER"), LanguageText.getConstant("PROJECT")));
				
				// Set input border
				memberBox.setBorder(BorderFactory.createCompoundBorder(memberBox.getBorder(), inputPadding));
				roleBox.setBorder(BorderFactory.createCompoundBorder(roleBox.getBorder(), inputPadding));

				// Add components
				this.table.put(new JLabel(LanguageText.getConstant("USER")));
				this.table.put(memberBox);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("ROLE")));
				this.table.put(roleBox);
			}
		});
		
		// Add component to south panel
		this.southPanel.add(addButton);
		
		// Configure dialog
		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	//TODO Add refresh
	/**
	 * Populate list
	 * @param users
	 */
	public void populateUserList(List<Member> users){
		
		// Reset array
		this.users = new ArrayList<>();
		
		// If a list was returned
		if(users != null){
			
			// Add projects
			this.users.addAll(users);
		}
		
		// Refresh list
		//this.refreshList();
	}
}
