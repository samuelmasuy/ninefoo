package ninefoo.view.include.menu.listener;

import ninefoo.model.object.Project;
import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.dialog.EditProjectDialog;
import ninefoo.view.include.menu.dialog.ViewAssignedActivitiesDialog;
import ninefoo.view.include.menu.dialog.ViewMyProjectsDialog;

/**
 * Listener used to communicate with Main Frame
 * @author Amir El Bawab
 * @author Sebouh Bardakjian
 */
public interface ToolsListener {
	
	// Create
	public void newProject(CreateProjectDialog formDialog, String name, String budget, String startDate, String deadline, String description);
	public void createUser();
	public void addUserToProject();
	public void createActivity();
	
	// Load
	public void loadAllMyProjectsByRole(ViewMyProjectsDialog viewMyProjectsDialog, String roleName);
	public void loadProject(ViewMyProjectsDialog viewMyProjectsDialog, int projectId);
	public void loadEditProjectFields(EditProjectDialog editProjectDialog, int projectId);
	public void loadAssignedActivitiesProject(ViewAssignedActivitiesDialog dialog);
	
	// Update
	public void updateProject(ViewMyProjectsDialog parentDialog, EditProjectDialog dialog, int projectId, String name, String budget, String startDate, String deadline, String description);
	
	//Delete
	public void deleteProject(ViewMyProjectsDialog parentDialog, Project project);
	
	// Logout
	public void logout();
	
	
}
