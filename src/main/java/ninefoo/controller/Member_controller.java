package ninefoo.controller;

import ninefoo.config.Session;
import ninefoo.lib.LanguageText;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.model.Member;
import ninefoo.model.Member_model;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.MemberListener;

/**
 * Control the 'member' functionality.
 * @see AbstractController, MemberListener
 */
public class Member_controller extends AbstractController implements MemberListener{
	
	/**
	 * Constructor
	 * @param view
	 */
	public Member_controller(UpdatableView view) {
		super(view);
	}
	
	/**
	 * Validate login
	 * @param username
	 * @param password
	 */
	@Override
	public void login(final String username, final String password){
		final Member_model mm = new Member_model();
		// Create a validation form
		ValidationForm validation = new ValidationForm();
		
		// Create validation rule
		ValidationRule usernameRule = new ValidationRule(LanguageText.getConstant("USERNAME"), username);
		ValidationRule passwordRule;
		
		// Set rules for username
		usernameRule.checkEmpty().checkFormat("[a-zA-Z0-9]*");
		
		// Create custom rules for password
		passwordRule = new ValidationRule(LanguageText.getConstant("PASSWORD"), password){
			@Override
			public boolean validate() {
				
				// Keep super validation (Recommended)
				boolean validate = super.validate();
				
				// Check if password is equal to demo

				Member memberCheck = mm.getMemberByUsername(username);
				if (memberCheck == null) {
					if (LanguageText.getCurrentLanguage() == LanguageText.ENGLISH)
						this.setErrorMessage("Username does not exist!");

					else if (LanguageText.getCurrentLanguage() == LanguageText.FRENCH)
						this.setErrorMessage("Le nom d'usager n'existe pas dans le repertoire.");
					return false;
				}

				if (memberCheck.getPassword() != password) {
					if (LanguageText.getCurrentLanguage() == LanguageText.ENGLISH)
						this.setErrorMessage("Password does not match");

					else if (LanguageText.getCurrentLanguage() == LanguageText.FRENCH)
						this.setErrorMessage("Le mot de passe est incorrect");
					return false;
				}

				return validate;
			}
		};
		
		// Add rules to the validation
		validation.setRule(usernameRule);
		validation.setRule(passwordRule);
		
		// If all requirements are met
		if(validation.validate()) {
            // Open session
            Session newSession = Session.getInstance();
            newSession.open(); // Session must be opened before setting the data inside it
            newSession.setUserId(1);

            this.view.updateLogin(true, null);
			
		// If requirements are not met
		} else {
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

		// Set rules for username
		usernameRule.checkEmpty().checkFormat("[a-zA-Z0-9]+");
		firstNameRule.checkEmpty().checkFormat("[a-zA-Z]+");
		lastNameRule.checkEmpty().checkFormat("[a-zA-Z]+");
		passwordRule.checkMinLength(4).checkMaxLength(10);

		// Add rules to the validation
		validation.setRule(usernameRule);
		validation.setRule(firstNameRule);
		validation.setRule(lastNameRule);
		validation.setRule(passwordRule);

		// If all requirements are met
		if(validation.validate()){

			Member newMember = new Member(firstName, lastName, username, password);
			Member_model mm = new Member_model();
			int success = mm.insertNewMember(newMember);
			if (success == 0) this.view.updateLogin(false, validation.getError());

			this.view.updateRegister(true, LanguageText.getConstant("REGISTRATION_SUCCESS"));
			// If requirements are not met
		} else {
			this.view.updateRegister(false, validation.getError());
		}
	}
}
