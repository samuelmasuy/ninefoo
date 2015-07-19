package ninefoo.lib.component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMButton extends JButton{
	
	private static final long serialVersionUID = 2967182422525479036L;
	
	private String key;
	private String toolTipKey;
	
	
	public PMButton(String key){
		this.setText(key);
		this.key = key;
	}
	
	public PMButton(ImageIcon icon){
		super(icon);
	}
	
	public PMButton(String key, ImageIcon icon){
		super(icon);
		this.key = key;
		super.setText(LanguageText.getConstant(key));
	}
	
	public void setText(String key){
		super.setText(LanguageText.getConstant(key));
		this.key = key;
	}
	
	public void setToolTipText(String key){
		super.setToolTipText(LanguageText.getConstant(key));
		this.toolTipKey = key;
	}
	
	public void Refresh(){
		this.setText(LanguageText.getConstant(key));
		this.setToolTipText(LanguageText.getConstant(toolTipKey));
	}
	
}
