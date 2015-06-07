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
    @Override
    public void createUpdateActivity(int row, final String activityId, String name, String description, String duration,
                                     String optimisticDuration, String likelyDuration, String pessimisticDuration,
                                     int projectID, int memberID, final List<Integer> prerequisites) {

        // Flag to know if we are creating an activity or updating one.
        boolean createActivity = false;

        final ValidationForm validation = new ValidationForm();

        // If ## create Activity
        if (activityId.equals(TabularData_view.PRE_CREATED_ID)) {
            createActivity = true;
        }
        // Update an Activity
        else {
            ValidationRule activityIDRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_ID"), activityId) {
                @Override
                public boolean validate() {

                    // Keep parent validation
                    boolean validate = super.validate();
                    // check if activity is in database
                    if (activity_model.getActivityById(Integer.parseInt(activityId)) == null) {
                        setErrorMessage(String.format(LanguageText.getConstant("ACTIVITY_NOT_FOUND")));
                        LOGGER.info("Activity does not exist.");
                        return false;
                    }
                    // Return parent validation
                    return validate;
                }
            };
            validation.setRule(activityIDRule);
            createActivity = false;
        }

        if (!name.isEmpty()) {
            ValidationRule activityNameRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_NAME"), name);
            activityNameRule.checkFormat("[a-zA-Z0-9]+").checkMaxLength(30);
            validation.setRule(activityNameRule);
        } else if (!description.isEmpty()) {
            ValidationRule activityDescriptionRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_DESCRIPTION"), name);
            activityDescriptionRule.checkFormat("[a-zA-Z0-9]+").checkMaxLength(120);
            validation.setRule(activityDescriptionRule);
        } else if (!duration.isEmpty()) {
            ValidationRule activityDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_DURATION"), duration);
            activityDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
            validation.setRule(activityDurationRule);
        } else if (!optimisticDuration.isEmpty()) {
            ValidationRule activityOptimisticDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_OPTIMISTIC_DURATION"), optimisticDuration);
            activityOptimisticDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
            validation.setRule(activityOptimisticDurationRule);
        } else if (!likelyDuration.isEmpty()) {
            ValidationRule activityLikelyDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LIKELY_DURATION"), likelyDuration);
            activityLikelyDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
            validation.setRule(activityLikelyDurationRule);
        } else if (!pessimisticDuration.isEmpty()) {
            ValidationRule activityPessimisticDurationRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_PESSIMISTIC_DURATION"), pessimisticDuration);
            activityPessimisticDurationRule.checkFormat("[0-9]+").checkMaxLength(2);
            validation.setRule(activityPessimisticDurationRule);
        } else if (!prerequisites.isEmpty()) {
            // TODO what to pass as second argument?
            ValidationRule activityPrerequesitesRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_PREREQUISITES"), "prerequesites") {
                @Override
                public boolean validate() {

                    // Keep parent validation
                    boolean validate = super.validate();
                    // Check if date is correct
                    for (Integer prereq : prerequisites) {
                        if (activity_model.getActivityById(prereq) == null) {
                            setErrorMessage(String.format(LanguageText.getConstant("ACTIVITY_NOT_FOUND")));
                            LOGGER.info("Activity does not exist.");
                            return false;
                        }
                    }
                    // Return parent validation
                    return validate;
                }
            };
            validation.setRule(activityPrerequesitesRule);
        } else {
            LOGGER.error("No value inserted!");
            return;
        }

        // If all requirements are met
        if (validation.validate()) {

            // get project
            Project tempProject = project_model.getProjectById(projectID);
            // TODO implement updateCreateActivity
//            if (tempProject == null) this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"));

            // get member
            Member tempMember = member_model.getMemberById(memberID);
            // TODO implement updateCreateActivity
//            if (tempProject == null) this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"));

            // get all prerequisites
            List<Activity> tempPrerequisites = null;
            for (Integer prereq : prerequisites) {
                Activity preReqActivity = activity_model.getActivityById(prereq);
                if (preReqActivity != null)
                    tempPrerequisites.add(preReqActivity);
            }
            // Create a temp activity
            Activity newActivity = new Activity(name, description, Integer.parseInt(duration),
                    Integer.parseInt(optimisticDuration), Integer.parseInt(likelyDuration),
                    Integer.parseInt(pessimisticDuration), tempProject, tempMember, tempPrerequisites);
            // get activity id
            // TODO where does the id of the new activity gets return
            // int newActivityId = newActivity.getActivityId();

            // create new activity
            if (createActivity) {
                // If insert failed
                if (activity_model.insertNewActivity(newActivity) == Database.ERROR) {

                    // Display error
                    // TODO implement updateCreateActivity
                    // this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"));

                } else {
                    // If insert successful
                    // Display success
                    // TODO implement updateCreateActivity
                    //this.view.updateCreateActivity(true, LanguageText.getConstant("PROJECT_CREATED"));

                }
            }
            // update activity
            else {

            }

            // If requirements are not met
        } else {
            // TODO implement updateCreateActivity
            // this.view.updateCreateActivity(false, validation.getError());
        }
    }

}
