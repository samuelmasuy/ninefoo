package ninefoo.controller.handler;

import ninefoo.config.Annotation.FinalVersion;
import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.config.RoleNames;
import ninefoo.config.Session;
import ninefoo.controller.handler.template.AbstractController;
import ninefoo.controller.logic.EarnedValueAnalysis;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.validationForm.ValidationForm;
import ninefoo.lib.validationForm.ValidationRule;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.object.Role;
import ninefoo.model.sql.*;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.ProjectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for 'Project': Create, Update and Delete projects
 *
 * @author Samuel Masuy, Amir El Bawab
 * @see AbstractController, ProjectListener
 */
public class Project_controller extends AbstractController implements ProjectListener {

    // Load models
    private Project_model project_model = new Project_model();
    private ProjectMember_model projectMember_model = new ProjectMember_model();
    private Role_model role_model = new Role_model();
    private Activity_model activity_model = new Activity_model();
    private Member_model member_model = new Member_model();

    /**
     * Constructor
     *
     * @param view
     */
    public Project_controller(UpdatableView view) {
        super(view);
    }

    @Override
    @FinalVersion(version = "1.0")
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
        nameRule.checkEmpty().checkMaxLength(Config.MAX_TITLE_LENGTH);
        descriptionRule.checkMaxLength(Config.MAX_DESCRIPTION_LENGTH);
        budgetRule.checkDouble().checkMaxLength(15); // TODO Change this to max money amount
        startDateRule.checkDateBefore(deadline);

        // Set rules
        validation.setRule(nameRule);
        validation.setRule(budgetRule);
        validation.setRule(deadlineRule);
        validation.setRule(descriptionRule);
        validation.setRule(startDateRule);

        // If requirements are met
        if (validation.validate()) {

            // Adjust the values type
            double doubleBudget = budget.isEmpty() ? 0 : Double.parseDouble(budget);

            // Create a temp project
            Project project = new Project(name, doubleBudget, DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT), DateHelper.parse(deadline, Config.DATE_FORMAT_SHORT), description);

