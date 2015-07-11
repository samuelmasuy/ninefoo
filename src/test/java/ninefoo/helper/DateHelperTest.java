package ninefoo.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

		@Test
		public void FormatTest(){
			SimpleDateFormat sdf = new SimpleDateFormat(Config.DATE_FORMAT_SHORT);
			Date testdate = new Date();
			String expected = sdf.format(testdate);
			// Will create current date in the format "dd/mm/yyyy"
			Calendar cal = Calendar.getInstance();
			String testparam = String.format("%02d/%02d/%d",
					cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));

			assertEquals("Date Formatting does not work",testparam,expected);
		}

        @Test
        public void testGetMinDate() throws Exception {
			Calendar cal = Calendar.getInstance();
			cal.set(2014, Calendar.JANUARY, 9);
			Date old_date = cal.getTime();
			cal.set(2018, Calendar.JANUARY, 9);
			Date new_date = cal.getTime();
			List<Date> dl = Arrays.asList(new_date, old_date);
			assertEquals("minimum date 2014", old_date, DateHelper.getMinDate(dl));
        }

        @Test
        public void testGetMaxDate() throws Exception {
			Calendar cal = Calendar.getInstance();
			cal.set(2014, Calendar.JANUARY, 9);
			Date old_date = cal.getTime();
			cal.set(2018, Calendar.JANUARY, 9);
			Date new_date = cal.getTime();
			List<Date> dl = Arrays.asList(new_date, old_date);
			assertEquals("maximum date 2018", new_date, DateHelper.getMaxDate(dl));
        }

        @Test
        public void testGetDifferenceDatesException() throws Exception {
			Calendar cal = Calendar.getInstance();
			cal.set(2014, Calendar.JANUARY, 9);
			Date old_date = cal.getTime();
			cal.set(2018, Calendar.JANUARY, 9);
			Date new_date = cal.getTime();

            Throwable e = null;

			try {
				DateHelper.getDifferenceDates(new_date, old_date);
			} catch (Throwable ex) {
				e = ex;
			}

			assertTrue("Should be illegal", e instanceof IllegalArgumentException);
        }

        @Test
        public void testGetDifferenceDates() throws Exception {
            Calendar cal = Calendar.getInstance();
            cal.set(2014, Calendar.JANUARY, 9);
            Date old_date = cal.getTime();
            cal.set(2014, Calendar.JANUARY, 10);
            Date new_date = cal.getTime();

			assertEquals(1, DateHelper.getDifferenceDates(old_date, new_date));

        }
}
