package ninefoo.view.project.table.listener;

import java.util.List;

import javax.swing.JFrame;

import ninefoo.model.object.Activity;
import ninefoo.view.project.table.dialog.CreateActivityDialog;

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
	
	// Edit
	public void updateActivity();
	
	// Delete
	public void deleteActivity(JFrame parentFrame, Activity activity);
	
	// View details
	public void viewActivityDetails();
	
}
