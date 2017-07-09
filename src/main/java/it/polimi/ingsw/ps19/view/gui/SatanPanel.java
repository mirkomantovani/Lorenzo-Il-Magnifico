package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class SatanPanel extends JPanel {
	private JTextField txtChooseThePlayer;
	
	public SatanPanel(int resourceWidth, GamePanel listener) {

		setMaximumSize(new Dimension(resourceWidth, 32767));

		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setForeground(UIManager.getColor("ArrowButton.disabledText"));

		setLayout(new FlowLayout(FlowLayout.CENTER, 40, 100));
		
		txtChooseThePlayer = new JTextField();
		txtChooseThePlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtChooseThePlayer.setEditable(false);
		txtChooseThePlayer.setText("Choose the player:");
		add(txtChooseThePlayer);
		txtChooseThePlayer.setColumns(10);


		PlayerColor red = new PlayerColor("red", resourceWidth);
		red.setName("Red");
		add(red);
		red.addMouseListener(listener);

		PlayerColor green = new PlayerColor("green", resourceWidth);
		add(green);
		green.setName("Green");
		green.addMouseListener(listener);

		PlayerColor blue = new PlayerColor("blue", resourceWidth);
		add(blue);
		blue.setName("Blue");
		blue.addMouseListener(listener);

		PlayerColor yellow = new PlayerColor("yellow", resourceWidth);
		add(yellow);
		yellow.setName("Yellow");
		yellow.addMouseListener(listener);

	}

}
