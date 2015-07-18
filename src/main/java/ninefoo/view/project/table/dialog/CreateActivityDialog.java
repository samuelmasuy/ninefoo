package ninefoo.view.project.table.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ninefoo.helper.ActivityHelper;
import ninefoo.lib.autocompleteComboBox.AutocompleteComboBox;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.datePicker.DatePicker;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.lib.multiDropdown.MultiDropdown;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.view.project.table.listener.TableToolsListener;

/**
 * Dialog for creating an activity
 * @author Sebouh Bardakjian, Amir El Bawab
 */
public class CreateActivityDialog extends CenterScrollSouthButtonDialog {
	
	private static final long serialVersionUID = -3895150614923358876L;
	
	// Define components
	private PMButton createButton;
	private JTextField activityLabel;
	private JTextArea description;
	private JTextField duration;
	private JTextField optimisticDuration;
	private JTextField likelyDuration;
	private JTextField pessimisticDuration;
	private JTextField cost;
	private DatePicker startDate;
	private DatePicker finishDate;
	private AutocompleteComboBox memberBox;
	private MultiDropdown prerequisiteDropdown;
	
	// Variables
	private List<Member> members_data;
	private List<Activity> activities_data;
	private String[] membersName;
	private String[] activitiesLabel;
	
	/** 
	 *  Constructor
	 */
	public CreateActivityDialog(final JFrame parentFrame, final TableToolsListener tableToolsListener) {
		
		// Load data
		tableToolsListener.loadAllMembersForCreateActivityDialog(this);
		tableToolsListener.loadActivitiesForCreateActivityDialog(this);
		
		// Initialize components
		this.createButton = new PMButton(LanguageText.getConstant("CREATE"));
		this.activityLabel = new JTextField(10);
		this.description = new JTextArea(3,10);
		this.duration = new JTextField(10);
		this.optimisticDuration = new JTextField(10);
		this.likelyDuration = new JTextField(10);
		this.pessimisticDuration = new JTextField(10);
		this.cost = new JTextField(10);
		this.startDate = new DatePicker(8);
		this.finishDate = new DatePicker(8);
		this.memberBox = new AutocompleteComboBox(membersName);
		this.prerequisiteDropdown = new MultiDropdown(LanguageText.getConstant("ADD_DEPENDENCY_ACT"), activitiesLabel);
		
		// If first activity, disable add prerequisite button
		if(activities_data.size() == 0)
			prerequisiteDropdown.setEnabled(false);
		
		// Set title
		this.setTitle(LanguageText.getConstant("CREATE_ACTIVITY_ACT"));
		
		// Add button listener
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no member selected
				if(memberBox.getSelectedIndex() < 0 || memberBox.checkAndGetText() == null){
					
					// Display error
					// TODO Add language
					JOptionPane.showMessageDialog(CreateActivityDialog.this, "Please select a member to continue.", LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				} else if (tableToolsListener != null)
					tableToolsListener.createActivity(CreateActivityDialog.this, activityLabel.getText(), description.getText(), duration.getText(), optimisticDuration.getText(), likelyDuration.getText(), pessimisticDuration.getText(), cost.getText(), startDate.getText(), finishDate.getText(), members_data.get(memberBox.checkAndGetIndex()).getMemberId(), prerequisiteDropdown.getDataIndex());
			}
		});
		
		// Add center scroll
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = -2943549344497607196L;
			
			@Override
			public void placeForm() {
				
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("CREATE_ACTIVITY_ACT"));
				
				// Set input border
				createButton.setBorder(BorderFactory.createCompoundBorder(createButton.getBorder(), inputPadding));
				activityLabel.setBorder(BorderFactory.createCompoundBorder(activityLabel.getBorder(), inputPadding));
				description.setBorder(BorderFactory.createCompoundBorder(description.getBorder(), inputPadding));
				duration.setBorder(BorderFactory.createCompoundBorder(duration.getBorder(), inputPadding));
				optimisticDuration.setBorder(BorderFactory.createCompoundBorder(optimisticDuration.getBorder(), inputPadding));
				likelyDuration.setBorder(BorderFactory.createCompoundBorder(likelyDuration.getBorder(), inputPadding));
				pessimisticDuration.setBorder(BorderFactory.createCompoundBorder(pessimisticDuration.getBorder(), inputPadding));
				cost.setBorder(BorderFactory.createCompoundBorder(pessimisticDuration.getBorder(), inputPadding));
				startDate.setBorder(BorderFactory.createCompoundBorder(startDate.getBorder(), inputPadding));
				finishDate.setBorder(BorderFactory.createCompoundBorder(finishDate.getBorder(), inputPadding));
				memberBox.setBorder(BorderFactory.createCompoundBorder(memberBox.getBorder(), inputPadding));
				prerequisiteDropdown.setBorder(BorderFactory.createCompoundBorder(prerequisiteDropdown.getBorder(), inputPadding));
				
				// Add components
				this.table.put(new PMLabel(LanguageText.getConstant("NAME")));
				this.table.put(activityLabel);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("DESCRIPTION")));
				this.table.put(new JScrollPane(description));
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("DURATION_ACT")));
				this.table.put(duration);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("OPTIMISTIC_ACT")));
				this.table.put(optimisticDuration);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("LIKELY_ACT")));
				this.table.put(likelyDuration);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("PESSIMISTIC_ACT")));
				this.table.put(pessimisticDuration);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("COST_ACT")));
				this.table.put(cost);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("START_ACT")));
				this.table.put(startDate);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("FINISH_ACT")));
				this.table.put(finishDate);
				
				this.table.newRow();
				this.table.put(new PMLabel(LanguageText.getConstant("MEMBER_ACT")));
				this.table.put(memberBox);
				
				this.table.newRow();
				this.table.placeCenterTop();
				this.table.put(new PMLabel(LanguageText.getConstant("PREREQ_ACT")));
				this.table.put(prerequisiteDropdown);
			}
		});
		
		// Add component to south panel
		this.southPanel.add(this.createButton);
		
		// Configure dialog
		this.setSize(new Dimension(360,500));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
		/**
		 * Populate list
		 * @param users
		 */
		public void populateMemberList(List<Member> members){
			
			// Reset array
			this.members_data = new ArrayList<>();
			
			// If a list was returned
			if(members != null){
				
				// Add projects
				this.members_data.addAll(members);
			}
			
			// Create a list of members
			this.membersName = new String[members.size()];
			
			// Populate list of user names
			for(int i=0; i < members.size(); i++)
				this.membersName[i] = members.get(i).getUsername();
		}
		
		/**
		 * Populate list
		 * @param activities
		 */
		public void populateActivityList(List<Activity> activities){
			
			// Reset array
			this.activities_data = new ArrayList<>();
			
			// If a list was returned
			if(activities != null){
				
				// Add projects
				this.activities_data.addAll(activities);
			}
			
			// Create a list of members
			this.activitiesLabel = new String[activities.size()];
			
			// Populate list of user names
			for(int i=0; i < activities.size(); i++)
				this.activitiesLabel[i] = ActivityHelper.getIdAndName(activities.get(i));
		}
}
