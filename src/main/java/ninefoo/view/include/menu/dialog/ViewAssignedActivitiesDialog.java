package ninefoo.view.include.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ninefoo.config.ActivityConfig;
import ninefoo.helper.ActivityHelper;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.excelTable.NumberedExcelTable;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

/**
 * Dialog that shows lists of assigned activities in their respective projects
 * @author Sebouh Bardakjian, Amir El Bawab
 */

public class ViewAssignedActivitiesDialog extends CenterScrollSouthButtonDialog{
	
	private static final long serialVersionUID = 6984471626529243638L;
	
	// Define components
	private PMButton close;
	private List<Project> projects;
	
	/** 
	 *  Constructor
	 */
	public ViewAssignedActivitiesDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.close = new PMButton("CLOSE");
		
		// Set title
		this.setTitle(LanguageText.getConstant("MY_ASSIGNED_ACTIVITIES_ACT"));
		
		// Load projects
		toolsListener.loadAssignedActivitiesProject(this);
		
		// Add button listener
		this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = 2612872642334096656L;

			@Override
			public void placeForm() {
				if(projects != null) {
					for(Project project : projects){
						this.table.newRow();
						this.table.put(new ProjectActivityWrapper(project));
					}
				}
			}
		});
		
		// Add component to south panel
		this.southPanel.add(this.close);
		
		// Configure dialog
		this.setSize(new Dimension(1000,750));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Set the projects that the users is involved in
	 * @param projects
	 */
	public void setProjects(List<Project> projects){
		this.projects = projects;
	}
	
	/**
	 * A panel that combines a project with a set of activities
	 * @author Amir El Bawab
	 */
	private class ProjectActivityWrapper extends JPanel{

		private static final long serialVersionUID = -9006410938025352835L;
		
		// Define components
		private PMLabel title;
		private NumberedExcelTable table;
		private JScrollPane tableScrollPane;
		
		public ProjectActivityWrapper(Project project) {
			
			// Set layout
			this.setLayout(new BorderLayout());
			
			// Initialize components
			this.title = new PMLabel(project.getProjectName());
			this.table = new NumberedExcelTable(ActivityConfig.TABLE_HEADER);
			this.tableScrollPane = this.table.getJScrollPane();
			
			// Add rows
			List<Activity> activities = project.getAcitivies();
			for(Activity activity : activities)
				table.addRow(ActivityHelper.getRow(activity));
			
			// Add components
			this.add(this.tableScrollPane, BorderLayout.CENTER);
			this.add(this.title, BorderLayout.NORTH);
			
			// Configure panel
			this.setPreferredSize(new Dimension(800, 300));
		}
	}
	
}