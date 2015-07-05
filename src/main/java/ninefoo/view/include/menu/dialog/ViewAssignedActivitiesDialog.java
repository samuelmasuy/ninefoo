package ninefoo.view.include.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ninefoo.config.ActivityConfig;
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
	private JButton close;
	private List<Project> projects;
	
	/** 
	 *  Constructor
	 */
	public ViewAssignedActivitiesDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.close = new JButton(LanguageText.getConstant("CLOSE"));
		
		// Set title
		this.setTitle(LanguageText.getConstant("MY_ASSIGNED_ACTIVITIES_ACT"));
		
		// Load projects
		toolsListener.loadAssignedActivitiesProject(this);
		
		// TODO Remove this line, just for testing before implementing the content of the controller
		this.dummyData();
		
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
				for(Project project : projects){
					this.table.newRow();
					this.table.put(new ProjectActivityWrapper(project));
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
	
	// TODO Delete this method, for testing only
	private void dummyData(){
		
		this.projects = new ArrayList<>();
		
		// Dummy projects
		Project project1 = new Project("Project 1", 100, null, null, "Hello");
		Project project2 = new Project("Project 2", 100, null, null, "Hello");
		
		Member member = new Member("Hello", "World", "", "");
		
		// Dummy activities
		Activity activity1 = new Activity(1, "Act1", "Desc", 10, 10, 10, 10, null, project1, member, null);
		Activity activity2 = new Activity(2, "Act2", "Desc", 10, 10, 10, 10, null, project1, member, null);
		
		List<Activity> acts = new ArrayList<>();
		acts.add(activity1);
		acts.add(activity2);
		
		project1.setAcitivies(acts);
		project2.setAcitivies(acts);
		
		this.projects.add(project1);
		this.projects.add(project2);
		this.projects.add(project1);
		this.projects.add(project2);
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
		private JLabel title;
		private NumberedExcelTable table;
		private JScrollPane tableScrollPane;
		
		public ProjectActivityWrapper(Project project) {
			
			// Set layout
			this.setLayout(new BorderLayout());
			
			// Initialize components
			this.title = new JLabel(project.getProjectName());
			this.table = new NumberedExcelTable(ActivityConfig.TABLE_HEADER);
			this.tableScrollPane = this.table.getJScrollPane();
			
			// Add rows
			List<Activity> activities = project.getAcitivies();
			for(Activity activity : activities)
				table.addRow(activity.getActivityId(), activity.getActivityLabel(), activity.getStartDate(), activity.getFinishDate(), activity.getDuration(), activity.getPrerequisitesAsString());
			
			// Add components
			this.add(this.tableScrollPane, BorderLayout.CENTER);
			this.add(this.title, BorderLayout.NORTH);
			
			// Configure panel
			this.setPreferredSize(new Dimension(800, 300));
		}
	}
	
}