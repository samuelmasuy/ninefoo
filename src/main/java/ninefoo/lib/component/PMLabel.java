package ninefoo.lib.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMLabel extends JLabel{

	private String key;
	
	public PMLabel(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
	}
	
	public PMLabel(){
		super();
	}
	
	public PMLabel(ImageIcon icon){
		super(icon);
	}
	
	
}
