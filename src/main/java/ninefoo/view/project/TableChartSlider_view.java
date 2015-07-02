package ninefoo.view.project;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;
import ninefoo.view.project.listener.TabularDataListener;

/**
 * Panel container of a the table and Gantt chart
 * @author Amir El Bawab
 */
public class TableChartSlider_view extends JPanel{
	
	private static final long serialVersionUID = 1821070698625712816L;
	
	// Declare panels
	private TabularData_view tabularPanel;
	private GanttChart_view chartPanel;
	private JSplitPane splitPane;
	private Project project;
	
	/**
	 * Constructor
	 */
	public TableChartSlider_view() {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize panels
		this.tabularPanel = new TabularData_view(this);
		this.chartPanel = new GanttChart_view();
		
		// Add the panels to the splitter
		this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabularPanel, chartPanel);
		
		// Customize the splitter
		this.splitPane.setResizeWeight(0.5);
		this.splitPane.setDividerLocation(0.5);
		this.splitPane.setDividerSize(4);
		this.splitPane.setContinuousLayout(true);
		
		// Add components
		this.add(splitPane);
	}
	
	/**
	 * Set tabular listener
	 * @param tabularDataListener
	 */
	public void setTabularDataListener(TabularDataListener tabularDataListener){
		this.tabularPanel.setTabularDataListener(tabularDataListener);
	}

	/**
	 * Add empty row to the table
	 */
	public void addEmptyRow(){
		this.tabularPanel.addEmptyRow();
	}
	
	/**
	 * Set project
	 * @param project
	 */
	public void setProject(Project project){
		this.project = project;
		this.tabularPanel.setProject(project);
	}
	
	/**
	 * Get project
	 * @return project
	 */
	public Project getProject(){
		return project;
	}
	
	/**
	 * Load and populate project
	 * @param project
	 */
	public void loadProject(Project project){
		
		// Set project
		this.setProject(project);
		
		// Populate activities
		this.tabularPanel.populateActivities();
	}
	
	/**
	 * Set error message
	 * @param message
	 */
	public void setErrorMessage(String message){
		JOptionPane.showMessageDialog(this, String.format("<html>%s</html>", message), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Update row
	 * @param row
	 * @param activity
	 */
	public void updateTableRow(int row, Activity activity){
		this.tabularPanel.updateRow(row, activity);
	}
	
	/**
	 * Reset table and chart
	 */
	public void reset(){
		this.tabularPanel.resetTable();
	}
}
