package ninefoo.lib.validationForm;

import java.util.Date;
import java.util.regex.Pattern;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;

/**
 * Rules for input fields.
 * @author Amir EL Bawab
 * @author Melissa
 */
public class ValidationRule {
	
	// Define restriction
	private boolean emptyChecker = false;
	private boolean formatChecker = false;
	private boolean minLengthChecker = false;
	private boolean maxLengthChecker = false;
	private boolean dateChecker = false;
	private boolean doubleChecker = false;
	private boolean dateBeforeChecker = false;
	private boolean dateAfterChecker = false;
	private boolean maxNumChecker = false;
	private boolean intChecker = false;
	
	// Variables
	private String name;
	private String value;
	private String pattern;
	private String errorMessage;
	private String dateBefore;
	private String dateAfter;
	
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
	 * Verify that the value is a date
	 * @return ValidationRule
	 */
	@Deprecated
	public ValidationRule checkDate(){
		this.dateChecker = true;
		return this;
	}
	
	/**
	 * Verify that the value is a double
	 * @return ValidationRule
	 */
	public ValidationRule checkDouble(){
		this.doubleChecker = true;
		return this;
	}
	
	/**
	 * Check if current date is before passed date
	 * @param date Future date
	 * @return ValidationRule
	 */
	public ValidationRule checkDateBefore(String date){
		this.dateBeforeChecker = true;
		this.dateBefore = date;
		return this;
	}
	
	/**
	 * Check if current date is after passed date
	 * @param date History date
	 * @return ValidationRule
	 */
	public ValidationRule checkDateAfter(String date){
		this.dateAfterChecker = true;
		this.dateAfter = date;
		return this;
	}
	
	/**
	 * Trim value
	 * @return ValidationRule
	 */
	public ValidationRule doTrim(){
		this.value.trim();
		return this;
	}
	
	/**
	 * Verify that the value is an int
	 * @author melissa
	 * @return ValidationRule
	 */
	public ValidationRule checkInt(){
		this.intChecker = true;
		return this;
	}
		
	
	/**
	 * Checks if value is lower than maxValue
	 * @author Melissa 
	 * @param maxValue maximum value
	 * @return ValidationRule
	 */
	private int maxValue;
	public ValidationRule checkMaxNumValue(int maxValue) {
		maxNumChecker=true;
		this.maxValue=maxValue;
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
		
		// If date checker is enabled, check if date is valid
		if(this.dateChecker){
			if(!value.isEmpty()){
				if(! DateHelper.isValid(value, Config.DATE_FORMAT_SHORT)){
					errorMessage = String.format(LanguageText.getConstant("WRONG_FORMAT") + " e.g. %s", name, Config.DATE_FORMAT_SHORT.toLowerCase());
					return false;
				}
			}
		}
		
		// If double checker is enabled, check if is double
		if(this.doubleChecker){
			
			// If value is not empty
			if(!this.value.isEmpty()){
				try{
					Double.parseDouble(this.value);
				} catch(NumberFormatException e){
					errorMessage = String.format(LanguageText.getConstant("WRONG_FORMAT"), this.name);
					return false;
				}
			}
		}
		
		// If date before checker is enabled, check if current date is before target date
		if(this.dateBeforeChecker){
			
			// If values are not empty
			if(!this.value.isEmpty() && !this.dateBefore.isEmpty()){
				Date historyDate = DateHelper.parse(this.value, Config.DATE_FORMAT_SHORT);
				Date futureDate = DateHelper.parse(this.dateBefore, Config.DATE_FORMAT_SHORT);
				if(historyDate.after(futureDate)){
					errorMessage = String.format(LanguageText.getConstant("WRONG_DATE_BEFORE_DATE"), this.name, this.dateBefore);
					return false;
				}
			}
		}
		
	
		
		// If date after checker is enabled, check if current date is after target date
		if(this.dateAfterChecker){
			
			// If values are not empty
			if(!this.value.isEmpty() && !this.dateAfter.isEmpty()){
				Date historyDate = DateHelper.parse(this.dateAfter, Config.DATE_FORMAT_SHORT);
				Date futureDate = DateHelper.parse(this.value, Config.DATE_FORMAT_SHORT);
				if(historyDate.after(futureDate)){
					errorMessage = String.format(LanguageText.getConstant("WRONG_DATE_AFTER_DATE"), this.name, this.dateAfter);
					return false;
				}
			}
		}
		
		
		
		
	//catch error if argument entered is not an integer
		if(this.intChecker){
			if(!this.value.isEmpty()){
				try{
					Integer.parseInt(this.value);
				} catch(NumberFormatException e){
					errorMessage = String.format(LanguageText.getConstant("WRONG_FORMAT"), this.name);
					return false;
				}
				
			}
			
			
		}
		
		//Using maxNumChecker implies that an integer form has been entered,
		//if the entered argument is a string ex. 'dog' then, the previous if statement
		//will catch the error, return false, and never reach this if statement
		if (this.maxNumChecker) {

			// If values are not empty
			if (!this.value.isEmpty()) {
				if (Integer.parseInt(this.value) > this.maxValue){
				errorMessage = String.format(LanguageText.getConstant("MAX_NUM_VALUE"), this.name);
				return false;
				}
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