            // If insert failed
            int projectId;
            if ((projectId = project_model.insertNewProject(project)) == Database.ERROR) {

                // Display error
                this.view.updateCreateProject(false, LanguageText.getConstant("ERROR_OCCURED"), null);

                // If insert successful
            } else {

                // Get roles
                Role role = role_model.getRoleByName(RoleNames.MANAGER);

                // Add member to database as manager
                this.projectMember_model.addMemberToProject(projectId, Session.getInstance().getUserId(), role);

                // Load the project
                Project insertedProject = project_model.getProjectById(projectId);

                // Store it in the session
                Session.getInstance().setProjectId(insertedProject.getProjectId());

                // Empty list of activities on create
                insertedProject.setAcitivies(new ArrayList<Activity>());

                // Display success
                this.view.updateCreateProject(true, String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("PROJECT")), insertedProject);
            }

            // If requirements are not met
        } else {

            // Display error
            this.view.updateCreateProject(false, validation.getError(), null);
        }
    }

    @Override
    @FinalVersion(version = "1.0")
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
        if (validation.validate()) {

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
            if (this.project_model.updateProject(project)) {

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
    @FinalVersion(version = "1.0")
    public void loadAllProjectsByMemberAndRole(int memberId, String roleName) {

        // Get role
        Role role = role_model.getRoleByName(roleName);

        // Get projects as a list
        List<Project> projects = projectMember_model.getAllProjectsByMemberAndRole(memberId, role.getRoleId());

        // Update GUI
        this.view.updateLoadAllProjectsByMemberAndRole(projects);
    }

    @Override
    @FinalVersion(version = "1.0")
    public void loadProject(int projectId) {

        // Get project
        Project project = this.project_model.getProjectById(projectId);

        // If project found
        if (project != null) {

            // Load activities and all their prerequisite
            List<Activity> actList = this.activity_model.getActivitiesByProjectId(projectId);
            for (Activity activity : actList) {

                // Set member
                activity.setMember(member_model.getMemberById(activity.getMemberId()));

                // Set prerequisites
                activity.setPrerequisites(this.activity_model.getActivityPrerequisites(activity));
            }

            // Load activities for this project
            project.setAcitivies(actList);

            // Store project in the session
            Session.getInstance().setProjectId(project.getProjectId());

            // Update view
            this.view.updateLoadProject(true, String.format(LanguageText.getConstant("LOADED"), String.format("%s '%s'", LanguageText.getConstant("PROJECT"), project.getProjectName())), project);

            // If project not found
        } else {

            // Update view
            this.view.updateLoadProject(false, LanguageText.getConstant("ERROR_OCCURED"), null);
        }
    }

    @Override
    @FinalVersion(version = "1.0")
    public void loadEditProjectFields(int projectId) {

        // Get project
        Project project = this.project_model.getProjectById(projectId);

        // If project found
        if (project != null) {

            // Update view
            this.view.updateLoadEditProjectFields(true, null, project);

            // If project not found
        } else {

            // Update view
            this.view.updateLoadEditProjectFields(false, LanguageText.getConstant("ERROR_OCCURED"), null);
        }
    }

    @Override
    public void addUserToProject(int memberId, int projectId, String roleName) {

        // Get the role from the DB
        Role role = role_model.getRoleByName(roleName);

        // If user not already assigned
        if (!projectMember_model.getAssignedAnyRole(memberId, projectId)) {

            // If insert is successful
            if (projectMember_model.addMemberToProject(projectId, memberId, role)) {

                this.view.updateAddUserToProject(true, LanguageText.getConstant("ADD_USER_SUCCESS_PRO"));

                // If failed
            } else {

                // Display error
                this.view.updateAddUserToProject(false, LanguageText.getConstant("ERROR_OCCURED"));
            }

            // If user already assigned
        } else {

            this.view.updateAddUserToProject(false, LanguageText.getConstant("ADD_USER_FAIL_PRO"));
        }
    }

    @Override
    @FinalVersion(version = "1.0")
    public void deleteProject(Project project) {

        // If delete successful
        if (project_model.deleteProject(project)) {

        	// Delete all activities related
        	List<Activity> activities = activity_model.getActivitiesByProject(project);
        	
        	// Delete activities
        	for(Activity activity : activities) {
        		activity_model.deleteActivityById(activity.getActivityId());
        		activity_model.deleteAllRelationshipsForAnActivity(activity.getActivityId());
        	}
        	
        	// Delete membered assigned to those projects
        	List<Member> members = member_model.getAllMembersForAProject(project.getProjectId());
        	
        	// Delete members
        	for(Member member : members)
        		projectMember_model.removeMemberFromProject(member.getMemberId(), project.getProjectId());
        	
            // Get role
            Role role = role_model.getRoleByName(RoleNames.MANAGER);

            // Load the list of projects
            List<Project> projects = projectMember_model.getAllProjectsByMemberAndRole(Session.getInstance().getUserId(), role.getRoleId());

            // If project to be deleted is now opened
            if (Session.getInstance().getProjectId() == project.getProjectId())
                Session.getInstance().setProjectId(Config.INVALID);

            // Update view
            this.view.updateDeleteProject(true, String.format("Project %s deleted successfully", project.getProjectName()), projects);
        } else {

            // Update view
            this.view.updateDeleteProject(false, LanguageText.getConstant("ERROR_OCCURED"), null);
        }
    }

	@Override
	public void removeMemberFromProject(int memberId, int projectId) {
		
		// If user assigned
		if(projectMember_model.getAssignedAnyRole(memberId, projectId)){
				
			// If deleted
			if(projectMember_model.removeMemberFromProject(memberId, projectId)){
		
				// TODO Add to language
				this.view.updateRemoveMemberFromProject(true, "User deleted form project");
			
			// If DB error
			} else {
				
				this.view.updateRemoveMemberFromProject(false, LanguageText.getConstant("ERROR_OCCURED"));
			}
		// If not
		} else {
			
			// If not in DB
			this.view.updateRemoveMemberFromProject(false, "User not assigned");
		}
		
	}

	@Override
	public void loadEarnedValueData(int projectId) {
		
		// TODO: Do the calculation
		Project project = project_model.getProjectById(projectId);
		project.setAcitivies(activity_model.getActivitiesByProject(project));
		
		EarnedValueAnalysis eva = new EarnedValueAnalysis(project);
		project.setTotalCost(eva.calculateTotalPlannedCost());
		project.setTotalPV(eva.calculateBudgetAtCompletion());
		project.setTotalAC(eva.calculateTotalActualCost());
		project.setTotalEV(eva.calculateTotalEarnedValue());
		project.setCostVariance(eva.calculateTotalCostVariance());
		project.setScheduleVariance(eva.calculateTotalScheduleVariance());
		project.setCpi(eva.calculateCostPerformanceIndex());
		project.setSpi(eva.calculateSchedulePerformanceIndex());
		project.setEAC(eva.calculateEstimateAtCompletion());
		project.setETC(eva.calculateEstimateToComplete());
		
		this.view.updateLoadEarnedValueData(true, null, project);
	}
}
