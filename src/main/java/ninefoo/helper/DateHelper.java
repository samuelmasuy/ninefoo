package ninefoo.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import ninefoo.config.Config;

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
	 * @return Date or null
	 */
	public static Date parse(String date, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
}
