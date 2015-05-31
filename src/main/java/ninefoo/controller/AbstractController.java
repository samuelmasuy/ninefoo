package ninefoo.controller;

import ninefoo.view.MainView;

/**
 * Every _controller must inherit this class
 */
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
