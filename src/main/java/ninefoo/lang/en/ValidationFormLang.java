package ninefoo.lang.en;

import ninefoo.lang.Language;

public class ValidationFormLang implements Language{
	
	public static String REQUIRED;
	public static String WRONG_FORMAT;
	public static String WRONG_USERNAME_PASSWORD;
	public static String MIN_LENGTH;
	public static String MAX_LENGTH;
	
	@Override
	public void load_en() {
		ValidationFormLang.REQUIRED = "%s is required.";
		ValidationFormLang.WRONG_FORMAT = "%s is not valid.";
		ValidationFormLang.WRONG_USERNAME_PASSWORD = "Wrong username or password.";
		ValidationFormLang.MIN_LENGTH = "%s must be at least %d.";
		ValidationFormLang.MAX_LENGTH = "%s must be at most %d.";
	}
	
	@Override
	public void load_fr() {
		ValidationFormLang.REQUIRED = "%s est requis.";
		ValidationFormLang.WRONG_FORMAT = "%s est invalide.";
		ValidationFormLang.WRONG_USERNAME_PASSWORD = "Nom d'utilisateur ou mot de passe incorrect.";
		ValidationFormLang.MIN_LENGTH = "%s doit être au moins %d.";
		ValidationFormLang.MAX_LENGTH = "%s doit être au plus %d.";
	}
}
