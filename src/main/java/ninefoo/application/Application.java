package ninefoo.application;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

		//Look and feel for the crossplatform 
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		// Welcome logger
		LOGGER.info("Application started ...");

		// Run the auto load before the Controller starts
		Config.autoload();

		// Start the application
		new MainController(String.format("%s - v%s", Config.APPLICATION_NAME, Config.APPLICATION_VERSION));
	}
}