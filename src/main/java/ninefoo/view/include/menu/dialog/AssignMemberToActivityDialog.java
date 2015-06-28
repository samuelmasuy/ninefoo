package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

public class AssignMemberToActivityDialog extends CenterFormSouthButtonDialog{

	// Define components
	private JButton assignButton;
	private JComboBox<String> activityBox;
	private JComboBox<String> memberBox;
	
	/** 
	 *  Constructor
	 */
	public AssignMemberToActivityDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.assignButton = new JButton("Assign");
		this.activityBox = new JComboBox<>(new String[] {"Activity1", "Activity2","Activity3", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2","Activity1BlaBla", "Act2"});
		this.memberBox = new JComboBox<>(new String[] {"Mem1", "Member2BlaBlaBla"});
		
		this.setTitle(LanguageText.getConstant("ASSIGN_MEMBER_ACTIVITY_ACT"));
		
		// Add button listener
		this.assignButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.assignMemberToActivity();
			}
		});
		
		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			@Override
			public void placeForm() {
				this.titledBorder.setTitle(String.format("%s >>> %s", LanguageText.getConstant("MEMBER"), LanguageText.getConstant("ACTIVITY")));
				
				activityBox.setBorder(BorderFactory.createCompoundBorder(activityBox.getBorder(), inputPadding));
				memberBox.setBorder(BorderFactory.createCompoundBorder(memberBox.getBorder(), inputPadding));
			
				// Add components
				this.table.put(new JLabel(LanguageText.getConstant("ACTIVITY")));
				this.table.put(activityBox);
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("MEMBER")));
				this.table.put(memberBox);
			}
		});
		
		// Add component to south panel
		this.southPanel.add(assignButton);
		
		// Configure dialog
		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
