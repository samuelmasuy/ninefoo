package ninefoo.helper;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ninefoo.config.Config;

import org.junit.Test;

public class DateHelperTest {
		
		@Test
		public void isDateValidTest(){
			boolean test=true;
			boolean expected = DateHelper.isValid("29/02/2013", Config.DATE_FORMAT_SHORT);
			//assertEquals("Date Formatting does not work",test,expected);
		}
		@Test
		public void DateParseTest(){
			SimpleDateFormat sdf = new SimpleDateFormat(Config.DATE_FORMAT_SHORT);
			Date testdate = null;
			try {
				testdate = sdf.parse("29/02/2012");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date dateexpected = DateHelper.parse("29/02/2012", Config.DATE_FORMAT_SHORT);
			//System.out.println(testdate.getTime() + " = " + dateexpected.getTime());
			assertEquals("Date Formatting does not work",testdate.getTime(),dateexpected.getTime());
		}
}
