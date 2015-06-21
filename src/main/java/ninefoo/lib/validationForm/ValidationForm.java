package ninefoo.lib.validationForm;

import java.util.ArrayList;

/**
 * Validate a form and generate automatic messages
 * @author Amir EL Bawab
 */
public class ValidationForm {
	
	// Define variables
	private ArrayList<ValidationRule> rules;
	private String errorMessage;
	private int errorCounter = 0;
	
	// Constructor
	public ValidationForm() {
		this.rules = new ArrayList<>();
	}
	
	/**
	 * Set a new rule
	 * @param rule
	 */
	public void setRule(ValidationRule rule){
		this.rules.add(rule);
	}
	
	/**
	 * Validate all rules
	 * @return boolean
	 */
	public boolean validate(){
		
		// Reset message
		this.errorMessage = "";
		
		// Check if any will fail
		boolean validate = true;
		
		// Validate all rules
		for(int i=0; i < rules.size(); i++){
			
			// Validate rule
			boolean subValidate = rules.get(i).validate();
			
			// If not valid
			if(!subValidate){
				
				// Append to error message
				this.appendErrorMessage(rules.get(i).getError());
				
				// Increment the number of errors
				this.errorCounter++;
			}
			
			// Update error
			validate = validate && subValidate;
		}
		
		return validate;
	}
	
	/**
	 * Replace the auto generated error
	 * @param message New message
	 */
	public void setErrorMessage(String message){
		this.errorMessage = message;
	}
	
	/**
	 * Append to the auto generated error
	 * @param message Appended message
	 */
	public void appendErrorMessage(String message){
		
		// New line
		if(errorCounter > 0)
			message = "<br>" + message;
		
		this.setErrorMessage(this.errorMessage + message);
	}
	
	/**
	 * Get error
	 * @return String
	 */
	public String getError(){
		return this.errorMessage;
	}
}
