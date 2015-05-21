package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ProjectTools extends JPanel{
	
	// Create components
	private JButton newProject, newMember;
	
	// Constructor
	public ProjectTools() {
		
		// Set layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Initialize components
		this.newProject = new JButton("New project");
		this.newMember = new JButton("New member");
		
		// Add components
		this.add(this.newProject);
		this.add(this.newMember);
		
		// Configure JPanel
		this.setPreferredSize(new Dimension(0, 50)); // Size of the JPanel (Width is automatically set)
		this.setVisible(true);
	}
}
