package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel{
	
	// Define components
	private JLabel status;
	
	// Constructor
	public StatusBar(){
		
		// Set layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Initialize components
		this.status = new JLabel("e.g. A new project has been created successfully");
		
		// Add components
		this.add(this.status);
		
		// Configure JPanel
		this.setPreferredSize(new Dimension(0, 30)); // Size of the JPanel (Width is automatically set)
		this.setVisible(true);
	}
}
