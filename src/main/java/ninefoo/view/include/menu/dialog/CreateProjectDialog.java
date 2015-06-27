package ninefoo.view.include.menu.dialog;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ninefoo.helper.LayoutHelper;
import ninefoo.lib.datePicker.DatePicker;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.CommonDialog;
import ninefoo.lib.layout.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

/**
 * Dialog that shows a form for creating a new project
 * @author Amir El Bawab
 */
public class CreateProjectDialog extends CommonDialog{
	
	private static final long serialVersionUID = 216394661255136241L;
	
	// Create components
	private JButton createButton;
	private JTextField name, budget;
	private JTextArea description;
	private DatePicker deadline, start;
	
	/**
	 * Constructor
	 */
	public CreateProjectDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.createButton = new JButton(LanguageText.getConstant("CREATE_PROJECT"));
		this.name = new JTextField(10);
		this.budget= new JTextField(10);
		this.deadline = new DatePicker(8);
		this.start = new DatePicker(8);
		this.description = new JTextArea(3,10);
		
		// Set title
		this.setTitle(LanguageText.getConstant("CREATE_NEW_PROJECT"));
		
		// Set center panel
		this.setCenterPanel(new FormDialog() {
			private static final long serialVersionUID = -2943549344497607196L;
			
			@Override
			public void placeForm() {
				
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));
				
				// Set input border
				name.setBorder(BorderFactory.createCompoundBorder(name.getBorder(), inputPadding));
				budget.setBorder(BorderFactory.createCompoundBorder(budget.getBorder(), inputPadding));
				deadline.setBorder(BorderFactory.createCompoundBorder(deadline.getBorder(), inputPadding));
				description.setBorder(BorderFactory.createCompoundBorder(description.getBorder(), inputPadding));
				start.setBorder(BorderFactory.createCompoundBorder(start.getBorder(), inputPadding));
				
				// Add components
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
		});
		
		// Add button listener
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(toolsListener != null)
					toolsListener.newProject(CreateProjectDialog.this, name.getText(), budget.getText(), start.getText(), deadline.getText(), description.getText());
			}
		});
		
		// Add components to panels
		this.southPanel.add(createButton);
		
		// Configure dialog
		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
}