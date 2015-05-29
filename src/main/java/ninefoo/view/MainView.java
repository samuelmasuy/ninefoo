package ninefoo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.config.Session;
import ninefoo.view.include.footer.StatusBar;
import ninefoo.view.include.menu.Builder;
import ninefoo.view.include.menu.Menu;
import ninefoo.view.include.menu.Tools;
import ninefoo.view.listeners.MemberListener;
import ninefoo.view.member.Login_view;
import ninefoo.view.member.Register_view;
import ninefoo.view.member.listeners.LoginListener;
import ninefoo.view.member.listeners.RegisterListener;
import ninefoo.view.project.TabularData_view;
import org.apache.logging.log4j.LogManager;

public class MainView extends JFrame{

    // Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
    // Define menu
	private Menu menu;
	
	// Define panels
	private Tools toolsPanel;
	private StatusBar statusBarPanel;
	private Builder builderPanel;
	private TabularData_view tabularDataPanel;
	private Login_view loginPanel;
	private Register_view registerPanel;
	
	// Define variables
	private JPanel currentCenterPanel;
	
	// Define listeners
	private MemberListener memberListener;
	
	public MainView(String AppTitle) {
		
		// Call super constructor
		super(AppTitle);
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize menu
		this.menu = new Menu();
		
		// Initialize panels
		this.toolsPanel = new Tools();
		this.statusBarPanel = new StatusBar();
		this.builderPanel = new Builder();
		this.tabularDataPanel = new TabularData_view();
		this.loginPanel = new Login_view();
		this.registerPanel = new Register_view();
		
		// Add panels
		this.add(this.toolsPanel, BorderLayout.NORTH);
		this.add(this.statusBarPanel, BorderLayout.SOUTH);
		this.add(this.builderPanel, BorderLayout.WEST);
		
		// Add menu
		this.setJMenuBar(menu);
		
		// By default, load login view
		this.loadView(loginPanel);
		
		// Add listener to login panel
		this.loginPanel.setLoginListener(new LoginListener() {
			
			/**
			 * Switch to register panel
			 */
			@Override
			public void registerLink() {
				MainView.this.registerPanel.reset();
				MainView.this.loadView(registerPanel);
			}
			
			/**
			 * Try to login
			 * @param username
			 * @param password
			 */
			@Override
			public void login(String username, String password) {
				LOGGER.info(String.format("Login info= [%s : %s]", username, password));
				
				// Pass info to controller
				if(memberListener != null)
					memberListener.login(username, password);
			}
		});
		
		// Add listener to register panel
		registerPanel.setRegisterListener(new RegisterListener() {
			
			/**
			 * Try to register
			 * @param firstName
			 * @param lastName
			 * @param username
			 * @param password
			 */
			@Override
			public void register(String firstName, String lastName, String username, String password) {
				
				// Logger info
				LOGGER.info(String.format("Registration info= [%s : %s : %s : %s]", firstName, lastName, username, password));
				
				// Pass info to controller
				if(memberListener != null)
					memberListener.register(firstName, lastName, username, password);
			}
			
			/**
			 * Switch to login panel
			 */
			@Override
			public void loginLink() {
				MainView.this.loginPanel.reset();
				MainView.this.loadView(loginPanel);
			}
		});
		
		// Configure the JFrame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		// Exit when click on X
		this.setPreferredSize(new Dimension(1000, 800)); 	// Frame initial size
		this.setMinimumSize(new Dimension(500, 500));		// Minimum window size
		this.setVisible(true); 								// Make the frame visible
		this.pack();										// Force setting the size of components
		this.setLocationRelativeTo(null); 					// Load on center of the screen
	}
	
	/**
	 * Load view to the center panel
	 * @param panel
	 */
	private void loadView(JPanel panel){
		
		// If current center panel is not set, set it
		if(this.currentCenterPanel == null)
			this.currentCenterPanel = panel;
			
		// If user is logged in
		if(Session.getInstance().isOpened()){
			this.toolsPanel.setVisible(true);
			this.statusBarPanel.setVisible(true);
			this.builderPanel.setVisible(true);
			this.menu.enableMenu();
			
		// If not logged in
		} else {
			this.toolsPanel.setVisible(false);
			this.statusBarPanel.setVisible(false);
			this.builderPanel.setVisible(false);
			this.menu.disableMenu();
		}
		
		// Update current center panel
		this.currentCenterPanel.setVisible(false);
		this.currentCenterPanel = panel;
		this.add(currentCenterPanel, BorderLayout.CENTER);
		this.currentCenterPanel.setVisible(true);
		this.repaint();
	}
	
	/**
	 * Set member listener
	 * @param memberListener
	 */
	public void setMemberListener(MemberListener memberListener){
		this.memberListener = memberListener;
	};
	
	/**
	 * Try login (Information sent from Controller)
	 * @param success
	 * @param message
	 */
	public void tryLogin(boolean success, String message){
		
		// If logged is successful
		if(success){
			
			// Change view
			MainView.this.loadView(tabularDataPanel);
			LOGGER.info("Login successful");
		
		// If not logged in, display error
		} else {
			loginPanel.setErrorMessage(message);
			LOGGER.error(message);
		}
	}
	
	/**
	 * Try register (Information sent from controller)
	 * @param success
	 * @param message
	 */
	public void tryRegister(boolean success, String message){
		
		// If register is successful
		if(success){
			
			// Load login view
			MainView.this.loginPanel.reset();
			MainView.this.loadView(loginPanel);
			MainView.this.loginPanel.setSuccessMessage(message);
			LOGGER.info(message);
		
		// If error
		} else {
			
			// Display error
			MainView.this.registerPanel.setErrorMessage(message);
			LOGGER.info(message);
		}
	}
}
