package ninefoo.view.listeners;

/**
 * Member Listener used to communicate with the Controller
 * @author Amir El Bawab
 */
public interface MemberListener {
	public void login(String username, String password);
	public void register(String firstName, String lastName, String username, String password);
	public void registerAndAssign(String firstName, String lastName, String username, String password, String role, int projectId);
	public void loadAllMembers();
	public void loadAllMembersForAProject(int projectId);
	public void logout();
}
