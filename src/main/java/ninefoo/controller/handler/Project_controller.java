package ninefoo.controller.handler;

import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.config.RoleNames;
import ninefoo.config.Session;
import ninefoo.controller.handler.template.AbstractController;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.validationForm.ValidationForm;
import ninefoo.lib.validationForm.ValidationRule;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import ninefoo.model.sql.Activity_model;
import ninefoo.model.sql.ProjectMember_model;
import ninefoo.model.sql.Project_model;
import ninefoo.model.sql.Role_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ProjectListener;

import java.util.List;

/**
 * Controller for 'Project': Create, Update and Delete projects
 * @author Samuel Masuy, Amir El Bawab
 * @see AbstractController, ProjectListener
 */
public class Project_controller extends AbstractController implements ProjectListener{

	// Load models
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
	public void createProject(String name, final String budget, String startDate, String deadline, String description) {
		
		// Create validation form
		ValidationForm validation = new ValidationForm();
		
		// Create rules
		ValidationRule nameRule = new ValidationRule(LanguageText.getConstant("NAME"), name);
		ValidationRule budgetRule = new ValidationRule(LanguageText.getConstant("BUDGET"), budget);
		ValidationRule deadlineRule = new ValidationRule(LanguageText.getConstant("DEADLINE"), deadline);
		ValidationRule startDateRule = new ValidationRule(LanguageText.getConstant("START_DATE"), startDate);
		ValidationRule descriptionRule = new ValidationRule(LanguageText.getConstant("DESCRIPTION"), description);
		
		// Set restrictions - should be the same as edit
		nameRule.checkEmpty().checkMaxLength(25);
		descriptionRule.checkMaxLength(150);
		budgetRule.checkDouble().checkMaxLength(15);
		startDateRule.checkDateBefore(deadline);
		
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
			
			// Create a temp project
			Project project = new Project(name, doubleBudget, DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT), DateHelper.parse(deadline, Config.DATE_FORMAT_SHORT), description);
			
			// If insert failed
			int projectId;
			if( (projectId = project_model.insertNewProject(project)) == Database.ERROR){
				
				// Display error
				this.view.updateCreateProject(false, LanguageText.getConstant("ERROR_OCCURED"));
			
			// If insert successful
			} else {
				
				// Get roles
				Role role = role_model.getRoleByName(RoleNames.MANAGER);
				
				// Add member to database as manager
				this.projectMember_model.addMemberToProject(projectId, Session.getInstance().getUserId(), role);
				
				// Display success
				this.view.updateCreateProject(true, String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("PROJECT")));
			}
		
		// If requirements are not met
		} else {
			
			// Display error
			this.view.updateCreateProject(false, validation.getError());
		}
	}
	
	@Override
	public void editProject(int projectId, String name, final String budget, String startDate, String deadline, String description) {
		
		// Create validation form
		ValidationForm validation = new ValidationForm();
		
		// Create rules
		ValidationRule nameRule = new ValidationRule(LanguageText.getConstant("NAME"), name);
		ValidationRule budgetRule = new ValidationRule(LanguageText.getConstant("BUDGET"), budget);
		ValidationRule deadlineRule = new ValidationRule(LanguageText.getConstant("DEADLINE"), deadline);
		ValidationRule startDateRule = new ValidationRule(LanguageText.getConstant("START_DATE"), startDate);
		ValidationRule descriptionRule = new ValidationRule(LanguageText.getConstant("DESCRIPTION"), description);
		
		// Set restrictions - Should be the same as create
		nameRule.checkEmpty().checkMaxLength(25);
		descriptionRule.checkMaxLength(150);
		budgetRule.checkDouble().checkMaxLength(15);
		startDateRule.checkDateBefore(deadline);
		
		// Set rules
		validation.setRule(nameRule);
		validation.setRule(budgetRule);
		validation.setRule(deadlineRule);
		validation.setRule(descriptionRule);
		validation.setRule(startDateRule);
		
		// If requirements met
		if(validation.validate()){
			
			// Get project
			Project project = this.project_model.getProjectById(projectId);
			
			// Adjust the values type
			double doubleBudget = budget.isEmpty() ? 0 : Double.parseDouble(budget);
						
			// Update object
			project.setProjectName(name);
			project.setBudget(doubleBudget);
			project.setDescription(description);
			project.setDeadlineDate(DateHelper.parse(deadline, Config.DATE_FORMAT_SHORT));
			project.setStartDate(DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT));
			
			// Update project
			if(this.project_model.updateProject(project)){
				
				// Update view
				this.view.updateEditProject(true, String.format(LanguageText.getConstant("UPDATED"), LanguageText.getConstant("PROJECT")));
			}
			
		// If requirement are not met
		} else {
			
			// Update view
			this.view.updateEditProject(false, validation.getError());
		}
	}

	@Override
	public void loadAllProjectsByMemberAndRole(int memberId, String roleName) {
		
		// Get role
		Role role = role_model.getRoleByName(roleName);
		
		// Get projects as a list
		List<Project> projects = project_model.getAllProjectsByMemberAndRole(memberId, role.getRoleId());
		
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
			for(Activity activity : actList)
				
				// Set prerequisites
				activity.setPrerequisites(this.activity_model.getActivityPrerequisites(activity));
			
			// Load activities for this project
			project.setAcitivies(actList);
			
			// Update view
			this.view.updateLoadProject(true, String.format(LanguageText.getConstant("LOADED"), String.format("%s '%s'", LanguageText.getConstant("PROJECT"), project.getProjectName())), project);
		
		// If project not found
		} else{
			
			// Update view
			this.view.updateLoadProject(false, LanguageText.getConstant("ERROR_OCCURED"), null);
		}
	}

	@Override
	public void loadEditProjectFields(int projectId) {
		
		// Get project 
		Project project = this.project_model.getProjectById(projectId);
		
		// If project found
		if(project != null) {
			
			// Update view
			this.view.updateLoadEditProjectFields(true, null, project);
			
		// If project not found
		} else {
			
			// Update view
			this.view.updateLoadEditProjectFields(false, LanguageText.getConstant("ERROR_OCCURED"), null);
		}
	}
}
