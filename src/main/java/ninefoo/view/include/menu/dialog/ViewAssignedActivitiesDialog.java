package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Member;
import ninefoo.view.include.menu.listener.ToolsListener;

/**
 * Dialog that shows lists of assigned activities in their respective projects
 * @author Sebouh Bardakjian
 */

public class ViewAssignedActivitiesDialog extends CenterFormSouthButtonDialog{
	
	// Define components
	private JButton goToProject;
	
	
	/** 
	 *  Constructor
	 */
	public ViewAssignedActivitiesDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.goToProject = new JButton("GO TO");
		
		this.setTitle(LanguageText.getConstant("MY_ASSIGNED_ACTIVITIES_ACT"));
		
		// Add button listener
		this.goToProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.viewAssignedActivitiesProject();
			}
		});
		
		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			@Override
			public void placeForm() {
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("MY_ASSIGNED_ACTIVITIES_ACT"));
				
				// Set input border
				// None
				
				// Add components
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("PROJECT")));
				this.table.put(goToProject);
			}
		});
		
		// Add component to south panel
		// None
		
		// Configure dialog
		this.setSize(new Dimension(500,450));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}

