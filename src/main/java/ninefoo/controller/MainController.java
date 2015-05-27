package ninefoo.controller;

import ninefoo.view.MainView;

public class MainController {
	
	// Create View
	private MainView view;
	
	// All controllers
	private MemberController memberController;
	
	/**
	 * Constructor
	 */
	public MainController(String appTitle) {
		this.view = new MainView(appTitle);
		view.setMemberListener(new MemberController(view));
	}
}
