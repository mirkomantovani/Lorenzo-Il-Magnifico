package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Panel that lets the player choose if he wants to be excommunicated or support the church
 *
 * @author Mirko
 */
public class ChooseExcommunicationPanel extends JPanel implements ActionListener {
	
	/** The show support. */
	private JButton showSupport;
	
	/** The be excommunicated. */
	private JButton beExcommunicated;
	
	/** The game panel. */
	private GamePanel gamePanel;

	/**
	 * Create the panel.
	 *
	 * @param gamePanel the game panel
	 */
	public ChooseExcommunicationPanel(GamePanel gamePanel) {
		
		this.gamePanel=gamePanel;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(222, 184, 135));
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		showSupport = new JButton("");
		showSupport.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/showsupportbtnpressed.png")));
		showSupport.setBorderPainted(false);
		showSupport.setContentAreaFilled(false);
		showSupport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_2.add(showSupport);
		showSupport.setIcon(new ImageIcon(ChooseAction.class.getResource("/showsupportbtn.png")));


		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 184, 135));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		beExcommunicated = new JButton("");
		beExcommunicated.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/beexcommunicatedbtnpressed.png")));
		beExcommunicated.setBorderPainted(false);
		beExcommunicated.setContentAreaFilled(false);
		beExcommunicated.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		beExcommunicated.setIcon(new ImageIcon(ChooseAction.class.getResource("/beexcommunicatedbtn.png")));
		panel.add(beExcommunicated);

		showSupport.addActionListener(this);
		beExcommunicated.addActionListener(this);

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.showSupport) {
			gamePanel.showSupport(true);

		} else if (e.getSource() == this.beExcommunicated) {
			gamePanel.showSupport(false);

		}
	}
}
