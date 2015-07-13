package ninefoo.view.listeners;

/**
 * Member Listener used to communicate with the Controller
 * @author Amir El Bawab
 */
public interface MemberListener {
	public void login(String username, String password);
	public void register(String firstName, String lastName, String username, String password);
	public void loadAllMembers();
	public void logout();
}
