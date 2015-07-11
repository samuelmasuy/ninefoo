package ninefoo.view.listeners;

import ninefoo.model.object.Project;

/**
 * Activity Listener used to communicate with the Controller
 * @author Amir El Bawab
 */
public interface ActivityListener {
	
	// Create
	public void createActivity(int row, String activityid, String activityLabel, String duration, String startDate, String finishDate, String cost, Project project, int memberId, String[] prerequisite);
	public void editActivity(int row, String activityid, String activityLabel, String duration, String startDate, String finishDate, String cost, Project project,int memberId, String[] prerequisite);
	
	// Load
	public void loadActivitiesByProject(Project project); // Populate the list in the dropdown everytime you add a prerequisite
}
