package ninefoo.view.include.menu.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ninefoo.helper.LayoutHelper;
import ninefoo.lib.FormDialog;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

public class EditProjectDialog extends JDialog{
	
	private static final long serialVersionUID = 216394661255136241L;
	
	// Create components
	private JButton saveButton;
	private JTextField name, budget, deadline, start;
	private JTextArea description;
	
	// Create panels
	private InputPanel inputPanel;
	
	/**
	 * Constructor
	 */
	public EditProjectDialog(JFrame parentFrame, final ViewMyProjectsDialog parentPanel, final ToolsListener toolsListener, final int projectId) {
		
		// Initialize components
		this.saveButton = new JButton("Update project");
		this.name = new JTextField(10);
		this.budget= new JTextField(10);
		this.deadline = new JTextField(10);
		this.start = new JTextField(10);
		this.description = new JTextArea(3,10);
		
		// Set title
		this.setTitle("Update project");
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Create components
		JPanel buttonContainer = new JPanel();
		
		// Create panels
		this.inputPanel = new InputPanel();
		
		// Add button listener
		this.saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolsListener != null)
					toolsListener.updateProject(parentPanel, EditProjectDialog.this, projectId, name.getText(), budget.getText(), description.getText());
			}
		});
		
		// Add components to panels
		buttonContainer.add(saveButton);
		
		// Add components to dialog
		this.add(buttonContainer, BorderLayout.SOUTH);
		this.add(inputPanel, BorderLayout.CENTER);
		
		// Configure dialog
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setSize(new Dimension(350,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Set error message
	 * @param msg
	 */
	public void setErrorMessage(String msg){
		inputPanel.setErrorMessage(msg);
	}
	
	/**
	 * Populate fields
	 * @param project
	 */
	public void populateFields(Project project){
		this.name.setText(project.getProjectName());
		this.budget.setText(project.getBudget() + "");
		this.start.setText(project.getStartDate() + "");
		this.deadline.setText(project.getDeadlineDate() + "");
		this.description.setText(project.getDescription());
	}
	
	/**
	 * Set success message
	 * @param msg
	 */
	public void setSuccessMessage(String msg){
		inputPanel.setSuccessMessage(msg);
	}
	
	/**
	 * Private class for the input form
	 * @see FormDialog
	 */
	private class InputPanel extends FormDialog{

		private static final long serialVersionUID = -2943549344497607196L;
		
		/**
		 * Constructor
		 */
		public InputPanel() {
			
			// Set border title
			this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));
			
			// Set input border
			Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
			name.setBorder(BorderFactory.createCompoundBorder(name.getBorder(), inputPadding));
			budget.setBorder(BorderFactory.createCompoundBorder(budget.getBorder(), inputPadding));
			deadline.setBorder(BorderFactory.createCompoundBorder(deadline.getBorder(), inputPadding));
			description.setBorder(BorderFactory.createCompoundBorder(description.getBorder(), inputPadding));
			start.setBorder(BorderFactory.createCompoundBorder(start.getBorder(), inputPadding));
			
			// Add components
			int row = 0;
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("NAME")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(name, gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("BUDGET")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(budget,gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("START_DATE")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(start,gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("DEADLINE")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(deadline, gc);
			
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(LanguageText.getConstant("DESCRIPTION")), gc);
			LayoutHelper.gcGrid(gc, row++, 1, 1);
			this.fixedPanel.add(new JScrollPane(description), gc);
			
			// Add panel
			this.add(fixedPanel);
		}
	}
}
