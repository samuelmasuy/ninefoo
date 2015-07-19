package ninefoo.controller.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ninefoo.config.Annotation.FinalVersion;
import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.controller.handler.template.AbstractController;
import ninefoo.helper.DateHelper;
import ninefoo.helper.StringHelper;
import ninefoo.lib.graph.Graph;
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
	@FinalVersion(version="1.0")
	public void createActivity(String activityLabel, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, final int memberId, final int[] prerequisitesId) {

		/**
		 * This validation form should be same for editActivity().
		 */
		// set individual rules for each passed parameter json file
		ValidationRule activityLabelRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LABEL_ACT"), activityLabel);
		ValidationRule activityDescriptionRule = new ValidationRule(LanguageText.getConstant("DESCRIPTION"), description);
		ValidationRule activityOptimisticRule = new ValidationRule(LanguageText.getConstant("OPTIMISTIC_ACT"), optimistic);
		ValidationRule activityLikelyRule = new ValidationRule(LanguageText.getConstant("LIKELY_ACT"), likely);
		ValidationRule activityPessimisticRule = new ValidationRule(LanguageText.getConstant("PESSIMISTIC_ACT"), pessimistic);
		ValidationRule activityStartDateRule = new ValidationRule(LanguageText.getConstant("START_ACT"), startDate);
		ValidationRule activityFinishDateRule = new ValidationRule(LanguageText.getConstant("FINISH_ACT"), finishDate);
		ValidationRule activityCostRule = new ValidationRule(LanguageText.getConstant("COST_ACT"), cost);
		
		// set restrictions for those rules
		activityLabelRule.doTrim().checkEmpty().checkMaxLength(Config.MAX_TITLE_LENGTH);
		activityDescriptionRule.checkMaxLength(Config.MAX_DESCRIPTION_LENGTH);
		activityOptimisticRule.checkMaxNumValue(Config.MAX_DATE_DURATION).checkMinNumValue(0).checkInt();
		activityLikelyRule.checkMaxNumValue(Config.MAX_DATE_DURATION).checkMinNumValue(0).checkInt();
		activityPessimisticRule.checkMaxNumValue(Config.MAX_DATE_DURATION).checkMinNumValue(0).checkInt();
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);
		activityFinishDateRule.checkEmpty();
		activityCostRule.checkDouble().checkMaxNumValue(Config.MAX_MONEY_AMOUNT).checkMinNumValue(0);
		
		// Custom rule for member
		ValidationRule activityMemberRule = new ValidationRule(LanguageText.getConstant("MEMBER_ACT"), String.valueOf(memberId)){
			@Override
			public boolean validate() {
				
				if(memberId == Config.INVALID){
					
					// TODO Add to language
					setErrorMessage("Please select a member");
					return false;
				}
				
				return true;
			}
		};
		
		// Custom rule for prerequisites
		ValidationRule activityPrerequisitesRule = new ValidationRule(LanguageText.getConstant("PREREQ_ACT"), null) {
			
			@Override
			public boolean validate() {
				
				// Convert int to Integer
				Integer data[] = new Integer[prerequisitesId.length];
				for(int i=0; i < data.length; i++)
					data[i] = prerequisitesId[i];
				
				// Condition for the prerequisite
				Set<Integer> prereqSet = new HashSet<>(Arrays.asList(data));
				
				// Run redundancy test
				if(prereqSet.size() != prerequisitesId.length){
					
					// TODO Add to language
					setErrorMessage("Some activities are added as dependent multiple times.");
					return false;
				}
				return true;
			}
		};
		
		// add a validation form which takes multiple validation rules
		ValidationForm activityValidation = new ValidationForm();

		// add the validation rules to the validation form
		activityValidation.setRule(activityLabelRule);
		activityValidation.setRule(activityDescriptionRule);
		activityValidation.setRule(activityOptimisticRule);
		activityValidation.setRule(activityLikelyRule);
		activityValidation.setRule(activityPessimisticRule);
		activityValidation.setRule(activityStartDateRule);
		activityValidation.setRule(activityFinishDateRule);
		activityValidation.setRule(activityCostRule);
		activityValidation.setRule(activityMemberRule);
		activityValidation.setRule(activityPrerequisitesRule);
		
		// if all the parameters passed respect the restrictions, add a new
		// activity object in this if statement
		if (activityValidation.validate()) {

			// retrieve member object from db by memberId, this is useful for
			// the activity constructor that needs a member object
			Member member = this.member_model.getMemberById(memberId);

			// Fix values
			double doubleCost = StringHelper.zeroOrDouble(cost);
			int intDuration = StringHelper.zeroOrInteger(duration);
			int intOptimistic = StringHelper.zeroOrInteger(optimistic);
			int intLikely = StringHelper.zeroOrInteger(likely);
			int intPessimistic = StringHelper.zeroOrInteger(pessimistic);
			Date DateStartDate = DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT);
			Date DateFinishDate = DateHelper.parse(finishDate, Config.DATE_FORMAT_SHORT);
			
			// Get the project
			Project project = this.project_model.getProjectById(Session.getInstance().getProjectId());
			
			// create activity
			Activity activity = new Activity(activityLabel, description, intDuration, intOptimistic, intLikely, intPessimistic, doubleCost, DateStartDate, DateFinishDate, project.getProjectId(), member.getMemberId(), null);

			// Set project 
			activity.setProject(project);
			
			// Set member
			activity.setMember(member);
			
			// add a new activity to the activity model
			// if insert failed
			int insertedActivityId;
			if ( (insertedActivityId = this.activity_model.insertNewActivity(activity)) == Database.ERROR) {

				// display error message
				this.view.updateCreateActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
			}// if
				// if activity added successfully, update the view because a new activity has been added to a project
			else {
				// get the new list of activities including the new activity and  update the project object

				// Insert prerequisites
				for(int actId : prerequisitesId)
					activity_model.addPrerequisite(insertedActivityId, actId);
					
				List<Activity> activitiesList = new ArrayList<>();

				// if unable to retrieve list of activities return an error message
				if ((activitiesList = this.activity_model.getActivitiesByProject(project)) == null) {
					this.view.updateCreateActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);

					// else assign the list to the project object
				} else {
					
					// Get information about the activities
					for(Activity currentActivity : activitiesList){
						
						// Fetch from the DB
						Member currentMember = member_model.getMemberById(currentActivity.getMemberId());
						List<Activity> currentActivityPrerequisites = activity_model.getActivityPrerequisites(currentActivity);
						
						// Set the member
						currentActivity.setMember(currentMember);
						
						// Set the list of prerequisites
						currentActivity.setPrerequisites(currentActivityPrerequisites);
					}
					
					// Attach the list of activities
					project.setAcitivies(activitiesList);

					// update the view with the new project object and display successful activity creation message
					this.view.updateCreateActivity(true, String.format(LanguageText.getConstant("CREATED"), LanguageText.getConstant("ACTIVITY_ACT")), project);
				}
			}// else
		} else {
			
			// Display error when validation error
			this.view.updateCreateActivity(false, activityValidation.getError(),null);
		}
	}//end of createActivity

	/**
	 * This method updates the activity information when user edits it.
	 * Update is done in the view and in the db
	 * This method is very similar to insertActivity. The only difference is when we update the view
	 * we call updateEditActivity instead of updateCreateActivity method and pass different error messages
	 * @author Melissa Duong 
	 * @date July-05-2015
	 */
	@Override
	public void editActivity(final int activityId, String activityLabel, String description, String duration, String optimistic, String likely, String pessimistic, String cost, String startDate, String finishDate, final int memberId, final int[] prerequisitesId) {
		
		/**
		 * This validation form should be same for createActivity().
		 */
		// set individual rules for each passed parameter json file
		ValidationRule activityLabelRule = new ValidationRule(LanguageText.getConstant("ACTIVITY_LABEL_ACT"), activityLabel);
		ValidationRule activityDescriptionRule = new ValidationRule(LanguageText.getConstant("DESCRIPTION"), description);
		ValidationRule activityOptimisticRule = new ValidationRule(LanguageText.getConstant("OPTIMISTIC_ACT"), optimistic);
		ValidationRule activityLikelyRule = new ValidationRule(LanguageText.getConstant("LIKELY_ACT"), likely);
		ValidationRule activityPessimisticRule = new ValidationRule(LanguageText.getConstant("PESSIMISTIC_ACT"), pessimistic);
		ValidationRule activityStartDateRule = new ValidationRule(LanguageText.getConstant("START_ACT"), startDate);
		ValidationRule activityFinishDateRule = new ValidationRule(LanguageText.getConstant("FINISH_ACT"), finishDate);
		ValidationRule activityCostRule = new ValidationRule(LanguageText.getConstant("COST_ACT"), cost);
		
		// set restrictions for those rules
		activityLabelRule.doTrim().checkEmpty().checkMaxLength(Config.MAX_TITLE_LENGTH);
		activityDescriptionRule.checkMaxLength(Config.MAX_DESCRIPTION_LENGTH);
		activityOptimisticRule.checkMaxNumValue(Config.MAX_DATE_DURATION).checkMinNumValue(0).checkInt();
		activityLikelyRule.checkMaxNumValue(Config.MAX_DATE_DURATION).checkMinNumValue(0).checkInt();
		activityPessimisticRule.checkMaxNumValue(Config.MAX_DATE_DURATION).checkMinNumValue(0).checkInt();
		activityStartDateRule.checkEmpty().checkDateBefore(finishDate);
		activityFinishDateRule.checkEmpty();
		activityCostRule.checkDouble().checkMaxNumValue(Config.MAX_MONEY_AMOUNT).checkMinNumValue(0);
		
		// Custom rule for member
		ValidationRule activityMemberRule = new ValidationRule(LanguageText.getConstant("MEMBER_ACT"), String.valueOf(memberId)){
			@Override
			public boolean validate() {
				
				if(memberId == Config.INVALID){
					
					// TODO Add to language
					setErrorMessage("Please select a member");
					return false;
				}
				
				return true;
			}
		};
		
		// Custom rule for prerequisites
		ValidationRule activityPrerequisitesRule = new ValidationRule(LanguageText.getConstant("PREREQ_ACT"), null) {
			
			@Override
			public boolean validate() {
				
				// Convert int to Integer
				Integer data[] = new Integer[prerequisitesId.length];
				for(int i=0; i < data.length; i++)
					data[i] = prerequisitesId[i];
				
				// Condition for the prerequisite
				Set<Integer> prereqSet = new HashSet<>(Arrays.asList(data));
				
				// Run self check test
				for(int i=0; i < prerequisitesId.length; i++){
					if(activityId == prerequisitesId[i]){
						
						// TODO Add to language
						setErrorMessage("You cannot choose a self dependent activity.");
						return false;
					}
				}
				
				// Run redundancy test
				if(prereqSet.size() != prerequisitesId.length){
					
					// TODO Add to language
					setErrorMessage("Some activities are added as dependent multiple times.");
					return false;
				}
				
				// Detect cycle
				if(prerequisitesId.length > 0) {
					
					// Get all activities for the current project
					List<Activity> allActivitiesForProject = activity_model.getActivitiesByProjectId(Session.getInstance().getProjectId());
					
					// Create graph
					Graph graph = new Graph(allActivitiesForProject.size());
					
					// Get old prerequisite for each activity
					for(Activity currentAcitivty : allActivitiesForProject) {
						
						// Get prerequisites for this activity
						List<Activity> currentActivityPrerequisites = activity_model.getActivityPrerequisites(currentAcitivty.getActivityId());
						
						// Loop on the prerequisites
						for(Activity currentPrerequisite : currentActivityPrerequisites)
							// Feed the graph
							graph.addEdge(currentPrerequisite.getActivityId(), currentAcitivty.getActivityId());
					}
					
					// Get new prerequisite for the current activity
					for(int i=0; i < prerequisitesId.length; i++){
						
						// Feed the graph
						graph.addEdge(prerequisitesId[i], activityId);
					}
					
					// If cycle detected
					if(graph.isCyclic()) {
						
						// TODO Add to language
						setErrorMessage("A cycle has been detected.");
						return false;
					}
				}
				
				return true;
			}
		};
		
		// add a validation form which takes multiple validation rules
		ValidationForm activityValidation = new ValidationForm();

		// add the validation rules to the validation form
		activityValidation.setRule(activityLabelRule);
		activityValidation.setRule(activityDescriptionRule);
		activityValidation.setRule(activityOptimisticRule);
		activityValidation.setRule(activityLikelyRule);
		activityValidation.setRule(activityPessimisticRule);
		activityValidation.setRule(activityStartDateRule);
		activityValidation.setRule(activityFinishDateRule);
		activityValidation.setRule(activityCostRule);
		activityValidation.setRule(activityMemberRule);
		activityValidation.setRule(activityPrerequisitesRule);

		// if all the parameters passed respect the restrictions, add a new
		// activity object in this if statement
		if (activityValidation.validate()) {

			// retrieve member object from db by memberId, this is useful for
			// the activity constructor that needs a member object
			Member member = this.member_model.getMemberById(memberId);

			// Fix values
			double doubleCost = StringHelper.zeroOrDouble(cost);
			int intDuration = StringHelper.zeroOrInteger(duration);
			int intOptimistic = StringHelper.zeroOrInteger(optimistic);
			int intLikely = StringHelper.zeroOrInteger(likely);
			int intPessimistic = StringHelper.zeroOrInteger(pessimistic);
			Date DateStartDate = DateHelper.parse(startDate, Config.DATE_FORMAT_SHORT);
			Date DateFinishDate = DateHelper.parse(finishDate, Config.DATE_FORMAT_SHORT);
			
			// Get the project
			Project project = this.project_model.getProjectById(Session.getInstance().getProjectId());
			
			// create activity
			Activity activity = new Activity(activityLabel, description, intDuration, intOptimistic, intLikely, intPessimistic, doubleCost, DateStartDate, DateFinishDate, project.getProjectId(), member.getMemberId(), null);

			// Set project 
			activity.setProject(project);
			
			// Set the activity id
			activity.setActivityId(activityId);
			
			// Set member
			activity.setMember(member);
						
			// if update failed
			if ( !this.activity_model.updateActivity(activity)) {

				// display error message
				this.view.updateEditActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);
			}// if
				// if activity updated successfully, update the view because a new activity has been added to a project
			else {
				// get the new list of activities including the new activity and  update the project object

				// Delete all the old prerequisites
				activity_model.deletePrerequisitesForAnActivity(activityId);
				
				// Insert prerequisites
				for(int actId : prerequisitesId)
					activity_model.addPrerequisite(activityId, actId);
					
				List<Activity> activitiesList = new ArrayList<>();

				// if unable to retrieve list of activities return an error message
				if ((activitiesList = this.activity_model.getActivitiesByProject(project)) == null) {
					this.view.updateEditActivity(false,LanguageText.getConstant("ERROR_OCCURED"), null);

					// else assign the list to the project object
				} else {
					
					// Get information about the activities
					for(Activity currentActivity : activitiesList){
						
						// Fetch from the DB
						Member currentMember = member_model.getMemberById(currentActivity.getMemberId());
						List<Activity> currentActivityPrerequisites = activity_model.getActivityPrerequisites(currentActivity);
						
						// Set the member
						currentActivity.setMember(currentMember);
						
						// Set the list of prerequisites
						currentActivity.setPrerequisites(currentActivityPrerequisites);
					}
					
					// Attach the list of activities
					project.setAcitivies(activitiesList);

					// update the view with the new project object and display successful activity creation message
					this.view.updateEditActivity(true, String.format(LanguageText.getConstant("UPDATED"), LanguageText.getConstant("ACTIVITY_ACT")), project);
				}
			}// else
		} else {
			
			// Display error when validation error
			this.view.updateEditActivity(false, activityValidation.getError(),null);
		}
	}//end of editActivity

	@Override
	public void loadActivitiesByProject(int projectId) {

		// Load project
		Project project = project_model.getProjectById(projectId);
		
		List<Activity> projectActivities = this.activity_model.getActivitiesByProject(project);
		if (projectActivities==null) {
			
			this.view.updateLoadActivitiesByProject(false, LanguageText.getConstant("ERROR_OCCURED"), null);
		}
		
		else{
			
			this.view.updateLoadActivitiesByProject(true, null , projectActivities);		
		}
	}//end of loadActivitiesByProject


	/**
	 * Load all corresponding activities by memberID
	 * these activities are also classified by projec to which they are assigned
	 * @param memberId the member Id is an integer
	 */
	@Override
	public void loadActivitiesForAllProjectByMember(int memberId) {
		//TODO implement a getActivitiesByMemberId method
		//TODO implement getProjectId method
		
		List<Project> projectByMember=this.project_model.getAllProjects();
		//get all the activities associated with a memberId from the db
	//	List<Activity> activitiesAssignedToMember =this.activity_model.getActivitiesByMemberId(memberId);//arraylist
		
		//****temporary replacement for the code line above so it runs correctly****
		List<Activity> activitiesAssignedToMember = new ArrayList<>();//arraylist
		
		Member testMember=new Member("Melissa", "Duong", "meDuong", "password");
		Project testProject=new Project("testProject", 200.00, DateHelper.parse("11-04-1234", Config.DATE_FORMAT_SHORT), DateHelper.parse("11-04-1236", Config.DATE_FORMAT_SHORT), "hello everybody");
//		Activity testActivity=new Activity("testActivity", 1, DateHelper.parse("12-04-1234", Config.DATE_FORMAT_SHORT), DateHelper.parse("12-04-1236", Config.DATE_FORMAT_SHORT), testProject, testMember, (double) 1);
		
//		activitiesAssignedToMember.add(testActivity);
		//****temporary replacement for the code line above so it runs correctly****
		
		
		//add project Ids associated with each activities
		Set <Integer> projectIdSet=new HashSet<>();//set of integers
		
		for (int i=0; i<activitiesAssignedToMember.size(); i++)
		{
			//projectIdSet.add(activitiesAssignedToMember.get(i).getProjectId());
			
			//********here is a temporary replacement to the above line in comment to not cause errors******
			projectIdSet.add(1);
			//********here is a temporary replacement to not cause errors*******

		}
		
		//convert the set containing the project ids to a list so that we can do nested loops below
		List <Integer>projectIdList=new ArrayList<>();
		projectIdList.addAll(projectIdSet);
		
		//create an arraylist containing all project objects with their projectId
		List <Project> projectsAssignedToMemberList= new ArrayList<>();
		
		
		for (int i=0; i<projectIdSet.size(); i++)
		{
			//add all projects assigned to a member into a list
			projectsAssignedToMemberList.add(this.project_model.getProjectById(projectIdList.get(i)));
			
			
			List<Activity>activitiesByProjectList=new ArrayList<>();
			
			//loop through all activities assigned to a member and create a list of activities
			//for a corresponding project. add the respective list of activities to the respective project
			for (int j=0;j<activitiesAssignedToMember.size(); j++)
			{
				//if    (activitiesAssignedToMember.get(j).getProjectId())== projectIdList.get(i))
				
				//********here is a temporary replacement to the above line in comment not cause errors******
				if    (true)
				//********here is a temporary replacement to not cause errors******
				
				{
					activitiesByProjectList.add(activitiesAssignedToMember.get(j));
					projectsAssignedToMemberList.get(i).setAcitivies(activitiesByProjectList);
	
				}
			
			}//end of loop through all activities- inner for loop
		}//end of loop through project list- outer for loop
			
	
	}//end of loadActivitiesForAllProjectByMember

	@Override
	public void loadActivity(int activityId) {
		
		// Fetch activity from DB
		Activity activity = activity_model.getActivityById(activityId);
		
		// If activity not found
		if(activity == null) {
			
			// Update view
			this.view.updateLoadActivity(false, LanguageText.getConstant("ERROR_OCCURED"), null);
			
		// If activity found
		} else {
			
			// Fetch member from DB
			Member member = member_model.getMemberById(activity.getMemberId());
			
			// Fetch prerequisite
			List<Activity> prerequisites = activity_model.getActivityPrerequisites(activityId);
			
			// Assign member to activity
			activity.setMember(member);
			
			// Assign prerequisites
			activity.setPrerequisites(prerequisites);
			
			// Update view
			this.view.updateLoadActivity(true, null, activity);
		}
	}

}//end of Activity Controller Class