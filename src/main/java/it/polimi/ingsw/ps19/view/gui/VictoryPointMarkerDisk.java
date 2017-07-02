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
	private BoardPanel boardPanel;
	private final static double WIDTH_PERC = 0.01312335958005249343832020997375;
	private final static double HEIGHT_PERC = 0.00462962962962962962962962962963;
	
	public VictoryPointMarkerDisk(String color){
		src = color;
		setVictoryPointMarkers();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance(20, 20, 0), 0, 0, this);
		}
	
	private void setVictoryPointMarkers(){
		
		boardPanel = new BoardPanel();
		int heightRel = (int) (tool.getScreenSize().height*HEIGHT_PERC);
		System.out.println(boardPanel.getDimension().getWidth());
		System.out.println(boardPanel.getDimension().getHeight());
		int widthRel = (int) (boardPanel.getDimension().getWidth()*WIDTH_PERC);
		

		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(widthRel + wCount, heightRel + hCount, 36,36);
		this.setVisible(true);
		this.setOpaque(false);
		wCount = wCount + 5;
	}
	

	
}
