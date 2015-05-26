package ninefoo.config;

import ninefoo.helper.Console;

public class Session {

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
			Console.log("Session populated");
		
		// If session closed
		} else {
			Console.log("Cannot popluate a closed session");
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
		Console.log("Session opened");
	}
	
	/**
	 * Close session
	 */
	public void close(){
		this.open = false;
		Console.log("Session closed");
	}
}