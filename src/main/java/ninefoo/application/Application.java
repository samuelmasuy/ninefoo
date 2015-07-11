package ninefoo.application;

import ninefoo.config.Config;
import ninefoo.controller.MainController;

import org.apache.logging.log4j.LogManager;

/**
 * Application driver
 * @author Amir EL Bawab, Samuel Masuy
 */ 
public class Application {

	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	// Main thread
	public static void main(String[] args) {

		// Welcome logger
		LOGGER.info("Application started ...");

		// Run the auto load before the Controller starts
		Config.autoload();

		// Start the application
		new MainController(String.format("%s - v%s", Config.APPLICATION_NAME, Config.APPLICATION_VERSION));
	}
}