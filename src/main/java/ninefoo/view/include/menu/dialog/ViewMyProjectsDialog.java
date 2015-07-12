package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import ninefoo.config.RoleNames;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.lib.layout.grid.GridTable;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;
import ninefoo.config.Config;

/**
 * Dialog showing the list of projects for a logged in user.
 * @author Amir El Bawab
 */
public class ViewMyProjectsDialog extends CenterFormSouthButtonDialog{
	
	private static final long serialVersionUID = 216394661255136241L;
	
	// Create components
	private JButton openButton, editButton, deleteButton;
	private JComboBox<String> roleBox;
	private JList<String> projectList;
	private DefaultListModel<String> listModel;
	private JScrollPane scrollList;
	private JLabel nameInfo, budgetInfo, createdDateInfo, startDateInfo, deadlineDateInfo;
	private JTextArea descriptionInfo;
	private ArrayList<Project> projects;
	
	// Define panels
	private DescriptionPanel descriptionPanel;
	
	// Constants
	private final String onEmpty = " ---";
	
	// Listeners
	ToolsListener toolsListener;
	
	/**
	 * Constructor
	 */
	public ViewMyProjectsDialog(final JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Set title
		this.setTitle(LanguageText.getConstant("VIEW_MY_PROJECTS_PRO"));
		
		// Initialize components
		this.openButton = new JButton(LanguageText.getConstant("OPEN_PRO"));
		this.editButton = new JButton(LanguageText.getConstant("EDIT_PRO"));
		this.deleteButton = new JButton(LanguageText.getConstant("DELETE_PRO"));
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
		
		// Set listeners
		this.toolsListener = toolsListener;
		
		// Initialize panels
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = 8802269677151999153L;

			@Override
			public void placeForm() {
				
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));
				
				// Set input border
				projectList.setBorder(BorderFactory.createCompoundBorder(projectList.getBorder(), inputPadding));
				
				// Initialize description panel
				descriptionPanel = new DescriptionPanel();
				
				// Configure the list
				scrollList.setPreferredSize(new Dimension(400,300));
				projectList.setVisibleRowCount(-1); // Display max possible
				projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				// Add components
				this.table.placeCenterLeft();
				this.table.paddingCell(5, 5, 5, 10);
				this.table.put(new JLabel("Role"));
				this.table.put(roleBox);

