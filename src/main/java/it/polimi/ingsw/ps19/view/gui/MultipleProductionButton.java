package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class MultipleProductionButton extends JButton {
	public MultipleProductionButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setPosition();
	}
	
	private void setPosition() {
	}

}
