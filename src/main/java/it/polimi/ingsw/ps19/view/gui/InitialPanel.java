package it.polimi.ingsw.ps19.view.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



/**
 * The Initial image that the players see while waiting for the game to start
 *
 * @author Mirko
 */
public class InitialPanel extends JPanel {
	

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The toolkit. */
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	/** The screen dimension. */
	private Dimension screenDimension;
	
	/** The img. */
	private Image img;

	/**
	 * Instantiates a new initial panel.
	 *
	 * @param image the image
	 */
	public InitialPanel(String image) {
		super(new GridBagLayout());
		
		screenDimension = toolkit.getScreenSize();
		
		try {
			img = ImageIO.read(this.getClass().getResource(image));
		} catch (IOException e) {
		}
		System.out.println(screenDimension.width+" e "+ screenDimension.height);
		img = img.getScaledInstance(screenDimension.width, screenDimension.height,
				Image.SCALE_SMOOTH);
		screenDimension.setSize(screenDimension.width, screenDimension.height);
		setPreferredSize(screenDimension);
		
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(img, 0, 0, this);
	}
	
//		setLayout(new BorderLayout());
//		setBackground(Color.BLACK);
//		JLabel bckgndImg = new JLabel(image, JLabel.CENTER);
//
//		this.add(bckgndImg, BorderLayout.CENTER);
//	}
}

