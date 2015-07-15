package ninefoo.view.project.table.listener;

import ninefoo.view.project.table.dialog.CreateActivityDialog;

/**
 * Listener used to communicate with the Table Container View
 * @author Sebouh Bardakjian
 */
public interface TableToolsListener {
	
	// Create
	public void createActivity();
	
	// Load
	public void loadAllMembersForCreateActivityDialog(CreateActivityDialog dialog);
	public void loadActivitiesForCreateActivityDialog(CreateActivityDialog dialog);
	
	// Edit
	public void updateActivity();
	
	// Delete
	public void deleteActivity();
	
	// View details
	public void viewActivityDetails();
	
}
