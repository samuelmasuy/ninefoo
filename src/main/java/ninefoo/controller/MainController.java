package ninefoo.controller;

import org.apache.logging.log4j.LogManager;

import ninefoo.view.frame.MainView;
import ninefoo.view.frame.UpdatableView;

/**
 * Main controller where the _controller classes get connected to the GUI
 */
public class MainController {
	
	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
		
	// Create View
	private UpdatableView view;
	
	/**
	 * Constructor
	 */
	public MainController(String appTitle) {
		
		// GUI started
		LOGGER.info("GUI launched ...");
		
		// Create view (GUI)
		this.view = new MainView(appTitle);
		
		// Set GUI Listener
		view.setMemberListener(new Member_controller(view));
	}
}
