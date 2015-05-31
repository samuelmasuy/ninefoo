package ninefoo.application;

import ninefoo.config.Config;
import ninefoo.controller.MainController;
import ninefoo.model.Project_model;

import org.apache.logging.log4j.LogManager;

/**
 * Application driver
 */
public class Application {

    // Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
	// Main thread
	public static void main(String[] args) throws ClassNotFoundException {

		// Welcome logger
		LOGGER.info("Application started ...");
				
		// Run the auto load before the Controller starts
		Config.autoload();
		
		// FIXME To be moved to controller
		Project_model.createDatabase();
		
		// Start the application
		new MainController(String.format("%s - v%s", Config.APPLICATION_NAME, Config.APPLICATION_VERSION));
	}
}
