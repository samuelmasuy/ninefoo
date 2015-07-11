package ninefoo.controller.handler;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	/**
	 * This method creates an activity and inserts it into the db 
	 * It returns an error message when the activity cannot be created
	 * @author Melissa Duong 
	 */
	@Override
	public void createActivity(int row, String activityId, String activityLabel, String duration, String startDate, String finishDate, String cost, Project project, int memberId, String[] prerequisite) {

		// set individual rules for each passed parameter json file
		ValidationRule activityLabelRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LABEL_ACT"), activityLabel);
		ValidationRule activityDurationRule = new ValidationRule(LanguageText.getConstant("DURATION_ACT"), duration);
		ValidationRule activityStartDateRule = new ValidationRule(LanguageText.getConstant("START_ACT"), startDate);
		ValidationRule activityFinishDateRule = new ValidationRule(LanguageText.getConstant("FINISH_ACT"), finishDate);
		ValidationRule activityCostRule = new ValidationRule(LanguageText.getConstant("COST_ACT"), cost);
		
		// set restrictions for those rules
		activityLabelRule.checkEmpty().checkMaxLength(25).checkFormat("[a-zA-Z0-9]+");
		activityDurationRule.checkEmpty().checkMaxNumValue(100000).checkMinNumValue(0).checkInt();
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);

		// add a validation form which takes multiple validation rules
		ValidationForm activityValidation = new ValidationForm();

		// add the validation rules to the validation form
		activityValidation.setRule(activityLabelRule);
		activityValidation.setRule(activityDurationRule);
		activityValidation.setRule(activityStartDateRule);
		activityValidation.setRule(activityFinishDateRule);
		activityValidation.setRule(activityCostRule);
		
		// Condition for the prerequisite
		Set<String> prereqSet = new HashSet<>(Arrays.asList(prerequisite));
		
		// Run redundancy test
		if(prereqSet.size() != prerequisite.length){
			this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"), null);
			return;
		}
		
		// TODO Check cycle test

		// if all the parameters passed respect the restrictions, add a new
		// activity object in this if statement
		if (activityValidation.validate()) {

			// retrieve member object from db by memberId, this is useful for
			// the activity constructor that needs a member object
			Member member = this.member_model.getMemberById(memberId);

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
				if ((activitiesList = this.activity_model.getActivitiesByProject(project)) == null) {
					this.view.updateCreateActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);

					// else assign the list to the project object
				} else {
					project.setAcitivies(activitiesList);

					// update the view with the new project object and display successful activity creation message
					this.view.updateCreateActivity(true,LanguageText.getConstant("CREATED"), project);
				}
			}// else
		} else {
			
			// Display error when validation error
			this.view.updateCreateActivity(false, activityValidation.getError(),null);
		}
	}

	/**
	 * This method updates the activity information when user edits it.
	 * Update is done in the view and in the db
	 * This method is very similar to insertActivity. The only difference is when we update the view
	 * we call updateEditActivity instead of updateCreateActivity method and pass different error messages
	 * @author Melissa Duong 
	 * @date July-05-2015
	 */
	@Override
	public void editActivity(int row, String activityId, String activityLabel, String duration, String startDate, String finishDate, String cost, Project project, int memberId, String[] prerequisite) {
		// set individual rules for each passed parameter json file
		ValidationRule activityLabelRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LABEL_ACT"), activityLabel);
		ValidationRule activityDurationRule = new ValidationRule(LanguageText.getConstant("DURATION_ACT"), duration);
		ValidationRule activityStartDateRule = new ValidationRule(LanguageText.getConstant("START_ACT"), startDate);
		ValidationRule activityFinishDateRule = new ValidationRule(LanguageText.getConstant("FINISH_ACT"), finishDate);
		ValidationRule activityCostRule = new ValidationRule(LanguageText.getConstant("COST_ACT"), cost);
		
		// set restrictions for those rules
		activityLabelRule.checkEmpty().checkMaxLength(25).checkFormat("[a-zA-Z0-9]+");
		activityDurationRule.checkEmpty().checkMaxNumValue(100000).checkMinNumValue(0).checkInt();
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);

		// add a validation form which takes multiple validation rules
		ValidationForm activityValidation = new ValidationForm();

		// add the validation rules to the validation form
		activityValidation.setRule(activityLabelRule);
		activityValidation.setRule(activityDurationRule);
		activityValidation.setRule(activityStartDateRule);
		activityValidation.setRule(activityFinishDateRule);
		activityValidation.setRule(activityCostRule);
		
		// Condition for the prerequisite
		Set<String> prereqSet = new HashSet<>(Arrays.asList(prerequisite));
		
		// Run redundancy test
		if(prereqSet.size() != prerequisite.length){
			this.view.updateCreateActivity(false, LanguageText.getConstant("ERROR_OCCURED"), null);
			return;
		}

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
				if ((activitiesList = this.activity_model.getActivitiesByProject(project)) == null) {
					this.view.updateEditActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
				}

				// else assign the list to the project object
				else {
					project.setAcitivies(activitiesList);
					
					// update the view with the new project object and display successful activity update message
					this.view.updateEditActivity(true,LanguageText.getConstant("UPDATED"), project);

				}
			}// else
		} else {
			
			// Display error when validation error
			this.view.updateEditActivity(false, activityValidation.getError(),null);
		}
	}

	@Override
	public void loadActivitiesByProject(Project project) {
		//gets the list of prerequisites, when creating an act. (new activity)
		//when new activity is pressed, we must pupolate the prereq act
		//similar one for memebrs dropdown
		
	/*	this.view.updateLoadActivitiesByProject
		//loading the values and pushing them back to the view
*/	}
}
