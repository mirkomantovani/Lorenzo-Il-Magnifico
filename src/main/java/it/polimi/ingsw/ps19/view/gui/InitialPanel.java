package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitialPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	public InitialPanel(ImageIcon image) {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		JLabel bckgndImg = new JLabel(image, JLabel.CENTER);

		this.add(bckgndImg, BorderLayout.CENTER);
	}
}

