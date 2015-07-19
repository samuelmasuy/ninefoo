package ninefoo.lib.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenuItem extends JMenuItem{

	static List<PMMenuItem> menuItemList = new ArrayList<PMMenuItem>(); 
	
	private String key;
	
	public PMMenuItem(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
		menuItemList.add(this);
	}
	
	public void Refresh(){
		this.setText(LanguageText.getConstant(key));
	}
}
