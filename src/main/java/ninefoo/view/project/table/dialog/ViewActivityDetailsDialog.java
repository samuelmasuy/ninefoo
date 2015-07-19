package ninefoo.view.project.table.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ninefoo.config.Config;
import ninefoo.helper.ActivityHelper;
import ninefoo.helper.DateHelper;
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
import ninefoo.model.object.Project;
import ninefoo.view.project.table.listener.TableToolsListener;

/**
 * Dialog for editing an activity
 * @author Sebouh Bardakjian
 */
public class ViewActivityDetailsDialog extends CenterScrollSouthButtonDialog {
	
	// Define components
	private PMButton closeButton;
	private PMLabel activityLabelInfo;
	private JTextArea descriptionInfo;
	private PMLabel durationInfo;
	private PMLabel optimisticDurationInfo;
	private PMLabel likelyDurationInfo;
	private PMLabel pessimisticDurationInfo;
	private PMLabel costInfo;
	private PMLabel startDateInfo;
	private PMLabel finishDateInfo;
	private ArrayList<Activity> activities_data;
	private PMLabel memberInfo;
	private ArrayList<PMLabel> prerequisiteList;
	
	
	/** 
	 *  Constructor
	 */
	public ViewActivityDetailsDialog(JFrame parentFrame, final TableToolsListener tableToolsListener) {
		
		// Initialize components
		this.closeButton = new PMButton("CLOSE");
		this.activityLabelInfo = new PMLabel();
		this.descriptionInfo = new JTextArea();
		this.durationInfo = new PMLabel();
		this.optimisticDurationInfo = new PMLabel();
		this.likelyDurationInfo = new PMLabel();
		this.pessimisticDurationInfo = new PMLabel();
		this.costInfo = new PMLabel();
		this.startDateInfo = new PMLabel();
		this.finishDateInfo = new PMLabel();
		this.memberInfo = new PMLabel();
		this.prerequisiteList = new ArrayList<PMLabel>();
		
		this.setTitle(LanguageText.getConstant("VIEW_ACTIVITY_DETAILS_ACT"));
		
		// Add button listener
		this.closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableToolsListener != null)
					dispose();
			}
		});
		
		// Add center scroll
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = -2943549344497607196L;
			
			@Override
			public void placeForm() {
				
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("VIEW_ACTIVITY_DETAILS_ACT"));
				
				// Set input border
				closeButton.setBorder(BorderFactory.createCompoundBorder(closeButton.getBorder(), inputPadding));
				
				// Add components
				this.table.put(new PMLabel("NAME"));
				this.table.put(activityLabelInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("DESCRIPTION"));
				this.table.put(new JScrollPane(descriptionInfo));
				
				this.table.newRow();
				this.table.put(new PMLabel("DURATION_ACT"));
				this.table.put(durationInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("OPTIMISTIC_ACT"));
				this.table.put(optimisticDurationInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("LIKELY_ACT"));
				this.table.put(likelyDurationInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("PESSIMISTIC_ACT"));
				this.table.put(pessimisticDurationInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("COST_ACT"));
				this.table.put(costInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("START_ACT"));
				this.table.put(startDateInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("FINISH_ACT"));
				this.table.put(finishDateInfo);
				
				this.table.newRow();
				this.table.put(new PMLabel("MEMBER_ACT"));
				this.table.put(memberInfo);
				
				
				for (int i = 0; i < prerequisiteList.size(); i++){
					this.table.newRow();
					
					if (i == 0){
						this.table.placeCenterTop();
						this.table.put(new PMLabel("PREREQ_ACT"));
					}else{
						this.table.getGridBagConstraints().gridx++;
					}
					this.table.put(prerequisiteList.get(i));
				}
			}
		});
		
		// Add component to south panel
		this.southPanel.add(this.closeButton);
		
		// Configure dialog
		this.setSize(new Dimension(360,500));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Populate activity data
	 */
	private void populateActivityData(Activity activity){
		this.activityLabelInfo.setText(String.format("<html><div>%s</div></html>", activity.getActivityLabel()));
		this.descriptionInfo.setText(activity.getDescription());
		this.durationInfo.setText(activity.getDuration() + "");
		this.optimisticDurationInfo.setText(activity.getOptimisticDuration() + "");
		this.likelyDurationInfo.setText(activity.getLikelyDuration() + "");
		this.pessimisticDurationInfo.setText(activity.getPessimisticDuration() + "");
		this.costInfo.setText(activity.getCost() + "");
		this.startDateInfo.setText(DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT_SHORT));
		this.finishDateInfo.setText(DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT_SHORT));
		this.memberInfo.setText(activity.getMember() + "");
		
		List<Activity> prerequisites = activity.getPrerequisites();
		
		for (int i = 0; i < prerequisites.size(); i++){
			this.prerequisiteList.get(i).setText(ActivityHelper.getIdAndName(prerequisites.get(i)));
		}
	}
}
