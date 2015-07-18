package ninefoo.lib.component;

import javax.swing.JMenuItem;

import ninefoo.lib.lang.LanguageText;

public class PMMenuItem extends JMenuItem{

	private String key;
	
	public PMMenuItem(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
	}
}
