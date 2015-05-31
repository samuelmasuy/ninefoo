package ninefoo.application;

import ninefoo.config.Config;
import ninefoo.controller.MainController;
import ninefoo.model.Project_model;

import org.apache.logging.log4j.LogManager;

public class Application {

    // Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
    // Global Constants
	public final static String APPLICATION_PATH = "ninefoo";
	public final static String VERSION = "1.0";
	
	// Main thread
	public static void main(String[] args) throws ClassNotFoundException {

		// Welcome logger
		LOGGER.info("Application started ...");
				
		// Run the auto load before the Controller starts
		Config.autoload();
		
		// FIXME To be moved to controller
		Project_model.createDatabase();
		
		// Start the application
		new MainController(String.format("PM Expert - v%s",  VERSION));
	}
}
