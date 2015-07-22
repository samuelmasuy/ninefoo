package ninefoo.view.listeners;

import ninefoo.model.object.Project;

/**
 * Project Listener used to communicate with the Controller
 *
 * @author Amir El Bawab
 */
public interface ProjectListener {

    // Create
    public void createProject(String name, String budget, String startDate, String deadline, String description);

    public void addUserToProject(int memberId, int projectId, String role);

    // Update
    public void editProject(int projectId, String name, String budget, String startDate, String deadline, String description);

    // Load
    public void loadEditProjectFields(int projectId);
    public void loadAllProjectsByMemberAndRole(int memberId, String roleName);
    public void loadProject(int projectId);

    // Delete
    public void deleteProject(Project project);
    public void removeMemberFromProject(int memberId, int projectId);;
}
