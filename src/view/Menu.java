package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	// Define components
	private JMenu file, edit, member, help;
	private JMenuItem newProject, openProject, saveProject, restart, exit, editTitle, editMembers, editActivites, login, logout, allMember, newMember, assignProject, tourGuide, aboutInfo;
	
	// Constructor
	public Menu() {
		
		// Initialize Menu
		this.file = new JMenu("Project");
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
		this.file.add(this.newProject);
		this.file.add(this.openProject);
		this.file.add(this.saveProject);
		this.file.add(this.restart);
		this.file.add(this.exit);
		this.edit.add(this.editTitle);
		this.edit.add(this.editMembers);
		this.edit.add(this.editActivites);
		this.member.add(this.login);
		this.member.add(this.allMember);
		this.member.add(this.newMember);
		this.member.add(this.logout);
		this.help.add(this.tourGuide);
		this.help.add(this.aboutInfo);
		
		// Add components
		this.add(file);
		this.add(edit);
		this.add(member);
		this.add(help);
	}
}
