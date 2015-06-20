package ninefoo.view.listeners;

import ninefoo.config.RoleNames;

public interface ProjectListener {
	public void createProject(String name, String budget, String startDate, String deadline, String description);
	public void loadAllProjectsByMemberAndRole(int memberId, RoleNames roleName);
	public void loadProject(int projectId);
	public void editProject(int projectId, String name, String budget, String description);
}
