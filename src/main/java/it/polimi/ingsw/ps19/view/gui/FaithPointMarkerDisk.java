package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FaithPointMarkerDisk extends JPanel{


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3087444235128620320L;
	
	private String src; //red,blue,green,yellow
	private final static int ORDER_DISC_DIAM = 36;
	private Image img;
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	private static int wCount = 0;
	private static final double WIDTH_PERC = 0.06824146981627296587926509186352;
	private static final double HEIGHT_PERC = 0.73333333333333333333333333333333;
	private final static double wDIM_PERC = 0.02624671916010498687664041994751;
	private final static double hDIM_PERC = 0.01851851851851851851851851851852;
	
	
	public FaithPointMarkerDisk(String color){
		src = color;
		setFaithPointMarkers();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		}
	
	private void setFaithPointMarkers(){
		
		
		int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
		int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	
		try {
			this.img = ImageIO.read(getClass().getResource("/"+src+"Disc.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBounds(widthRel + wCount, heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
		wCount = wCount + 5;
	}
	

}
