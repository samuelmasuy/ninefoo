package ninefoo.view.project.table.dialog;

import ninefoo.config.Config;
import ninefoo.helper.ActivityHelper;
import ninefoo.helper.DateHelper;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Activity;
import ninefoo.view.project.table.listener.TableToolsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for editing an activity
 *
 * @author Sebouh Bardakjian
 */
public class ViewActivityDetailsDialog extends CenterScrollSouthButtonDialog {

    // Define components
    private PMButton closeButton;
    private JLabel activityLabelInfo;
    private JLabel descriptionInfo;
    private JLabel durationInfo;
    private JLabel optimisticDurationInfo;
    private JLabel likelyDurationInfo;
    private JLabel pessimisticDurationInfo;
    private JLabel costInfo;
    private JLabel actualCostInfo;
    private JLabel plannedPercentage;
    private JLabel actualPercentage;
    private JLabel startDateInfo;
    private JLabel finishDateInfo;
    private JLabel plannedValue;
    private JLabel earnedValue;
    private ArrayList<Activity> activities_data;
    private JLabel memberInfo;
    private ArrayList<PMLabel> prerequisiteList;
    private JScrollPane descScroll;
    
    /**
     * Constructor
     */
    public ViewActivityDetailsDialog(JFrame parentFrame, final TableToolsListener tableToolsListener, Activity activity) {

        // Initialize components
        this.closeButton = new PMButton("CLOSE");
        this.activityLabelInfo = new JLabel();
        this.descriptionInfo = new JLabel();
        this.durationInfo = new JLabel();
        this.optimisticDurationInfo = new JLabel();
        this.likelyDurationInfo = new JLabel();
        this.pessimisticDurationInfo = new JLabel();
        this.costInfo = new JLabel();
        this.startDateInfo = new JLabel();
        this.finishDateInfo = new JLabel();
        this.memberInfo = new JLabel();
        this.prerequisiteList = new ArrayList<PMLabel>();
        this.descScroll = new JScrollPane(descriptionInfo);
        descScroll.setPreferredSize(new Dimension(100,100));
        this.actualCostInfo = new JLabel();
        this.plannedPercentage = new JLabel();
        this.actualPercentage = new JLabel();
        this.earnedValue = new JLabel();
        this.plannedValue = new JLabel();
        
        // Set title
        this.setTitle(LanguageText.getConstant("VIEW_ACTIVITY_DETAILS_ACT"));

        // Load activity
        tableToolsListener.loadActivityForViewDetails(this, activity.getActivityId());
        
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
                this.table.put(new PMLabel("ACTUAL_COST_ACT"));
                this.table.put(actualCostInfo);
                
                this.table.newRow();
                this.table.put(new PMLabel("PLANNED_PERCENTAGE_ACT"));
                this.table.put(plannedPercentage);
                
                this.table.newRow();
                this.table.put(new PMLabel("ACTUAL_PERCENTAGE_ACT"));
                this.table.put(actualPercentage);
                
                this.table.newRow();
                this.table.put(new PMLabel("PV_PRO"));
                this.table.put(plannedValue);
                
                this.table.newRow();
                this.table.put(new PMLabel("EV_PRO"));
                this.table.put(earnedValue);
                
                this.table.newRow();
                this.table.put(new PMLabel("START_ACT"));
                this.table.put(startDateInfo);

                this.table.newRow();
                this.table.put(new PMLabel("FINISH_ACT"));
                this.table.put(finishDateInfo);

                this.table.newRow();
                this.table.put(new PMLabel("MEMBER_ACT"));
                this.table.put(memberInfo);

                this.table.newRow();
                this.table.put(new PMLabel("DESCRIPTION"));
                this.table.put(descScroll);


                for (int i = 0; i < prerequisiteList.size(); i++) {
                    this.table.newRow();

                    if (i == 0) {
                        this.table.placeCenterTop();
                        this.table.put(new PMLabel("PREREQ_ACT"));
                    } else {
                        this.table.getGridBagConstraints().gridx++;
                    }
                    this.table.put(prerequisiteList.get(i));
                }
            }
        });

        // Add component to south panel
        this.southPanel.add(this.closeButton);

        // Configure dialog
        this.setSize(new Dimension(360, 500));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Populate activity data
     */
    public void populateActivityData(Activity activity) {
        this.activityLabelInfo.setText(String.format("<html><div>%s</div></html>", activity.getActivityLabel()));
        this.descriptionInfo.setText(activity.getDescription());
        this.durationInfo.setText(activity.getDuration() + "");
        this.optimisticDurationInfo.setText(activity.getOptimisticDuration() + "");
        this.likelyDurationInfo.setText(activity.getLikelyDuration() + "");
        this.pessimisticDurationInfo.setText(activity.getPessimisticDuration() + "");
        this.costInfo.setText(activity.getPlannedCost() + "");
        this.startDateInfo.setText(DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT_SHORT));
        this.finishDateInfo.setText(DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT_SHORT));
        this.memberInfo.setText(activity.getMember().getFirstName() + " " + activity.getMember().getLastName());
        this.actualCostInfo.setText(activity.getActualCost() + "");
        this.plannedPercentage.setText(String.valueOf(!activity.getFinishDate().after(DateHelper.getToday()) ? 100 : 0));
        this.actualPercentage.setText(activity.getActualPercentage() + "");
        this.plannedValue.setText(String.valueOf(!activity.getFinishDate().after(DateHelper.getToday()) ? activity.getPlannedCost() : 0));
        this.earnedValue.setText(String.valueOf(activity.getActualPercentage() == 100 ? activity.getPlannedCost() : 0));
        
        List<Activity> prerequisites = activity.getPrerequisites();

        for (int i = 0; i < prerequisites.size(); i++) {
            this.prerequisiteList.get(i).setText(ActivityHelper.getIdAndName(prerequisites.get(i)));
        }
    }
}
