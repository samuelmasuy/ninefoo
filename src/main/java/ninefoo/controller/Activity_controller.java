package ninefoo.controller;

import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.project.TabularData_view;

public class Activity_controller extends AbstractController implements ActivityListener{

	/**
	 * Constructor
	 * @param view
	 */
	public Activity_controller(UpdatableView view) {
		super(view);
	}

	
	// TODO Put @param
	/**
	 * Create or update activity
	 * @param row
	 */
	@Override
	public void createUpdateActivity(int row, String activityId, String activityName, String start, String end, String activityCompleted) {
		
		// If ## create
		if(activityId.equals(TabularData_view.PRE_CREATED_ID)){
			// TODO Create activity
			
		// If number update
		} else {
			// TODO Update activity
		}
	}
}
