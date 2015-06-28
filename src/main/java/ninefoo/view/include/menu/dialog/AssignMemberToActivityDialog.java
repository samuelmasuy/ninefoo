package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

public class AssignMemberToActivityDialog extends CenterFormSouthButtonDialog{

	// Define components
	private JButton assignButton;
	
	/** 
	 *  Constructor
	 */
	public AssignMemberToActivityDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.assignButton = new JButton("Assign");
		
		this.assignButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.assignMemberToActivity();
			}
		});
		
		this.southPanel.add(assignButton);
		
		// Configure dialog
		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
