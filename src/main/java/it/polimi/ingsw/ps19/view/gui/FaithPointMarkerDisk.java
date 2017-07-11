package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class FaithPointMarkerDisk.
 *
 * @author matteo
 *  This class represents the images of the markers for the faithPoint of each player
 */
public class FaithPointMarkerDisk extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3087444235128620320L;
	
	/** The src. */
	private String src; //red,blue,green,yellow
	
	/** The img. */
	private Image img;
	
	/** The w count. */
	static int wCount = 0;
	
	/** The Constant WIDTH_PERC. */
	private static final double WIDTH_PERC = 0.06824146981627296587926509186352;
	
	/** The Constant HEIGHT_PERC. */
	private static final double HEIGHT_PERC = 0.73333333333333333333333333333333;
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.02624671916010498687664041994751;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.01851851851851851851851851851852;
	
	/** The width rel. */
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	
	/** The height rel. */
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	
	/** The Constant slotRelDim. */
	private final static double slotRelDim = 0.03937007874015748031496062992126;
	
	/**
	 * Instantiates a new faith point marker disk.
	 *
	 * @param color the color
	 */
	public FaithPointMarkerDisk(String color){
		src = color;
		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(widthRel , heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
		setFaithPointMarkers();
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
	 * Sets the faith point markers.
	 */
	public void setFaithPointMarkers(){
		
		this.setBounds(widthRel , heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
	}
	
	
	/**
	 * Sets the faith points amount.
	 *
	 * @param val the new faith points amount
	 */
	public void setFaithPointsAmount(double val){
		
		Random random = new Random();
		int n = random.nextInt(50) + 100;
		
		if(val>=15)
			val=18.7;
		
		widthRel = (int) ((BoardPanel.dimension.getWidth() + n)*WIDTH_PERC);
		if(val <=2){
			widthRel = (int) (widthRel + val*slotRelDim*(BoardPanel.dimension.getWidth()));
			this.setFaithPointMarkers();
			widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		}
		else if(val > 6){
			widthRel = (int)(widthRel + 8.5*slotRelDim*(BoardPanel.dimension.getWidth()));
			widthRel = (int)(widthRel + (val-8.5)*slotRelDim*BoardPanel.dimension.getWidth());
			this.setFaithPointMarkers();
			widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		} else if(val == 3){
			widthRel = (int)(widthRel + 3.5*slotRelDim*(BoardPanel.dimension.getWidth()));
			this.setFaithPointMarkers();
			widthRel = (int) ((BoardPanel.dimension.getWidth() + n)*WIDTH_PERC);
		} else if(val == 4){
			widthRel = (int)(widthRel + 5.2*slotRelDim*(BoardPanel.dimension.getWidth()));
			this.setFaithPointMarkers();
			widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		} else {
			widthRel = (int)(widthRel + 7*slotRelDim*(BoardPanel.dimension.getWidth()));
			this.setFaithPointMarkers();
			widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		}
		
	}

}
