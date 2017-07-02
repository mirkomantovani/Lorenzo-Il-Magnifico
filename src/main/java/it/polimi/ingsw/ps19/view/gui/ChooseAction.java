package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Color;

/**
 * The panel that lets the user choose what he wants to do when it's his turn
 * @author Mirko
 *
 */
public class ChooseAction extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChooseAction() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(222, 184, 135));
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/actionbtnpressed.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_2.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(ChooseAction.class.getResource("/actionbtn.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(222, 184, 135));
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(222, 184, 135));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/activatebtnpressed.png")));
		btnNewButton_1.setIcon(new ImageIcon(ChooseAction.class.getResource("/activatebtn.png")));
		panel_1.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 184, 135));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/discardbtnpress.png")));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setIcon(new ImageIcon(ChooseAction.class.getResource("/discardbtn.png")));
		panel.add(btnNewButton_2);
		

	}

}
