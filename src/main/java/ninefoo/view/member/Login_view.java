package ninefoo.view.member;

import ninefoo.helper.LayoutHelper;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.panel.FormPanel;
import ninefoo.view.member.listeners.LoginListener;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Login form.
 *
 * @author Amir El Bawab
 */
public class Login_view extends FormPanel {

    private static final long serialVersionUID = 1578431485931709295L;

    // Define components
    private PMButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
	private PMLabel welcomeTitle;
    private PMLabel registerText;
    private LoginListener loginListener;

    // Constructor
    public Login_view() {

        // Call super constructor
        super();

        // Initialize components
		this.loginButton = new PMButton("LOGIN", true);
        this.usernameField = new JTextField(10);
        this.passwordField = new JPasswordField(10);
		this.welcomeTitle = new PMLabel("WELCOME", true);
		this.registerText = new PMLabel("REGISTRATION_LINK", true);

        // Configure buttons
        Border inputPadding = BorderFactory.createEmptyBorder(3, 3, 3, 3);
        this.usernameField.setBorder(BorderFactory.createCompoundBorder(this.usernameField.getBorder(), inputPadding));
        this.passwordField.setBorder(BorderFactory.createCompoundBorder(this.passwordField.getBorder(), inputPadding));
        this.registerText.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set border name
		//this.titledBorder.setTitle(LanguageText.getConstant("WELCOME") + "!");

        // Add listeners for the login button
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginListener != null) {
                    loginListener.login(usernameField.getText(), new String(passwordField.getPassword()));
                }
            }
        });

        // Add listeners for the register link
        registerText.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (loginListener != null) {
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
		LayoutHelper.gcGrid(gc, row, 0, 2);
		fixedPanel.add(new PMLabel("WELCOME", true), gc);
		int logoHeight = 5;
		row += logoHeight;
        LayoutHelper.gcGrid(gc, row++, 0, 2);
        fixedPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/login_user.png"))), gc);

        LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new PMLabel("USERNAME", true), gc);
        LayoutHelper.gcGrid(gc, row++, 1, 1);
        fixedPanel.add(this.usernameField, gc);

        LayoutHelper.gcGrid(gc, row, 0, 1);
		fixedPanel.add(new PMLabel("PASSWORD", true), gc);
        LayoutHelper.gcGrid(gc, row++, 1, 1);
        fixedPanel.add(this.passwordField, gc);

        LayoutHelper.gcGrid(gc, row++, 1, 1);
        fixedPanel.add(this.loginButton, gc);

        LayoutHelper.gcGrid(gc, row++, 0, 2);
        fixedPanel.add(registerText, gc);

        // Add components to this panel
        this.add(fixedPanel);

        // Configure fixedPanel
        this.fixedPanel.setMinimumSize(new Dimension(300, 350));
    }

    /**
     * Set login listener
     *
     * @param loginListener Listener defined in the view/ main class
     */
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    /**
     * Set the success message as a prompt
     *
     * @param msg Message to be displayed
     */
    public void setSuccessMessage(String msg) {
        JOptionPane.showMessageDialog(this, String.format("<html>%s</html>", msg), LanguageText.getConstant("OPERATION_SUCCESSFUL"), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Reset form
     */
    public void reset() {
        this.usernameField.setText("");
        this.passwordField.setText("");
    }
}
