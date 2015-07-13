package ninefoo.config;

import ninefoo.model.object.Project;

import org.apache.logging.log4j.LogManager;

/**
 * Session class follows the Singleton approach. Only one session can be create per process.
 * A session must be opened when a user login to the application, and closed on logout.
 * @author Amir EL Bawab
 */
public class Session {

	// Logger
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
    // Create one instance only
	private final static Session instance = new Session();
	
	// Define variables
	private int userId;
	private boolean open;
	private int currentProject;
	
	/**
	 * Constructor - Private
	 */
	private Session() {
		this.userId = 0;
		this.open = false;
	};
	
	/**
	 * Get instance
	 * @return Session
	 */
	public static Session getInstance(){
		return instance;
	}
	
	/**
	 * Get user id
	 * @return int
	 */
	public int getUserId(){
		return Session.instance.userId;
	}
	
	/**
	 * Set user id
	 * @param userId
	 */
	public void setUserId(int userId){
		
		// If session opened
		if(this.isOpened()){
			this.userId = userId;
			LOGGER.info("Session populated");
		
		// If session closed
		} else {
			LOGGER.info("Cannot popluate a closed session");
		}
	}
	
	/**
	 * Checks if the session is open
	 * @return boolean
	 */
	public boolean isOpened(){
		return this.open;
	}
	
	/**
	 * Open session
	 */
	public void open(){
		if(this.open == false){
			this.open = true;
			LOGGER.info("Session opened");
		}
	}
	
	/**
	 * Close session
	 */
	public void close(){
		if(this.open == true){
			this.open = false;
			this.userId = 0;
			LOGGER.info("Session closed");
		}
	}
	
	/**
	 * Set project id
	 * @param project
	 */
	public void setProjectId(int project){
		this.currentProject = project;
	}
	
	/**
	 * Get project id
	 * @return project id
	 */
	public int getProjectId(){
		return this.currentProject;
	}
}