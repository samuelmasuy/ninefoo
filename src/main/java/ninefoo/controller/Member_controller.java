package ninefoo.controller;

import ninefoo.config.Session;
import ninefoo.lib.LanguageText;
import ninefoo.lib.ValidationForm;
import ninefoo.lib.ValidationRule;
import ninefoo.view.frame.MainView;
import ninefoo.view.frame.UpdatableView;
import ninefoo.view.listeners.MemberListener;

/**
 * Control the 'member' functionality.
 * @see AbstractControlle, MemberListener
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
	public void login(String username, final String password){
		
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
				if(!password.equals("demo")){
					
					// Add custom message for this rule
					if(LanguageText.getCurrentLanguage() == LanguageText.ENGLISH)
						this.setErrorMessage("Password must be \"demo\".");
					
					else if(LanguageText.getCurrentLanguage() == LanguageText.FRENCH)
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
				
				this.view.updateLogin(true, null);
			
			// If user not found
			}else{
				this.view.updateLogin(false, LanguageText.getConstant("WRONG_USERNAME_PASSWORD"));
			}
		
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
	public void register(String firstName, String lastName, String username, String password) {
		
		// FIXME Validate input first (Check above example)
		this.view.updateRegister(true, LanguageText.getConstant("REGISTRATION_SUCCESS"));
	}
}
