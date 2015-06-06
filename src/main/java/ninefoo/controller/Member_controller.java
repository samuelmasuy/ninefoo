package ninefoo.controller;

import ninefoo.config.Database;
import ninefoo.config.Session;
import ninefoo.lib.LanguageText;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.model.Member;
import ninefoo.model.Member_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.MemberListener;

import org.apache.logging.log4j.LogManager;

/**
 * Control the 'member' functionality.
 * @see AbstractController, MemberListener
 */
public class Member_controller extends AbstractController implements MemberListener{

	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

	// Define Models
	private Member_model member_model;

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
		firstNameRule.checkEmpty().checkFormat("[a-zA-Z]+");
		lastNameRule.checkEmpty().checkFormat("[a-zA-Z]+");
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
		
		// TODO update view to login
	}
}
