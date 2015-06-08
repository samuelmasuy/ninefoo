package ninefoo.lib;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class ValidationRuleTest {


	public void test01checkEmpty(){
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
	public void test02checkFormat(){
		//validationrule.checkerror
		
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
	
	
	public void test03minLength(){
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
	
	
	public void test04maxLength(){
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
	
	
	public void test05date(){
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
