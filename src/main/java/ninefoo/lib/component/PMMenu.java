package ninefoo.lib.component;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenu extends JMenu implements Refreshable{

	private static final long serialVersionUID = -443009858990289836L;
	
	private String key;

    public PMMenu(String key) {
        super(LanguageText.getConstant(key));
        this.key = key;
		componentList.add(this);
	}
	
	public void refresh(){
		this.setText(LanguageText.getConstant(key));
    }
}
