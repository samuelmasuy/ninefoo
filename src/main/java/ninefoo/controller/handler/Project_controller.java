package ninefoo.controller.handler;

import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.config.RoleNames;
import ninefoo.config.Session;
import ninefoo.controller.AbstractController;
import ninefoo.helper.DateHelper;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.Activity;
import ninefoo.model.Activity_model;
import ninefoo.model.Project;
import ninefoo.model.ProjectMember_model;
import ninefoo.model.Project_model;
import ninefoo.model.Role;
import ninefoo.model.Role_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ProjectListener;

import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project_controller extends AbstractController implements ProjectListener{
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	// Global models
	private Project_model project_model = new Project_model();
	private ProjectMember_model projectMember_model = new ProjectMember_model();
	private Role_model role_model = new Role_model();
	private Activity_model activity_model = new Activity_model();
	
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
			if( !deadline.isEmpty() && (dateDeadline = DateHelper.parse(deadline, Config.DATE_FORMAT_SHORT)) == null)
				LOGGER.error("Unexpected error!");
			
			Date dateStart = null;
			if( !startDate.isEmpty() && (dateStart = DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT)) == null)
				LOGGER.error("Unexpected error!");
			
			// Create a temp project
			Project project = new Project(name, doubleBudget, dateStart, dateDeadline, description);
			
			// If insert failed
			int projectId;
			if( (projectId = project_model.insertNewProject(project)) == Database.ERROR){
				
				// Display error
				this.view.updateCreateProject(false, LanguageText.getConstant("ERROR_OCCURED"));
			
			// If insert successful
			} else {
				
				// Get roles
				Role role = role_model.getRoleByName("Manager");
				
				// Add member to database as manager
				this.projectMember_model.addMemberToProject(projectId, Session.getInstance().getUserId(), role);
				
				// Display success
				this.view.updateCreateProject(true, LanguageText.getConstant("PROJECT_CREATED"));
			}
		
		// If requirements are not met
		} else {
			
			// Display error
			this.view.updateCreateProject(false, validation.getError());
		}
	}

	@Override
	public void loadAllProjectsByMemberAndRole(int memberId, RoleNames roleName) {
		
		// Load models
		Project_model project_model = new Project_model();
		Role_model role_model = new Role_model();
		
		// Check role
		Role role = null;
		switch(roleName){
		case Manager:
			role = role_model.getRoleByName("Manager");
			break;
		case Member:
			role = role_model.getRoleByName("Member");
			break;
		}
		
		// Get projects as a list
		List<Project> projects = project_model.getAllProjectsByMemberAndRole(memberId, role.getRoleId());
		
		// Make sure the list is not null
		if(projects == null)
			projects = new ArrayList<>();
		
		// Update GUI
		this.view.updateLoadAllProjectsByMemberAndRole(projects);
	}

	@Override
	public void loadProject(int projectId) {
		
		// Get project 
		Project project = this.project_model.getProjectById(projectId);
		
		// If project found
		if(project != null){
			
			// Load prerequisite for each activity
			List<Activity> actList = this.activity_model.getActivitiesByProjectId(projectId);
			for(Activity activity : actList){
				
				// Set prerequisites
				activity.setPrerequisites(this.activity_model.getActivityPrerequisites(activity));
			}
			
			// Load activities for this project
			project.setAcitivies(actList);
			
			// Update view
			this.view.updateLoadProject(true, String.format("Project '%s' loaded succesfully", project.getProjectName()), project);
		
		// If project not found
		} else{
			
			// Update view
			this.view.updateLoadProject(false, LanguageText.getConstant("ERROR_OCCURED"), null);
		}
	}

	@Override
	public void editProject(int projectId, String name, final String budget, String description) {
		
		ValidationForm validation = new ValidationForm();
		
		ValidationRule nameRule = new ValidationRule(LanguageText.getConstant("NAME"), name);
		ValidationRule descriptionRule = new ValidationRule(LanguageText.getConstant("DESCRIPTION"), description);
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
		
		nameRule.checkEmpty();
		descriptionRule.checkMaxLength(150);
		
		// Set rules
		validation.setRule(nameRule);
		validation.setRule(descriptionRule);
		validation.setRule(budgetRule);
		
		// If requirements met
		if(validation.validate()){
			
			// Get project
			Project project = this.project_model.getProjectById(projectId);
			
			// Adjust the values type
			double doubleBudget = budget.isEmpty() ? 0 : Double.parseDouble(budget);
						
			// Update object
			project.setProjectName(name);
			project.setBudget(doubleBudget);
			
			// Update project
			if(this.project_model.updateProject(project)){
				
				// Update view
				this.view.updateEditProject(true, "Project updated!", project);
			}
			
		// If requirement are not met
		} else {
			
			// Update view
			this.view.updateEditProject(false, validation.getError(), null);
		}
	}
}
