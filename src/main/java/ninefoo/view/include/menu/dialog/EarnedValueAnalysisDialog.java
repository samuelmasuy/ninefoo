package ninefoo.view.include.menu.dialog;

import ninefoo.config.Session;
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

/**
 * Dialog for displaying earned value analysis
 *
 * @author Sebouh Bardakjian
 */
public class EarnedValueAnalysisDialog extends CenterScrollSouthButtonDialog {

	private static final long serialVersionUID = 238786251669316509L;

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
    private JLabel costVariancePerformance;
    private JLabel scheduleVariancePerformance;
    private JLabel cpiPerformance;
    private JLabel spiPerformance;
    
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
        this.costVariancePerformance = new JLabel();
        this.scheduleVariancePerformance = new JLabel();
        this.cpiPerformance = new JLabel();
        this.spiPerformance = new JLabel();
        
        // Set title
        this.setTitle(LanguageText.getConstant("EARNED_VALUE_ANALYSIS"));

        // Load fields
        toolsListener.loadEarnedValueData(this, Session.getInstance().getProjectId());;
        
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
                this.table.put(new PMLabel("PLANNED_COST_PRO"));
                this.table.put(totalCost);

                this.table.newRow();
                this.table.put(new PMLabel("PV_PRO"));
                this.table.put(totalPV);

                this.table.newRow();
                this.table.put(new PMLabel("AC_PRO"));
                this.table.put(totalAC);

                this.table.newRow();
                this.table.put(new PMLabel("EV_PRO"));
                this.table.put(totalEV);

                this.table.newRow();
                this.table.put(new PMLabel("COST_VARIANCE_PRO"));
                this.table.put(costVariance);
                this.table.put(costVariancePerformance);

                this.table.newRow();
                this.table.put(new PMLabel("SCHEDULE_VARIANCE_PRO"));
                this.table.put(scheduleVariance);
                this.table.put(scheduleVariancePerformance);

                this.table.newRow();
                this.table.put(new PMLabel("CPI_PRO"));
                this.table.put(cpi);
                this.table.put(cpiPerformance);

                this.table.newRow();
                this.table.put(new PMLabel("SPI_PRO"));
                this.table.put(spi);
                this.table.put(spiPerformance);


                this.table.newRow();
                this.table.put(new PMLabel("EAC_PRO"));
                this.table.put(EAC);
                
                this.table.newRow();
                this.table.put(new PMLabel("ETC_PRO"));
                this.table.put(ETC);

            }
        });

        // Add component to south panel
        this.southPanel.add(this.closeButton);

        // Configure dialog
        this.setSize(new Dimension(550, 500));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Populate activity data
     */
    public void populateEarnedValueData(Project project) {

    	this.projectLabel.setText(project.getProjectName());
    	this.totalCost.setText(String.format("%.2f" ,project.getTotalCost()));
    	this.totalPV.setText(String.format("%.2f" ,project.getTotalPV()));
    	this.totalAC.setText(String.format("%.2f" ,project.getTotalAC()));
    	this.totalEV.setText(String.format("%.2f" ,project.getTotalEV()));
    	this.costVariance.setText(String.format("%.2f" ,project.getCostVariance()));
    	this.scheduleVariance.setText(String.format("%.2f" ,project.getScheduleVariance()));
    	this.cpi.setText(String.format("%.2f" ,project.getCpi()));
    	this.spi.setText(String.format("%.2f" ,project.getSpi()));
    	this.EAC.setText(String.format("%.2f" ,project.getEAC()));
    	this.ETC.setText(String.format("%.2f" ,project.getETC()));
    	
    	double costVar = project.getCostVariance();
    	double schedVar = project.getScheduleVariance();
    	double spiVar = project.getSpi();
    	double cpiVar = project.getCpi();
    	
    	if (costVar > 0){
    		this.costVariancePerformance.setText(LanguageText.getConstant("UNDER_BUDGET"));
    	} else if (costVar < 0) {
    		this.costVariancePerformance.setText(LanguageText.getConstant("OVER_BUDGET"));
    	} else {
    		this.costVariancePerformance.setText(LanguageText.getConstant("ON_BUDGET"));
    	}
    	
    	if (schedVar > 0){
    		this.scheduleVariancePerformance.setText(LanguageText.getConstant("AHEAD_SCHEDULE"));
    	} else if (schedVar < 0) {
    		this.scheduleVariancePerformance.setText(LanguageText.getConstant("BEHIND_SCHEDULE"));
    	} else {
    		this.scheduleVariancePerformance.setText(LanguageText.getConstant("ON_SCHEDULE"));
    	}
    	
    	this.cpiPerformance.setText(String.format(LanguageText.getConstant("EARNING_FOR_SPENT"), String.format("%.2f", cpiVar)));
    	this.spiPerformance.setText(String.format("Current status is " + String.format("%.2f", (spiVar * 100)) + "%% of what was planned"));
    
    }
}
