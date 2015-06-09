package ninefoo.view.project.listener;

import ninefoo.model.Project;
import ninefoo.view.project.dialog.ActivityDependencyDialog;

public interface TabularDataListener {
	public void tableUpdated(int row, Project project, String activityId, String activityName, String start, String end, String duration, String activityCompleted);
	public void dependencyLink(ActivityDependencyDialog dialog, int activityIdDependent, int activityIdDependentOn, int row);
}
