package ninefoo.view.project.table.listener;

import ninefoo.model.object.Project;
import ninefoo.view.project.dialog.ActivityDependencyDialog;

/**
 * Table Listener used to communicate with the Main Frame
 * @author Amir El Bawab
 */
public interface TabularDataListener {
	public void tableUpdated(int row, Project project, String activityId, String activityName, String start, String end, String duration, String activityCompleted);
	public void dependencyLink(ActivityDependencyDialog dialog, int activityIdDependent, int activityIdDependentOn, int row);
}
