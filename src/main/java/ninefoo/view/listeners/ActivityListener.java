package ninefoo.view.listeners;

import ninefoo.model.object.Project;

/**
 * Activity Listener used to communicate with the Controller
 * @author Amir El Bawab
 */
public interface ActivityListener {
	public void createActivity(int row, String activityid, String activityLabel, String duration, String startDate, String finishDate, Project project, String completion, int memberId);
	public void createDependentActivities(int activityIdDependent, int activityIdDependentOn, int row);
}
