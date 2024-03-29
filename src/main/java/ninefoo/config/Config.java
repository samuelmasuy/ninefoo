package ninefoo.config;

import ninefoo.config.Annotation.AutoloadAtRuntime;
import ninefoo.config.Annotation.AutoloadConfig;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class contains constants and methods that configure the application
 *
 * @author Amir EL Bawab, Samuel Masuy, Farzad MajidFayyaz, Sebouh Bardakjian
 * @see Autoload
 */
public class Config {

    // Logger
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    // Application name
    public final static String APPLICATION_NAME = "PM Expert";

    // Application version
    public final static String APPLICATION_VERSION = "1.0";

    // Path for the application
    public final static String APPLICATION_PATH = "ninefoo";

    // Date format used when creating (and parsing) dates.
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // Date format short version
    public final static String DATE_FORMAT_SHORT = "dd/MM/yyyy";

    // Date format alpha version
    public final static String DATE_FORMAT_ALPHA = "MMMMM d, yyyy";

    // Max title length
    public final static int MAX_TITLE_LENGTH = 25;

    // Max description length
    public final static int MAX_DESCRIPTION_LENGTH = 150;

    // Max date duration
    public final static int MAX_DATE_DURATION = 100000;

    // Max money amount
    public final static int MAX_MONEY_AMOUNT = 1000000000;

    // Invalid information
    public final static int INVALID = -1;

    // Round value
    public final static int DECIMAL_ROUND = 2;

    /**
     * Auto load methods
     *
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void autoload() {

        LOGGER.info("--------- AUTOLOAD START ----------");

        // Auto load methods in Autoload.java with annotation @autoload(active = true)
        // The methods are not executed in any order, so make sure they don't conflict, or use priority attribute
        Autoload autoload = new Autoload();
        AutoloadConfig configAnnotation = autoload.getClass().getAnnotation(AutoloadConfig.class);
        Method methods[] = autoload.getClass().getMethods();

        // If class annotation exists
        if (configAnnotation != null) {

            // Execute methods by priority
            for (int priority = 0; priority <= configAnnotation.lowestPriority(); priority++) {
                for (Method method : methods) {
                    AutoloadAtRuntime autoloadAnnotation = method.getAnnotation(AutoloadAtRuntime.class);

                    if (autoloadAnnotation != null) {
                        if (autoloadAnnotation.active() && autoloadAnnotation.priority() == priority) {
                            try {
                                LOGGER.info(String.format("Autoload method: %s with priority: %d", method.getName(), autoloadAnnotation.priority()));
                                method.invoke(autoload);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            // Run methods with priority more than last priority
            for (Method method : methods) {
                AutoloadAtRuntime autoloadAnnotation = method.getAnnotation(AutoloadAtRuntime.class);

                if (autoloadAnnotation != null) {
                    if (autoloadAnnotation.active() && autoloadAnnotation.priority() > configAnnotation.lowestPriority()) {
                        try {
                            LOGGER.info(String.format("Autoload method: %s with priority: %d", method.getName(), autoloadAnnotation.priority()));
                            method.invoke(autoload);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        LOGGER.info("--------- AUTOLOAD COMPLETE ----------");
    }
}
