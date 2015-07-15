package ninefoo.controller.handler;

import java.util.List;

import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.controller.handler.template.AbstractController;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.validationForm.ValidationForm;
import ninefoo.lib.validationForm.ValidationRule;
import ninefoo.model.object.Member;
import ninefoo.model.object.Role;
import ninefoo.model.sql.Member_model;
import ninefoo.model.sql.ProjectMember_model;
import ninefoo.model.sql.Role_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.MemberListener;

/**
 * Controller for 'Member': Create, Update and Delete members
 * @author Samuel Masuy
 * @author Melissa Duong
 * @see AbstractController, ActivityListener
 */
public class Member_controller extends AbstractController implements MemberListener{

	// Define Models
	private Member_model member_model;
	private Role_model role_model;
	private ProjectMember_model projectMember_model;
	private Project_controller project_controller;
	/**
	 * Constructor
	 * @param view
	 */
	public Member_controller(UpdatableView view) {
		super(view);
		
		// Initialize models
		this.member_model = new Member_model();
	}
	
	/**
	 * Validate login
	 * @param username
	 * @param password
	 */
	@Override
	public void login(final String username, final String password){
		
		// Create a validation form
		ValidationForm validation = new ValidationForm();
		
		// Create validation rule
		ValidationRule usernameRule = new ValidationRule(LanguageText.getConstant("USERNAME"), username);
		ValidationRule passwordRule = new ValidationRule(LanguageText.getConstant("PASSWORD"), password);
		
		// Set rules for username and password
		usernameRule.checkEmpty().checkFormat("[a-zA-Z0-9]+"); // No special characters
		passwordRule.checkEmpty().checkFormat("[^ ]+"); // No spaces allowed
		
		// Add rules to the validation
		validation.setRule(usernameRule);
		validation.setRule(passwordRule);
		
		// If all input requirements are met
		if(validation.validate()) {
			
			// Check if user is in the database
			Member member;
			if( (member = member_model.getMemberByUsername(username)) != null && member.getPassword().equals(password)){
				
	            // Open session
	            Session newSession = Session.getInstance();
	            newSession.open(); // Session must be opened before setting the data inside it
	            newSession.setUserId(member.getMemberId());
	            
	            // Update view
	            this.view.updateLogin(true, null);
			
			// If login is not successful
			} else {
				
				// Display error
				this.view.updateLogin(false, LanguageText.getConstant("WRONG_USERNAME_PASSWORD"));
			}
            
		// If requirements are not met
		} else {
			
			// Display errors
			this.view.updateLogin(false, validation.getError());
		}
	}

	/**
	 * Validate registration
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	@Override
	public void register(String firstName, String lastName, String username, final String password) {
		
		// Create a validation form
		ValidationForm validation = new ValidationForm();

		// Create validation rule
		ValidationRule usernameRule = new ValidationRule(LanguageText.getConstant("USERNAME"), username);
		ValidationRule firstNameRule = new ValidationRule(LanguageText.getConstant("FIRST_NAME"), firstName);
		ValidationRule lastNameRule = new ValidationRule(LanguageText.getConstant("LAST_NAME"), lastName);
		ValidationRule passwordRule = new ValidationRule(LanguageText.getConstant("PASSWORD"), password);

		// Set rules for the fields
		usernameRule.checkEmpty().checkFormat("[a-zA-Z0-9]+");
		firstNameRule.checkEmpty().checkFormat("[a-zA-Z ]+");
		lastNameRule.checkEmpty().checkFormat("[a-zA-Z ]+");
		passwordRule.checkMinLength(4).checkMaxLength(10).checkFormat("[^ ]+");

		// Add rules to the validation
		validation.setRule(usernameRule);
		validation.setRule(firstNameRule);
		validation.setRule(lastNameRule);
		validation.setRule(passwordRule);

		// If all requirements are met
		if(validation.validate()){

			// Create a temp member
			Member newMember = new Member(firstName, lastName, username, password);
			
			// Check if user is unique
			if(member_model.getMemberByUsername(username) == null){
				
				// If registration not successful
				if(member_model.insertNewMember(newMember) == Database.ERROR){
					
					// Display error
					this.view.updateRegister(false, LanguageText.getConstant("ERROR_OCCURED"));
				} else {

					// Display success
					this.view.updateRegister(true, LanguageText.getConstant("REGISTRATION_SUCCESS"));
				}
			
			// If user is not unique
			} else {

				// Display error
				this.view.updateRegister(false, String.format(LanguageText.getConstant("UNIQUE"), LanguageText.getConstant("USERNAME")));
			}
			
		// If requirements are not met
		} else {
			this.view.updateRegister(false, validation.getError());
		}
	}
	
	/**
	 * Logout
	 */
	@Override
	public void logout() {
		Session.getInstance().close();
		this.view.updateLogout();
	}

