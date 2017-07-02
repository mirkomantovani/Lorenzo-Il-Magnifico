package it.polimi.ingsw.ps19.view.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.ImagesConstants;


/**
 * This JPanel extension is the left-side of the frame, the one containing the board image with 
 * all his components, at the moment of the creation the board image is scaled mantaining the
 * proportions to adapt it to any kind of screen, components put into this BoardPanel must
 * set the bounds for themselves and scale their dimensions to create a responsive UI
 * @author Mirko
 *
 */
public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;
	static Dimension dimension;
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private int imgHeight;
	private int imgWidth;



	public BoardPanel() {
		super(new GridBagLayout());
		
//		 JButton button;
////			setLayout(new GridBagLayout());
//			GridBagConstraints c = new GridBagConstraints();
//		button = new JButton("Button 2");
//		c.ipady = 10; 
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 1;
//		c.gridx = 1;
//		c.gridy = 0;
//		button.setOpaque(false);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		this.add(button, c);
		
		
		
		dimension = toolkit.getScreenSize();
		
		try {
			img = ImageIO.read(this.getClass().getResource(ImagesConstants.BOARD));
		} catch (IOException e) {
		}
		System.out.println(dimension.width+" e "+ dimension.height);
		imgHeight=img.getHeight(null);
		imgWidth=img.getWidth(null);
		float ratio=imgWidth/imgHeight;
		System.out.println(imgHeight+" e "+ imgWidth);
		img = img.getScaledInstance(dimension.height*imgWidth/imgHeight, dimension.height,
				Image.SCALE_SMOOTH);
		dimension.setSize(dimension.height*imgWidth/imgHeight, dimension.height);
		setPreferredSize(dimension);
		
	
	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

	public Dimension getDimension() {
		return dimension;
	}
	
	
	

}
