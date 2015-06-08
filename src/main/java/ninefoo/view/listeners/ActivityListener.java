package ninefoo.view.listeners;

import ninefoo.model.Project;

public interface ActivityListener {
	public void createUpdateActivity(int row, String activityLabel, String duration, String startDate, String finishDate, Project project, int memberId);
}
