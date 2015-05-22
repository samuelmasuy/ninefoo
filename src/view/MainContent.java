package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MainContent extends JPanel{
	
	// Constructor
	public MainContent() {
		
		// Set border
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		// Set background color (FOR DEMO ONLY)
		this.setBackground(Color.WHITE);
		
		// Configure JPanel
		this.setVisible(true);
	}
}
