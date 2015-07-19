package ninefoo.view.listeners;

import ninefoo.model.object.Project;

/**
 * Activity Listener used to communicate with the Controller
 * @author Amir El Bawab
 */
public interface ActivityListener {
	
	// Create
	public void createActivity(String name, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, int memberId, int[] prerequisitesId);
	public void editActivity(int row, String activityid, String activityLabel, String duration, String startDate, String finishDate, String cost, Project project,int memberId, String[] prerequisite);
	
	// Load
	public void loadActivitiesByProject(int projectId);
	public void loadActivitiesForAllProjectByMember(int memberId);
	public void loadActivity(int activityId);
}
