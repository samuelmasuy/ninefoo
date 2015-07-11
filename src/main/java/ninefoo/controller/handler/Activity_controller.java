package ninefoo.controller.handler;

import java.util.ArrayList;
import java.util.List;

import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.controller.handler.template.AbstractController;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.validationForm.ValidationForm;
import ninefoo.lib.validationForm.ValidationRule;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.sql.Activity_model;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.Project_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ActivityListener;

/**
 * Controller for 'Activity': Create, Update and Delete activities
 * 
 * @author Samuel Masuy
 * @author Melissa Duong
 * @see AbstractController, ActivityListener
 */
public class Activity_controller extends AbstractController implements
		ActivityListener {

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

	/**
	 * This method creates an activity and inserts it into the db 
	 * It returns an error message when the activity cannot be created
	 * @author Melissa Duong 
	 * 
	 * 
	 */
	@Override
	public void createActivity(int row, String activityId,
			String activityLabel, String duration, String startDate,
			String finishDate, String cost, Project project, String completion, int memberId) {

		// set individual rules for each passed parameter json file
		ValidationRule activityLabelRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LABEL_ACT"), activityLabel);
		ValidationRule activityDurationRule = new ValidationRule(LanguageText.getConstant("DURATION_ACT"), duration);
		ValidationRule activityStartDateRule = new ValidationRule(LanguageText.getConstant("START_ACT"), startDate);
		ValidationRule activityFinishDateRule = new ValidationRule(LanguageText.getConstant("FINISH_ACT"), finishDate);

		// ********************************************
		// NOT SURE ABOUT THIS ONE activity completion??
		ValidationRule activityCompletionRule = new ValidationRule(
				LanguageText.getConstant("PLANNED_PERCENTAGE_ACT"), completion);

		// set restrictions for those rules
		activityLabelRule.checkEmpty().checkMaxLength(25);
		activityDurationRule.checkEmpty().checkMaxNumValue(100000).checkInt();
				//.checkFormat("[0-9]+");
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);

		// activityCompletionRule.checkEmpty().checkFormat("[0-9]+").checkMaxNumValue(100);

		// add a validation form which takes multiple validation rules
		ValidationForm activityValidation = new ValidationForm();

		// add the validation rules to the validation form
		activityValidation.setRule(activityLabelRule);
		activityValidation.setRule(activityCompletionRule);
		activityValidation.setRule(activityDurationRule);
		activityValidation.setRule(activityStartDateRule);
		activityValidation.setRule(activityFinishDateRule);

		// if all the parameters passed respect the restrictions, add a new
		// activity object in this if statement
		if (activityValidation.validate()) {

			// retrieve member object from db by memberId, this is useful for
			// the activity constructor that needs a member object
			Member member = this.member_model.getMemberById(memberId);

			// TODO add a completion (% completion) parameter for the activity
			// constructor used right here below

			// Make cost Double | null
			Double doubleCost = cost.isEmpty() ? null : new Double(Double.parseDouble(cost));
			
			// create activity
			Activity activity = new Activity(activityLabel, Integer.parseInt(duration), DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT), DateHelper.parse(finishDate, Config.DATE_FORMAT_SHORT), project, member, doubleCost);

			// add a new activity to the activity model
			// if insert failed
			if (this.activity_model.insertNewActivity(activity) == Database.ERROR) {

				// display error message
				this.view.updateCreateActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
			}// if
				// if activity added successfully, update the view because a new activity has been added to a project
			else {
				// get the new list of activities including the new activity and  update the project object

				List<Activity> activitiesList = new ArrayList<>();

				// if unable to retrieve list of activities return an error message
				if ((activitiesList = this.activity_model
						.getActivitiesByProject(project)) == null) {
					this.view.updateCreateActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
				}

				// else assign the list to the project object
				else {
					project.setAcitivies(activitiesList);
				}

				// update the view with the new project object and display successful activity creation message
				this.view.updateCreateActivity(true,LanguageText.getConstant("CREATED"), project);

			}// else

		}
		// Display error when validation error
		this.view.updateCreateActivity(false, activityValidation.getError(),null);

	}

	/**
	 * This method updates the activity information when user edits it.
	 * Update is done in the view and in the db
	 * This method is very similar to insertActivity. The only difference is when we update the view
	 * we call updateEditActivity instead of updateCreateActivity method and pass different error messages
	 * @author Melissa Duong 
	 * @date July-05-2015
	 */
	public void editActivity(int row, String activityId, String activityLabel, String duration, String startDate, String finishDate, String cost, Project project, String completion, int memberId) {
		// set individual rules for each passed parameter json file
		ValidationRule activityLabelRule = new ValidationRule(
				LanguageText.getConstant("ACTIVITY_LABEL_ACT"), activityLabel);
		ValidationRule activityDurationRule = new ValidationRule(
				LanguageText.getConstant("DURATION_ACT"), duration);
		ValidationRule activityStartDateRule = new ValidationRule(
				LanguageText.getConstant("START_ACT"), startDate);
		ValidationRule activityFinishDateRule = new ValidationRule(
				LanguageText.getConstant("FINISH_ACT"), finishDate);
		
		
		// ********************************************
		// NOT SURE ABOUT THIS ONE activity completion??
		ValidationRule activityCompletionRule = new ValidationRule(
				LanguageText.getConstant("PLANNED_PERCENTAGE_ACT"), completion);
		
		// set restrictions for those rules
		activityLabelRule.checkEmpty().checkMaxLength(25);
		activityDurationRule.checkEmpty().checkMaxNumValue(100000).checkInt();
			//	.checkFormat("[0-9]+");
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);
		
		// add a validation form which takes multiple validation rules
		ValidationForm activityValidation = new ValidationForm();

		// add the validation rules to the validation form
		activityValidation.setRule(activityLabelRule);
		activityValidation.setRule(activityCompletionRule);
		activityValidation.setRule(activityDurationRule);
		activityValidation.setRule(activityStartDateRule);
		activityValidation.setRule(activityFinishDateRule);

		// if all the parameters passed respect the restrictions, add a new
		// activity object in this if statement
		if (activityValidation.validate()) {

			// retrieve member object from db by memberId, this is useful for
			// the activity constructor that needs a member object
			Member member = this.member_model.getMemberById(memberId);

			// TODO add a completion (% completion) parameter for the activity
			// constructor used right here below

			// Make cost Double | null
			Double doubleCost = cost.isEmpty() ? null : new Double(Double.parseDouble(cost));
			
			// create activity
			Activity activity = new Activity(activityLabel, Integer.parseInt(duration), DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT), DateHelper.parse(finishDate, Config.DATE_FORMAT_SHORT), project, member, doubleCost);

			// update activity in the activity model
			// if update failed
			if (this.activity_model.updateActivity(activity) == false) {

				// display error message
				this.view.updateCreateActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
			}// if
				// if activity updated successfully, update the view 
			else {
				// get the new list of activities including the updated activity and  update the project object

				List<Activity> activitiesList = new ArrayList<>();

				// if unable to retrieve list of activities return an error message
				if ((activitiesList = this.activity_model
						.getActivitiesByProject(project)) == null) {
					this.view.updateEditActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
				}

				// else assign the list to the project object
				else {
					project.setAcitivies(activitiesList);
				}

				// update the view with the new project object and display successful activity update message
				this.view.updateEditActivity(true,LanguageText.getConstant("UPDATED"), project);

			}// else

		}
		// Display error when validation error
		this.view.updateEditActivity(false, activityValidation.getError(),null);
	}

	@Override
	public void createDependentActivities(int activityIdDependent, int activityIdDependentOn, int row) {
	/*	ValidationForm dependantActivityValidation = new ValidationForm();
		ValidationRule activityLabelRule =new ValidationRule ("acitivtyLabel" , activityLabel );
		
		activityLabelRule.checkEmpty().checkMaxLength(25);
		activityDurationRule.checkEmpty().checkMaxLength(200).checkFormat("[0-9]+");
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);
		activityFinishDateRule.checkEmpty().checkDateAfter(startDate);
		activityCompletionRule.checkEmpty().checkFormat("[0-9]+").checkMaxNumValue(100);
		
	//	this.view.updateEditActivity(success, message, project); not implemented yet
			this.view.updateCreateActivity(true, "activity successfully created, project);		
		
	*/	
		
		
	}
}
