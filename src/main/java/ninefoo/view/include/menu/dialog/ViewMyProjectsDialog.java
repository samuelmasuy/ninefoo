package ninefoo.view.include.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private JButton openButton;
	private JComboBox<String> roleBox;
	private JList<String> projectList;
	private DefaultListModel<String> listModel;
	private JScrollPane scrollList;
	private JLabel descriptionLabel, createdDate, startDate, deadlineDate;
	private ArrayList<Project> projects;
	
	/**
	 * Constructor
	 */
	public ViewMyProjectsDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Set title
		this.setTitle("View my projects");
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize components
		this.openButton = new JButton("Open");
		this.roleBox = new JComboBox<String>(new String[]{"Manager", "Member"});
		this.listModel = new DefaultListModel<>();
		this.projectList = new JList<String>(listModel);
		this.scrollList = new JScrollPane(this.projectList);
		this.descriptionLabel = new JLabel();
		this.createdDate = new JLabel();
		this.startDate = new JLabel();
		this.deadlineDate = new JLabel();
		
		// Create components
		JPanel buttonContainer = new JPanel();
		
		// Add components to panels
		buttonContainer.add(openButton);
		
		// Set default role box value
		this.roleBox.setSelectedIndex(0);
		
		// Add role box listener
		this.roleBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				populateProjectList(toolsListener);
			}
		});
		
		// Populate list
		this.populateProjectList(toolsListener);
		
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
					toolsListener.loadProject(projects.get(projectList.getSelectedIndex()).getProjectId());
			}
		});
		
		// Add components to dialog
		this.add(buttonContainer, BorderLayout.SOUTH);
		this.add(new ProjectPanel(), BorderLayout.CENTER);
		
		// Configure dialog
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setSize(new Dimension(800,600));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Populate list
	 * @param toolsListener
	 */
	private void populateProjectList(ToolsListener toolsListener){
		
		// Reset array
		this.projects = new ArrayList<>();
		
		// Remove all elements
		this.listModel.removeAllElements();
		
		// Fetch data by role
		RoleNames[] roleNames = {RoleNames.Manager, RoleNames.Member};
		if(toolsListener != null)
			this.projects.addAll(toolsListener.getAllMyProjectsByRole(roleNames[this.roleBox.getSelectedIndex()]));
			
		// Populate list
		for(int i=0; i < projects.size(); i++)
			this.listModel.addElement(projects.get(i).getProjectName());
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
