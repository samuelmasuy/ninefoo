package ninefoo.helper;

import ninefoo.config.Config;
import org.apache.logging.log4j.LogManager;

/**
 * Helper methods for String
 *
 * @author Amir EL Bawab
 */
public class StringHelper {

    // Logger
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * Check if the object exist and return its <code>toString()</code> if it does, otherwise null
     *
     * @param object
     * @return String or null
     */
    public static String stringOrEmpty(Object object) {
        return object == null ? "" : object.toString();
    }

    /**
     * Convert the string to integer
     *
     * @param value
     * @return if the string is a valid integer, the method will return this integer. If the
     * value is <code>NULL</code>, empty or non-integer, a 0 will be returned.
     */
    public static int zeroOrInteger(String value) {

        // If not found or empty
        if (value == null || value.trim().isEmpty())
            return 0;

        // If not a number
        try {
            int intValue = Integer.parseInt(value);
            return intValue;
        } catch (NumberFormatException e) {
            LOGGER.error(String.format("Cannot convert '%s' to Integer!", value));
        }
        return 0;
    }

    /**
     * Convert the string to double
     *
     * @param value
     * @return if the string is a valid integer, the method will return this integer. If the
     * value is <code>NULL</code>, empty or non-integer, a 0 will be returned.
     */
    public static double zeroOrDouble(String value) {

        // If not found or empty
        if (value == null || value.trim().isEmpty())
            return 0.0;

        // If not a number
        try {
            String format = "%." + Config.DECIMAL_ROUND + "f";
            double doubleValue = Double.parseDouble(String.format(format, Double.parseDouble(value)));
            return doubleValue;
        } catch (NumberFormatException e) {
            LOGGER.error(String.format("Cannot convert '%s' to Double!", value));
        }
        return 0;
    }

    /**
     * Join two strings with a comma
     *
     * @param original Original message
     * @param extra    Additional message
     * @return Concatinated message
     */
    public static String join(String original, String extra) {
        if (original == null)
            return extra;

        if (extra == null)
            return original;

        String string = original;
        if (!string.isEmpty())
            string += ", ";
        return string + extra;
    }
}
