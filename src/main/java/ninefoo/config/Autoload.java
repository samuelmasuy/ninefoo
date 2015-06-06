package ninefoo.config;

import org.apache.logging.log4j.LogManager;

import ninefoo.config.Annotation.autoload;
import ninefoo.lib.LanguageText;
import ninefoo.model.DbManager;
import ninefoo.model.Member;
import ninefoo.model.Member_model;

/**
 * Methods in this class will be loaded automatically if they have the annotation <code>@autoload</code> and [optional] parameter <code>active = true</code><br>
 * Note that the order of method execution in this class is random.<br>
 * To temporarily disable a method, make the <code>active = false</code> in the annotation.
 */
public class Autoload {
	
	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
		
	@autoload
	public void loadEngLanguage(){
		LanguageText.setLanguage(LanguageText.ENGLISH);
		LOGGER.info("Auto loaded language is: English");
	}
	
	@autoload(active = false)
	public void loadFrLanguage(){
		LanguageText.setLanguage(LanguageText.FRENCH);
		LOGGER.info("Auto loaded language is: French");
	}
	
	@autoload
	public void loadLanguages(){
		
		// Add language classes - Exclude the Lang from the class name
		LanguageText.addLanguage("ValidationForm");
		LanguageText.addLanguage("InputForm");
	}
	
	@autoload
	public void createDB(){
		DbManager.createTables();
	}
	
	@autoload(active=false)
	public void createDemoUser(){
		Member newMember = new Member("demo", "demo", "demo", "demo");
		Member_model mm = new Member_model();
		int success = mm.insertNewMember(newMember);
		LOGGER.info(success);
	}
}
