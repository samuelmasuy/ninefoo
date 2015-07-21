package ninefoo.lib.component;

import java.util.ArrayList;
import java.util.List;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenu extends JMenu implements Refreshable{

	//static List<PMMenu> menuList = new ArrayList<PMMenu>(); 
	
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
