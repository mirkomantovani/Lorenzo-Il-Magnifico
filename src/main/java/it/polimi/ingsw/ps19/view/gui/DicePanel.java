package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DicePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -543922157239004995L;
	
	private int value;
	private String diceColor;
	private Image img;
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	private static final double WIDTH_PERC =  0.52149606299212598425196850393701;
	private static final double HEIGHT_PERC = 0.90592592592592592592592592592593;
	private final static double wDIM_PERC = 0.06561679790026246719160104986877;
	private final static double hDIM_PERC = 0.0462962962962962962962962962963;


	
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		
		}

}
