package it.polimi.ingsw.ps19.view.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A panel to request the user to authenticate and notify the gamepanel when he's done that
 * @author Mirko
 *
 */
public class AskAuthenticationPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private GamePanel listener;

	/**
	 * Create the panel.
	 */
	public AskAuthenticationPanel(GamePanel listener) {
		setLayout(null);
		
		this.listener=listener;
		
		textField = new JTextField();
		textField.setToolTipText("Type your username");
		textField.setBounds(15, 58, 146, 26);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Type your password");
		passwordField.setBounds(15, 147, 183, 26);
		add(passwordField);
		
		JButton btnAuthenticate = new JButton("Authenticate");
		btnAuthenticate.setBounds(417, 170, 159, 29);
		add(btnAuthenticate);
		btnAuthenticate.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAuthenticate.setBackground(new Color(20, 20, 0));
		btnAuthenticate.setForeground(new Color(255, 255, 255));
		btnAuthenticate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAuthenticate.addActionListener(this);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblUsername.setBounds(15, 22, 124, 20);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPassword.setBounds(15, 111, 102, 20);
		add(lblPassword);
		
		JTextArea txtrTypeYourUsername = new JTextArea();
		txtrTypeYourUsername.setLineWrap(true);
		txtrTypeYourUsername.setEditable(false);
		txtrTypeYourUsername.setBackground(Color.ORANGE);
		txtrTypeYourUsername.setFont(new Font("Monospaced", Font.ITALIC, 16));
		txtrTypeYourUsername.setText("Type your username and your password, "
				+ "if you've never signed up before with this username an account will be created, "
				+ "otherwise you'll login into your account preserving your game stats");
		txtrTypeYourUsername.setBounds(258, 21, 498, 130);
		add(txtrTypeYourUsername);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		String username;
		String password;
		username=textField.getText();
		password=passwordField.getText();
		listener.notifyAuthenticateClick(username,password);
		System.out.println("ask auth panel click");
		
	}
}
