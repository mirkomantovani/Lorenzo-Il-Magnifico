package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class MarketButton extends JButton {
	public MarketButton(int i) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(25));
		setName(""+i);
		setPosition(i);
	}

	private void setPosition(int i) {
//		switch su 1 2 3 4 e setbounds giusti
	}

}
