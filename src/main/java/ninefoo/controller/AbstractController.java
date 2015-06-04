package ninefoo.controller;

import ninefoo.view.frame.UpdatableView;

/**
 * Every _controller must inherit this class
 */
public class AbstractController {
	protected UpdatableView view;
	
	/**
	 * Constructor
	 * @param view Main view
	 */
	public AbstractController(UpdatableView view) {
		this.view = view;
	}
}
