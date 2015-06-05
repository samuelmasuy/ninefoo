package ninefoo.view.include.menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

public class Tools extends JPanel{
	
	private static final long serialVersionUID = -1862085076331720213L;

	// Create components
	private JButton newProject, newMember, newActivity, assign, logout;
	
	// Create listener
	private ToolsListener toolsListener;
	
	// Constructor
	public Tools(final JFrame parentFrame) {
		
		// Set layout
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		// Initialize components
		this.newProject = new JButton(new ImageIcon(getClass().getResource("/images/new_project.png")));
		this.newMember = new JButton(new ImageIcon(getClass().getResource("/images/new_user.png")));
		this.newActivity = new JButton(new ImageIcon(getClass().getResource("/images/new_activity.png")));
		this.logout = new JButton(new ImageIcon(getClass().getResource("/images/logout.png")));
		this.assign = new JButton(new ImageIcon(getClass().getResource("/images/assign.png")));
		
		// Customize buttons
		this.newProject.setContentAreaFilled(false);
		this.newProject.setBorder(null);
		this.newProject.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.newProject.setHorizontalTextPosition(SwingConstants.CENTER);
		this.newProject.setText("New Project");
		
		this.newActivity.setContentAreaFilled(false);
		this.newActivity.setBorder(null);
		this.newActivity.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.newActivity.setHorizontalTextPosition(SwingConstants.CENTER);
		this.newActivity.setText("New Activity");
		
		this.newMember.setContentAreaFilled(false);
		this.newMember.setBorder(null);
		this.newMember.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.newMember.setHorizontalTextPosition(SwingConstants.CENTER);
		this.newMember.setText("New Member");
		
		this.assign.setContentAreaFilled(false);
		this.assign.setBorder(null);
		this.assign.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.assign.setHorizontalTextPosition(SwingConstants.CENTER);
		this.assign.setText("Assign");

		this.logout.setContentAreaFilled(false);
		this.logout.setBorder(null);
		this.logout.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.logout.setHorizontalTextPosition(SwingConstants.CENTER);
		this.logout.setText("Logout");
		
		// Add new project listener
		this.newProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Open dialog
				if(toolsListener != null)
					new CreateProjectDialog(parentFrame, toolsListener);
			}
		});
		
		// Add new activity listener
		this.newActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Create new activity
				if(toolsListener != null)
					toolsListener.newActivity();
			}
		});
		
		// Add logout listener
		this.logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Logout
				if(toolsListener != null)
					toolsListener.logout();
			}
		});
		
		// Add components
		int spacing = 20;
		this.add(Box.createRigidArea(new Dimension(10,0)));
		this.add(this.newProject);
		this.add(Box.createRigidArea(new Dimension(spacing, 0)));
		this.add(this.newActivity);
		this.add(Box.createRigidArea(new Dimension(spacing, 0)));
		this.add(this.newMember);
		this.add(Box.createRigidArea(new Dimension(spacing, 0)));
		this.add(this.assign);
		this.add(Box.createRigidArea(new Dimension(spacing, 0)));
		this.add(this.logout);
		
		// Configure JPanel
		this.setPreferredSize(new Dimension(0, 100)); // Size of the JPanel (Width is automatically set)
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
