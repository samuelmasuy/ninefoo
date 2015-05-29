package ninefoo.config;

import org.apache.logging.log4j.LogManager;


public class Session {

	// Logger
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
    // Create one instance only
	private final static Session instance = new Session();
	
	// Define variables
	private int userId;
	private boolean open;
	
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
		return this.userId;
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
		this.open = true;
		LOGGER.info("Session opened");
	}
	
	/**
	 * Close session
	 */
	public void close(){
		this.open = false;
		LOGGER.info("Session closed");
	}
}