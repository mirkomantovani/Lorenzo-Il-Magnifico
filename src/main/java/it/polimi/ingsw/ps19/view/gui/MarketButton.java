package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;

/**
 * The Class MarketButton.
 *
 * @author matteo
 * This is the class for the market buttons
 */
public class MarketButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The width rel. */
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	
	/** The height rel. */
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	
	/** The Constant wDIM_PERC. */
	private final static double wDIM_PERC = 0.06561679790026246719160104986877;
	
	/** The Constant hDIM_PERC. */
	private final static double hDIM_PERC = 0.0462962962962962962962962962963;
	
	/** The Constant wFIRST_SLOT. */
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	
	/** The Constant hFIRST_SLOT. */
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	
	/** The Constant wFirstMarket. */
	private final static double wFirstMarket = 0.53149606299212598425196850393701;
	
	/** The Constant hFirstMarket. */
	private final static double hFirstMarket = 0.79166666666666666666666666666667;
	
	
	/**
	 * Instantiates a new market button.
	 *
	 * @param i the i
	 */
	public MarketButton(int i) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(25));
		setName(""+i);
		setPosition(i);
	}

	/**
	 * Sets the position.
	 *
	 * @param i the new position
	 */
	private void setPosition(int i) {
//			public void PlaceFamiliarIntoMarket(int market){
		if(i == 1){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket);
			
			
		} else if(i == 2){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket + BoardPanel.dimension.getWidth()*0.09);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket);
			
		
		} else if(i == 3){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket + BoardPanel.dimension.getWidth()*0.175);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket + BoardPanel.dimension.getHeight()*0.0175);
			
		} else if(i == 4){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket + BoardPanel.dimension.getWidth()*0.235);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket + BoardPanel.dimension.getHeight()*0.065);
			
		}
		setBounds(widthRel,heightRel,(int) (BoardPanel.dimension.getWidth()*this.wDIM_PERC),(int) (BoardPanel.dimension.getHeight()*this.hDIM_PERC));
	}

}
