package ninefoo.lib;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class ValidationRuleTest {

	@Test
	public void test01checkEmpty(){
		/**
		 * Two test cases: one where an array is empty and one where an array is not empty
		 * An empty string is returned if the validation passes (with .getError() method), and an error message string is returned 
		 * when the validation fails.
		 */
		ValidationRule testEmpty= new ValidationRule("username", "z"); 
		testEmpty.checkEmpty();//check against this
		testEmpty.validate();//populate the error message if any, for the testing
		String testEmptyError=testEmpty.getError();
		
		ValidationRule testEmpty2= new ValidationRule("username",""); 
		testEmpty2.checkEmpty();//check against this
		testEmpty2.validate();//populate the error message if any, for the testing
		String testEmptyError2=testEmpty2.getError();
		
		assertNull(testEmptyError);
		assertNotNull(testEmptyError2);
		
		
		
	}
	
	@Test
	public void test02checkFormat(){
		/**
		 * Two test cases: one where the username follows the pattern tested against, and one that does not follow 
		 * the pattern tested against.
		 *An empty string is returned if the validation passes (with .getError() method), and an error message string is returned 
		 * when the validation fails.
		 */
		
		ValidationRule testFormat= new ValidationRule("username", "melissa"); 
		testFormat.checkFormat("melissa");//check against this
		testFormat.validate();//populate the error message if any, for the testing
		String testFormatError=testFormat.getError();
		
		ValidationRule testFormat2= new ValidationRule("username","z"); 
		testFormat2.checkFormat("[abc]");//check against this
		testFormat2.validate();//populate the error message if any, for the testing
		String testFormatError2=testFormat2.getError();
		

		assertNull(testFormatError);
		assertNotNull(testFormatError2);
		
		
	}
	
	@Test
	public void test03minLength(){
		/**
		 * Two test cases: one where the username exceeds the minimum required length, and one where the username does not
		 * exceed the minimum required length
		 * An empty string is returned if the validation passes (with .getError() method), and an error message string is returned 
		 * when the validation fails.
		 */
		
		
		ValidationRule testLength= new ValidationRule("username", "1234567890000"); 
		testLength.checkMinLength(10);
		testLength.validate();
		String testLengthError=testLength.getError();
		
		ValidationRule testLength2= new ValidationRule("username", "melissa"); 
		testLength2.checkMinLength(10);
		testLength2.validate();
		String testLengthError2=testLength2.getError();
		
		
		assertNull(testLengthError);
		assertNotNull(testLengthError2);
		
	}
	
	@Test
	public void test04maxLength(){
		
		/**
		 * Two test cases: one where the username does not exceeds the max required length, and one where the username 
		 * exceed the max required length
		 * An empty string is returned if the validation passes (with .getError() method), and an error message string is returned 
		 * when the validation fails.
		 */
		ValidationRule testMaxLength= new ValidationRule("username", "1230"); 
		testMaxLength.checkMaxLength(10);
		testMaxLength.validate();
		String testMaxLengthError=testMaxLength.getError();
		
		ValidationRule testMaxLength2= new ValidationRule("username", "melissaaaaaaaaaaaaaaa"); 
		testMaxLength2.checkMaxLength(10);
		testMaxLength2.validate();
		String testMaxLengthError2=testMaxLength2.getError();
		
		
		assertNull(testMaxLengthError);
		assertNotNull(testMaxLengthError2);
		
	}
	
	@Test
	public void test05date(){
		/**
		 * Two test cases: one where the date format is correct, and one where the date format is wrong
		 * An empty string is returned if the validation passes (with .getError() method), and an error message string is returned 
		 * when the validation fails.
		 */
		ValidationRule testDate= new ValidationRule("username", "12/05/2015"); 
		testDate.checkDate();
		testDate.validate();
		String testDateError=testDate.getError();
		
		ValidationRule testDate2= new ValidationRule("username", "gougones"); 
		testDate2.checkDate();
		testDate2.validate();
		String testDateError2=testDate2.getError();
		
		
		assertNull(testDateError);
		assertNotNull(testDateError2);
		
	}
	
	@Test
	public void test06validate(){
		
		/**
		 * Multiple test cases to verify that the validate method works. When a String does pass a validation test,
		 * it returns True, if it passes the validation test, it returns false.
		 */
		ValidationRule testDate= new ValidationRule("username", "12/05/2015"); 
		testDate.checkDate();
		testDate.validate();
		
		
		ValidationRule testDate2= new ValidationRule("username", "gougones"); 
		testDate2.checkDate();
		testDate2.validate();
	
		ValidationRule testMaxLength= new ValidationRule("username", "1230"); 
		testMaxLength.checkMaxLength(10);
		testMaxLength.validate();
		//String testMaxLengthError=testMaxLength.getError();
		
		ValidationRule testMaxLength2= new ValidationRule("username", "melissaaaaaaaaaaaaaaa"); 
		testMaxLength2.checkMaxLength(10);
		testMaxLength2.validate();
		//String testMaxLengthError2=testMaxLength2.getError();
		
		ValidationRule testLength= new ValidationRule("username", "1234567890000"); 
		testLength.checkMinLength(10);
		testLength.validate();
		//String testLengthError=testLength.getError();
		
		ValidationRule testLength2= new ValidationRule("username", "melissa"); 
		testLength2.checkMinLength(10);
		testLength2.validate();
		//String testLengthError2=testLength2.getError();
		
		ValidationRule testFormat= new ValidationRule("username", "melissa"); 
		testFormat.checkFormat("melissa");//check against this
		testFormat.validate();//populate the error message if any, for the testing
		//String testFormatError=testFormat.getError();
		
		ValidationRule testFormat2= new ValidationRule("username","z"); 
		testFormat2.checkFormat("[abc]");//check against this
		testFormat2.validate();//populate the error message if any, for the testing
		
		
		ValidationRule testEmpty= new ValidationRule("username", "z"); 
		testEmpty.checkEmpty();//check against this
		testEmpty.validate();//populate the error message if any, for the testing
		
		
		ValidationRule testEmpty2= new ValidationRule("username",""); 
		testEmpty2.checkEmpty();//check against this
		testEmpty2.validate();//populate the error message if any, for the testing
		
		
		
		assertTrue(testDate.validate());
		assertFalse(testDate2.validate());
		
		assertTrue(testMaxLength.validate());
		assertFalse(testMaxLength2.validate());
		
		assertTrue(testLength.validate());
		assertFalse(testLength2.validate());
		
		assertTrue(testFormat.validate());
		assertFalse(testFormat2.validate());
		
		assertTrue(testEmpty.validate());
		assertFalse(testEmpty2.validate());
	}
	
	
	
}
