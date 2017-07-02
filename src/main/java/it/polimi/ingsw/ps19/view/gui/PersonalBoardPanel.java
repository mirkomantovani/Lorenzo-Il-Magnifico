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
public class PersonalBoardPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private Image img;
	private Dimension dimension;
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();

	public PersonalBoardPanel() {
		super(new GridBagLayout());
		
dimension = toolkit.getScreenSize();
		
		try {
			img = ImageIO.read(this.getClass().getResource(
					"/completepersonalboardmirkosmall.jpg"));
		} catch (IOException e) {
		}
		int imgHeight=img.getHeight(null);
		int imgWidth=img.getWidth(null);
//		float ratio=imgWidth/imgHeight;
//		System.out.println(imgHeight+" e "+ imgWidth);
		img = img.getScaledInstance(dimension.width, dimension.width*imgHeight/imgWidth,
				Image.SCALE_SMOOTH);
//		dimension.setSize(1000, 600);
		setPreferredSize(new Dimension(dimension.width,dimension.width*imgHeight/imgWidth));
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(img, 0, 0, this);
	}

}
