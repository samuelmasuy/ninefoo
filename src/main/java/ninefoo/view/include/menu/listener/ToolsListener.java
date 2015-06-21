package ninefoo.view.include.menu.listener;

import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.dialog.EditProjectDialog;
import ninefoo.view.include.menu.dialog.ViewMyProjectsDialog;
import ninefoo.config.RoleNames;

/**
 * Listener used to communicate with Main Frame
 * @author Amir El Bawab
 */
public interface ToolsListener {
	public void newProject(CreateProjectDialog formDialog, String name, String budget, String startDate, String deadline, String description);
	public void newActivity();
	public void loadAllMyProjectsByRole(ViewMyProjectsDialog viewMyProjectsDialog, RoleNames roleName);
	public void loadProject(ViewMyProjectsDialog viewMyProjectsDialog, int projectId);
	public void logout();
	public void updateProject(ViewMyProjectsDialog parentDialog, EditProjectDialog dialog, int projectId, String name, String budget, String description);
}
