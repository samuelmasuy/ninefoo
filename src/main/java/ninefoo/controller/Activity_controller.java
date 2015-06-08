package ninefoo.controller;

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
				
				// Set it as the affected one
				affectedActivity = updatedActivity;
			}
			
			// Update view
			this.view.updateCreateUpdateActivity(true, null, row, affectedActivity);
		
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
			
			// Display error
			this.view.updateCreateUpdateActivity(false, validation.getError(), row, affectedActivity);
		}
		
	}

    /**
     * Create or update activity.
     *
     * @param row                 row number.
     * @param activityId          Id of the activity.
     * @param name                Name of the activity
     * @param description         Description of the activity.
     * @param duration            Duration of the activity.
     * @param optimisticDuration  Start of the activity.
     * @param likelyDuration      End of the activity.
     * @param pessimisticDuration End of the activity.
     * @param projectID           project which has this activity.
     * @param memberID            member of this activity.
     * @param prerequisites       list of activities ID.
     */
//    @Override
//    public void createUpdateActivity(int row, final String activityId, String name, String description, String duration,
//                                     String optimisticDuration, String likelyDuration, String pessimisticDuration,
//                                     int projectID, int memberID, final List<Integer> prerequisites) {
//
//        // Flag to know if we are creating an activity or updating one.
//        boolean createActivity = false;
//
//        final ValidationForm validation = new ValidationForm();
//
//        // If ## create Activity
//        if (activityId.equals(TabularData_view.PRE_CREATED_ID)) {
//            createActivity = true;
//        }
//        // Update an Activity
//        else {
//            ValidationRule activityIDRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_ID"), activityId) {
//                @Override
//                public boolean validate() {
//
//                    // Keep parent validation
//                    boolean validate = super.validate();
//                    // check if activity is in database
//                    if (activity_model.getActivityById(Integer.parseInt(activityId)) == null) {
//                        setErrorMessage(String.format(LanguageText.getConstant("ACTIVITY_NOT_FOUND")));
//                        LOGGER.info("Activity does not exist.");
//                        return false;
//                    }
//                    // Return parent validation
//                    return validate;
//                }
//            };
//            validation.setRule(activityIDRule);
//            createActivity = false;
//        }
//
//        if (!name.isEmpty()) {
//            ValidationRule activityNameRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_NAME"), name);
//            activityNameRule.checkFormat("[a-zA-Z0-9]+").checkMaxLength(30);
//            validation.setRule(activityNameRule);
//        } else if (!description.isEmpty()) {
//            ValidationRule activityDescriptionRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_DESCRIPTION"), name);
//            activityDescriptionRule.checkFormat("[a-zA-Z0-9]+").checkMaxLength(120);
//            validation.setRule(activityDescriptionRule);
//        } else if (!duration.isEmpty()) {
//            ValidationRule activityDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_DURATION"), duration);
//            activityDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
//            validation.setRule(activityDurationRule);
//        } else if (!optimisticDuration.isEmpty()) {
//            ValidationRule activityOptimisticDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_OPTIMISTIC_DURATION"), optimisticDuration);
//            activityOptimisticDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
//            validation.setRule(activityOptimisticDurationRule);
//        } else if (!likelyDuration.isEmpty()) {
//            ValidationRule activityLikelyDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LIKELY_DURATION"), likelyDuration);
//            activityLikelyDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
//            validation.setRule(activityLikelyDurationRule);
//        } else if (!pessimisticDuration.isEmpty()) {
//            ValidationRule activityPessimisticDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_PESSIMISTIC_DURATION"), pessimisticDuration);
//            activityPessimisticDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
//            validation.setRule(activityPessimisticDurationRule);
//        } else if (!prerequisites.isEmpty()) {
//            // TODO what to pass as second argument?
//            ValidationRule activityPrerequesitesRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_PREREQUISITES"), "prerequesites") {
//                @Override
//                public boolean validate() {
//
//                    // Keep parent validation
//                    boolean validate = super.validate();
//                    // Check if date is correct
//                    for (Integer prereq : prerequisites) {
//                        if (activity_model.getActivityById(prereq) == null) {
//                            setErrorMessage(String.format(LanguageText.getConstant("ACTIVITY_NOT_FOUND")));
//                            LOGGER.info("Activity does not exist.");
//                            return false;
//                        }
//                    }
//                    // Return parent validation
//                    return validate;
//                }
//            };
//            validation.setRule(activityPrerequesitesRule);
//        } else {
//            LOGGER.error("No value inserted!");
//            return;
//        }
//
//        // If all requirements are met
//        if (validation.validate()) {
//
//            // get project
//            Project tempProject = project_model.getProjectById(projectID);
//            // TODO implement updateCreateActivity
////            if (tempProject == null) this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"));
//
//            // get member
//            Member tempMember = member_model.getMemberById(memberID);
//            // TODO implement updateCreateActivity
////            if (tempProject == null) this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"));
//
//            // get all prerequisites
//            List<Activity> tempPrerequisites = null;
//            for (Integer prereq : prerequisites) {
//                Activity preReqActivity = activity_model.getActivityById(prereq);
//                if (preReqActivity != null)
//                    tempPrerequisites.add(preReqActivity);
//            }
//            // Create a temp activity
//            Activity newActivity = new Activity(name, description, Integer.parseInt(duration),
//                    Integer.parseInt(optimisticDuration), Integer.parseInt(likelyDuration),
//                    Integer.parseInt(pessimisticDuration), tempProject, tempMember, tempPrerequisites);
//            // get activity id
//            // TODO where does the id of the new activity gets return
//            // int newActivityId = newActivity.getActivityId();
//
//            // create new activity
//            if (createActivity) {
//                // If insert failed
//                if (activity_model.insertNewActivity(newActivity) == Database.ERROR) {
//
//                    // Display error
//                    // TODO implement updateCreateActivity
//                    // this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"));
//
//                } else {
//                    // If insert successful
//                    // Display success
//                    // TODO implement updateCreateActivity
//                    //this.view.updateCreateActivity(true, LanguageText.getConstant("PROJECT_CREATED"));
//
//                }
//            }
//            // update activity
//            else {
//
//            }
//
//            // If requirements are not met
//        } else {
//            // TODO implement updateCreateActivity
//            // this.view.updateCreateActivity(false, validation.getError());
//        }
//    }

}
