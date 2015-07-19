package ninefoo.config;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Farzad MajidFayyaz, Amir El Bawab
 * Date 03-Jun-2015.
 */
public class RoleNames {
	
	// Available roles
	public static String MANAGER = LanguageText.getConstant("MANAGER");
	public static String MEMBER = LanguageText.getConstant("MEMBER");
	
	// Array of available roles
	public static final String[] ROLES = {MANAGER, MEMBER};
	
	public void Refresh(){
		MANAGER = LanguageText.getConstant("MANAGER");
		MEMBER = LanguageText.getConstant("MEMBER");
	}
}
