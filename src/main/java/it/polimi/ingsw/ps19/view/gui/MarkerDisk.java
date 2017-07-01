package it.polimi.ingsw.ps19.view.gui;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MarkerDisk extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409734849829937305L;
	
	private String src; //red,blue,green,yellow
	private final static int DISC_DIAM = 36;
	private Image img;
	
	

	
	public MarkerDisk(String color){
		this.src = color;
		try {
			this.img = ImageIO.read(getClass().getResource("/"+color+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(782, 792, DISC_DIAM, DISC_DIAM);
		this.repaint();
	}
	
	
	
	

}
