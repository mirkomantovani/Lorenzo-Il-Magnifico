package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class MarketButton extends JButton {
	public MarketButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(25));
	}

}
