package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;

/**
 * The Class SingleProductionButton.
 *
 * @author matteo
 * This button leads a player to place familiars in the single production area
 */
public class SingleProductionButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The width rel. */
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	
	/** The height rel. */
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	
	/** The Constant wFIRST_SLOT. */
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	
	/** The Constant hFIRST_SLOT. */
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	
	/** The Constant wProduction. */
	private final static double wProduction = 0.06561679790026246719160104986877;
	
	/** The Constant hProduction. */
	private final static double hProduction = 0.81018518518518518518518518518519;
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.07874015748031496062992125984252;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.05555555555555555555555555555556;
	
	/**
	 * Instantiates a new single production button.
	 */
	public SingleProductionButton() {
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
		widthRel = (int)(BoardPanel.dimension.getWidth()*wProduction);
		heightRel = (int)(BoardPanel.dimension.getHeight()*hProduction);
		setBounds(widthRel,heightRel,(int) (BoardPanel.dimension.getWidth()*this.wDIM_PERC),(int) (BoardPanel.dimension.getHeight()*this.hDIM_PERC));
	}
}
