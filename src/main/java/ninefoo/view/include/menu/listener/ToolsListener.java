package ninefoo.view.include.menu.listener;

import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.dialog.EditProjectDialog;
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
	public void assignMemberToActivity();
	public void addUserToProject();
	public void viewAssignedActivitiesProject();
	public void createActivity();
	
	// Load
	public void loadAllMyProjectsByRole(ViewMyProjectsDialog viewMyProjectsDialog, String roleName);
	public void loadProject(ViewMyProjectsDialog viewMyProjectsDialog, int projectId);
	public void loadEditProjectFields(EditProjectDialog editProjectDialog, int projectId);

	// Update
	public void updateProject(ViewMyProjectsDialog parentDialog, EditProjectDialog dialog, int projectId, String name, String budget, String startDate, String deadline, String description);
	
	// Logout
	public void logout();
	
	
}
