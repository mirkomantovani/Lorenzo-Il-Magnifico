package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class OrderMarkerDisk.
 *
 * @author matteo
 * 
 * This class represents the images of the marker discs to show the order of in-game players
 */
public class OrderMarkerDisk extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8409734849829937305L;
	
	/** The src. */
	private String src; //red,blue,green,yellow
	
	/** The Constant ORDER_DISC_DIAM. */
	private final static int ORDER_DISC_DIAM = 36;
	
	/** The img. */
	private Image img;
	
	/** The tool. */
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	
	/** The Constant WIDTH_PERC. */
	private final static double WIDTH_PERC = 0.76771653543307086614173228346457;
	
	/** The Constant HEIGHT_PERC. */
	private final static double HEIGHT_PERC = 0.5324074074;
	
	/** The Constant DELTA_PERC. */
	private final static double DELTA_PERC= 0.03703703704;
	
	/** The Ordercounter. */
	static int Ordercounter = 0;
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.04724409448818897637795275590551;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.03333333333333333333333333333333;
	
	/** The width rel. */
	private int widthRel;
	
	/** The height rel. */
	private int heightRel;
	
	/** The delta. */
	private int delta;
	


	
	/**
	 * Instantiates a new order marker disk.
	 *
	 * @param color the color
	 */
	public OrderMarkerDisk(String color){
		this.src = color;
		
		setOrderMarkers();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		}
	
	/**
	 * Sets the order markers.
	 */
	protected void setOrderMarkers(){
		
		
		this.heightRel = (int) (tool.getScreenSize().height*HEIGHT_PERC);
		this.widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		this.delta = (int) (DELTA_PERC*tool.getScreenSize().height);
		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(widthRel, heightRel + delta*Ordercounter, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
		Ordercounter++;
	}

	/**
	 * Gets the src.
	 *
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}
	
	
	

}
