package ninefoo.view.listeners;

import java.util.List;

import ninefoo.config.RoleNames;
import ninefoo.model.Project;

public interface ProjectListener {
	public void createProject(String name, String budget, String startDate, String deadline, String description);
	public List<Project> getAllProjectsByMemberAndRole(int memberId, RoleNames roleName);
	public void loadProject(int projectId);
}
