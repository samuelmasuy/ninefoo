package ninefoo.controller;

import ninefoo.view.frame.UpdatableView;

/**
 * Every _controller must inherit this class
 * @author Amir El Bawab
 */
public abstract class AbstractController {
	
	protected UpdatableView view;
	
	/**
	 * Constructor
	 * @param view Main view
	 */
	public AbstractController(UpdatableView view) {
		this.view = view;
	}
}
