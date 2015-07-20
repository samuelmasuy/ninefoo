package ninefoo.view.member.listeners;

/**
 * Registration Listener used to communicate with the Main Frame
 *
 * @author Amir El Bawab
 */
public interface RegisterListener {
    public void register(String firstName, String lastname, String username, String password);

    public void loginLink();
}
