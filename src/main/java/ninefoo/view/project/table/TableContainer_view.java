package ninefoo.view.project.table;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.model.object.Project;
import ninefoo.view.project.table.listener.TableToolsListener;

public class TableContainer_view extends JPanel{
	// Declare Variables
	private TabularData_view tabularDataPanel;
	private TableToolbar_view tableToolbarPanel; 
	
	public TableContainer_view(JPanel parentPanel) {
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize variables
		tableToolbarPanel = new TableToolbar_view(parentPanel);
		tabularDataPanel = new TabularData_view(parentPanel);
		
		this.add(tableToolbarPanel, BorderLayout.SOUTH);
		this.add(tabularDataPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Set project
	 * @param project
	 */
	public void setProject(Project project){
		this.tabularDataPanel.setProject(project);
	}
	
	/**
	 * Populate table
	 */
	public void populateTable(){
		this.tabularDataPanel.populateTable();
	}
	
	/**
	 * Reset table
	 */
	public void resetTable(){
		this.tabularDataPanel.resetTable();
	}
}
