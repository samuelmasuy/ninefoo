package ninefoo.lib.lang;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ninefoo.lib.lang.parserObjects.LanguageObject;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Singleton class, loads all the language constants and put them in a hash map.
 *
 * @author Amir EL Bawab
 */
public class LanguageText {

    // Logger - Must be declared before the Singleton instance
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    // Singleton
    private static final LanguageText instance = new LanguageText();

    // Define language array list
    private ArrayList<String> languages;
    private HashMap<String, String> phrases;

    // Store current language
    public static final String ENGLISH = "en";
    public static final String FRENCH = "fr";
    public static final String GERMAN = "de";
    private String currentLanguage;


    // Constructor
    private LanguageText() {

        // Initialize variable
        languages = new ArrayList<>();
        phrases = new HashMap<>();

        // Add languages
        languages.add(ENGLISH);
        languages.add(FRENCH);
        languages.add(GERMAN);

    }

    /**
     * Add constants in different languages if available
     *
     * @param className
     */
    private void addLanguageToInstance(String className) {

        // Load class
        for (String language : languages) {

            String classPath = String.format("/lang/%s/%sLang.json", language, className);
            ObjectMapper mapper = new ObjectMapper();
            try {

                // Read file
                InputStream file = getClass().getResourceAsStream(classPath);
                LanguageObject lang = mapper.readValue(file, LanguageObject.class);
                Iterator<Map.Entry<String, String>> iter = lang.language.entrySet().iterator();
                while (iter.hasNext()) {
                    Entry<String, String> entry = iter.next();
                    phrases.put(entry.getKey() + "_" + language, entry.getValue());
                    iter.remove();
                }
                LOGGER.info(String.format("\"%s\" version of \"%s\" was loaded succesfully!", language, lang.name));
            } catch (JsonParseException e) {
                LOGGER.error(String.format("Parsing error in language file: '%s'", classPath));
            } catch (JsonMappingException e) {
                LOGGER.error(String.format("Mapping error in language file: '%s'", classPath));
            } catch (IOException e) {
                LOGGER.error(String.format("Error loading language file: '%s'", classPath));
            }
        }
    }

    /**
     * Add new language to instance
     *
     * @param className
     */
    public static void addLanguage(String className) {
        LanguageText.instance.addLanguageToInstance(className);
    }

    /**
     * Get constant
     *
     * @param constantName
     * @return String or <code>"undefined"</code> if not found
     */
    public static String getConstant(String constantName) {
        String key = constantName + "_" + instance.currentLanguage;
        if (instance.phrases.get(key) != null)
            return instance.phrases.get(constantName + "_" + instance.currentLanguage);
        return "undefined";
    }

    /**
     * Get current language
     *
     * @return int (e.g. <code>ENGLISH</code>, <code>FRENCH</code>)
     */
    public static String getCurrentLanguage() {
        return instance.currentLanguage;
    }

    /**
     * Set language
     *
     * @param language
     */
    public static void setLanguage(String language) {
        switch (language) {
            case ENGLISH:
            case GERMAN:
                LanguageText.instance.currentLanguage = language;
                LOGGER.info("Language set to: " + language);
            case FRENCH:
                LanguageText.instance.currentLanguage = language;
                LOGGER.info("Language set to: " + language);
        }
    }
}
