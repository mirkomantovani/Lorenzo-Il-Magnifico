package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class DicePanel.
 *
 * @author matteo
 * 
 * This class represent the dices images on the boardPanel
 */
public class DicePanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -543922157239004995L;
	
	/** The value. */
	private int value;
	
	/** The dice color. */
	private String diceColor;
	
	/** The img. */
	private Image img;
	
	/** The width rel. */
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	
	/** The height rel. */
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	
	/** The Constant WIDTH_PERC. */
	private static final double WIDTH_PERC =  0.52149606299212598425196850393701;
	
	/** The Constant HEIGHT_PERC. */
	private static final double HEIGHT_PERC = 0.90592592592592592592592592592593;
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.06561679790026246719160104986877;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.0462962962962962962962962962963;


	
	/**
	 * Instantiates a new dice panel.
	 *
	 * @param value the value
	 * @param diceColor the dice color
	 */
	/**
	 * @param value
	 * @param diceColor
	 */
	public DicePanel(int value,String diceColor){
		this.value = value;
		this.diceColor = diceColor;
		try {
			this.img = ImageIO.read(getClass().getResource("/" + value + diceColor + ".png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(diceColor){
		
		case "black" : 
						break;
		case "white" : widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC*1.17);
						break;
		case "orange" : widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC*1.34);
						break;
		default : 
						break;
		
		}
		
	    this.setBounds(widthRel , heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
		//this.getParent().setComponentZOrder(this, 0);
		
		widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		
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
