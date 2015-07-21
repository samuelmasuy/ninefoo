package ninefoo.lib.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ninefoo.lib.lang.LanguageText;

/**
 * @author Sebouh Bardakjian
 */
public class PMLabel extends JLabel implements Refreshable {

	//static List<PMLabel> labelList = new ArrayList<PMLabel>(); 
	
	private String key;
	
	public PMLabel(String key){
		this(key, false);
	}
	
	public PMLabel(){
		super();
	}
	
	public PMLabel(ImageIcon icon){
		super(icon);
	}
	
	public PMLabel(String key, boolean addToList) {
		super(LanguageText.getConstant(key));
		this.key = key;
		if (addToList){
			componentList.add(this);
		}	
	}

	public void refresh(){
		this.setText(LanguageText.getConstant(key));
	}
	
	
}
