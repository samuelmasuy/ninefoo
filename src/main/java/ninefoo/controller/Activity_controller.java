package ninefoo.controller;

import ninefoo.config.Database;
import ninefoo.lib.LanguageText;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.model.*;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.project.TabularData_view;

import org.apache.logging.log4j.LogManager;

import java.util.List;

/**
 * Created By Sam.
 */
public class Activity_controller extends AbstractController implements ActivityListener {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
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
	public void createUpdateActivity(int row, String activityLabel, String duration, String startDate, String finishDate, Project project, int memberId) {
		
		// Validation form
		ValidationForm validation = new ValidationForm();
		
		// TODO Put name in the language folder
		// Create rules
		ValidationRule durationRule = new ValidationRule("Duration", duration);
		ValidationRule activityNameRule = new ValidationRule("Activity name", activityLabel);
		ValidationRule startDateRule = new ValidationRule("Start date", startDate);
		ValidationRule finishDateRule = new ValidationRule("Finish date", finishDate);
		
		// Set limits
		activityNameRule.checkEmpty();
		durationRule.checkDate();
		startDateRule.checkDate();
		finishDateRule.checkDate();
		
		// If requirements are met
		if(validation.validate()){
			
			this.view.updateCreateProject(true, "Test");
		
		// If requirements are not met
		} else {
			
			// Display error
			this.view.updateCreateProject(false, validation.getError());;
		}
	}
}
