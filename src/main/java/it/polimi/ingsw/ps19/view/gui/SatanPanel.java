package it.polimi.ingsw.ps19.view.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import it.polimi.ingsw.ps19.constant.ImagesConstants;

public class SatanPanel extends JPanel {
	private JTextField txtChooseThePlayer;
	private Image img;
	
	public SatanPanel(int resourceWidth, GamePanel listener) {

		setMaximumSize(new Dimension(resourceWidth, 32767));

		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setForeground(UIManager.getColor("ArrowButton.disabledText"));

		setLayout(new FlowLayout(FlowLayout.CENTER, 40, 100));
		
		try {
			img = ImageIO.read(this.getClass().getResource("src/main/resources/satan.jpg"));
		} catch (IOException e) {
		}
		img.getScaledInstance(getWidth(), getHeight(), 0);
		
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
