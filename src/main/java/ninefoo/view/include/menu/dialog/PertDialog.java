package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import ninefoo.lib.component.PMButton;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

public class PertDialog extends CenterScrollSouthButtonDialog{
	private PMButton closeButton;
	
	public PertDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
        this.closeButton = new PMButton("CLOSE");
		
        setCenterPanel(new FormDialog() {
			
			@Override
			public void placeForm() {
				
			}
		});
        
        this.southPanel.add(closeButton);
        
		// Configure dialog
        this.setSize(new Dimension(400, 500));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
	}
}
