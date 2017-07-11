package it.polimi.ingsw.ps19.view.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.constant.ImagesConstants;


/**
 * This JPanel extension is the left-side of the frame, the one containing the board image with 
 * all his components, at the moment of the creation the board image is scaled mantaining the
 * proportions to adapt it to any kind of screen, components put into this BoardPanel must
 * set the bounds for themselves and scale their dimensions to create a responsive UI.
 *
 * @author Mirko
 */
public class BoardPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The img. */
	private Image img;
	
	/** The dimension. */
	static Dimension dimension;
	
	/** The toolkit. */
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	/** The img height. */
	private int imgHeight;
	
	/** The img width. */
	private int imgWidth;



	/**
	 * Instantiates a new board panel.
	 */
	public BoardPanel() {
		super(new GridBagLayout());  //metto a null
		
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
		imgHeight=img.getHeight(null);
		imgWidth=img.getWidth(null);
		img = img.getScaledInstance(dimension.height*imgWidth/imgHeight, dimension.height,
				Image.SCALE_SMOOTH);
		dimension.setSize(dimension.height*imgWidth/imgHeight, dimension.height);
		setPreferredSize(dimension);
		
	}



	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

	/**
	 * Gets the dimension.
	 *
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}
	
}
