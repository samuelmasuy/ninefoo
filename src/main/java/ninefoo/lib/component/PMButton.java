package ninefoo.lib.component;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PMButton extends JButton{

	public PMButton(String title){
		super(title);
	}
	
	public PMButton(ImageIcon icon){
		super(icon);
	}
	
	public PMButton(String title, ImageIcon icon){
		super(title, icon);
	}
}
