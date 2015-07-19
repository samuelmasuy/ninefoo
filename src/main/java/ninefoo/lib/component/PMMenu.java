package ninefoo.lib.component;

import javax.swing.JMenu;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenu extends JMenu{

	private String key;
	
	public PMMenu(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
	}
}
