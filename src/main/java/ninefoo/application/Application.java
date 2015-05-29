package ninefoo.application;

import ninefoo.controller.MainController;
import ninefoo.lib.LanguageText;
import ninefoo.view.MainView;
import ninefoo.model.Project_model;
import org.apache.logging.log4j.LogManager;


public class Application {

    // Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
	
    // Global Constants
	public final static String APPLICATION_PATH = "/";
	public final static LanguageText LANGUAGE_TEXT = new LanguageText();
	public final static String VERSION = "1.0";
	
	// Main thread
	public static void main(String[] args) throws ClassNotFoundException {

        // Welcome logger
		LOGGER.info("Application started ...");
		
		// FIXME To be moved to controller
		Project_model.createDatabase();
		
		// Set default language to English
		LANGUAGE_TEXT.load_en();
		
		// Start the application
		new MainController(String.format("PM Expert - v%s",  VERSION));
	}
}
