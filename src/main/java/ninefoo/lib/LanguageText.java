package ninefoo.lib;

import java.util.ArrayList;

import ninefoo.lang.en.ValidationFormLang;
import ninefoo.lang.Language;

public class LanguageText {
	
	// Define language array list
	ArrayList<Language> languages;
	
	// Store current language
	public final int ENGLISH = 0;
	public final int FRENCH = 1;
	private int currentLanguage;
	
	// Constructor
	public LanguageText() {
		
		// Initialize language array list
		languages = new ArrayList<>();
		
		// Add language classes
		languages.add(new ValidationFormLang());
	}
	
	/**
	 * Load English text
	 */
	public void load_en(){
		for(int i=0; i < languages.size(); i++)
			this.languages.get(i).load_en();
		this.currentLanguage = this.ENGLISH;
	}
	
	/**
	 * Load French text
	 */
	public void load_fr(){
		for(int i=0; i < languages.size(); i++)
			this.languages.get(i).load_fr();
		this.currentLanguage = this.FRENCH;
	}
	
	/**
	 * Get current language
	 * @return int (e.g. <code>ENGLISH</code>, <code>FRENCH</code>)
	 */
	public int getCurrentLanguage(){
		return this.currentLanguage;
	}
}
