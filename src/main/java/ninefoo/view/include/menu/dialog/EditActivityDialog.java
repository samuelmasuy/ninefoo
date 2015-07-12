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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ninefoo.lib.autocompleteComboBox.AutocompleteComboBox;
import ninefoo.lib.datePicker.DatePicker;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.lib.multiDropdown.MultiDropdown;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.view.include.menu.listener.ToolsListener;

/**
 * Dialog for editing an activity
 * @author Sebouh Bardakjian
 */
public class EditActivityDialog extends CenterScrollSouthButtonDialog {
	
	// Define components
	private JButton updateButton;
	private JTextField activityLabel;
	private JTextArea description;
	private JTextField duration;
	private JTextField optimisticDuration;
	private JTextField likelyDuration;
	private JTextField pessimisticDuration;
	private JTextField cost;
	private DatePicker startDate;
	private DatePicker finishDate;
	private ArrayList<Member> members_data;
	private ArrayList<Activity> activities_data;
	private AutocompleteComboBox memberBox;
	private MultiDropdown prerequisiteDropdown;
	
	
	/** 
	 *  Constructor
	 */
	public EditActivityDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Dummy data
		String[] members_dummy = new String[] {"Mem1", "Member2BlaBlaBla"};
				
		
		// Initialize components
		this.updateButton = new JButton(LanguageText.getConstant("UPDATE_ACTIVITY_ACT"));
		this.activityLabel = new JTextField(10);
		this.description = new JTextArea(3,10);
		this.duration = new JTextField(10);
		this.optimisticDuration = new JTextField(10);
		this.likelyDuration = new JTextField(10);
		this.pessimisticDuration = new JTextField(10);
		this.cost = new JTextField(10);
		this.startDate = new DatePicker(8);
		this.finishDate = new DatePicker(8);
		this.memberBox = new AutocompleteComboBox(members_dummy);
		this.prerequisiteDropdown = new MultiDropdown("Add dependency", new String[]{"One", "Two"});
		
		this.setTitle(LanguageText.getConstant("UPDATE_ACTIVITY_ACT"));
		
		// Add button listener
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.updateActivity();
			}
		});
		
		// Add center scroll
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = -2943549344497607196L;
			
			@Override
			public void placeForm() {
				
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("UPDATE_ACTIVITY_ACT"));
				
				// Set input border
				updateButton.setBorder(BorderFactory.createCompoundBorder(updateButton.getBorder(), inputPadding));
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
				prerequisiteDropdown.setBorder(BorderFactory.createCompoundBorder(memberBox.getBorder(), inputPadding));
				
				// Add components
				this.table.put(new JLabel(LanguageText.getConstant("NAME")));
				this.table.put(activityLabel);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("DESCRIPTION")));
				this.table.put(new JScrollPane(description));
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("DURATION_ACT")));
				this.table.put(duration);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("OPTIMISTIC_ACT")));
				this.table.put(optimisticDuration);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("LIKELY_ACT")));
				this.table.put(likelyDuration);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("PESSIMISTIC_ACT")));
				this.table.put(pessimisticDuration);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("COST_ACT")));
				this.table.put(cost);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("START_ACT")));
				this.table.put(startDate);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("FINISH_ACT")));
				this.table.put(finishDate);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("MEMBER_ACT")));
				this.table.put(memberBox);
				
				this.table.newRow();
				this.table.placeCenterTop();
				this.table.put(new JLabel(LanguageText.getConstant("PREREQ_ACT")));
				this.table.put(prerequisiteDropdown);
			}
		});
		
		// Add component to south panel
		this.southPanel.add(this.updateButton);
		
		// Configure dialog
		this.setSize(new Dimension(360,500));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	//TODO Add refresh
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
			this.activities_data = new ArrayList<>();
			
			// If a list was returned
			if(activities != null){
				
				// Add projects
				this.activities_data.addAll(activities);
			}
			
			// Refresh list
			//this.refreshList();
		}
}
