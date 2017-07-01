package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author matteo
 *
 */
public class OrderMarkerDisk extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409734849829937305L;
	
	private String src; //red,blue,green,yellow
	private final static int ORDER_DISC_DIAM = 36;
	private Image img;
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	private final static double WIDTH_PERC = 0.3046875;
	private final static double HEIGHT_PERC = 0.5324074074;
	private final static double DELTA_PERC= 0.03703703704;
	private static int Ordercounter = 0;
	
	private int widthRel;
	private int heightRel;
	private int delta;

	
	public OrderMarkerDisk(String color){
		this.src = color;
		
		setOrderMarkers();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance(36, 36, 0), 0, 0, this);
		}
	
	private void setOrderMarkers(){
		this.heightRel = (int) (tool.getScreenSize().height*HEIGHT_PERC);
		this.widthRel = (int) (tool.getScreenSize().width*WIDTH_PERC);
		this.delta = (int) (DELTA_PERC*tool.getScreenSize().height);
		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(widthRel, heightRel + delta*Ordercounter, 36,36);
		this.setVisible(true);
		this.setOpaque(false);
		Ordercounter++;
	}
	
	

}
