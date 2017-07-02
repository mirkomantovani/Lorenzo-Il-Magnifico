package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MilitaryPointMarkerDisk extends JPanel{
	

	private String src; //red,blue,green,yellow
	private final static int ORDER_DISC_DIAM = 36;
	private Image img;
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	private static int hCount = 0;
	private static int wCount = 0;
	private static double WIDTH_PERC = 0.86614173228346456692913385826772;
	private static double HEIGHT_PERC = 0.89814814814814814814814814814815;
	private BoardPanel boardPanel;
	
	public MilitaryPointMarkerDisk(String color){
		src = color;
		setMilitaryPointMarkers();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance(20, 20, 0), 0, 0, this);
		}
	
	private void setMilitaryPointMarkers(){
		
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
		this.setBounds(widthRel + wCount, heightRel , 36,36);
		this.setVisible(true);
		this.setOpaque(false);
		wCount = wCount + 5;
	}

}
