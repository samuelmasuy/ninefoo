package ninefoo.lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidationFormTest {
	
	@Test
	public void  test01validate(){
		/**
		 * This method tests multiple validation rules. 
		 * When validating, it should return true if every rule passes against
		 * a form checking method. If one or more rules fails the form checking, 
		 * the returned value of .validate() is false.
		 * Two test cases are given here: one which every rule is correct and one where
		 * every rule is false.
		 */
		
		ValidationRule testDate= new ValidationRule("username", "12/05/2015"); 
		ValidationRule testDate2= new ValidationRule("username", "gougones"); 
		
		testDate.checkDate();
		testDate2.checkDate();
	
	
		ValidationRule testEmpty= new ValidationRule("username", "zzz"); 		
		ValidationRule testEmpty2= new ValidationRule("username",""); 
		
		testEmpty.checkEmpty();
		testEmpty2.checkEmpty();

				
		ValidationForm testNoError= new ValidationForm ();
		ValidationForm testAllError= new ValidationForm ();
		
		
		testNoError.setRule(testDate);
		testAllError.setRule(testDate2);
		
		testNoError.setRule(testEmpty);
		testAllError.setRule(testEmpty2);
		
		assertTrue(testNoError.validate());
		assertFalse(testAllError.validate());
		
		
	}
	
	

}
