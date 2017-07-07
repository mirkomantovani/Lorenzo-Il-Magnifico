package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;

/**
 * The Class SingleHarvestButton.
 *
 * @author matteo
 * This button leads a player to place familiars in the single harvest area
 */
public class SingleHarvestButton extends JButton {
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.07874015748031496062992125984252;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.05555555555555555555555555555556;
	
	/** The width rel. */
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	
	/** The height rel. */
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	
	/** The Constant wFIRST_SLOT. */
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	
	/** The Constant hFIRST_SLOT. */
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	
	/** The Constant wHarvest. */
	private final static double wHarvest = 0.06561679790026246719160104986877;
	
	/** The Constant hHarvest. */
	private final static double hHarvest = 0.88888888888888888888888888888889;
	
	
	/**
	 * Instantiates a new single harvest button.
	 */
	public SingleHarvestButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(25));
		setName("");
		setPosition();
	}

	/**
	 * Sets the position.
	 */
	private void setPosition() {
		widthRel = (int)(BoardPanel.dimension.getWidth()*wHarvest);
		heightRel = (int)(BoardPanel.dimension.getHeight()*hHarvest);
		setBounds(widthRel,heightRel,(int)(BoardPanel.dimension.getWidth()*this.wDIM_PERC),(int) (BoardPanel.dimension.getHeight()*this.hDIM_PERC));
	}
}
