package ninefoo.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Helpers methods for manipulating a Date
 *
 * @author Farzad MajidFayyaz, Amir El Bawab
 */
public class DateHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Validate date, and check if it exists
     *
     * @param date
     * @return boolean
     */
    public static boolean isValid(String date, String dateFormat) {
        if (date != null) {
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
     *
     * @param date
     * @param dateFormat
     * @return Date or null
     * @throws ParseException
     */
    public static Date parse(String date, String dateFormat) {
        if (date != null && !date.trim().isEmpty()) {
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
     *
     * @param originalDate the input date to be converted to the correct format.
     * @return string representation of the input date in the correct format, NULL if the
     * input is empty.
     */
    public static String format(Date originalDate, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        if (originalDate != null)
            return sdf.format(originalDate);

        return null;
    }

    /**
     * Get Min Activity Date
     *
     * @param activities
     */
    public static Date getMinDate(List<Date> dates) {
        Date minDate = null;
        if (dates != null) {
            if (dates.size() == 1)
                return dates.get(0);

            for (int i = 0; i < dates.size(); i++) {
                if (minDate == null || minDate.after(dates.get(i)))
                    minDate = dates.get(i);

            }
        }
        return minDate;

    }

    /**
     * Get Max Activity Date
     *
     * @param activities
     */
    public static Date getMaxDate(List<Date> dates) {
        Date maxDate = null;
        if (dates != null) {
            if (dates.size() == 1)
                return dates.get(0);

            for (int i = 0; i < dates.size(); i++) {
                if (maxDate == null || maxDate.before(dates.get(i)))
                    maxDate = dates.get(i);
            }
        }
        return maxDate;

    }

    /**
     * Get Difference of Max - Min Dates in int Number of days
     */
    public static int getDifferenceDates(Date minDate, Date maxDate) {
        if (minDate.after(maxDate))
            throw new IllegalArgumentException("Min date should be at most the same as Max date!");
        long diff = maxDate.getTime() - minDate.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

    /**
     * Get today date
     *
     * @return Date today
     */
    public static Date getToday() {
        Calendar defaultCal = Calendar.getInstance();
        return defaultCal.getTime();
    }

    /**
     * Get a date after/before x days from today
     *
     * @param day
     * @return date
     */
    public static Date getDateRelativeToToday(int day) {
        return getDateRelativeToDate(getToday(), day);
    }

    /**
     * Get a date after/before x days from date n
     *
     * @param date
     * @param day
     * @return date
     */
    public static Date getDateRelativeToDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }
}
