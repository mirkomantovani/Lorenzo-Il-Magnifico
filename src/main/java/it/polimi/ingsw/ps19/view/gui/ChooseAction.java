package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

public class ChooseAction extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChooseAction() {
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Orange");
		rdbtnNewRadioButton.setSelectedIcon(new ImageIcon(ChooseAction.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		add(rdbtnNewRadioButton);

	}

}
