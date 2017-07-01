package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MarkerDisk extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409734849829937305L;

	private String src; // red,blue,green,yellow
	private final static int DISC_DIAM = 36;
	private Image img;

	public MarkerDisk(String color) {
		super(new GridBagLayout());
		this.src = color;
		setOpaque(false);

		img=getImage("/" + color + "Disc.png");
		
		img=img.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
//		try {
//
//			// this.img =
//			// ImageIO.read(getClass().getResource("/devcards_f_en_c_1.png"));
//			this.img = ImageIO.read(getClass().getResource("/" + color + "Disc.png"));
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		this.setBounds(782, 766, 200, 200);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

	public Image getImage(String path) {
		ImageIcon imageIcon = new ImageIcon(MarkerDisk.class.getResource(path));
		Image tmpImage = imageIcon.getImage();

		BufferedImage image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().drawImage(tmpImage, 0, 0, null);
		tmpImage.flush();

		return image;
	}

}
