package ninefoo.view.project.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ninefoo.lib.component.PMButton;
import ninefoo.lib.lang.LanguageText;
import ninefoo.view.project.table.dialog.CreateActivityDialog;
import ninefoo.view.project.table.dialog.EditActivityDialog;
import ninefoo.view.project.table.dialog.ViewActivityDetailsDialog;
import ninefoo.view.project.table.listener.TableToolsListener;

/**
 * Bottom Left panel toolbar for activity actions
 * @author Amir El Bawab, Sebouh Bardakjian
 */
public class TableToolbar_view extends JPanel {
	private static final long serialVersionUID = -7124432397140089151L;

	// Declare components
	private PMButton addActivity, editActivity, deleteActivity, viewActivity;
	
	// Declare listener
	private TableToolsListener tableToolsListener;
	
	/**
	 * Constructor
	 * @param parentPanel
	 */
	public TableToolbar_view(final JFrame parentFrame, JPanel parentPanel) {
		// Set layout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// Initialize components
		this.viewActivity = new PMButton(new ImageIcon(getClass().getResource("/images/buttons/general/eye.png")));
		this.addActivity = new PMButton(new ImageIcon(getClass().getResource("/images/buttons/general/add.png")));
		this.editActivity = new PMButton(new ImageIcon(getClass().getResource("/images/buttons/general/edit.png")));
		this.deleteActivity = new PMButton(new ImageIcon(getClass().getResource("/images/buttons/general/remove.png")));
		
		// Configure buttons
		this.viewActivity.setBorderPainted(false); 
		this.viewActivity.setContentAreaFilled(false); 
		this.viewActivity.setFocusPainted(false); 
		this.viewActivity.setOpaque(false);
		this.viewActivity.setText(LanguageText.getConstant("VIEW_DETAILS_ACT"));
		
		this.addActivity.setBorderPainted(false); 
		this.addActivity.setContentAreaFilled(false); 
		this.addActivity.setFocusPainted(false); 
		this.addActivity.setOpaque(false);
		this.addActivity.setText(LanguageText.getConstant("ADD_ACT"));
		
		this.editActivity.setBorderPainted(false); 
		this.editActivity.setContentAreaFilled(false); 
		this.editActivity.setFocusPainted(false); 
		this.editActivity.setOpaque(false);
		this.editActivity.setText(LanguageText.getConstant("EDIT_ACT"));
		
		this.deleteActivity.setBorderPainted(false); 
		this.deleteActivity.setContentAreaFilled(false); 
		this.deleteActivity.setFocusPainted(false); 
		this.deleteActivity.setOpaque(false);
		this.deleteActivity.setText(LanguageText.getConstant("REMOVE_ACT"));
		
		// Add component
		this.add(addActivity);
		this.add(editActivity);
		this.add(deleteActivity);
		this.add(viewActivity);
		
		// Add new activity listener
		this.addActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Create new activity
				if(tableToolsListener != null)
					new CreateActivityDialog(parentFrame, tableToolsListener);
			}
		});
		
		// Add edit listener
		this.editActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no activity selected, show error
				//if(projectList.getSelectedIndex() < 0)
				//	JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("MISSING_DELETE"),LanguageText.getConstant("PROJECT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If activity selected
				//else if(tableToolsListener != null){
					new EditActivityDialog(parentFrame, tableToolsListener);
					//}
				//}
			}
		});
		
		// Add view activity details listener
		this.viewActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no activity selected, show error
				//if(projectList.getSelectedIndex() < 0)
				//	JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("MISSING_DELETE"),LanguageText.getConstant("PROJECT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If activity selected
				//else if(tableToolsListener != null){
					new ViewActivityDetailsDialog(parentFrame, tableToolsListener);
				//	}
				//}
			}
		});
		
		// Add delete listener
		this.deleteActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no activity selected, show error
				//if(projectList.getSelectedIndex() < 0)
				//	JOptionPane.showMessageDialog(ViewMyProjectsDialog.this, String.format(LanguageText.getConstant("MISSING_DELETE"),LanguageText.getConstant("PROJECT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If activity selected
				//else if(tableToolsListener != null){
					//String projectName = "'" + projectList.getSelectedValue() + "'";
					int reply = JOptionPane.showConfirmDialog(parentFrame, String.format(LanguageText.getConstant("DELETE_CONFIRMATION") /*, LanguageText.getConstant("ACTIVITY_ACT").toLowerCase(), activity name */) , LanguageText.getConstant("DELETE_CONFIRMATION"), JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION){
					    tableToolsListener.deleteActivity(/* parentFrame, projects.get(projectList.getSelectedIndex())*/);
					}
				//}
			}
		});
		
	}
	
	/**
	 * Set listener
	 */
	public void setTableToolsListener(TableToolsListener tableToolsListener){
		this.tableToolsListener = tableToolsListener;
	}
	
	/**
	 * Set new activity button enabled
	 * @param enable Boolean
	 */
	public void setAddActivityEnabled(boolean enable){
		this.addActivity.setEnabled(enable);
	}
	
	/**
	 * Set edit activity button enabled
	 * @param enable Boolean
	 */
	public void setEditActivityEnabled(boolean enable){
		this.editActivity.setEnabled(enable);
	}
	
	/**
	 * Set delete activity button enabled
	 * @param enable Boolean
	 */
	public void setDeleteActivityEnabled(boolean enable){
		this.deleteActivity.setEnabled(enable);
	}
	
	/**
	 * Set View activity details button enabled
	 * @param enable Boolean
	 */
	public void setViewActivityEnabled(boolean enable){
		this.viewActivity.setEnabled(enable);
	}
}
