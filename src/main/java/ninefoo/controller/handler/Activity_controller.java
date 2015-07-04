package ninefoo.controller.handler;

import ninefoo.controller.handler.template.AbstractController;
import ninefoo.lib.validationForm.ValidationForm;
import ninefoo.model.object.Project;
import ninefoo.model.sql.Activity_model;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.Project_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ActivityListener;

/**
 * Controller for 'Activity': Create, Update and Delete activities
 * @author Samuel Masuy
 * @see AbstractController, ActivityListener
 */
public class Activity_controller extends AbstractController implements ActivityListener {

    // Load model
    private Activity_model activity_model = new Activity_model();
    private Project_model project_model = new Project_model();
    private Member_model member_model = new Member_model();

    /**
     * Constructor
     *
     * @param view
     */
    public Activity_controller(UpdatableView view) {
        super(view);
    }

	@Override
	public void createActivity(int row, String activityId, String activityLabel, String duration, String startDate, String finishDate, Project project, String completion, int memberId) {
		ValidationForm validation = new ValidationForm();
		
		//check if empty
		
		
	}

	@Override
	public void createDependentActivities(int activityIdDependent, int activityIdDependentOn, int row) {
		
	}
}
