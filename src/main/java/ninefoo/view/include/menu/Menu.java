package ninefoo.view.include.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Top Menu bar.
 * @author Amir El Bawab
 */
public class Menu extends JMenuBar{
	
	private static final long serialVersionUID = -481460797811819303L;
	
	// Define components
	private JMenu project, edit, member, help;
	private JMenuItem newProject, openProject, saveProject, restart, exit, editTitle, editMembers, editActivites, login, logout, allMember, newMember, assignProject, tourGuide, aboutInfo;
	
	// Constructor
	public Menu() {
		
		// Initialize Menu
		this.project = new JMenu("Project");
		this.edit = new JMenu("Edit");
		this.member = new JMenu("Member");
		this.help = new JMenu("Help");
		
		// Initialize Sub-Menu
		this.newProject = new JMenuItem("New project");
		this.openProject = new JMenuItem("Open project");
		this.saveProject = new JMenuItem("Save project");
		this.restart = new JMenuItem("Restart");
		this.exit = new JMenuItem("Exit");
		this.editTitle = new JMenuItem("Edit title");
		this.editMembers = new JMenuItem("Edit members");
		this.editActivites = new JMenuItem("Edit Activities");
		this.login = new JMenuItem("Login");
		this.allMember = new JMenuItem("All member");
		this.newMember = new JMenuItem("New member");
		this.assignProject = new JMenuItem("Assign project");
		this.logout = new JMenuItem("Logout");
		this.tourGuide = new JMenuItem("Tour guide");
		this.aboutInfo = new JMenuItem("About");
		
		// Dependency
		this.project.add(this.newProject);
		this.project.add(this.openProject);
		this.project.add(this.saveProject);
		this.project.add(this.restart);
		this.project.add(this.exit);
		this.edit.add(this.editTitle);
		this.edit.add(this.editMembers);
		this.edit.add(this.editActivites);
		this.member.add(this.login);
		this.member.add(this.allMember);
		this.member.add(this.newMember);
		this.member.add(this.assignProject);
		this.member.add(this.logout);
		this.help.add(this.tourGuide);
		this.help.add(this.aboutInfo);
		
		// Add components
		this.add(project);
		this.add(edit);
		this.add(member);
		this.add(help);
	}
	
	/**
	 * Disable menu buttons
	 */
	public void disableMenu(){
		this.project.setEnabled(false);
		this.edit.setEnabled(false);
		this.member.setEnabled(false);
	}
	
	/**
	 * Enable menu buttons
	 */
	public void enableMenu(){
		this.project.setEnabled(true);
		this.edit.setEnabled(true);
		this.member.setEnabled(true);
	}
}
