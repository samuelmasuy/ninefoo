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

import ninefoo.helper.LayoutHelper;
import ninefoo.lib.FormPanel;
import ninefoo.lib.LanguageText;
import ninefoo.view.member.listeners.RegisterListener;

public class Register_view extends FormPanel{
	
	private static final long serialVersionUID = 6743725688945067304L;
	
	// Define components
	private JButton registerButton;
	private JTextField usernameField, firstName, lastName;
	private JPasswordField passwordField;
	private JLabel loginText;
	private RegisterListener registerListener;
	
	// Constructor
	public Register_view() {
		
		// Initialize components
		this.registerButton = new JButton(LanguageText.getConstant("REGISTER"));
		this.firstName = new JTextField(10);
		this.lastName = new JTextField(10);
		this.usernameField = new JTextField(10);
		this.passwordField = new JPasswordField(10);
		this.loginText = new JLabel(LanguageText.getConstant("LOGIN_LINK"));
		
		// Configure buttons
		Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		this.firstName.setBorder(BorderFactory.createCompoundBorder(this.firstName.getBorder(), inputPadding));
		this.lastName.setBorder(BorderFactory.createCompoundBorder(this.lastName.getBorder(), inputPadding));
		this.usernameField.setBorder(BorderFactory.createCompoundBorder(this.usernameField.getBorder(), inputPadding));
		this.passwordField.setBorder(BorderFactory.createCompoundBorder(this.passwordField.getBorder(), inputPadding));
		this.loginText.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Set Border title
		titledBorder.setTitle(LanguageText.getConstant("NEW_ACCOUNT"));
		
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
		LayoutHelper.gcGrid(gc, row++, 0, 2);
		fixedPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/register_user.png"))), gc);
		
		LayoutHelper.gcGrid(gc, row++, 0, 2);
		fixedPanel.add(this.errorMessage, gc);
		
		LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new JLabel(LanguageText.getConstant("FIRST_NAME")), gc);
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.firstName, gc);
		
		LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new JLabel(LanguageText.getConstant("LAST_NAME")), gc);
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.lastName, gc);
		
		LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new JLabel(LanguageText.getConstant("USERNAME")), gc);
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.usernameField, gc);
		
		LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new JLabel(LanguageText.getConstant("PASSWORD")), gc);
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.passwordField, gc);
		
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.registerButton, gc);
		
		LayoutHelper.gcGrid(gc, row++, 0, 2);
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
