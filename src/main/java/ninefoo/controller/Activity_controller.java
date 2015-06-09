package ninefoo.controller;

import ninefoo.lib.LanguageText;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.model.*;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ActivityListener;
import ninefoo.view.project.TabularData_view;

import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
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
	public void createUpdateActivity(int row, String activityId, String activityLabel, String duration, String startDate, String finishDate, Project project, String completion, int memberId) {
		
		// If activity has just been added, don't do anything
		if(activityId.equals(TabularData_view.PRE_CREATED_ID) && activityLabel.isEmpty() && duration.isEmpty() && startDate.isEmpty() && finishDate.isEmpty())
			return;
		
		// Validation form
		ValidationForm validation = new ValidationForm();
		
		// TODO Put name in the language folder
		// Create rules
		ValidationRule durationRule = new ValidationRule("Duration", duration);
		ValidationRule activityNameRule = new ValidationRule("Activity name", activityLabel);
		ValidationRule startDateRule = new ValidationRule("Start date", startDate);
		ValidationRule finishDateRule = new ValidationRule("Finish date", finishDate);
		ValidationRule completionRule = new ValidationRule("Activity completed", completion);
		
		// Set limits
		activityNameRule.checkEmpty().checkFormat("[a-zA-Z0-9 ]+");
		durationRule.checkFormat("[0-9]*");
		startDateRule.checkDate();
		finishDateRule.checkDate();
		completionRule.checkFormat("[0-9]*");
		
		// Set rules
		validation.setRule(activityNameRule);
		validation.setRule(durationRule);
		validation.setRule(startDateRule);
		validation.setRule(finishDateRule);
		validation.setRule(completionRule);
		
		// TODO When there's an error, don't apply the data entered
		// If requirements are met
		if(validation.validate()){
			
			// Get current member
			Member member = this.member_model.getMemberById(memberId);
			
			// Target activity
			Activity affectedActivity = null;
			
			// Adjust values
			int intDuration = duration.isEmpty() ? 0 : Integer.parseInt(duration);
			
			// Create temporary instance
			Activity activity = new Activity(activityLabel, intDuration, startDate, finishDate, project, member);
			
			// If new activity
			if(activityId.equals(TabularData_view.PRE_CREATED_ID)){
				
				// Insert it into the DB
				int actId = this.activity_model.insertNewActivity(activity);
				
				// Fetch the insert activity
				Activity insertedActivity = this.activity_model.getActivityById(actId);
				
				// Set it as the affected one
				affectedActivity = insertedActivity;
				
			// If activity already exist
			} else {

				// Update activity
				this.activity_model.updateActivity(activity);
				
				// Fetch the updated activity
				Activity updatedActivity = this.activity_model.getActivityById(Integer.parseInt(activityId));
				
				// Load its prerequisites
				updatedActivity.setPrerequisites(this.activity_model.getActivityPrerequisites(updatedActivity));
				
				// Set it as the affected one
				affectedActivity = updatedActivity;
			}
			
			// Refresh project 
			Project refreshedProject = this.project_model.getProjectById(project.getProjectId());
			
			// Load prerequisite for each activity
			List<Activity> actList = this.activity_model.getActivitiesByProjectId(project.getProjectId());
			for(Activity act : actList){
				
				// Set prerequisites
				act.setPrerequisites(this.activity_model.getActivityPrerequisites(act));
			}
			
			// Set activities for the project
			refreshedProject.setAcitivies(actList);
			
			// Update view
			this.view.updateCreateUpdateActivity(true, null, row, affectedActivity, refreshedProject);
		
		// If requirements are not met
		} else {
			
			// Target activity
			Activity affectedActivity = null;
			
			// If new activity
			if(activityId.equals(TabularData_view.PRE_CREATED_ID)){
				
				// Create an empty activity
				affectedActivity = new Activity(null, 0, null, null, null, null);
				
			// If not a new activity, return old info
			} else {
				
				// Fetch the old activity
				Activity oldActivity = this.activity_model.getActivityById(Integer.parseInt(activityId));
				
				// Set it as the affected one
				affectedActivity = oldActivity;
			}
			
			// Refresh project 
			Project refreshedProject = this.project_model.getProjectById(project.getProjectId());
			
			// Load prerequisite for each activity
			List<Activity> actList = this.activity_model.getActivitiesByProjectId(project.getProjectId());
			for(Activity act : actList){
				
				// Set prerequisites
				act.setPrerequisites(this.activity_model.getActivityPrerequisites(act));
			}
			
			// Set activities for the project
			refreshedProject.setAcitivies(actList);		
			
			// Display error
			this.view.updateCreateUpdateActivity(false, validation.getError(), row, affectedActivity, refreshedProject);
		}
	}

	@Override
	public void createDependentActivities(int activityIdDependent, int activityIdDependentOn, int row) {
		
		// Fetch activities
		Activity activityDependentOn = this.activity_model.getActivityById(activityIdDependentOn);
		Activity activityDependent = this.activity_model.getActivityById(activityIdDependent);
					
		// Check if dependency already exist
		List<Activity> preList = this.activity_model.getActivityPrerequisites(activityDependent);
		for(Activity activity : preList){
			if(activity.getActivityId() == activityIdDependentOn){

				// Update view
				this.view.updateCreateDependentActivities(false, "Dependency already exists", row, null);

				// Exit
				return;
			}
		}
		
		// Insert activity
		if(this.activity_model.insertActivityPrerequisites(activityIdDependent, activityDependentOn)) {
			
			// Reload dependency
			preList = this.activity_model.getActivityPrerequisites(activityDependent);
			
			// Set prerequisites
			activityDependent.setPrerequisites(preList);
			
			// Update view
			this.view.updateCreateDependentActivities(true, "Dependency created succesfully", row, activityDependent);
		
		} else {
		
			// Update view
			this.view.updateCreateDependentActivities(false, LanguageText.getConstant("ERROR_OCCURED"), row, null);
		}
	}
}
