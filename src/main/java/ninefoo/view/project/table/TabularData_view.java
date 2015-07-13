package ninefoo.view.project.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ninefoo.config.ActivityConfig;
import ninefoo.lib.excelTable.NumberedExcelTable;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;

/**
 * Table showing data about activities in a project
 * @author Amir El Bawab, Sebouh Bardakjian
 */
public class TabularData_view extends JPanel {

	// Logger
	private static final long serialVersionUID = 6595729954886810500L;

	// Variables
	private Project project;
	
	// Declare components
	private NumberedExcelTable dataTable;
	private JScrollPane dataTableScrollPane;
	
	// Constructor
	public TabularData_view(final JPanel parentPanel) {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize components
		this.dataTable = new NumberedExcelTable(ActivityConfig.TABLE_HEADER);
		this.dataTableScrollPane = this.dataTable.getJScrollPane();
		
		// Configure scrolling speed
		this.dataTableScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		this.dataTableScrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		
		// Customize the scroll table
		this.dataTableScrollPane.setPreferredSize(new Dimension(300, 0));
		
		// Add components
		this.add(dataTableScrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Set project
	 * @param project
	 */
	public void setProject(Project project){
		this.project = project;
	}
	
	/**
	 * Populate activities
	 */
	public void populateTable(){
		
		// Remove all existing rows
		this.resetTable();
		
		// Add rows
		List<Activity> activities = this.project.getAcitivies();
		for(Activity activity : activities)
			this.dataTable.addRow(activity.getActivityId(), activity.getActivityLabel(), activity.getStartDate(), activity.getFinishDate(), activity.getDuration(), activity.getPrerequisitesAsString());
	}
	
	/**
	 * Empty table
	 */
	public void resetTable(){
		this.dataTable.empty();
	}
}
