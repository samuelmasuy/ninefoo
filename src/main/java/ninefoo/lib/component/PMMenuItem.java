package ninefoo.lib.component;

import javax.swing.JMenuItem;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenuItem extends JMenuItem{

	private String key;
	
	public PMMenuItem(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
	}
	
	public void Refresh(){
		this.setText(LanguageText.getConstant(key));
	}
}
