package ninefoo.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;

import ninefoo.config.Config;

/**
 * Singleton class, loads all the language constants and put them in a hash map.
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
	private String currentLanguage;
	
	
	// Constructor
	private LanguageText() {
		
		// Initialize variable
		languages = new ArrayList<>();
		phrases = new HashMap<>();
		
		// Add languages
		languages.add(ENGLISH);
		languages.add(FRENCH);
		
		// Add language classes - Exclude the Lang from the class name
		this.addLanguage("ValidationForm");
	}
	
	/**
	 * Add constants in different languages if available
	 * @param className
	 */
	private void addLanguage(String className){
		
		// Load class
		for(String language : languages){
			String classPath = String.format("%s.lang.%s.%sLang", Config.APPLICATION_PATH, language, className);
			try {
				Class <?> languageClass = Class.forName(classPath);
				
				// Store variables in a hash map
				for(Field field : languageClass.getFields()){
					try {
						phrases.put(field.getName() + "_" + language, (String)field.get(languageClass));
					} catch (IllegalArgumentException e) {
						LOGGER.error(String.format("Illegal language constant '%s' in %s version of %s", field.getName(), language, className));
					} catch (IllegalAccessException e) {
						LOGGER.error(String.format("Illegal language constant access '%s' in %s version of %s", field.getName(), language, className));
					}
				}
				LOGGER.info(String.format("%s version of %s was loaded succesfully!", language, className));
			} catch (ClassNotFoundException e) {
				LOGGER.error(String.format("%s version of the language '%s' was not found!", language, className));
			}
		}
	}
	
	/**
	 * Get constant
	 * @param constantName
	 * @return String or <code>"undefined"</code> if not found
	 */
	public static String getConstant(String constantName){
		String key = constantName + "_" + instance.currentLanguage;
		if(instance.phrases.get(key) != null)
			return instance.phrases.get(constantName + "_" + instance.currentLanguage);
		return "undefined";
	}
	
	/**
	 * Get current language
	 * @return int (e.g. <code>ENGLISH</code>, <code>FRENCH</code>)
	 */
	public static String getCurrentLanguage(){
		return instance.currentLanguage;
	}
	
	/**
	 * Set language
	 * @param language
	 */
	public static void setLanguage(String language){
		switch(language){
		case ENGLISH:
		case FRENCH:
			LanguageText.instance.currentLanguage = language;
			LOGGER.info("Language set to: " + language);
		}
	}
}
