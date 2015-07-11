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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ninefoo.helper.LayoutHelper;
import ninefoo.lib.layout.panel.FormPanel;
import ninefoo.lib.lang.LanguageText;
import ninefoo.view.member.listeners.LoginListener;

/**
 * Login form.
 * @author Amir El Bawab
 */
public class Login_view extends FormPanel{
	
	private static final long serialVersionUID = 1578431485931709295L;
	
	// Define components
	private JButton loginButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel registerText;
	private LoginListener loginListener;
	
	// Constructor
	public Login_view() {
		
		// Call super constructor
		super();
		
		// Initialize components
		this.loginButton = new JButton(LanguageText.getConstant("LOGIN"));
		this.usernameField = new JTextField(10);
		this.passwordField = new JPasswordField(10);
		this.registerText = new JLabel(LanguageText.getConstant("REGISTRATION_LINK"));
		
		// Configure buttons
		Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
		this.usernameField.setBorder(BorderFactory.createCompoundBorder(this.usernameField.getBorder(), inputPadding));
		this.passwordField.setBorder(BorderFactory.createCompoundBorder(this.passwordField.getBorder(), inputPadding));
		this.registerText.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Set border name
		this.titledBorder.setTitle(LanguageText.getConstant("WELCOME") + "!");
		
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
		
		// Add user name listener
		usernameField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginButton.doClick();
			}
		});
		
		// Add password listener
		passwordField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginButton.doClick();
			}
		});
				
		
		// Add components to fixedPanel
		int row = 0;
		LayoutHelper.gcGrid(gc, row++, 0, 2);
		fixedPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/login_user.png"))), gc);
		
		LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new JLabel(LanguageText.getConstant("USERNAME")), gc);
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.usernameField, gc);
		
		LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new JLabel(LanguageText.getConstant("PASSWORD")), gc);
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.passwordField, gc);
		
		LayoutHelper.gcGrid(gc, row++, 1, 1);
		fixedPanel.add(this.loginButton, gc);
		
		LayoutHelper.gcGrid(gc, row++, 0, 2);
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
	 * Set the success message as a prompt
	 * @param msg Message to be displayed
	 */
	public void setSuccessMessage(String msg){
		JOptionPane.showMessageDialog(this, String .format("<html>%s</html>", msg), LanguageText.getConstant("OPERATION_SUCCESSFUL"), JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Reset form
	 */
	public void reset(){
		this.usernameField.setText("");
		this.passwordField.setText("");
	}
}
