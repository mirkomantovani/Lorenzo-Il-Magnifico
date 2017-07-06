package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

/**
 * @author matteo
 * This is the class for the market buttons
 *
 */
public class MarketButton extends JButton {
	
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	private final static double wDIM_PERC = 0.06561679790026246719160104986877;
	private final static double hDIM_PERC = 0.0462962962962962962962962962963;
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	private final static double wFirstMarket = 0.53149606299212598425196850393701;
	private final static double hFirstMarket = 0.79166666666666666666666666666667;
	private transient Toolkit tool = Toolkit.getDefaultToolkit();
	public MarketButton(int i) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(25));
		setName(""+i);
		setPosition(i);
	}

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
