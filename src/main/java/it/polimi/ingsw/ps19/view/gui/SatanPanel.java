package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;

public class SatanPanel extends JPanel {
	
	public SatanPanel(int resourceWidth, GamePanel listener) {

		setMaximumSize(new Dimension(resourceWidth, 32767));

		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setForeground(UIManager.getColor("ArrowButton.disabledText"));

		setLayout(new FlowLayout(FlowLayout.CENTER, 40, 100));


		PlayerColor red = new PlayerColor("red", resourceWidth);
		red.setName("red");
		add(red);
		red.addMouseListener(listener);

		PlayerColor green = new PlayerColor("green", resourceWidth);
		add(green);
		green.setName("green");
		green.addMouseListener(listener);

		PlayerColor blue = new PlayerColor("blue", resourceWidth);
		add(blue);
		blue.setName("blue");
		blue.addMouseListener(listener);

		PlayerColor yellow = new PlayerColor("yellow", resourceWidth);
		add(yellow);
		yellow.setName("yellow");
		yellow.addMouseListener(listener);

	}

}
