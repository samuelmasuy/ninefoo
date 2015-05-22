package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.Controller;

public class ProjectManagement extends JFrame{
	
	// Define menu
	private Menu menu;
	
	// Define panels
	private ProjectTools projectTools;
	private StatusBar statusBar;
	private ProjectBuilder projectBuilder;
	private MainContent mainContent;
	
	// Define controllers
	private Controller controller;
	
	public ProjectManagement(String AppTitle) {
		
		// Call super constructor
		super(AppTitle);
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize meun
		this.menu = new Menu();
		
		// Initialize panels
		this.projectTools = new ProjectTools();
		this.statusBar = new StatusBar();
		this.projectBuilder = new ProjectBuilder();
		this.mainContent = new MainContent();
		
		// Initialize controller
		this.controller = new Controller();
		
		// Add panels
		this.add(this.projectTools, BorderLayout.NORTH);
		this.add(this.statusBar, BorderLayout.SOUTH);
		this.add(this.projectBuilder, BorderLayout.WEST);
		this.add(this.mainContent, BorderLayout.CENTER);
		
		// Add menu
		this.setJMenuBar(menu);
		
		// Configure the JFrame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		// Exit when click on X
		this.setPreferredSize(new Dimension(1000, 800)); 	// Frame initial size
		this.setVisible(true); 								// Make the frame visible
		this.pack();										// Force setting the size of components
		this.setLocationRelativeTo(null); 					// Load on center of the screen
	}
}
