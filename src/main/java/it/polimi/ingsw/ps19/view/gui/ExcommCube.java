package it.polimi.ingsw.ps19.view.gui;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class ExcommCube.
 *
 * @author matteo
 * this class represent the image of excommunication dices
 */
public class ExcommCube extends JPanel{
	
	/** The period. */
	private int period;
	
	/** The player. */
	private String player;
	
	/** The img. */
	private Image img;
	
	/** The width rel. */
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	
	/** The height rel. */
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	
	/** The Constant WIDTH_PERC. */
	private static final double WIDTH_PERC = 0.2028503937007874015748031496063;
	
	/** The Constant HEIGHT_PERC. */
	private static final double HEIGHT_PERC = 0.61559259259259259259259259259259;
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.02624671916010498687664041994751;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.02351851851851851851851851851852;
	
	/** The Constant offset. */
	private final static double offset = 0.075;

	
	/**
	 * Instantiates a new excomm cube.
	 *
	 * @param period the period
	 * @param player the player
	 */
	public ExcommCube(int period,String player){
		this.period = period;
		this.player = player;
		try {
			this.img = ImageIO.read(getClass().getResource("/" + player + "Cube.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		
		/** The height rel. */
		 heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
		switch(player){
		
		case "yellow" : widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
						break;
		case "red" : widthRel =(int) (BoardPanel.dimension.getWidth()*WIDTH_PERC*1.1);
						break;
		case "blue" : heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC*1.03);
						break;
		case "green" : heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC*1.03);
						widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC*1.1);
						break;
		default : 
						break;
		
		}
		
	    this.setBounds((int) (widthRel + (period-1)*offset*BoardPanel.dimension.getWidth()), heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
		//this.getParent().setComponentZOrder(this, 0);
		
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		
		}

}
