package ninefoo.lib.component;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenuItem extends JMenuItem implements Refreshable{

	private static final long serialVersionUID = 6683646410309115224L;
	
	private String key;

    public PMMenuItem(String key) {
        super(LanguageText.getConstant(key));
        this.key = key;
		componentList.add(this);
	}
	
	public void refresh(){
		this.setText(LanguageText.getConstant(key));
    }
}
