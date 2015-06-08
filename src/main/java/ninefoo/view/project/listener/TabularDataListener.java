package ninefoo.view.project.listener;

import ninefoo.model.Project;

public interface TabularDataListener {
	public void tableUpdated(int row, Project project, String activityId, String activityName, String start, String end, String duration, String activityCompleted);
}
