package ninefoo.view.include.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import ninefoo.config.RoleNames;
import ninefoo.helper.LayoutHelper;
import ninefoo.lib.FormDialog;
import ninefoo.lib.LanguageText;
import ninefoo.model.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

public class ViewMyProjectsDialog extends JDialog{
	
	private static final long serialVersionUID = 216394661255136241L;
	
	// Create components
	private JButton openButton, editButton;
	private JComboBox<String> roleBox;
	private JList<String> projectList;
	private DefaultListModel<String> listModel;
	private JScrollPane scrollList;
	private JLabel descriptionLabel, createdDate, startDate, deadlineDate;
	private ArrayList<Project> projects;
	
	// Define panels
	ProjectPanel projectPanel;
	
	// Declare listener
	 ToolsListener toolsListener;
	
	/**
	 * Constructor
	 */
	public ViewMyProjectsDialog(final JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Set title
		this.setTitle("View my projects");
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize components
		this.openButton = new JButton("Open");
		this.editButton = new JButton("Edit");
		this.roleBox = new JComboBox<String>(new String[]{"Manager", "Member"});
		this.listModel = new DefaultListModel<>();
		this.projectList = new JList<String>(listModel);
		this.scrollList = new JScrollPane(this.projectList);
		this.descriptionLabel = new JLabel();
		this.createdDate = new JLabel();
		this.startDate = new JLabel();
		this.deadlineDate = new JLabel();
		
		// Initialize panels
		this.projectPanel = new ProjectPanel();
		
		// Initialize projects list
		this.projects = new ArrayList<>();
		
		// Set listener
		this.toolsListener = toolsListener;
		
		// Create components
		JPanel buttonContainer = new JPanel();
		
		// Add components to panels
		buttonContainer.add(openButton);
		buttonContainer.add(editButton);
		
		// Set default role box value
		this.roleBox.setSelectedIndex(0);
		
		// FIXME Change from enum to String
		final RoleNames[] roles = {RoleNames.Manager, RoleNames.Member};
		
		// Load projects
		toolsListener.loadAllMyProjectsByRole(ViewMyProjectsDialog.this, roles[roleBox.getSelectedIndex()]);
		
		// Add role box listener
		this.roleBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolsListener != null)
					toolsListener.loadAllMyProjectsByRole(ViewMyProjectsDialog.this, roles[roleBox.getSelectedIndex()]);
			}
		});
		
		// Add open listener
		this.openButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Change text to read form library
				// If no project selected, show error
				if(projectList.getSelectedIndex() < 0)
					JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, "Please select a project to open", "Opeartion failed", JOptionPane.ERROR_MESSAGE);
				
				// If project selected
				else if(toolsListener != null)
					toolsListener.loadProject(ViewMyProjectsDialog.this, projects.get(projectList.getSelectedIndex()).getProjectId());
			}
		});
		
		// Add edit listener
		this.editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no project selected, show error
				if(projectList.getSelectedIndex() < 0)
					JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, "Please select a project to edit", "Opeartion failed", JOptionPane.ERROR_MESSAGE);
				
				// If project selected
				else if(toolsListener != null){
					new EditProjectDialog(parentFrame, ViewMyProjectsDialog.this, toolsListener, projects.get(projectList.getSelectedIndex()).getProjectId());
				}
			}
		});
		
		// Add components to dialog
		this.add(buttonContainer, BorderLayout.SOUTH);
		this.add(projectPanel, BorderLayout.CENTER);
		
		// Configure dialog
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setSize(new Dimension(700,550));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Populate list
	 * @param toolsListener
	 */
	public void populateProjectList(List<Project> projects){
		
		// Reset array
		this.projects = new ArrayList<>();
		
		// Add projects
		this.projects.addAll(projects);
		
		// Refresh list
		this.refreshList();
	}
	
	/**
	 * Populate one project only
	 * @param project
	 */
	public void populateProject(Project project){
		
		// Search for the project
		for(int i=0; i < projects.size(); i++){
			
			// Find the project
			if(project.getProjectId() == projects.get(i).getProjectId()){
				
				// Replace project by the new one
				projects.set(i, project);
				break;
			}
		}
		
		// Refresh list
		this.refreshList();
	}
	
	/**
	 * Refresh the list
	 */
	public void refreshList(){
		
		// Remove all elements
		this.listModel.removeAllElements();
		
		// Refresh list
		for(int i=0; i < projects.size(); i++)
			this.listModel.addElement(projects.get(i).getProjectName());
	}
	
	/**
	 * Set error message
	 * @param msg
	 */
	public void setErrorMessage(String msg){
		projectPanel.setErrorMessage(msg);
	}
	
	/**
	 * Set success message
	 * @param msg
	 */
	public void setSuccessMessage(String msg){
		projectPanel.setSuccessMessage(msg);
	}
	
	/**
	 * Private class for the project list
	 * @see FormDialog
	 */
	private class ProjectPanel extends FormDialog{

		private static final long serialVersionUID = -9050498399402050988L;

		/**
		 * Constructor
		 */
		public ProjectPanel() {
			
			// Set border title
			this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));
			
			// Set input border
			Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
			projectList.setBorder(BorderFactory.createCompoundBorder(projectList.getBorder(), inputPadding));
			
			// Create description panel
			JPanel descriptionPanel = new JPanel();
			
			// Configure the list
			projectList.setPreferredSize(new Dimension(550,500));
			projectList.setVisibleRowCount(20);
			projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			// Add components
			int row = 0;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(5, 5, 5, 10);
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel("Role"), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(roleBox, gc);

			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			LayoutHelper.gcGrid(gc, row, 0, 2);
			this.fixedPanel.add(scrollList, gc);
			
			// TODO Add description panel
//			LayoutHelper.gcGrid(gc, row++, 2, 1);
//			this.fixedPanel.add(descriptionPanel, gc);
			
			// Add panel
			this.add(fixedPanel);
		}
	}
}
