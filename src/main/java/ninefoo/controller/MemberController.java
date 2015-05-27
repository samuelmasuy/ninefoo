package ninefoo.controller;

import ninefoo.application.Application;
import ninefoo.config.Session;
import ninefoo.lang.en.ValidationFormLang;
import ninefoo.lib.ValidationFeedback;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;

public class Member_controller {
	
	// Constructor
	public Member_controller() {
		// TODO to be completed
		
	}
	
	/**
	 * Validate login
	 * @param username
	 * @param password
	 * @return ValidationFeedback
	 */
	public ValidationFeedback login(String username, final String password){
		
		// Create a validation form
		ValidationForm validation = new ValidationForm();
		
		// Create validation rule
		ValidationRule usernameRule = new ValidationRule("Username", username);
		ValidationRule passwordRule;
		
		// Set rules for username
		usernameRule.checkEmpty().checkFormat("[a-zA-Z0-9]*");
		
		// Create custom rules for password
		passwordRule = new ValidationRule("Password", password){
			@Override
			public boolean validate() {
				
				// Keep super validation (Recommended)
				boolean validate = super.validate();
				
				// Check if password is equal to demo
				if(!password.equals("demo")){
					
					// Add custom message for this rule
					if(Application.LANGUAGE_TEXT.getCurrentLanguage() == Application.LANGUAGE_TEXT.ENGLISH)
						this.setErrorMessage("Password must be \"demo\".");
					
					else if(Application.LANGUAGE_TEXT.getCurrentLanguage() == Application.LANGUAGE_TEXT.FRENCH)
						this.setErrorMessage("Mot de passe doit etre \"demo\".");
					
					return false;
				}
				
				return validate;
			}
		};
		
		// Add rules to the validation
		validation.setRule(usernameRule);
		validation.setRule(passwordRule);
		
		// If all requirements are met
		if(validation.validate()){
			
			// FOR TEST ONLY - (Username: demo, Password: demo)
			if(username.equals("demo") && password.equals("demo")){
				
				// Open session
				Session newSession = Session.getInstance();
				newSession.open(); // Session must be opened before setting the data inside it
				newSession.setUserId(1);
				
				return new ValidationFeedback(true, null);
			
			// If user not found
			}else{
				return new ValidationFeedback(false, ValidationFormLang.WRONG_USERNAME_PASSWORD);
			}
		
		// If requirements are not met
		} else {
			return new ValidationFeedback(false, validation.getError());
		}
	}
}
