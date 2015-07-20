package ninefoo.view.include.menu.listener;

import ninefoo.model.object.Project;
import ninefoo.view.include.menu.dialog.*;

/**
 * Listener used to communicate with Main Frame
 *
 * @author Amir El Bawab
 * @author Sebouh Bardakjian
 */
public interface ToolsListener {

    // Create
    public void newProject(CreateProjectDialog formDialog, String name, String budget, String startDate, String deadline, String description);

    public void createAndAssignUserToProject(String firstName, String lastName, String username, String password, String roleName, int projectId);

    public void addUserToProject(AddUserToProjectDialog dialog, int memberId, int projectId, String role);

    // Load
    public void loadAllMyProjectsByRole(ViewMyProjectsDialog viewMyProjectsDialog, String roleName);
    public void loadProject(ViewMyProjectsDialog viewMyProjectsDialog, int projectId);
    public void loadEditProjectFields(EditProjectDialog editProjectDialog, int projectId);
    public void loadAllMembersForAddUserToProjectDialog(AddUserToProjectDialog dialog);
    public void loadAssignedActivitiesProject(ViewAssignedActivitiesDialog dialog);
    
    // Update
    public void updateProject(ViewMyProjectsDialog parentDialog, EditProjectDialog dialog, int projectId, String name, String budget, String startDate, String deadline, String description);

    //Delete
    public void deleteProject(ViewMyProjectsDialog parentDialog, Project project);

    // Logout
    public void logout();


}
