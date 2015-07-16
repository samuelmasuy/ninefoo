package ninefoo.view.frame;

import java.util.List;

import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.listeners.MemberListener;
import ninefoo.view.listeners.ProjectListener;

/**
 * Inherited by the GUI Main Frame. Also used in the Controller to know which methods it should call
 * @author Amir El Bawab
 */
public interface UpdatableView {
	
	// Update GUI. Start methods with prefix: update
	public void updateLogin(boolean success, String message);
	public void updateRegister(boolean success, String message);
	public void updateCreateProject(boolean success, String message, Project project);
	public void updateCreateActivity(boolean success, String message, Project project);
	public void updateEditActivity(boolean success, String message, Project project);
	public void updateLogout();
	public void updateLoadProject(boolean success, String message, Project project);
	public void updateCreateDependentActivities(boolean success, String message, int row, Activity activity);
	public void updateEditProject(boolean success, String message);
	public void updateLoadAllProjectsByMemberAndRole(List<Project> projects);
	public void updateLoadEditProjectFields(boolean success, String message, Project project);
	public void updateLoadAssignedActivitiesProject(boolean success, String message, List<Project> projects);
	public void updateLoadActivitiesByProject(boolean success, String message, List<Activity> activities);
	public void updateAddUserToProject(boolean success, String message);
	public void updateLoadAllMembers(boolean success, String message, List<Member> user);
	public void updateRegisterAndAssign(boolean success, String message);
	public void updateDeleteProject(boolean success, String message, List<Project> projects);
	
	// Set listeners. Start methods with prefix: set
	public void setMemberListener(MemberListener memberListener);
	public void setProjectListener(ProjectListener projectListener);
	public void setActivityListener(ActivityListener activityListener);

}
