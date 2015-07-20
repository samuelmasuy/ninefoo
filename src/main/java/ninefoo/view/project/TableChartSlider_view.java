package ninefoo.view.project;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Project;
import ninefoo.view.project.chart.GanttChart_view;
import ninefoo.view.project.table.TableToolbarContainer_view;
import ninefoo.view.project.table.listener.TableToolsListener;

/**
 * Panel container of a the table and Gantt chart
 * @author Amir El Bawab
 */
public class TableChartSlider_view extends JPanel{
	
	private static final long serialVersionUID = 1821070698625712816L;
	
	// Declare panels
	private TableToolbarContainer_view tableContainer;
	private GanttChart_view chartPanel;
	private JSplitPane splitPane;
	private Project project;
	
	/**
	 * Constructor
	 */
	public TableChartSlider_view(JFrame parentFrame) {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize panels
		this.tableContainer = new TableToolbarContainer_view(parentFrame, this);
		this.chartPanel = new GanttChart_view();
		
		// Add the panels to the splitter
		this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableContainer, chartPanel);
		
		// Customize the splitter
		this.splitPane.setResizeWeight(0.5);
		this.splitPane.setDividerLocation(0.5);
		this.splitPane.setDividerSize(4);
		this.splitPane.setContinuousLayout(true);
		
		// By default the tool bar is not visible
		this.setVisibleToolbar(false);
		
		// Add components
		this.add(splitPane);
	}
	
	/**
	 * Set project
	 * @param project
	 */
	public void setProject(Project project){
		this.project = project;
		this.tableContainer.setProject(project);
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
	}
	
	/**
	 * Set error message
	 * @param message
	 */
	public void setErrorMessage(String message){
		JOptionPane.showMessageDialog(this, String.format("<html>%s</html>", message), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Reset table and chart
	 */
	public void reset(){
		this.tableContainer.resetTable();
	}
	
	public void refresh(){
		this.tableContainer.populateTable();
		
		if(project != null)
			this.chartPanel.populateGanttChart(project.getAcitivies());
	}
	
	/**
	 * Set table tools listener
	 * @param tableToolsListener
	 */
	public void setTableToolsListener(TableToolsListener tableToolsListener){
		tableContainer.setTableToolsListener(tableToolsListener);
	}
	
	/**
	 * Set new activity button enabled
	 * @param enable Boolean
	 */
	public void setAddActivityEnabled(boolean enable){
		this.tableContainer.setAddActivityEnabled(enable);
	}
	
	/**
	 * Set edit activity button enabled
	 * @param enable Boolean
	 */
	public void setEditActivityEnabled(boolean enable){
		this.tableContainer.setEditActivityEnabled(enable);
	}
	
	/**
	 * Set delete activity button enabled
	 * @param enable Boolean
	 */
	public void setDeleteActivityEnabled(boolean enable){
		this.tableContainer.setDeleteActivityEnabled(enable);
	}
	
	/**
	 * Set View activity details button enabled
	 * @param enable Boolean
	 */
	public void setViewActivityEnabled(boolean enable){
		this.tableContainer.setViewActivityEnabled(enable);
	}
	
	/**
	 * Show/Hide tool bar below the table
	 * @param isVisible
	 */
	public void setVisibleToolbar(boolean isVisible){
		this.tableContainer.setVisibleToolbar(isVisible);
	}
}
