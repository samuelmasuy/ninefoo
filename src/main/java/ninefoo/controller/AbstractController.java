package ninefoo.controller;

import ninefoo.view.MainView;

public class AbstractController {
	protected MainView view;
	
	/**
	 * Constructor
	 * @param view Main view
	 */
	public AbstractController(MainView view) {
		this.view = view;
	}
}
