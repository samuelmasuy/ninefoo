package ninefoo.view.include.footer;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ninefoo.lib.component.PMLabel;

/**
 * Footer status bar.
 * @author Amir El Bawab
 */
public class StatusBar extends JPanel{
	
	private static final long serialVersionUID = -5534459903549181858L;
	
	// Define components
	private PMLabel status;
	
	// Constructor
	public StatusBar(){
		
		// Set layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Initialize components
		this.status = new PMLabel("e.g. A new project has been created successfully");
		
		// Add components
		this.add(this.status);
		
		// Configure JPanel
		this.setPreferredSize(new Dimension(0, 30)); // Size of the JPanel (Width is automatically set)
		this.setVisible(false);
	}
}