	@Override
	public void loadAllMembers() {
		
		// Get all members
		List<Member> members = member_model.getAllMembers();
		
		// If error occurred
		if(members == null) {
			
			// Display error
			this.view.updateLoadAllMembers(false, LanguageText.getConstant("ERROR_OCCURED"), null);
		
		// If users found
		} else {
			
			// Load them
			this.view.updateLoadAllMembers(true, null, members);
		}
	}

	
	/**
	 * This method is used when a project is created 
	 * and the manager wants to directly register a new member 
	 * and assign him/her to a role in the project.
	 * Refers to the 'new member' icon in the GUI
	 * 
	 * @param firstName first name
	 * @param lastName last name
	 * @param username username
	 * @param password password
	 * @param roleName role of the new member
	 * @param projectId Id of the project the member is assigned to
	 */
	@Override
	public void registerAndAssign(String firstName, String lastName, String username, String password, String roleName, int projectId) {
		
		//user successfully inserted
		//assignmet was successfull
		
		
		//check first name last name username and password
		//check role in db
		
		//register follows register() method in this class
		//assign user to the project
		//its the icon that says NewMember
		//update the view
		//project controller for role if it is not working
		// Create a validation form
		ValidationForm validation = new ValidationForm();

		// Create validation rule
		ValidationRule usernameRule = new ValidationRule(LanguageText.getConstant("USERNAME"), username);
		ValidationRule firstNameRule = new ValidationRule(LanguageText.getConstant("FIRST_NAME"), firstName);
		ValidationRule lastNameRule = new ValidationRule(LanguageText.getConstant("LAST_NAME"), lastName);
		ValidationRule passwordRule = new ValidationRule(LanguageText.getConstant("PASSWORD"), password);

		// Set rules for the fields
		usernameRule.checkEmpty().checkFormat("[a-zA-Z0-9]+");
		firstNameRule.checkEmpty().checkFormat("[a-zA-Z ]+");
		lastNameRule.checkEmpty().checkFormat("[a-zA-Z ]+");
		passwordRule.checkMinLength(4).checkMaxLength(10).checkFormat("[^ ]+");

		// Add rules to the validation
		validation.setRule(usernameRule);
		validation.setRule(firstNameRule);
		validation.setRule(lastNameRule);
		validation.setRule(passwordRule);

		// If all requirements are met
		if(validation.validate()){

			// Create a temp member
			Member newMember = new Member(firstName, lastName, username, password);
			
			// Check if user is unique
			if(member_model.getMemberByUsername(username) == null){
				
				// If registration not successful
				if(member_model.insertNewMember(newMember) == Database.ERROR){
					
					// Display error
					this.view.updateRegisterAndAssign (false, LanguageText.getConstant("ERROR_OCCURED"));
				} else {
					/*
					 * 
					 */
					
					// Get the role from the DB
					//error if cannot retrieve member?					
					Role memberRole=role_model.getRoleByName(roleName);
					
					// If user not already assigned
					if(!projectMember_model.getAssignedAnyRole(newMember.getMemberId(), projectId)) {
						
						// If insert is successful
						if(projectMember_model.addMemberToProject(projectId, newMember.getMemberId(), memberRole)){
							
							// TODO Add to the language
							this.view.updateRegisterAndAssign(true, "Member successfully added");
						
						// If failed
						} else {
							
							// Display error
							this.view.updateRegisterAndAssign(false, LanguageText.getConstant("ERROR_OCCURED"));
						}
						
					// If user already assigned
					} else {
						
						// TODO add to the language
						this.view.updateRegisterAndAssign(false, "User already assigned!");
					}
					
					/**
					 * 
					 * 
					 */
/*					//assign member to role and insert it in the db
					//should we throw an exception if we are unable to retrieve the role of a member??
					project_controller.addUserToProject(newMember.getMemberId(), projectId, roleName);*/

					// Display success //should we display registration success , and member added ????
					this.view.updateRegisterAndAssign(true, LanguageText.getConstant("REGISTRATION_SUCCESS"));
				}
			
			// If user is not unique
			} else {

				// Display error
				this.view.updateRegisterAndAssign(false, String.format(LanguageText.getConstant("UNIQUE"), LanguageText.getConstant("USERNAME")));
			}
			
		// If requirements are not met
		} else {
			this.view.updateRegisterAndAssign(false, validation.getError());
		}
		
		
	}
}
