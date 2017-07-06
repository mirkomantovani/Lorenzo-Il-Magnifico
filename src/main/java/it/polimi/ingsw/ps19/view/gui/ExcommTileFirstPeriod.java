package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class ExcommTileFirstPeriod.
 */
public class ExcommTileFirstPeriod extends JPanel{
	
	/**
	 * The Constant serialVersionUID.
	 *
	 * @author matteo
	 * This class represents the image of a first period excommunication tile
	 */
	private static final long serialVersionUID = -4041818591027769735L;
	
	/** The id. */
	private int id; // tiles from 1 to 7
	
	/** The img. */
	private Image img;
	
	/** The width rel. */
	int widthRel = (int) (BoardPanel.dimension.getWidth()*WIDTH_PERC);
	
	/** The height rel. */
	int heightRel = (int) (BoardPanel.dimension.getHeight()*HEIGHT_PERC);
	
	/** The Constant WIDTH_PERC. */
	private static final double WIDTH_PERC = 0.1938503937007874015748031496063;
	
	/** The Constant HEIGHT_PERC. */
	private static final double HEIGHT_PERC = 0.60759259259259259259259259259259;
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.06561679790026246719160104986877;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.09422222222222222222222222222222;

	
	/**
	 * Instantiates a new excomm tile first period.
	 *
	 * @param id the id
	 * @param effect the effect
	 */
	public ExcommTileFirstPeriod(int id, String effect){
		this.id = id;
		System.out.println("Excomm id:"+id);
		try {
			this.img = ImageIO.read(getClass().getResource("/"+id+".png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setToolTipText(effect);
		this.setBounds(widthRel , heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		this.setVisible(true);
		this.setOpaque(false);
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
