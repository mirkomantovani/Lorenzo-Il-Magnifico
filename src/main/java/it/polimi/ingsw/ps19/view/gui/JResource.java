package it.polimi.ingsw.ps19.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A generic resource displaying image, name and amount.
 *
 * @author Mirko
 */
public class JResource extends JLabel {

	/** The amount. */
	private int amount;
	
	/** The resource type. */
	private String resourceType;

	/**
	 * Instantiates a new j resource.
	 *
	 * @param resourceType the resource type
	 * @param rightPanelWidth the right panel width
	 */
	public JResource(String resourceType, int rightPanelWidth) {

		this.resourceType = resourceType;

		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
		setBackground(Color.ORANGE);
		setHorizontalAlignment(SwingConstants.LEFT);
		setVerticalAlignment(SwingConstants.TOP);
		setToolTipText("This is your number of" + resourceType);
		if (resourceType.equals("woodstone")) {
			setText("stone: 1, wood: 1  ");
		} else if (resourceType.equals("military")) {
			setText("militaries: " + amount + "  ");
		} else {
			setText(resourceType + "s: " + amount + "  ");
		}

		setFont(new Font("SansSerif", Font.BOLD, 20));

		ImageIcon imageIcon = new ImageIcon(JResource.class.getResource("/" + resourceType + ".png"));
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

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
		if (resourceType.equals("woodstone")) {
			setText("stone: 1, wood: 1  ");
		} else if (resourceType.equals("military")) {
			setText("militaries: " + amount + "  ");
		} else {
			setText(resourceType + "s: " + amount + "  ");
		}
	}

}