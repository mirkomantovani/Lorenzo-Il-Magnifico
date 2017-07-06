package it.polimi.ingsw.ps19.view.gui;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ExcommCube extends JPanel{
	
	private int period;
	private String player;
	private Image img;
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	private static final double WIDTH_PERC = 0.1788503937007874015748031496063;
	private static final double HEIGHT_PERC = 0.64759259259259259259259259259259;
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
		
		case "yellow" : widthRel = widthRel + 5;
		case "red" : widthRel = widthRel + 10;
		case "blue" : widthRel = widthRel + 15;
		case "green" : widthRel = widthRel + 20;
		default : widthRel = widthRel;
		
		}
		
	    this.setBounds((int) (widthRel + (period-1)*offset*BoardPanel.dimension.getWidth()), heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
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
