package ninefoo.view.frame;

import java.util.List;

import ninefoo.model.Activity;
import ninefoo.model.Project;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.listeners.MemberListener;
import ninefoo.view.listeners.ProjectListener;

public interface UpdatableView {
	public void updateLogin(boolean success, String message);
	public void updateRegister(boolean success, String message);
	public void updateCreateProject(boolean success, String message);
	public void updateCreateUpdateActivity(boolean success, String message, int row, Activity activity, Project project);
	public void updateLogout();
	public void updateLoadProject(boolean success, String message, Project project);
	public void updateCreateDependentActivities(boolean success, String message, int row, Activity activity);
	public void updateEditProject(boolean success, String message, Project project);
	public void updateLoadAllProjectsByMemberAndRole(List<Project> projects);
	
	public void setMemberListener(MemberListener memberListener);
	public void setProjectListener(ProjectListener projectListener);
	public void setActivityListener(ActivityListener activityListener);
	
}
