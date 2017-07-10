package it.polimi.ingsw.ps19.view.gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PlayerColor extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String color;

	
	public PlayerColor(String color, int rightPanelWidth) {

		this.color = color;

		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
		setBackground(Color.ORANGE);
		setHorizontalAlignment(SwingConstants.LEFT);
		setVerticalAlignment(SwingConstants.TOP);


		ImageIcon imageIcon = new ImageIcon(JResource.class.getResource("/" + color + "Disc.png"));
		Image image = imageIcon.getImage(); // transform it
		double w = image.getWidth(null);
		double h = image.getHeight(null);
		double ratio = w / h;
		// System.out.println("wi:"+w+" he:"+h);
		// System.out.println(""+ratio);
		// System.out.println("w:"+(int)(rightPanelWidth/9)+"
		// h:"+(int)(rightPanelWidth/9/ratio));

		Image newimg = image.getScaledInstance((int) (rightPanelWidth / 14), (int) (rightPanelWidth / 14 / ratio),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		setIcon(imageIcon);

	}
}
