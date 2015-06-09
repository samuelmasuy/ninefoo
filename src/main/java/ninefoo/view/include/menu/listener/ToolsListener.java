package ninefoo.view.include.menu.listener;

import java.util.List;

import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.dialog.EditProjectDialog;
import ninefoo.view.include.menu.dialog.ViewMyProjectsDialog;
import ninefoo.config.RoleNames;
import ninefoo.model.Project;;

public interface ToolsListener {
	public void newProject(CreateProjectDialog formDialog, String name, String budget, String startDate, String deadline, String description);
	public void newActivity();
	public List<Project> getAllMyProjectsByRole(ViewMyProjectsDialog viewMyProjectsDialog, RoleNames roleName);
	public void loadProject(ViewMyProjectsDialog viewMyProjectsDialog, int projectId);
	public void logout();
	public void updateProject(ViewMyProjectsDialog parentDialog, EditProjectDialog dialog, int projectId, String name, String budget, String description);
}
