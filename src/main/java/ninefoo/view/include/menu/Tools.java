package ninefoo.view.include.menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

public class Tools extends JPanel{
	
	private static final long serialVersionUID = -1862085076331720213L;

	// Create components
	private JButton newProject, newMember;
	
	// Create listener
	private ToolsListener toolsListener;
	
	// Constructor
	public Tools(final JFrame parentFrame) {
		
		// Set layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Initialize components
		this.newProject = new JButton(new ImageIcon(getClass().getResource("/images/new_project.png")));
		this.newMember = new JButton(new ImageIcon(getClass().getResource("/images/new_user.png")));

		// Customize buttons
		this.newProject.setContentAreaFilled(false);
		this.newProject.setBorder(null);
		this.newProject.setToolTipText("New Project");
		this.newMember.setContentAreaFilled(false);
		this.newMember.setBorder(null);
		this.newMember.setToolTipText("New Member");
		
		// Add new project listener
		this.newProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Open dialog
				new CreateProjectDialog(parentFrame, toolsListener);
			}
		});
		
		// Add components
		this.add(this.newProject);
		this.add(this.newMember);
		
		// Configure JPanel
		this.setPreferredSize(new Dimension(0, 50)); // Size of the JPanel (Width is automatically set)
		this.setVisible(false);
	}
	
	/**
	 * Set tools listener
	 * @param toolsListener
	 */
	public void setToolsListener(ToolsListener toolsListener){
		this.toolsListener = toolsListener;
	}
}
