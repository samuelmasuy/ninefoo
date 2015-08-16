package ninefoo.lib.lang;

import org.junit.Test;
import static org.junit.Assert.*;

public class LanguageTextTest {

	@Test
	public void testAddLanguage_wrong_file() {
		assertEquals(false, LanguageText.addLanguage("FILE_DOES_NOT_EXIST"));
	}
	
	@Test
	public void testAddLanguage_wrong_constant() {
		LanguageText.setLanguage(LanguageText.ENGLISH);
		assertEquals("undefined", LanguageText.getConstant("CONSTANT_NOT_FOUND"));
	}
	
	@Test
	public void testAddLanguage_correct_file() {
		assertEquals(true, LanguageText.addLanguage("Activity"));
	}
	
	@Test
	public void testAddLanguage_correct_constant() {
		LanguageText.setLanguage(LanguageText.ENGLISH);
		assertEquals("Activity", LanguageText.getConstant("ACTIVITY_ACT"));
	}
}
