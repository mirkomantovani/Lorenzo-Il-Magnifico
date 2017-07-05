package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

public class CouncilButton extends JButton {
	public CouncilButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	}

}
