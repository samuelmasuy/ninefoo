package ninefoo.view.member;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import ninefoo.application.Application;
import ninefoo.lib.FormPanel;
import ninefoo.view.member.listeners.LoginListener;

public class Login_view extends FormPanel{
	
	// Define components
	private JButton loginButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel registerText, errorMessage;
	private LoginListener loginListener;
	
	// Constructor
	public Login_view() {
		
		// Call super constructor
		super();
		
		// Initialize components
		this.loginButton = new JButton("Login");
		this.usernameField = new JTextField(10);
		this.passwordField = new JPasswordField(10);
		this.registerText = new JLabel("<html>Don't have an account ? <font color=\"#000099\"><u>Register now</u></font></html>");
		this.errorMessage = new JLabel();
		
		// Configure buttons
		Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		this.usernameField.setBorder(BorderFactory.createCompoundBorder(this.usernameField.getBorder(), inputPadding));
		this.passwordField.setBorder(BorderFactory.createCompoundBorder(this.passwordField.getBorder(), inputPadding));
		this.registerText.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Configure error message
		this.errorMessage.setFont(new Font(this.errorMessage.getFont().getFontName(), Font.PLAIN, 12));
		this.errorMessage.setForeground(Color.RED);
		
		// Set border name
		this.titledBorder.setTitle("Welcome!");
		
		// Add listeners for the login button
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(loginListener != null){
					loginListener.login(usernameField.getText(), new String(passwordField.getPassword()));
				}
			}
		});
		
		// Add listeners for the register link
		registerText.addMouseListener(new MouseListener() {
			
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
				if(loginListener != null){
					loginListener.registerLink();
				}
			}
		});
		
		// Add components to fixedPanel
		int row = 0;
		this.gcGrid(row++, 0, 2);
		fixedPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/login_user.png"))), gc);
		
		this.gcGrid(row++, 0, 2);
		fixedPanel.add(this.errorMessage, gc);
		
		this.gcGrid(row, 0, 1);
		fixedPanel.add(new JLabel("Username"), gc);
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.usernameField, gc);
		
		this.gcGrid(row, 0, 1);
		fixedPanel.add(new JLabel("Password"), gc);
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.passwordField, gc);
		
		this.gcGrid(row++, 1, 1);
		fixedPanel.add(this.loginButton, gc);
		
		this.gcGrid(row++, 0, 2);
		fixedPanel.add(registerText, gc);
		
		// Add components to this panel
		this.add(fixedPanel);
		
		// Configure fixedPanel
		this.fixedPanel.setMinimumSize(new Dimension(300,350));
	}
	
	/**
	 * Set login listener
	 * @param loginListener Listener defined in the view/ main class
	 */
	public void setLoginListener(LoginListener loginListener){
		this.loginListener = loginListener;
	}
	
	/**
	 * Set the error message
	 * @param msg Message to be displayed
	 */
	public void setErrorMessage(String msg){
		this.errorMessage.setText(String .format("<html>%s</html>", msg));
	}
}
