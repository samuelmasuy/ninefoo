package ninefoo.lib;

public class ValidationFeedback {
	
	// Define variables
	private boolean success = false;
	private String message;
	
	/**
	 * Constructor
	 * @param success Is successful
	 * @param message Message to be displayed.
	 */
	public ValidationFeedback(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	/**
	 * Get success
	 * @return boolean
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Get message
	 * @return
	 */
	public String getMessage() {
		return message;
	}
}
