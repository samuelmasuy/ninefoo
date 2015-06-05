package ninefoo.view.listeners;

public interface MemberListener {
	public void login(String username, String password);
	public void register(String firstName, String lastName, String username, String password);
	public void logout();
}
