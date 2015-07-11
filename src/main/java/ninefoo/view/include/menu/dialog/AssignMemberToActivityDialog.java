package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ninefoo.lib.autocompleteComboBox.AutocompleteComboBox;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;
import ninefoo.model.object.Member;
import ninefoo.model.object.Activity;

/**
 * Dialog that shows lists of activities and members in order to assign one another
 * @author Sebouh Bardakjian
 */

public class AssignMemberToActivityDialog extends CenterFormSouthButtonDialog{

	private static final long serialVersionUID = -5201591507213738168L;
	
	// Define components
	private JButton assignButton;
	private AutocompleteComboBox activityBox;
	private AutocompleteComboBox memberBox;
	private ArrayList<Member> users;
	private ArrayList<Activity> activities;
	
	/** 
	 *  Constructor
	 */
	public AssignMemberToActivityDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Dummy data
		String[] activities_data = new String[] {"Activity1", "Activity2","Activity3", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2"};
		String[] members_data = new String[] {"Mem1", "Member2BlaBlaBla"};
		
		// Initialize components
		this.assignButton = new JButton(LanguageText.getConstant("ASSIGN_ACT"));
		this.activityBox = new AutocompleteComboBox(activities_data);
		this.memberBox = new AutocompleteComboBox(members_data);
		
		this.setTitle(LanguageText.getConstant("ASSIGN_MEMBER_ACTIVITY_ACT"));
		
		// Add button listener
		this.assignButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.assignMemberToActivity();
			}
		});
		
		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = 873337676902693739L;

			@Override
			public void placeForm() {
				// Set border title
				this.titledBorder.setTitle(String.format("%s >> %s", LanguageText.getConstant("MEMBER"), LanguageText.getConstant("ACTIVITY_ACT")));
				
				// Set input border
				activityBox.setBorder(BorderFactory.createCompoundBorder(activityBox.getBorder(), inputPadding));
				memberBox.setBorder(BorderFactory.createCompoundBorder(memberBox.getBorder(), inputPadding));
			
				// Add components
				this.table.put(new JLabel(LanguageText.getConstant("ACTIVITY_ACT")));
				this.table.put(activityBox);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("MEMBER")));
				this.table.put(memberBox);
			}
		});
		
		// Add component to south panel
		this.southPanel.add(assignButton);
		
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
	
	//TODO Add refresh
	/**
	 * Populate list
	 * @param activities
	 */
	public void populateActivityList(List<Activity> activities){
		
		// Reset array
		this.activities = new ArrayList<>();
		
		// If a list was returned
		if(activities != null){
			
			// Add projects
			this.activities.addAll(activities);
		}
		
		// Refresh list
		//this.refreshList();
	}
	
}
