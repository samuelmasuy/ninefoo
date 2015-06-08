package ninefoo.lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidationFormTest {
	
	@Test
	public void  test01validate(){
		
		ValidationRule testDate= new ValidationRule("username", "12/05/2015"); 
		ValidationRule testDate2= new ValidationRule("username", "gougones"); 	
	
	//	ValidationRule testMaxLength= new ValidationRule("username", "1230"); 		
	//	ValidationRule testMaxLength2= new ValidationRule("username", "melissaaaaaaaaaaaaaaa"); 
	
//		ValidationRule testLength= new ValidationRule("username", "1234567890000"); 		
//		ValidationRule testLength2= new ValidationRule("username", "melissa"); 
		
		
	//	ValidationRule testFormat= new ValidationRule("username", "melissa");		
	//	ValidationRule testFormat2= new ValidationRule("username","z"); 
				
	//--	ValidationRule testEmpty= new ValidationRule("username", ""); 		
		ValidationRule testEmpty2= new ValidationRule("username","zzz"); 

				
		ValidationForm testNoError= new ValidationForm ();
		ValidationForm testAllError= new ValidationForm ();
		
		//--	testNoError.setRule(testDate);
		testAllError.setRule(testDate2);
		
	/*	testNoError.setRule(testMaxLength);
		testAllError.setRule(testMaxLength2);
		
		testNoError.setRule(testLength);
		testAllError.setRule(testLength2);
		
		testNoError.setRule(testFormat);
		testAllError.setRule(testFormat2);*/
		
	//	testNoError.setRule(testEmpty);
		testAllError.setRule(testEmpty2);
		
	//	assertTrue(testNoError.validate());
	//	assertFalse(testAllError.validate());
		
		testDate2.checkDate();
		assertEquals(testAllError, false);
		
		
	}
	
	

}
