package ninefoo.view.include.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import ninefoo.config.RoleNames;
import ninefoo.helper.DateHelper;
import ninefoo.helper.LayoutHelper;
import ninefoo.lib.form.FormDialog;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;
import ninefoo.config.Config;

/**
 * Dialog showing the list of projects for a logged in user.
 * @author Amir El Bawab
 */
public class ViewMyProjectsDialog extends JDialog{
	
	private static final long serialVersionUID = 216394661255136241L;
	
	// Create components
	private JButton openButton, editButton;
	private JComboBox<String> roleBox;
	private JList<String> projectList;
	private DefaultListModel<String> listModel;
	private JScrollPane scrollList;
	private JLabel nameInfo, budgetInfo, createdDateInfo, startDateInfo, deadlineDateInfo;
	private JTextArea descriptionInfo;
	private ArrayList<Project> projects;
	
	// Define panels
	private ProjectPanel projectPanel;
	private DescriptionPanel descriptionPanel;
	
	// Constants
	private String onEmpty = " ---";
	
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
		this.roleBox = new JComboBox<String>(RoleNames.ROLES);
		this.listModel = new DefaultListModel<>();
		this.projectList = new JList<String>(listModel);
		this.scrollList = new JScrollPane(this.projectList);
		this.nameInfo = new JLabel();
		this.budgetInfo = new JLabel();
		this.descriptionInfo = new JTextArea();
		this.createdDateInfo = new JLabel();
		this.startDateInfo = new JLabel();
		this.deadlineDateInfo = new JLabel();
		
		// Initialize panels
		this.projectPanel = new ProjectPanel();
		
		// Initialize projects list
		this.projects = new ArrayList<>();
		
		// Create components
		JPanel buttonContainer = new JPanel();
		
		// Add components to panels
		buttonContainer.add(openButton);
		buttonContainer.add(editButton);
		
		// Empty the fields
		this.resetDescriptionPanel();
		
		// Configure label
		this.descriptionInfo.setEditable(false);
		this.descriptionInfo.setBackground(null);
		
		// Set default role box value
		this.roleBox.setSelectedIndex(0);
		
		// Load projects
		toolsListener.loadAllMyProjectsByRole(ViewMyProjectsDialog.this, roleBox.getSelectedItem().toString());
		
		// Add role box listener
		this.roleBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolsListener != null){
					toolsListener.loadAllMyProjectsByRole(ViewMyProjectsDialog.this, roleBox.getSelectedItem().toString());
					resetDescriptionPanel();
				}
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
		
		// Add listener for list click
		this.projectList.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {this.mouseClicked(e);}
			
			@Override
			public void mousePressed(MouseEvent e) {this.mouseClicked(e);}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int index= projectList.getSelectedIndex();
				
				// Update description
				if(index >= 0)
					populateDescriptionPanel(projects.get(index));
			}
		});
		
		// Add components to dialog
		this.add(buttonContainer, BorderLayout.SOUTH);
		this.add(projectPanel, BorderLayout.CENTER);
		
		// Configure dialog
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setSize(new Dimension(800,550));
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
		
		// If a list was returned
		if(projects != null){
			
			// Add projects
			this.projects.addAll(projects);
		}
		
		// Refresh list
		this.refreshList();
	}
	
	/**
	 * Populate one project only
	 * @param project
	 */
	@Deprecated
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
	 * Populate description panel
	 */
	private void populateDescriptionPanel(Project project){
		this.nameInfo.setText(String.format("<html><div WIDTH=20>%s</div></html>", project.getProjectName()));
		this.descriptionInfo.setText(project.getDescription());
		this.budgetInfo.setText(project.getBudget() + "");
		this.createdDateInfo.setText(DateHelper.format(project.getCreateDate(), Config.DATE_FORMAT_SHORT));
		this.deadlineDateInfo.setText(DateHelper.format(project.getDeadlineDate(), Config.DATE_FORMAT_SHORT));
		this.startDateInfo.setText(DateHelper.format(project.getStartDate(), Config.DATE_FORMAT_SHORT));
	}
	
	/**
	 * Empty the fields
	 */
	private void resetDescriptionPanel(){
		this.nameInfo.setText(onEmpty);
		this.descriptionInfo.setText(onEmpty);
		this.budgetInfo.setText(onEmpty);
		this.createdDateInfo.setText(onEmpty);
		this.deadlineDateInfo.setText(onEmpty);
		this.startDateInfo.setText(onEmpty);
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
			
			// Initialize description panel
			descriptionPanel = new DescriptionPanel();
			
			// Configure the list
			scrollList.setPreferredSize(new Dimension(400,300));
			projectList.setVisibleRowCount(-1); // Display max possible
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
			LayoutHelper.gcGrid(gc, row++, 2, 1);
			this.fixedPanel.add(descriptionPanel, gc);
			
			// Add panel
			this.add(fixedPanel);
		}
	}
	
	private class DescriptionPanel extends FormDialog{
		
		private static final long serialVersionUID = -137495661786196964L;

		public DescriptionPanel() {
			
			// Add scroll pane for description
			JScrollPane descriptionScroll = new JScrollPane(descriptionInfo);
			
			// Configure the scroll pane
			descriptionScroll.setPreferredSize(new Dimension(200,150));
			descriptionScroll.setBorder(null);
			
			fixedPanel.setBorder(null);
			
			// Add components
			int row = 0;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(5, 5, 5, 10);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("PROJECT")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(nameInfo, gc);
			
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("BUDGET")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(budgetInfo, gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("DATE_CREATED")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(createdDateInfo, gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("START_DATE")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(startDateInfo, gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("DEADLINE")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(deadlineDateInfo, gc);
			
			LayoutHelper.gcGrid(gc, row++, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("DESCRIPTION")), gc);
			LayoutHelper.gcGrid(gc, row, 0, 2);
			this.fixedPanel.add(descriptionScroll, gc);
			
			// Add fixed panel
			this.add(fixedPanel);
		}
	}
}
