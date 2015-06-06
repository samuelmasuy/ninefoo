package ninefoo.view.project;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ninefoo.view.project.listener.TabularDataListener;

public class TableChartSlider_view extends JPanel{
	
	private static final long serialVersionUID = 1821070698625712816L;
	
	// Declare panels
	private TabularData_view tabularPanel;
	private GanttChart_view chartPanel;
	private JSplitPane splitPane;
	
	/**
	 * Constructor
	 */
	public TableChartSlider_view() {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize panels
		this.tabularPanel = new TabularData_view();
		this.chartPanel = new GanttChart_view();
		
		// Add the panels to the splitter
		this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabularPanel, chartPanel);
		
		// Customize the splitter
		this.splitPane.setResizeWeight(0.3);
		this.splitPane.setDividerLocation(0.3);
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
}
