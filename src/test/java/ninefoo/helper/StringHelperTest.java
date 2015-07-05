package ninefoo.helper;

import static org.junit.Assert.*;

import org.junit.Test;

import ninefoo.config.Config;
import ninefoo.model.object.Project;

public class StringHelperTest {
	
	/*
	 * This will test the StringHelper by creating a new project and verify if not null and it returns the toString of the object
	 */
	@Test
	public void test01_stringHelper(){
		//Create a project and verify if its an object by calling the StringHelperTest
		Project testProject = new Project("ATestProject3", 1003.00, DateHelper.parse("06/05/2015", Config.DATE_FORMAT_SHORT), DateHelper.parse("07/05/2015", Config.DATE_FORMAT_SHORT), "A Project to test the DB");

		boolean expected = true; 
		boolean actual = false;
		
		if(StringHelper.stringOrEmpty(testProject) != null)
			actual = true;
			System.out.println(testProject.toString());
			
		assertEquals("The testProject is not an object", expected, actual);
	}

	@Test
	public void testJoin() {
		String first = "foo";
		String second = "bar";

		assertEquals("should give foo", first, StringHelper.join(first, null));
		assertEquals("should give bar", second, StringHelper.join(second, null));
		assertEquals("should give foobar", first + ", " + second, StringHelper.join(first, second));
	}

}
