package ninefoo.controller;

import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ProjectListener;

public class Project_controller extends AbstractController implements ProjectListener{

	/**
	 * Constructor
	 * @param view
	 */
	public Project_controller(UpdatableView view) {
		super(view);
	}
	
	@Override
	public void createProject(String name, String budget, String deadline, String description) {
		
		// TODO Verify correct deadline, correct budget [if not empty], name is required, description [optional]. 
		// Date on the GUI is going to be dd/mm/yyyy
		
	}
}
