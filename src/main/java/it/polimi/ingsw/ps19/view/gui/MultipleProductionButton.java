package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

/**
 * The Class MultipleProductionButton.
 *
 * @author matteo
 * This class represents the button to place familiars in the multiple production area
 */
public class MultipleProductionButton extends JButton {
	
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
	
	/** The Constant hProduction. */
	private final static double hProduction = 0.81018518518518518518518518518519;
	
	/** The Constant wBigHarvest. */
	private final static double wBigHarvest = 0.17060367454068241469816272965879;
	
	
	
	
	/**
	 * Instantiates a new multiple production button.
	 */
	public MultipleProductionButton() {
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
		heightRel = (int)(BoardPanel.dimension.getHeight()*hProduction);
		setBounds(widthRel,heightRel,(int)(2.5*wDIM_PERC*BoardPanel.dimension.getWidth()), (int) (1.3*hDIM_PERC*BoardPanel.dimension.getWidth()));
	}

}
