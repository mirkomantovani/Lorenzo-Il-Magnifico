package it.polimi.ingsw.ps19.view.gui;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ExcommTileSecondPeriod extends JPanel{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3936204196996153621L;
	private int id; // tiles from 8 to 14
	private Image img;
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	private static final double WIDTH_PERC = 0.2688503937007874015748031496063;
	private static final double HEIGHT_PERC = 0.61859259259259259259259259259259;
	private final static double wDIM_PERC = 0.06561679790026246719160104986877;
	private final static double hDIM_PERC = 0.09022222222222222222222222222222;

	
	public ExcommTileSecondPeriod(int id, String effect){
		this.id = id;
		try {
			this.img = ImageIO.read(getClass().getResource("/"+id+".png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setToolTipText(effect);
		this.setBounds(widthRel , heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Disegno l'immagine sul pannello alle coordinate (0,0)
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
		}
}

