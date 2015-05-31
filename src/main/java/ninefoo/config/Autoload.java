package ninefoo.config;

import org.apache.logging.log4j.LogManager;

import ninefoo.config.Annotation.autoload;
import ninefoo.lib.LanguageText;

public class Autoload {
	
	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
		
	@autoload(active = true)
	public void loadEngLanguage(){
		LanguageText.setLanguage(LanguageText.ENGLISH);
		LOGGER.info("Auto loaded language is: English");
	}
	
	
	@autoload(active = false)
	public void loadFrLanguage(){
		LanguageText.setLanguage(LanguageText.FRENCH);
		LOGGER.info("Auto loaded language is: French");
	}
}
