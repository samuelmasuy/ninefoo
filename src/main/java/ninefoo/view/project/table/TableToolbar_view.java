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
import ninefoo.model.object.Activity;
import ninefoo.view.project.table.dialog.CreateActivityDialog;
import ninefoo.view.project.table.dialog.EditActivityDialog;
import ninefoo.view.project.table.dialog.ViewActivityDetailsDialog;
import ninefoo.view.project.table.listener.TableToolsListener;
import ninefoo.view.project.table.listener.ToolbarListener;

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
	private ToolbarListener toolbarListener;
	
	/**
	 * Constructor
	 * @param parentPanel
	 */
	public TableToolbar_view(final JFrame parentFrame) {
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
		this.viewActivity.setText("VIEW_DETAILS_ACT");
		this.viewActivity.setToolTipText("VIEW_ACTIVITY_DETAILS_ACT");
		
		this.addActivity.setBorderPainted(false); 
		this.addActivity.setContentAreaFilled(false); 
		this.addActivity.setFocusPainted(false); 
		this.addActivity.setOpaque(false);
		this.addActivity.setText("ADD_ACT");
		this.addActivity.setToolTipText("ADD_A_NEW_ACTIVITY_ACT");
		
		this.editActivity.setBorderPainted(false); 
		this.editActivity.setContentAreaFilled(false); 
		this.editActivity.setFocusPainted(false); 
		this.editActivity.setOpaque(false);
		this.editActivity.setText("EDIT_ACT");
		this.editActivity.setToolTipText("EDIT_THE_ACTIVITY_PROPERTIES_ACT");
		
		this.deleteActivity.setBorderPainted(false); 
		this.deleteActivity.setContentAreaFilled(false); 
		this.deleteActivity.setFocusPainted(false); 
		this.deleteActivity.setOpaque(false);
		this.deleteActivity.setText("REMOVE_ACT");
		this.deleteActivity.setToolTipText("REMOVE_THE_ACTIVITY_ACT");
		
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
				
				// If listener set
				if(toolbarListener != null){
					
					// Store selected activity
					Activity selectedActivity = toolbarListener.getSelectedActivity();
					
					// If no activity selected, show error
					if(selectedActivity == null){
						JOptionPane.showMessageDialog(parentFrame, String.format(LanguageText.getConstant("MISSING_EDIT_ACT"),LanguageText.getConstant("ACTIVITY_ACT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
					
					// If activity selected
					} else {
						new EditActivityDialog(parentFrame, tableToolsListener, selectedActivity.getActivityId());
					}
				}
			}
		});
		
		// Add view activity details listener
		this.viewActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no activity selected, show error
				if(toolbarListener.getSelectedActivity() == null){
					JOptionPane.showMessageDialog(parentFrame, String.format(LanguageText.getConstant("MISSING_VIEW_ACT"),LanguageText.getConstant("ACTIVITY_ACT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If activity selected
				} else {
					new ViewActivityDetailsDialog(parentFrame, tableToolsListener);
				}
			}
		});
		
		// Add delete listener
		this.deleteActivity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If no activity selected, show error
				if(toolbarListener.getSelectedActivity() == null){
					JOptionPane.showMessageDialog(parentFrame, String.format(LanguageText.getConstant("MISSING_DELETE_ACT"),LanguageText.getConstant("ACTIVITY_ACT").toLowerCase()), LanguageText.getConstant("OPERATION_FAILED"), JOptionPane.ERROR_MESSAGE);
				
				// If activity selected
				} else {
					String activityName = "'" + toolbarListener.getSelectedActivity().getActivityLabel() + "'";
					int reply = JOptionPane.showConfirmDialog(parentFrame, String.format(LanguageText.getConstant("DELETE_CONFIRMATION_TEXT"), LanguageText.getConstant("ACTIVITY_ACT").toLowerCase(), activityName ) , LanguageText.getConstant("DELETE_CONFIRMATION"), JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION){
					    tableToolsListener.deleteActivity(parentFrame, toolbarListener.getSelectedActivity());
					}
				}
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
	
	/**
	 * Set toolbar listener
	 * @param toolbarListener
	 */
	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener = toolbarListener;
	}
}
