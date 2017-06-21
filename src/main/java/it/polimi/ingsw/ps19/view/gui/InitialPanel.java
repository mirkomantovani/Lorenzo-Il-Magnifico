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
 * @author Mirko
 *
 */
public class InitialPanel extends JPanel {
	

	
	private static final long serialVersionUID = 1L;
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension screenDimension;
	private Image img;

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

