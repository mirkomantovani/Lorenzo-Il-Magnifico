package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class VictoryPointMarkerDisk extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2138063191515420324L;
	
	private String src; //red,blue,green,yellow
	private final static int ORDER_DISC_DIAM = 36;
	private Image img;
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	private static int hCount = 0;
	private static int wCount = 0;
	private final static double WIDTH_PERC = 0.01312335958005249343832020997375;
	private final static double HEIGHT_PERC = 0.00462962962962962962962962962963;
	private final static double wDIM_PERC = 0.02624671916010498687664041994751;
	private final static double hDIM_PERC = 0.01851851851851851851851851851852;
	
	public VictoryPointMarkerDisk(String color){
		src = color;
		setVictoryPointMarkers();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		}
	
	private void setVictoryPointMarkers(){
		
	
		int heightRel = (int) (tool.getScreenSize().height*HEIGHT_PERC);

		int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		

		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(widthRel + wCount, heightRel + hCount, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
		wCount = wCount + 5;
	}
	

	
}
