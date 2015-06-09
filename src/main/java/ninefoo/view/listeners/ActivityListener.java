package ninefoo.view.listeners;

import ninefoo.model.Project;

public interface ActivityListener {
	public void createUpdateActivity(int row, String activityid, String activityLabel, String duration, String startDate, String finishDate, Project project, String completion, int memberId);
	public void createDependentActivities(int activityIdDependent, int activityIdDependentOn, int row);
}
