package ninefoo.application;
import ninefoo.lib.LanguageText;
import ninefoo.view.MainView;
import ninefoo.model.Project_model;

public class Application {

	// Global Constants
	public final static String APPLICATION_PATH = "/ninefoo/";
	public final static LanguageText LANGUAGE_TEXT = new LanguageText();
	
	// Main thread
	public static void main(String[] args) throws ClassNotFoundException {

		Project_model.createDatabase();
		// Set default language to English
		LANGUAGE_TEXT.load_en();
		
		// Create the GUI
		new MainView("Application name");
	}
}
