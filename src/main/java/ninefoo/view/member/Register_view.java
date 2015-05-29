package ninefoo.view.member;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ninefoo.lib.FormPanel;
import ninefoo.view.member.listeners.RegisterListener;

public class Register_view extends FormPanel{
	
	// Define components
	private JButton registerButton;
	private JTextField usernameField, firstName, lastName;
	private JPasswordField passwordField;
	private JLabel loginText;
	private RegisterListener registerListener;
	
	// Constructor
	public Register_view() {
		
		// Initialize components
		this.registerButton = new JButton("Register");
		this.firstName = new JTextField(10);
		this.lastName = new JTextField(10);
		this.usernameField = new JTextField(10);
		this.passwordField = new JPasswordField(10);
		this.loginText = new JLabel("<html>Already registered ? <font color=\"#000099\"><u>Login now</u></font></html>");
		
		// Configure buttons
		Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		this.firstName.setBorder(BorderFactory.createCompoundBorder(this.firstName.getBorder(), inputPadding));
		this.lastName.setBorder(BorderFactory.createCompoundBorder(this.lastName.getBorder(), inputPadding));
		this.usernameField.setBorder(BorderFactory.createCompoundBorder(this.usernameField.getBorder(), inputPadding));
		this.passwordField.setBorder(BorderFactory.createCompoundBorder(this.passwordField.getBorder(), inputPadding));
		this.loginText.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Set Border title
		titledBorder.setTitle("New account");
		
		// Add listeners for the register button
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(registerListener != null){
					registerListener.register(firstName.getText(), lastName.getText(), usernameField.getText(), new String(passwordField.getPassword()));
				}
			}
		});
		
		// Add listeners for the login link
		loginText.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(registerListener != null){
					registerListener.loginLink();
				}
			}
		});
		
		// Add components to fixedPanel
		int row = 0;
		this.gcGrid(row++, 0, 2);
		fixedPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/register_user.png"))), gc);
		
		this.gcGrid(row++, 0, 2);
		fixedPanel.add(this.errorMessage, gc);
		
		this.gcGrid(row, 0, 1);
		fixedPanel.add(new JLabel("First name"), gc);
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.firstName, gc);
		
		this.gcGrid(row, 0, 1);
		fixedPanel.add(new JLabel("Last name"), gc);
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.lastName, gc);
		
		this.gcGrid(row, 0, 1);
		fixedPanel.add(new JLabel("Username"), gc);
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.usernameField, gc);
		
		this.gcGrid(row, 0, 1);
		fixedPanel.add(new JLabel("Password"), gc);
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.passwordField, gc);
		
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.registerButton, gc);
		
		this.gcGrid(row++, 0, 2);
		fixedPanel.add(loginText, gc);
		
		// Add components to this panel
		this.add(fixedPanel);
		
		// Configure fixedPanel
		fixedPanel.setPreferredSize(new Dimension(300,450));
	}
	
	/**
	 * Set register listener
	 * @param registerListener Listener defined in the view/ main class
	 */
	public void setRegisterListener(RegisterListener registerListener){
		this.registerListener = registerListener;
	}
	
	/**
	 * Reset form
	 */
	public void reset(){
		this.errorMessage.setText("");
		this.usernameField.setText("");
		this.passwordField.setText("");
		this.firstName.setText("");
		this.lastName.setText("");
	}
}
