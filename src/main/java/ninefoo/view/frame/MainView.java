package ninefoo.view.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.config.Session;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;
import ninefoo.view.include.footer.StatusBar;
import ninefoo.view.include.menu.Menu;
import ninefoo.view.include.menu.Tools;
import ninefoo.view.include.menu.dialog.CreateProjectDialog;
import ninefoo.view.include.menu.dialog.EditProjectDialog;
import ninefoo.view.include.menu.dialog.ViewAssignedActivitiesDialog;
import ninefoo.view.include.menu.dialog.ViewMyProjectsDialog;
import ninefoo.view.include.menu.listener.ToolsListener;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.listeners.MemberListener;
import ninefoo.view.listeners.ProjectListener;
import ninefoo.view.member.Login_view;
import ninefoo.view.member.Register_view;
import ninefoo.view.member.listeners.LoginListener;
import ninefoo.view.member.listeners.RegisterListener;
import ninefoo.view.project.TableChartSlider_view;

import org.apache.logging.log4j.LogManager;

/**
 * Main frame for the GUI
 * @author Amir El Bawab
 * @author Sebouh Bardakjian
 */
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
	private ViewMyProjectsDialog viewMyProjectsDialog;
	private EditProjectDialog editProjectDialog;
	private ViewAssignedActivitiesDialog viewAssignedActivitiesDialog;
	
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
		Session.getInstance().open();
		Session.getInstance().setUserId(1);
		this.loadView(tableChartPanel);
