package ninefoo.view.include.menu;

import ninefoo.lib.component.PMButton;
import ninefoo.view.include.menu.dialog.*;
import ninefoo.view.include.menu.listener.ToolsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Top tools menu. Shows option like create a project, etc...
 *
 * @author Amir El Bawab
 * @author Sebouh Bardakjian
 */
public class Tools extends JPanel {

    private static final long serialVersionUID = -1862085076331720213L;

    // Create components
    private PMButton newProject, newMember, logout, viewProject, addUser, viewAssigned, earnedValueAnalysis;

    // Create listener
    private ToolsListener toolsListener;

    // Constructor
    public Tools(final JFrame parentFrame) {

        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        // Initialize components
		this.newProject = new PMButton(new ImageIcon(getClass().getResource("/images/new_project.png")), true);
		this.newMember = new PMButton(new ImageIcon(getClass().getResource("/images/new_user.png")), true);
		this.logout = new PMButton(new ImageIcon(getClass().getResource("/images/logout.png")), true);
		this.viewProject = new PMButton(new ImageIcon(getClass().getResource("/images/view_project.png")), true);
		this.addUser = new PMButton(new ImageIcon(getClass().getResource("/images/assign.png")), true);
		this.viewAssigned = new PMButton(new ImageIcon(getClass().getResource("/images/view_activities.png")), true);
		this.earnedValueAnalysis = new PMButton(new ImageIcon(getClass().getResource("/images/view_activities.png")), true);
		
        // Customize buttons
        this.newProject.setContentAreaFilled(false);
        this.newProject.setBorder(null);
        this.newProject.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.newProject.setHorizontalTextPosition(SwingConstants.CENTER);
        this.newProject.setText("NEW_PROJECT_PRO");
        this.newProject.setToolTipText("CREATE_A_NEW_PROJECT_PRO");

        this.newMember.setContentAreaFilled(false);
        this.newMember.setBorder(null);
        this.newMember.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.newMember.setHorizontalTextPosition(SwingConstants.CENTER);
        this.newMember.setText("NEW_MEMBER");
        this.newMember.setToolTipText("REGISTER_A_NEW_MEMBER");

        this.viewProject.setContentAreaFilled(false);
        this.viewProject.setBorder(null);
        this.viewProject.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.viewProject.setHorizontalTextPosition(SwingConstants.CENTER);
        this.viewProject.setText("VIEW_PROJECTS_PRO");
        this.viewProject.setToolTipText("VIEW_MY_PROJECTS_PRO");

        this.logout.setContentAreaFilled(false);
        this.logout.setBorder(null);
        this.logout.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.logout.setHorizontalTextPosition(SwingConstants.CENTER);
        this.logout.setText("LOGOUT");

        this.addUser.setContentAreaFilled(false);
        this.addUser.setBorder(null);
        this.addUser.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.addUser.setHorizontalTextPosition(SwingConstants.CENTER);
        this.addUser.setText("ADD_USER_PRO");
        this.addUser.setToolTipText("ASSIGN_USERS_TO_THE_PROJECT_PRO");

        this.viewAssigned.setContentAreaFilled(false);
        this.viewAssigned.setBorder(null);
        this.viewAssigned.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.viewAssigned.setHorizontalTextPosition(SwingConstants.CENTER);
        this.viewAssigned.setText("VIEW_ASSIGNED");
        this.viewAssigned.setToolTipText("VIEW_MY_ASSIGNED_ACTIVITIES");

        this.earnedValueAnalysis.setContentAreaFilled(false);
        this.earnedValueAnalysis.setBorder(null);
        this.earnedValueAnalysis.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.earnedValueAnalysis.setHorizontalTextPosition(SwingConstants.CENTER);
        this.earnedValueAnalysis.setText("EARNED_VALUE_ANALYSIS");
        this.earnedValueAnalysis.setToolTipText("CALCULATE_EARNED_VALUE_ANALYSIS");
        
        // Disable buttons at start
        this.addUser.setEnabled(false);
        this.newMember.setEnabled(false);
        this.earnedValueAnalysis.setEnabled(false);

        // Add new project listener
        this.newProject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Open dialog
                if (toolsListener != null)
                    new CreateProjectDialog(parentFrame, toolsListener);
            }
        });

        // Add view project listener
        this.viewProject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    new ViewMyProjectsDialog(parentFrame, toolsListener);
            }
        });

        // Add logout listener
        this.logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Logout
                if (toolsListener != null)
                    toolsListener.logout();
            }
        });

        this.newMember.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    new CreateUserDialog(parentFrame, toolsListener);
            }
        });

        this.addUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    new AddUserToProjectDialog(parentFrame, toolsListener);
            }
        });

        this.viewAssigned.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    new ViewAssignedActivitiesDialog(parentFrame, toolsListener);
            }
        });
        
        this.earnedValueAnalysis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
                    new EarnedValueAnalysisDialog(parentFrame, toolsListener);
			}
		});

        // Add components
        int spacing = 20;
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(this.newProject);
        this.add(Box.createRigidArea(new Dimension(spacing, 0)));
        this.add(this.viewProject);
        this.add(Box.createRigidArea(new Dimension(spacing, 0)));
        this.add(this.viewAssigned);
        this.add(Box.createRigidArea(new Dimension(spacing, 0)));
        this.add(this.newMember);
        this.add(Box.createRigidArea(new Dimension(spacing, 0)));
        this.add(this.addUser);
        this.add(Box.createRigidArea(new Dimension(spacing, 0)));
        this.add(this.earnedValueAnalysis);
        this.add(Box.createRigidArea(new Dimension(spacing, 0)));
        this.add(this.logout);

        // Configure JPanel
        this.setPreferredSize(new Dimension(0, 100)); // Size of the JPanel (Width is automatically set)
        this.setVisible(false);
    }

    /**
     * Set tools listener
     *
     * @param toolsListener
     */
    public void setToolsListener(ToolsListener toolsListener) {
        this.toolsListener = toolsListener;
    }

    /**
     * Set add user button enabled
     *
     * @param enable Boolean
     */
    public void setAddUserEnabled(boolean enable) {
        this.addUser.setEnabled(enable);
    }

    /**
     * Set new member button enabled
     *
     * @param enable Boolean
     */
    public void setNewMemberEnabled(boolean enable) {
        this.newMember.setEnabled(enable);
    }
    
    /**
     * Set earned value analysis button enabled
     *
     * @param enable Boolean
     */
    public void setEarnedValueAnalysisEnabled(boolean enable) {
        this.earnedValueAnalysis.setEnabled(enable);
    }

}
