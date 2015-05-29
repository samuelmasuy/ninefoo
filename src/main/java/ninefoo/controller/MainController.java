package ninefoo.controller;

import ninefoo.view.MainView;

public class MainController {
	
	// Create View
	private MainView view;
	
	/**
	 * Constructor
	 */
	public MainController(String appTitle) {
		
		// Create view (GUI)
		this.view = new MainView(appTitle);
		
		// Set GUI Listener
		view.setMemberListener(new Member_controller(view));
	}
}
