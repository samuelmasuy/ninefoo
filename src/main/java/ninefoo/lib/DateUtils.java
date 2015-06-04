package ninefoo.lib;

import ninefoo.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Farzad on 03-Jun-2015.
 */
public class DateUtils {
    private static final Logger LOGGER = LogManager.getLogger();

    // Formatter object for parsing and creating dates.
    private final static SimpleDateFormat formatter = new SimpleDateFormat(Config.DATE_FORMAT);

    /**
     * Parses the specified string (which should be in the correct format) to a date.
     * @param dateString the input string to be parsed.
     * @return the parsed date, NULL if the parsing was not successful.
     */
    public static Date parse(String dateString) {

        Date parsedDate = null;

        try {

            if (dateString != null)
                parsedDate = formatter.parse(dateString);

        } catch (ParseException e) {
            LOGGER.warn("Could not parse date. Correct format is '" + Config.DATE_FORMAT +
                    "' --- detailed info: " + e.getMessage());
        }

        return parsedDate;
    }

    /**
     * Returns a string representation in the correct format for the specified date.
     * @param originalDate the input date to be converted to the correct format.
     * @return string representation of the input date in the correct format, NULL if the
     *         input is empty.
     */
    public static String format(Date originalDate) {

        if (originalDate != null)
            return formatter.format(originalDate);

        return null;
    }
}
