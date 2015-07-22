package ninefoo.lib.component;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMButton extends JButton implements Refreshable{

    private static final long serialVersionUID = 2967182422525479036L;

    private String key;
    private String toolTipKey;


    public PMButton(String key) {
		this(key, false);
    }

	public PMButton(ImageIcon icon, boolean addToList){
        super(icon);
		if (addToList){
			componentList.add(this);
		}	
    }

    public PMButton(String key, ImageIcon icon) {
        super(icon);
        this.key = key;
        super.setText(LanguageText.getConstant(key));
    }

	public PMButton(String key, boolean addToList) {
		this.setText(key);
		this.key = key;
		if (addToList){
			componentList.add(this);
		}
	}

    public void setText(String key) {
        super.setText(LanguageText.getConstant(key));
        this.key = key;
    }

    public void setToolTipText(String key) {
        super.setToolTipText(LanguageText.getConstant(key));
        this.toolTipKey = key;
	}
	
	public void refresh(){
		this.setText(key);
		this.setToolTipText(toolTipKey);
    }

}
