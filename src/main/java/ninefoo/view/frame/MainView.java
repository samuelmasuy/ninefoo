package ninefoo.view.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.config.Session;
import ninefoo.model.Activity;
import ninefoo.view.include.footer.StatusBar;
import ninefoo.view.include.menu.Menu;
import ninefoo.view.include.menu.Tools;
import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.listener.ToolsListener;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.listeners.MemberListener;
import ninefoo.view.listeners.ProjectListener;
import ninefoo.view.member.Login_view;
import ninefoo.view.member.Register_view;
import ninefoo.view.member.listeners.LoginListener;
import ninefoo.view.member.listeners.RegisterListener;
import ninefoo.view.project.TableChartSlider_view;
import ninefoo.view.project.listener.TabularDataListener;

import org.apache.logging.log4j.LogManager;

public class MainView extends JFrame implements UpdatableView{

	private static final long serialVersionUID = 4320359862680051619L;

	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
    // Define menu
	private Menu menu;
	
	// Define panels
	private Tools toolsPanel;
	private StatusBar statusBarPanel;
	private TableChartSlider_view tableChartPanel;
	private Login_view loginPanel;
	private Register_view registerPanel;
	
	// Define dialogs
	private CreateProjectDialog createProjectDialog;
	
	// Define variables
	private JPanel currentCenterPanel;
	
	// Define listeners
	private MemberListener memberListener;
	private ProjectListener projectListener;
	private ActivityListener activityListener;
	
	public MainView(String AppTitle) {
		
		// Call super constructor
		super(AppTitle);
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize menu
		this.menu = new Menu();
		
		// Initialize panels
		this.toolsPanel = new Tools(this);
		this.statusBarPanel = new StatusBar();
		this.tableChartPanel = new TableChartSlider_view();
		this.loginPanel = new Login_view();
		this.registerPanel = new Register_view();
		
		// Add panels
		this.add(this.toolsPanel, BorderLayout.NORTH);
		this.add(this.statusBarPanel, BorderLayout.SOUTH);
		
		// Add menu
		this.setJMenuBar(menu);
		
		// By default, load login view
//		Session.getInstance().open();
//		this.loadView(tableChartPanel);
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
		this.registerPanel.setRegisterListener(new RegisterListener() {
			
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
		
		// Add listener to tools panel
		this.toolsPanel.setToolsListener(new ToolsListener() {
			
			@Override
			public void newProject(CreateProjectDialog dialog, String name, String budget, String startDate, String deadline, String description) {
				LOGGER.info(String.format("Project '%s' has been been submitted!", name));
				
				// If listener has been set
				if(projectListener != null){
					
					// Store dialog
					createProjectDialog = dialog;
					
					// Pass to controller
					projectListener.createProject(name, budget, startDate, deadline, description);
				}
			}

			@Override
			public void newActivity() {
				LOGGER.info("Activity added");
				
				// Add empty row
				tableChartPanel.addEmptyRow();
			}

			@Override
			public void logout() {
				
				// Logout
				if(memberListener != null)
					memberListener.logout();
			}
		});
		
		// Add listener to table
		this.tableChartPanel.setTabularDataListener(new TabularDataListener() {
			
			@Override
			public void tableUpdated(int row, String activityId, String activityName, String start, String end, String activityCompleted) {
				
				// Create or update listener
				if(activityListener != null)
					activityListener.createUpdateActivity(row, activityId, activityName, start, end, activityCompleted);
				
				LOGGER.info(String.format("Table updated at row %d: %s, %s, %s, %s, %s", row, activityId, activityName, start, end, activityCompleted));
			}
		});
		
		// Configure the JFrame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		// Exit when click on X
		this.setPreferredSize(new Dimension(1300, 700)); 	// Frame initial size
		this.setMinimumSize(new Dimension(600, 600));		// Minimum window size
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
			this.menu.enableMenu();
			
		// If not logged in
		} else {
			this.toolsPanel.setVisible(false);
			this.statusBarPanel.setVisible(false);
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
	@Override
	public void setMemberListener(MemberListener memberListener){
		this.memberListener = memberListener;
	};
	
	/**
	 * Set project listener
	 * @param memberListener
	 */
	@Override
	public void setProjectListener(ProjectListener projectListener){
		this.projectListener = projectListener;
	};
	
	/**
	 * Set activity listener
	 * @param activityListener
	 */
	@Override
	public void setActivityListener(ActivityListener activityListener) {
		this.activityListener = activityListener;
	}
	
	/**
	 * Try login (Information sent from Controller)
	 * @param success
	 * @param message
	 */
	@Override
	public void updateLogin(boolean success, String message){
		
		// If logged is successful
		if(success){
			
			// Change view
			MainView.this.loadView(tableChartPanel);
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
	@Override
	public void updateRegister(boolean success, String message){
		
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
	
	/**
	 * Try to create the project
	 * @param success
	 * @param message
	 */
	@Override
	public void updateCreateProject(boolean success, String message){
		
		// If dialog exists
		if(createProjectDialog != null){
			
			// If project created
			if(success){
				
				LOGGER.info(message);
				
				// Display success message
				createProjectDialog.setSuccessMessage(message);
				
				// Close dialog
				createProjectDialog.dispose();
			
			// If project not created
			} else {
				
				LOGGER.error(message);
				
				// Display message
				createProjectDialog.setErrorMessage(message);
			}
			
			// Reset dialog pointer so that it cannot be used anywhere else
			createProjectDialog = null;
		}
	}

	@Override
	public void updateCreateUpdateProject(int row, Activity activity, boolean success, String message) {
		// TODO To be completed
	}
}