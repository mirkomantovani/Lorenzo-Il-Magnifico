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
 * The panel that lets the user choose to discard a leader card or end his round
 * when he's already performed an action.
 *
 * @author Mirko
 */
public class EndOrDiscardPanel extends JPanel implements ActionListener {

	
	/** The end. */
	private JButton end;
	
	/** The discard. */
	private JButton discard;
	
	/** The game panel. */
	private GamePanel gamePanel;

	/**
	 * Create the panel.
	 *
	 * @param gamePanel the game panel
	 */
	public EndOrDiscardPanel(GamePanel gamePanel) {
		
		this.gamePanel=gamePanel;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(222, 184, 135));
		add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		end = new JButton("");
		end.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/endroundbtnpressed.png")));
		end.setBorderPainted(false);
		end.setContentAreaFilled(false);
		end.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_2.add(end);
		end.setIcon(new ImageIcon(ChooseAction.class.getResource("/endroundbtn.png")));


		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 184, 135));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		discard = new JButton("");
		discard.setPressedIcon(new ImageIcon(ChooseAction.class.getResource("/discardbtnpressed.png")));
		discard.setBorderPainted(false);
		discard.setContentAreaFilled(false);
		discard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		discard.setIcon(new ImageIcon(ChooseAction.class.getResource("/discardbtn.png")));
		panel.add(discard);

		end.addActionListener(this);
		discard.addActionListener(this);

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.end) {
			gamePanel.notifyEndRound();

		} else if (e.getSource() == this.discard) {
			gamePanel.notifyDiscardClick();

		}
	}
}
