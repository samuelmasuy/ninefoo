package ninefoo.controller.handler;

import ninefoo.controller.handler.template.AbstractController;
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
	Activity_controller(UpdatableView view) {
		super(view);
	}

	/**
	 * Completed by Melissa Duong on July-04-2015 ValidationFormLang.json
	 * 
	 */
	@Override
	public void createActivity(int row, String activityId,
			String activityLabel, String duration, String startDate,
			String finishDate, Project project, String completion, int memberId) {

		// set individual rules for each passed parameter
		ValidationRule activityLabelRule = new ValidationRule("acitivtyLabel",activityLabel);
		ValidationRule activityDurationRule = new ValidationRule("duration",duration);
		ValidationRule activityStartDateRule = new ValidationRule("startDate",startDate);
		ValidationRule activityFinishDateRule = new ValidationRule("finishDate", finishDate);
		ValidationRule activityCompletionRule = new ValidationRule("completion", completion);
		
		// set restrictions for those rules
		activityLabelRule.checkEmpty().checkMaxLength(25);
		activityDurationRule.checkEmpty().checkMaxLength(200).checkFormat("[0-9]+");
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);
		activityFinishDateRule.checkEmpty().checkDateAfter(startDate);
		activityCompletionRule.checkEmpty().checkFormat("[0-9]+").checkMaxNumValue(100);

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

			// TODO add a completion (% completion) parameter for the activity constructor used right here below
			
			// create activity
			Activity activity = new Activity(activityLabel,Integer.parseInt(duration), startDate, finishDate, project,member);
			
			// add a new activity to the activity model
			this.activity_model.insertNewActivity(activity);

		}

		// this.view.updateEditActivity(success, message, project); not
		// implemented yet
		// this.view.updateCreateActivity(true, "activity successfully created",
		// project);
	}

	/**
	 * Completed by Melissa Duong on July-05-2015
	 * 
	 */
	public void editActivity(int row, String activityId, String activityLabel, String duration, String startDate, String finishDate, Project project, String completion, int memberId) {
		// set individual rules for each passed parameter
		ValidationRule activityLabelRule = new ValidationRule("acitivtyLabel",activityLabel);
		ValidationRule activityDurationRule = new ValidationRule("duration",duration);
		ValidationRule activityStartDateRule = new ValidationRule("startDate",startDate);
		ValidationRule activityFinishDateRule = new ValidationRule("finishDate", finishDate);
		ValidationRule activityCompletionRule = new ValidationRule("completion", completion);
		
		// set restrictions for those rules
		activityLabelRule.checkEmpty().checkMaxLength(25);
		activityDurationRule.checkEmpty().checkMaxLength(200).checkFormat("[0-9]+");
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);
		activityFinishDateRule.checkEmpty().checkDateAfter(startDate);
		activityCompletionRule.checkEmpty().checkFormat("[0-9]+").checkMaxNumValue(100);
		
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

			// edit activity in the db
			Activity activity = new Activity(activityLabel,Integer.parseInt(duration), startDate, finishDate, project,member);
			
			
			//update the activity content in the db
			this.activity_model.updateActivity(activity);

		}

		// this.view.updateEditActivity(success, message, project); not
		// implemented yet
		// this.view.updateCreateActivity(true, "activity successfully created",
		// project);


		
	//	this.view.updateEditActivity(success, message, project); not implemented yet
	//		this.view.updateCreateActivity(true, "activity successfully created, project);		
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
