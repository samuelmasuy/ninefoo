package ninefoo.application;
import ninefoo.controller.MainController;
import ninefoo.lib.LanguageText;
import ninefoo.view.MainView;

public class Application {
	
	// Global Constants
	public final static String APPLICATION_PATH = "/";
	public final static LanguageText LANGUAGE_TEXT = new LanguageText();
	public final static String VERSION = "1.0";
	
	// Main thread
	public static void main(String[] args) {
		
		// Set default language to English
		LANGUAGE_TEXT.load_en();
		
		// Start the application
		new MainController(String.format("PM Expert - v%s",  VERSION));
	}
}
