package ninefoo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helpers methods for manipulating a Date
 * @author Farzad MajidFayyaz, Amir El Bawab
 */
public class DateHelper {

	 private static final Logger LOGGER = LogManager.getLogger();
	
	/**
	 * Validate date, and check if it exists
	 * @param date
	 * @return boolean
	 */
	public static boolean isValid(String date, String dateFormat){
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			try {
				sdf.parse(date);
				return true;
			} catch (ParseException e) {
				LOGGER.error("Error parsing date: " + date);
			}
		}
		return false;
	}
	
	/**
	 * Parse to date
	 * @param date
	 * @param dateFormat
	 * @throws ParseException
	 * @return Date or null
	 */
	public static Date parse(String date, String dateFormat){
		if(date != null && !date.trim().isEmpty()){
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				LOGGER.error("Error parsing date: " + date);
			}
		}
		return null;
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
