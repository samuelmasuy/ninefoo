package ninefoo.view.project.table.listener;

import javax.swing.JFrame;

import ninefoo.model.object.Activity;
import ninefoo.view.project.table.dialog.CreateActivityDialog;
import ninefoo.view.project.table.dialog.EditActivityDialog;

/**
 * Listener used to communicate with the Table Container View
 * @author Sebouh Bardakjian
 */
public interface TableToolsListener {
	
	// Create
	public void createActivity(CreateActivityDialog dialog, String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, Integer[] prerequisitesId);
	
	// Load
	public void loadAllMembersForCreateActivityDialog(CreateActivityDialog dialog);
	public void loadActivitiesForCreateActivityDialog(CreateActivityDialog dialog);
	public void loadAllMembersForEditActivityDialog(EditActivityDialog dialog);
	public void loadActivitiesForEditActivityDialog(EditActivityDialog dialog);
	
	// Edit
	public void updateActivity(EditActivityDialog dialog, String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, Integer[] prerequisitesId);
	
	// Delete
	public void deleteActivity(JFrame parentFrame, Activity activity);
	
	// View details
	public void viewActivityDetails();
	
}
