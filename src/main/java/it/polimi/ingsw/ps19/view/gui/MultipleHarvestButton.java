package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

/**
 * The Class MultipleHarvestButton.
 *
 * @author matteo
 * This is the button to place familiars in the multiple harvest area
 */
public class MultipleHarvestButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	/** The Constant wBigHarvest. */
	private final static double wBigHarvest = 0.17060367454068241469816272965879;
	
	/** The Constant hHarvest. */
	private final static double hHarvest= 0.88888888888888888888888888888889;

	
	
	/**
	 * Instantiates a new multiple harvest button.
	 */
	public MultipleHarvestButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setPosition();
	}
	
	/**
	 * Sets the position.
	 */
	private void setPosition() {
		widthRel = (int)(BoardPanel.dimension.getWidth()*wBigHarvest);
		heightRel = (int)(BoardPanel.dimension.getHeight()*hHarvest);
		setBounds(widthRel,heightRel,(int)(2.5*wDIM_PERC*BoardPanel.dimension.getWidth()), (int) (1.3*hDIM_PERC*BoardPanel.dimension.getWidth()));
	}

	
	
}
