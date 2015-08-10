package ninefoo.view.include.menu.dialog;

import ninefoo.config.Config;
import ninefoo.helper.ActivityHelper;
import ninefoo.helper.DateHelper;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for displaying earned value analysis
 *
 * @author Sebouh Bardakjian
 */
public class EarnedValueAnalysisDialog extends CenterScrollSouthButtonDialog {

    // Define components
    private PMButton closeButton;
    private JLabel projectLabel;
    private JLabel totalCost;
    private JLabel totalPV;
    private JLabel totalAC;
    private JLabel totalEV;
    private JLabel costVariance;
    private JLabel scheduleVariance;
    private JLabel cpi;
    private JLabel spi;
    private JLabel EAC;
    private JLabel ETC;
    
    /**
     * Constructor
     */
    public EarnedValueAnalysisDialog(JFrame parentFrame, final ToolsListener toolsListener) {

        // Initialize components
        this.closeButton = new PMButton("CLOSE");
        this.projectLabel = new JLabel();
        this.totalCost = new JLabel();
        this.totalPV = new JLabel();
        this.totalAC = new JLabel();
        this.totalEV = new JLabel();
        this.costVariance = new JLabel();
        this.scheduleVariance = new JLabel();
        this.cpi = new JLabel();
        this.spi = new JLabel();
        this.EAC = new JLabel();
        this.ETC = new JLabel();
        
        // Set title
        this.setTitle(LanguageText.getConstant("EARNED_VALUE_ANALYSIS"));

        // Add button listener
        this.closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    dispose();
            }
        });

        // Add center scroll
        this.setCenterPanel(new FormDialog() {

			@Override
            public void placeForm() {

                // Set border title
                this.titledBorder.setTitle(LanguageText.getConstant("EARNED_VALUE_ANALYSIS"));

                // Set input border
                closeButton.setBorder(BorderFactory.createCompoundBorder(closeButton.getBorder(), inputPadding));

                // Add components
                this.table.put(new PMLabel("PROJECT"));
                this.table.put(projectLabel);

                this.table.newRow();
                this.table.put(new PMLabel("COST"));
                this.table.put(totalCost);

                this.table.newRow();
                this.table.put(new PMLabel("PV"));
                this.table.put(totalPV);

                this.table.newRow();
                this.table.put(new PMLabel("AC"));
                this.table.put(totalAC);

                this.table.newRow();
                this.table.put(new PMLabel("EV"));
                this.table.put(totalEV);

                this.table.newRow();
                this.table.put(new PMLabel("COST_VARIANCE"));
                this.table.put(costVariance);

                this.table.newRow();
                this.table.put(new PMLabel("SCHEDULE_VARIANCE"));
                this.table.put(scheduleVariance);

                this.table.newRow();
                this.table.put(new PMLabel("CPI"));
                this.table.put(cpi);

                this.table.newRow();
                this.table.put(new PMLabel("SPI"));
                this.table.put(spi);

                this.table.newRow();
                this.table.put(new PMLabel("EAC"));
                this.table.put(EAC);
                
                this.table.newRow();
                this.table.put(new PMLabel("ETC"));
                this.table.put(ETC);

            }
        });

        // Add component to south panel
        this.southPanel.add(this.closeButton);

        // Configure dialog
        this.setSize(new Dimension(400, 500));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Populate activity data
     */
    public void populateEarnedValueData(Project project) {
//        this.activityLabelInfo.setText(String.format("<html><div>%s</div></html>", activity.getActivityLabel()));
//        this.descriptionInfo.setText(activity.getDescription());
//        this.durationInfo.setText(activity.getDuration() + "");
//        this.optimisticDurationInfo.setText(activity.getOptimisticDuration() + "");
//        this.likelyDurationInfo.setText(activity.getLikelyDuration() + "");
//        this.pessimisticDurationInfo.setText(activity.getPessimisticDuration() + "");
//        this.costInfo.setText(activity.getPlannedCost() + "");
//        this.startDateInfo.setText(DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT_SHORT));
//        this.finishDateInfo.setText(DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT_SHORT));
//        this.memberInfo.setText(activity.getMember().getFirstName() + " " + activity.getMember().getLastName());

        this.projectLabel.setText(project.getProjectName());
        this.totalCost = new JLabel();
        this.totalPV = new JLabel();
        this.totalAC = new JLabel();
        this.totalEV = new JLabel();
        this.costVariance = new JLabel();
        this.scheduleVariance = new JLabel();
        this.cpi = new JLabel();
        this.spi = new JLabel();
        this.EAC = new JLabel();
        this.ETC = new JLabel();
    }
}
