package ninefoo.lib.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenu extends JMenu{

	static List<PMMenu> menuList = new ArrayList<PMMenu>(); 
	
	private String key;
	
	public PMMenu(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
		menuList.add(this);
	}
	
	public void Refresh(){
		this.setText(LanguageText.getConstant(key));
	}
}
