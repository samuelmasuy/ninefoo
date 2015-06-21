package ninefoo.view.member.listeners;

/**
 * Login Listener used to communicate with the Main Frame
 * @author Amir El Bawab
 */
public interface LoginListener {
	public void login(String username, String password);
	public void registerLink();
}