				this.table.newRow();
				this.table.placeTopLeft();;
				this.table.put(scrollList, 2);
				this.table.put(descriptionPanel);
			}
		});
		
		// Initialize projects list
		this.projects = new ArrayList<>();
		
		// Add components to panels
		this.southPanel.add(openButton);
		this.southPanel.add(editButton);
		this.southPanel.add(deleteButton);
		
		// Configure label
		this.descriptionInfo.setEditable(false);
		this.descriptionInfo.setBackground(null);
		
		// Set default role box value
		this.roleBox.setSelectedIndex(0);
		
		// Load the list
		this.reloadProjectListByRole();
		
		// Add role box listener
		this.roleBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolsListener != null){
					reloadProjectListByRole();
					enableActionsByRole();
				}
			}
		});
		
		// Add open listener
		this.openButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no project selected, show error
				if(projectList.getSelectedIndex() < 0)
					JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("MISSING_OPEN"), LanguageText.getConstant("PROJECT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
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
					JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("MISSING_EDIT"),LanguageText.getConstant("PROJECT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If project selected
				else if(toolsListener != null){
					new EditProjectDialog(parentFrame, ViewMyProjectsDialog.this, toolsListener, projects.get(projectList.getSelectedIndex()).getProjectId());
				}
			}
		});
		
		// Add edit listener
		this.deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no project selected, show error
				if(projectList.getSelectedIndex() < 0)
					JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("MISSING_DELETE"),LanguageText.getConstant("PROJECT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If project selected
				else if(toolsListener != null){
					String projectName = "'" + projectList.getSelectedValue() + "'";
					int reply = JOptionPane.showConfirmDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("DELETE_CONFIRMATION_TEXT"), LanguageText.getConstant("PROJECT").toLowerCase(), projectName) , LanguageText.getConstant("DELETE_CONFIRMATION"), JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION){
					    toolsListener.deleteProject(ViewMyProjectsDialog.this, projects.get(projectList.getSelectedIndex()));
					}
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
		
		// Configure dialog
		this.setSize(new Dimension(700,500));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Load the list of projects by role
	 */
	public void reloadProjectListByRole(){
		
		// Load the list
		if(this.toolsListener != null){
			toolsListener.loadAllMyProjectsByRole(ViewMyProjectsDialog.this, roleBox.getSelectedItem().toString());
			resetDescriptionPanel();
		}
	}
	
	/**
	 * Enable / Disable edit and delete buttons based on role
	 */
	public void enableActionsByRole(){
		if (roleBox.getSelectedItem() == RoleNames.MANAGER){
			this.editButton.setEnabled(true);
			this.deleteButton.setEnabled(true);
		}else if (roleBox.getSelectedItem() == RoleNames.MEMBER){
			this.editButton.setEnabled(false);
			this.deleteButton.setEnabled(false);
		}
	}
	
	/** 
	 * Get the roleBox selected role
	 */
	public String getSelectedRole(){
		return roleBox.getSelectedItem().toString();
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
	 * Populate description panel
	 */
	private void populateDescriptionPanel(Project project){
		this.nameInfo.setText(String.format("<html><div>%s</div></html>", project.getProjectName()));
		this.descriptionInfo.setText(project.getDescription());
		this.budgetInfo.setText(project.getBudget() + "");
		this.createdDateInfo.setText(DateHelper.format(project.getCreateDate(), Config.DATE_FORMAT_SHORT));
		this.deadlineDateInfo.setText(DateHelper.format(project.getDeadlineDate(), Config.DATE_FORMAT_SHORT));
		this.startDateInfo.setText(DateHelper.format(project.getStartDate(), Config.DATE_FORMAT_SHORT));
	}
	
	/**
	 * Refresh description panel
	 */
	public void refreshDescriptionPanel(){
		if(projectList.getSelectedIndex() >= 0)
			this.populateDescriptionPanel(projects.get(projectList.getSelectedIndex()));
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
	 * Description panel for the projects
	 * @author Amir El Bawab
	 */
	private class DescriptionPanel extends JPanel{
		
		private static final long serialVersionUID = -137495661786196964L;

		public DescriptionPanel() {
			
			// Set layout
			this.setLayout(new GridBagLayout());
			
			// Add table
			GridTable table = new GridTable(this);
			
			// Configure table
			table.getGridBagConstraints().fill = GridBagConstraints.BOTH;
			
			// Add scroll pane for description
			JScrollPane descriptionScroll = new JScrollPane(descriptionInfo);
			
			// Configure the scroll pane
			descriptionScroll.setPreferredSize(new Dimension(200,150));
			descriptionScroll.setBorder(null);
			
			// Configure table
			table.paddingCell(5, 5, 5, 5);
			table.placeTopLeft();
			
			// Add components
			table.put(new JLabel(LanguageText.getConstant("PROJECT")));
			table.put(nameInfo);
			
			table.newRow();
			table.put(new JLabel(LanguageText.getConstant("BUDGET")));
			table.put(budgetInfo);
			
			table.newRow();
			table.put(new JLabel(LanguageText.getConstant("DATE_CREATED")));
			table.put(createdDateInfo);
			
			table.newRow();
			table.put(new JLabel(LanguageText.getConstant("START_DATE")));
			table.put(startDateInfo);
			
			table.newRow();
			table.put(new JLabel(LanguageText.getConstant("DEADLINE")));
			table.put(deadlineDateInfo);
			
			table.newRow();
			table.put(new JLabel(LanguageText.getConstant("DESCRIPTION")));
			
			table.newRow();
			table.put(descriptionScroll, 2);
		}
	}
}
