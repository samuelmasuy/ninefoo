package ninefoo.controller;

import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.helper.DateHelper;
import ninefoo.lib.LanguageText;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.model.Project;
import ninefoo.model.Project_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ProjectListener;

import org.apache.logging.log4j.LogManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project_controller extends AbstractController implements ProjectListener{
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	private Project_model project_model = new Project_model();

	/**
	 * Constructor
	 * @param view
	 */
	public Project_controller(UpdatableView view) {
		super(view);
	}
	
	@Override
	public void createProject(String name, final String budget, final String startDate, final String deadline, String description) {
		
		// Create validation form
		ValidationForm validation = new ValidationForm();
		
		// Create rules
		ValidationRule nameRule = new ValidationRule(LanguageText.getConstant("NAME"), name);
		ValidationRule budgetRule = new ValidationRule(LanguageText.getConstant("BUDGET"), budget){
			
			@Override
			public boolean validate() {
				
				// Keep parent validation
				boolean validate = super.validate();
				
				// Parse budget to double, if not empty
				if(!budget.isEmpty()){
					try{
						Double.parseDouble(budget);
						
					// If not a double
					} catch(IllegalArgumentException e){
						setErrorMessage(String.format(LanguageText.getConstant("WRONG_FORMAT"), LanguageText.getConstant("BUDGET")));
						LOGGER.error("Bugdet must be of type double");
						return false;
					}
				}
				
				// Return parent validation
				return validate;
			}
		};
		ValidationRule deadlineRule = new ValidationRule(LanguageText.getConstant("DEADLINE"), deadline){
			@Override
			public boolean validate() {
				
				// Keep parent validation
				boolean validate = super.validate();
				
				// Check if date is correct, if not empty
				if(!deadline.isEmpty()){
					if(! DateHelper.isValid(deadline, Config.DATE_FORMAT_SHORT)){
						setErrorMessage(String.format(LanguageText.getConstant("WRONG_FORMAT") + " e.g. %s", LanguageText.getConstant("DEADLINE"), Config.DATE_FORMAT_SHORT.toLowerCase()));
						LOGGER.info("Invalid Date format.");
						return false;
					}
				}
				
				// Return parent validation
				return validate;
			}
		};
		ValidationRule startDateRule = new ValidationRule(LanguageText.getConstant("START_DATE"), startDate){
			@Override
			public boolean validate() {
				
				// Keep parent validation
				boolean validate = super.validate();
				
				// Check if date is correct, if not empty
				if(!startDate.isEmpty()){
					if(! DateHelper.isValid(startDate, Config.DATE_FORMAT_SHORT)){
						setErrorMessage(String.format(LanguageText.getConstant("WRONG_FORMAT") + " e.g. %s", LanguageText.getConstant("START_DATE"), Config.DATE_FORMAT_SHORT.toLowerCase()));
						LOGGER.info("Invalid Date format.");
						return false;
					}
				}
				
				// Return parent validation
				return validate;
			}
		};
		ValidationRule descriptionRule = new ValidationRule(LanguageText.getConstant("DESCRIPTION"), description);
		
		// Set restrictions
		nameRule.checkEmpty();
		descriptionRule.checkMaxLength(150);
		
		// Set rules
		validation.setRule(nameRule);
		validation.setRule(budgetRule);
		validation.setRule(deadlineRule);
		validation.setRule(descriptionRule);
		validation.setRule(startDateRule);
		
		// If requirements are met
		if(validation.validate()){
			
			// Adjust the values type
			double doubleBudget = budget.isEmpty() ? 0 : Double.parseDouble(budget);
			
			Date dateDeadline = null;
			if( (dateDeadline = DateHelper.parse(deadline, Config.DATE_FORMAT_SHORT)) == null)
				LOGGER.error("Unexpected error!");
			
			Date dateStart = null;
			if( (dateStart = DateHelper.parse(deadline, Config.DATE_FORMAT_SHORT)) == null)
				LOGGER.error("Unexpected error!");
			
			
			// Create a temp project
			Project project = new Project(name, doubleBudget, dateStart, dateDeadline, description);
			
			// If insert failed
			if(project_model.insertNewProject(project) == Database.ERROR){
				
				// Display error
				this.view.updateCreateProject(false, LanguageText.getConstant("ERROR_OCCURED"));
			
			// If insert successful
			} else {
			
				// Display success
				this.view.updateCreateProject(true, LanguageText.getConstant("PROJECT_CREATED"));
			}
		
		// If requirements are not met
		} else {
			
			// Display error
			this.view.updateCreateProject(false, validation.getError());
		}
	}
}