//		this.loadView(loginPanel);
		
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
			public void logout() {
				
				// Logout
				if(memberListener != null)
					memberListener.logout();
			}

			@Override
			public void loadAllMyProjectsByRole(ViewMyProjectsDialog dialog, String roleName) {

				LOGGER.info(String.format("Retreiving projects from the DB for user id %d and role %s ", Session.getInstance().getUserId(), roleName.toString()));
				
				// Notify to controller
				if(projectListener != null){
					
					// Set dialog
					viewMyProjectsDialog = dialog;
					
					// Load projects
					projectListener.loadAllProjectsByMemberAndRole(Session.getInstance().getUserId(), roleName);
				}
			}

			@Override
			public void loadProject(ViewMyProjectsDialog dialog, int projectId) {
				
				// Store view my projects dialog
				viewMyProjectsDialog = dialog;
				
				// Notify controller
				projectListener.loadProject(projectId);
			}

			@Override
			public void updateProject(ViewMyProjectsDialog parentDialog, EditProjectDialog dialog, int projectId, String name, String budget, String startDate, String deadline, String description) {
				
				// Store dialog
				viewMyProjectsDialog = parentDialog;
				editProjectDialog = dialog;
				
				// If not null
				if(projectListener != null)
					projectListener.editProject(projectId, name, budget, startDate, deadline, description);
			}

			@Override
			public void loadEditProjectFields(EditProjectDialog dialog, int projectId) {
				
				// Store dialog
				editProjectDialog = dialog;
				
				// Pass it to controller
				if(projectListener != null)
					projectListener.loadEditProjectFields(projectId);
			}

			@Override
			public void createUser() {
				// TODO createUser
				System.out.println("Create user clicked...");
			}

			@Override
			public void addUserToProject() {
				// TODO addUserToProject
				System.out.println("Added user to project clicked...");
			}

			@Override
			public void loadAssignedActivitiesProject(ViewAssignedActivitiesDialog dialog) {
				
				// Set dialog
				viewAssignedActivitiesDialog = dialog;
				
				// Pass it to controller
				if(projectListener != null)
					projectListener.loadAssignedActivitiesProject();
			}

			@Override
			public void createActivity() {
				// TODO createActivity
				System.out.println("Create new activity clicked...");
			}

			@Override
			public void deleteProject(ViewMyProjectsDialog parentDialog, Project project) {
				// TODO Auto-generated method stub
				System.out.println("Delete activity confirmed...!");
			}

			@Override
			public void updateActivity() {
				// TODO Auto-generated method stub
				System.out.println("Edit activity clicked...");
				
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
	
	/************************************************************
	*                                                           *
	*     					MEMBER                              *
	*                                                           *
	*************************************************************/
	
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
	
	@Override
	public void updateLogout() {
		this.loginPanel.reset();
		this.loadView(loginPanel);
		this.tableChartPanel.reset();
		this.toolsPanel.setNewActivityEnabled(false);
		this.tableChartPanel.setProject(null);
		LOGGER.info("Logout successful");
	}

	/************************************************************
	*                                                           *
	*     					PROJECT                             *
	*                                                           *
	*************************************************************/
	
	/**
	 * Try to create the project
	 * @param success
	 * @param message
	 */
	@Override
	public void updateCreateProject(boolean success, String message, Project project){
		
		// If dialog exists
		if(createProjectDialog != null){
			
			// If project created
			if(success){
				
				LOGGER.info(message);
				
				// Display success message
				createProjectDialog.setSuccessMessage(message);
				
				// Close dialog
				createProjectDialog.dispose();
				
				// Load project
				this.tableChartPanel.loadProject(project);
				
				// Enable creating activities
				this.toolsPanel.setNewActivityEnabled(true);
			
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
	public void updateLoadProject(boolean success, String message, Project project) {
		
		// If dialog exists
		if(this.viewMyProjectsDialog != null){
			
			// If success
			if(success){
				
				LOGGER.info(message);
				
				// Show success message
				this.viewMyProjectsDialog.setSuccessMessage(message);
				
				// Close window
				this.viewMyProjectsDialog.dispose();
				
				// Enable new activity button
				this.toolsPanel.setNewActivityEnabled(true);
				
				// Reset data
				this.tableChartPanel.reset();
				
				// Load project
				this.tableChartPanel.loadProject(project);
			} else {
				
				LOGGER.error(message);
				
				// Show error message
				this.viewMyProjectsDialog.setErrorMessage(message);
			}
			
			// Reset pointer so it cannot be used anywhere else
			this.viewMyProjectsDialog = null;
		}
	}

	@Override
	public void updateEditProject(boolean success, String message) {
		
		// If successful
		if(success){
			
			// Set success message
			this.editProjectDialog.setSuccessMessage(message);
			
			// Update project
			this.viewMyProjectsDialog.reloadProjectListByRole();
			
			// Close window
			this.editProjectDialog.dispose();
			
			// Clear values
			this.viewMyProjectsDialog = null;
			this.editProjectDialog = null;
			
		// If error
		}else{
			
			// Display error
			this.editProjectDialog.setErrorMessage(message);
		}
	}

	@Override
	public void updateLoadAllProjectsByMemberAndRole(List<Project> projects) {
		
		// Update parent list
		this.viewMyProjectsDialog.populateProjectList(projects);
		
		// Reset pointer
		this.viewMyProjectsDialog = null;
	}

	@Override
	public void updateLoadEditProjectFields(boolean success, String message, Project project) {
		
		// If success
		if(success) {
			
			// Populate fields
			this.editProjectDialog.populateFields(project);
			
		// If error occurred
		} else {
			
			// Display error message
			this.editProjectDialog.setErrorMessage(message);
		}
		
		// Reset dialog
		this.editProjectDialog = null;
	}
	
	@Override
	public void updateLoadAssignedActivitiesProject(boolean success, String message, List<Project> projects) {
		
		// If load successful
		if(success) {
			
			// Set projects
			this.viewAssignedActivitiesDialog.setProjects(projects);
			
		} else {
			
			// Display error
			this.viewAssignedActivitiesDialog.setErrorMessage(message);
			
			// Close dialog
			this.viewAssignedActivitiesDialog.dispose();
			
			// Reset pointer
			this.viewAssignedActivitiesDialog = null;
		}
	}
	
	/************************************************************
	*                                                           *
	*     					ACITIVITY                           *
	*                                                           *
	*************************************************************/
	
	@Override
	public void updateCreateActivity(boolean success, String message, Project project) {
		
		// TODO updateCreateActivity
	}
	
	@Override
	public void updateCreateDependentActivities(boolean success, String message, int row, Activity activity) {
		// TODO updateCreateDependentActivities
	}

	@Override
	public void updateEditActivity(boolean success, String message, Project project) {
		// TODO Auto-generated method stub
		
	}
}
