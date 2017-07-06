package it.polimi.ingsw.ps19.view.gui;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author matteo
 * this class represent the image of excommunication dices
 */
public class ExcommCube extends JPanel{
	
	private int period;
	private String player;
	private Image img;
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	private static final double WIDTH_PERC = 0.2028503937007874015748031496063;
	private static final double HEIGHT_PERC = 0.61559259259259259259259259259259;
	private final static double wDIM_PERC = 0.02624671916010498687664041994751;
	private final static double hDIM_PERC = 0.02351851851851851851851851851852;
	private final static double offset = 0.075;

	
	public ExcommCube(int period,String player){
		this.period = period;
		this.player = player;
		try {
			this.img = ImageIO.read(getClass().getResource("/" + player + "Cube.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		
		}

}
