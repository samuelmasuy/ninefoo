package ninefoo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Helpers methods for manipulating a Date
 * @author Farzad MajidFayyaz, Amir El Bawab
 */
public class DateHelper {

	/**
	 * Validate date, and check if it exists
	 * @param date
	 * @return boolean
	 */
	public static boolean isValid(String date, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(date);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Parse to date
	 * @param date
	 * @param dateFormat
	 * @throws ParseException
	 * @return Date or null
	 */
	public static Date parse(String date, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Returns a string representation in the correct format for the specified date.
	 * @param originalDate the input date to be converted to the correct format.
	 * @return string representation of the input date in the correct format, NULL if the
	 *         input is empty.
	 */
	public static String format(Date originalDate, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		if (originalDate != null)
			return sdf.format(originalDate);

		return null;
	}
}
