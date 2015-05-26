package ninefoo.view.include.menu;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ninefoo.application.Application;

public class Tools extends JPanel{
	
	// Create components
	private JButton newProject, newMember;
	
	// Constructor
	public Tools() {
		
		// Set layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Initialize components			
		this.newProject = new JButton(new ImageIcon(getClass().getResource(Application.APPLICATION_PATH + "view/images/new_project.png")));
		this.newMember = new JButton(new ImageIcon(getClass().getResource(Application.APPLICATION_PATH + "view/images/new_user.png")));
		
		// Customize buttons
		this.newProject.setContentAreaFilled(false);
		this.newProject.setBorder(null);
		this.newProject.setToolTipText("New Project");
		this.newMember.setContentAreaFilled(false);
		this.newMember.setBorder(null);
		this.newMember.setToolTipText("New Member");
		
		// Add components
		this.add(this.newProject);
		this.add(this.newMember);
		
		// Configure JPanel
		this.setPreferredSize(new Dimension(0, 50)); // Size of the JPanel (Width is automatically set)
		this.setVisible(false);
	}
}
