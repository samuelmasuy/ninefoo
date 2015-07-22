package ninefoo.lib.component;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMLabel extends JLabel implements Refreshable {

	private static final long serialVersionUID = -4417341275618072018L;
	
	private String key;

    public PMLabel(String key) {
		this(key, false);
    }

    public PMLabel() {
        super();
    }

    public PMLabel(ImageIcon icon) {
        super(icon);
	}
	
	public PMLabel(String key, boolean addToList) {
		setText(key);
		this.key = key;
		if (addToList){
			componentList.add(this);
		}	
	}
	
	@Override
	public void setText(String key) {
		this.key = key;
		super.setText(LanguageText.getConstant(key));
	}

	public void refresh(){
		this.setText(key);
    }
}
