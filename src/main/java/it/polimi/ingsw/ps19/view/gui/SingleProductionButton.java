package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;

public class SingleProductionButton extends JButton {
	public SingleProductionButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(25));
		setName("");
		setPosition();
	}

	private void setPosition() {
	}
}
