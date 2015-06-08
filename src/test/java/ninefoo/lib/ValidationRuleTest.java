package ninefoo.lib;
import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

public class ValidationRuleTest {

	// Define restriction
		private boolean emptyChecker = false;
		private boolean formatChecker = false;
		private boolean minLengthChecker = false;
		private boolean maxLengthChecker = false;
		private boolean dateChecker = false;
		
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
		public ValidationRuleTest(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
	
	@Test
	public void TestRules(){
		ValidationRuleTest expected = new ValidationRuleTest("Rule1","Kickass music");
		
	}
	
}
