package ninefoo.view.include.menu;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ninefoo.lib.component.PMMenu;
import ninefoo.lib.component.PMMenuItem;

/**
 * Top Menu bar.
 * @author Amir El Bawab
 */
public class Menu extends JMenuBar{
	
	private static final long serialVersionUID = -481460797811819303L;
	
	// Define components
	private PMMenu project, edit, member, help, language;
	private JMenuItem newProject, openProject, saveProject, restart, exit, editTitle, editMembers, editActivites, login, logout, allMember, newMember, assignProject, tourGuide, aboutInfo, english, french;
	
	// Constructor
	public Menu() {
		
		// Initialize Menu
		this.project = new PMMenu("Project");
		this.edit = new PMMenu("Edit");
		this.member = new PMMenu("Member");
		this.language = new PMMenu("Language");
		this.help = new PMMenu("Help");
		
		// Initialize Sub-Menu
		this.newProject = new PMMenuItem("New project");
		this.openProject = new PMMenuItem("Open project");
		this.saveProject = new PMMenuItem("Save project");
		this.restart = new PMMenuItem("Restart");
		this.exit = new PMMenuItem("Exit");
		this.editTitle = new PMMenuItem("Edit title");
		this.editMembers = new PMMenuItem("Edit members");
		this.editActivites = new PMMenuItem("Edit Activities");
		this.login = new PMMenuItem("Login");
		this.allMember = new PMMenuItem("All member");
		this.newMember = new PMMenuItem("New member");
		this.assignProject = new PMMenuItem("Assign project");
		this.logout = new PMMenuItem("Logout");
		this.tourGuide = new PMMenuItem("Tour guide");
		this.aboutInfo = new PMMenuItem("About");
		this.english = new PMMenuItem("English");
		this.french = new PMMenuItem("French");
		
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
		this.language.add(this.english);
		this.language.add(this.french);
		
		// Add components
		this.add(project);
		this.add(edit);
		this.add(member);
		this.add(language);
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
