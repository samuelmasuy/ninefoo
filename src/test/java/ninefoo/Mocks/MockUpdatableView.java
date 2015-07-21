package ninefoo.Mocks;

import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.listeners.MemberListener;
import ninefoo.view.listeners.ProjectListener;

import java.util.List;

/**
 * Created by samuel on 2015-07-09.
 */
public class MockUpdatableView implements UpdatableView {

    private String called_method;
    private String message;
    private String success;
    private Object object_loaded;

    public MockUpdatableView() {
        this.called_method = null;
        this.message = null;
        this.success = null;
        this.object_loaded = null;
    }

    public void reset_default() {
        this.called_method = null;
        this.message = null;
        this.success = null;
        this.object_loaded = null;
    }

    public String get_called_method() {
        return this.called_method;
    }

    public String get_success() {
        return this.success;
    }

    public String get_message() {
        return this.message;
    }
    public Object get_object_loaded() {
        return this.object_loaded;
    }

    @Override
    public void updateLogin(boolean success, String message) {
        this.called_method = "updateLogin";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateRegister(boolean success, String message) {
        this.called_method = "updateRegister";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateCreateProject(boolean success, String message, Project project) {
        this.called_method = "updateCreateProject";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateCreateActivity(boolean success, String message, Project project) {
        this.called_method = "updateCreateActivity";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateEditActivity(boolean success, String message, Project project) {
        this.called_method = "updateEditActivity";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateLogout() {
        this.called_method = "updateLogout";
    }

    @Override
    public void updateLoadProject(boolean success, String message, Project project) {
        this.called_method = "updateLoadProject";
        this.success = String.valueOf(success);
        this.message = message;

    }

    @Override
    public void updateCreateDependentActivities(boolean success, String message, int row, Activity activity) {
        this.called_method = "updateCreateDependentActivities";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateEditProject(boolean success, String message) {
        this.called_method = "updateEditProject";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateLoadAllProjectsByMemberAndRole(List<Project> projects) {
        this.called_method = "updateLoadAllProjectsByMemberAndRole";
    }

    @Override
    public void updateLoadEditProjectFields(boolean success, String message, Project project) {
        this.called_method = "updateLoadEditProjectFields";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateLoadActivitiesByProject(boolean success, String message, List<Activity> activities) {
        this.called_method = "updateLoadActivitiesByProject";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateAddUserToProject(boolean success, String message) {
        this.called_method = "updateAddUserToProject";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateLoadAllMembers(boolean success, String message, List<Member> user) {
        this.called_method = "updateLoadAllMembers";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateRegisterAndAssign(boolean success, String message) {
        this.called_method = "updateRegisterAndAssign";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void setMemberListener(MemberListener memberListener) {
        this.called_method = "setMemberListener";
    }

    @Override
    public void setProjectListener(ProjectListener projectListener) {
        this.called_method = "setProjectListener";
    }

    @Override
    public void setActivityListener(ActivityListener activityListener) {
        this.called_method = "setActivityListener";
    }

    @Override
    public void updateDeleteProject(boolean success, String message, List<Project> projects) {
        this.called_method = "updateDeleteProject";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateLoadAllMembersForAProject(boolean success, String message, List<Member> users) {
        this.called_method = "updateLoadAllMembersForAProject";
        this.success = String.valueOf(success);
        this.message = message;
    }

    @Override
    public void updateLoadActivity(boolean success, String message, Activity activity) {
        this.called_method = "updateLoadActivity";
        this.success = String.valueOf(success);
        this.message = message;
        this.object_loaded = activity;
    }

	@Override
	public void updateLoadActivitiesForAllProjectByMember(boolean success, String message, List<Project> projects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDeleteActivity(boolean success, String message, Project project) {
		// TODO Auto-generated method stub
		
	}
}
