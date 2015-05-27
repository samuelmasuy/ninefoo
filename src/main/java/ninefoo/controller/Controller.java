package ninefoo.controller;

import ninefoo.view.MainView;

public class Controller {
	protected MainView view;
	
	/**
	 * Constructor
	 * @param view Main view
	 */
	public Controller(MainView view) {
		this.view = view;
	}
}
