package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PointMarkerDisk extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2138063191515420324L;
	
	private String src; //red,blue,green,yellow
	private final static int ORDER_DISC_DIAM = 36;
	private Image img;
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	
	public PointMarkerDisk(String color){
		src = color;
		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	this.setBounds(0, 0 , 36,36);
	this.setVisible(true);
	this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance(7, 7, 0), 0, 0, this);
		}
	
}
