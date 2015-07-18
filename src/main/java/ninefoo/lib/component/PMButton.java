package ninefoo.lib.component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ninefoo.lib.lang.LanguageText;

public class PMButton extends JButton{

	private String key;
	
	public PMButton(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
	}
	
	public PMButton(ImageIcon icon){
		super(icon);
	}
	
	public PMButton(String key, ImageIcon icon){
		super(LanguageText.getConstant(key), icon);
		this.key = key;
	}
}
