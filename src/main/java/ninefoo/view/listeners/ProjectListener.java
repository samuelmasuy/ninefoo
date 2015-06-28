package ninefoo.view.listeners;

/**
 * Project Listener used to communicate with the Controller
 * @author Amir El Bawab
 */
public interface ProjectListener {
	public void createProject(String name, String budget, String startDate, String deadline, String description);
	public void loadAllProjectsByMemberAndRole(int memberId, String roleName);
	public void loadProject(int projectId);
	public void editProject(int projectId, String name, String budget, String startDate, String deadline, String description);
	public void loadEditProjectFields(int projectId);
}
