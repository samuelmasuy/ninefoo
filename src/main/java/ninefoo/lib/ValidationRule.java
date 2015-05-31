package ninefoo.lib;

import java.util.regex.Pattern;

/**
 * Rules for input fields.
 */
public class ValidationRule {
	
	// Define restriction
	private boolean emptyChecker = false;
	private boolean formatChecker = false;
	private boolean minLengthChecker = false;
	private boolean maxLengthChecker = false;
	
	// Variables
	private String name;
	private String value;
	private String pattern;
	private String errorMessage;
	
	/**
	 * Constructor
	 * @param name Input name
	 * @param value Input value
	 */
	public ValidationRule(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Verify that the input is not empty
	 * @return this
	 */
	public ValidationRule checkEmpty(){
		this.emptyChecker = true;
		return this;
	}
	
	/**
	 * Verify that the value matches a pattern
	 * @param pattern Regex pattern
	 * @return this
	 */
	public ValidationRule checkFormat(String pattern){
		this.pattern = pattern;
		formatChecker = true;
		return this;
	}
	
	/**
	 * Verify that the value is at least <code>minLength</code>
	 * @param minLength Minimum length
	 * @return this
	 */
	private int minLength;
	public ValidationRule checkMinLength(int minLength){
		this.minLength = minLength;
		this.minLengthChecker = true;
		return this;
	}
	
	/**
	 * Verify that the value is at most <code>maxLength</code>
	 * @param maxLength Maximum length
	 * @return this
	 */
	private int maxLength;
	public ValidationRule checkMaxLength(int maxLength){
		this.maxLength = maxLength;
		this.maxLengthChecker = true;
		return this;
	}
	
	/**
	 * Run validation test
	 * @return boolean
	 */
	public boolean validate(){
		
		// If empty checker is enabled, check if empty
		if(this.emptyChecker){
			if(this.value.isEmpty()){
				errorMessage = String.format(LanguageText.getConstant("REQUIRED"), this.name);
				return false;
			}
		}
		
		// If format checker is enabled, check if pattern matches
		if(this.formatChecker){
			if(!Pattern.matches(this.pattern, this.value)){
				errorMessage = String.format(LanguageText.getConstant("WRONG_FORMAT"), this.name);
				return false;
			}
		}
		
		// If min length checker is enabled, check length
		if(this.minLengthChecker){
			if(this.value.length() < minLength){
				errorMessage = String.format(LanguageText.getConstant("MIN_LENGTH"), this.name, minLength);
				return false;
			}
		}
		
		// If max length checker is enabled, check length
		if(this.maxLengthChecker){
			if(this.value.length() > maxLength){
				errorMessage = String.format(LanguageText.getConstant("MAX_LENGTH"), this.name, maxLength);
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Replace the auto generated error
	 * @param message New message
	 */
	public void setErrorMessage(String message){
		this.errorMessage = message;
	}
	
	/**
	 * Get error message
	 * @return String or <code>null</code> if <code>validate()</code> was not executed
	 */
	public String getError(){
		return this.errorMessage;
	}
}
