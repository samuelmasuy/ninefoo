package ninefoo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ninefoo.model.object.Activity;

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
	
	/**
	 * Get Min Activity Date
	 * @param activities
	 */
	public static Date getMinDate(List<Date> dates){
		Date minDate = null;
		if (dates != null){
			if (dates.size() == 1)
				return dates.get(0);
			
			for (int i = 0; i < dates.size(); i++){
				System.out.println(minDate);
				System.out.println(dates.get(i));
				if (minDate == null || minDate.after(dates.get(i)))
					minDate = dates.get(i);
				System.out.println("Min: " + minDate);
				System.out.println();
				
			}
		}
		return minDate;
		
	}
	
	/**
	 * Get Max Activity Date
	 */
	public static Date getMaxDate(List<Activity> activities){
		Date maxDate = null;
		if (activities != null){
			if (activities.size() == 1)
				return activities.get(0).getFinishDate();
			
			for (int i = 0; i < activities.size() - 1; i++){
				if (activities.get(i).getFinishDate().compareTo(activities.get(i + 1).getFinishDate()) > 0){
					maxDate = activities.get(i).getFinishDate();
				} else {
					maxDate = activities.get(i + 1).getFinishDate();
				}
			}
		}
		return maxDate;
		
	}
	
	/**
	 * Get Difference of Max - Min Dates in int Number of days
	 */
	public static int getDifferenceDates(Date minDate, Date maxDate){
		if (minDate.after(maxDate))
			throw new IllegalArgumentException("Min date should be at most the same as Max date!");
		long diff = maxDate.getTime() - minDate.getTime();
		return (int)(diff / (24 * 60 * 60 * 1000));
	}
	
	
}
