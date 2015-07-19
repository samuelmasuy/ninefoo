package ninefoo.lib.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMLabel extends JLabel{

	static List<PMLabel> labelList = new ArrayList<PMLabel>(); 
	
	private String key;
	
	public PMLabel(String key){
		super(LanguageText.getConstant(key));
		this.key = key;
		labelList.add(this);
	}
	
	public PMLabel(){
		super();
		labelList.add(this);
	}
	
	public PMLabel(ImageIcon icon){
		super(icon);
		labelList.add(this);
	}
	
	public void Refresh(){
		this.setText(LanguageText.getConstant(key));
	}
	
	
}
