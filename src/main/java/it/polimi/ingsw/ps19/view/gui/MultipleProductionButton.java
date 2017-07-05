package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class MultipleProductionButton extends JButton {
	private final static double wDIM_PERC = 0.07874015748031496062992125984252;
	private final static double hDIM_PERC = 0.05555555555555555555555555555556;
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	private final static double wBigHarvest = 0.17060367454068241469816272965879;
	private final static double hProduction = 0.88888888888888888888888888888889;
	
	
	
	
	public MultipleProductionButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setPosition();
	}
	
	private void setPosition() {
		widthRel = (int)(BoardPanel.dimension.getWidth()*wBigHarvest);
		heightRel = (int)(BoardPanel.dimension.getHeight()*hProduction);
		setBounds(widthRel,heightRel,(int)(2.5*wDIM_PERC*BoardPanel.dimension.getWidth()), (int) (1.3*hDIM_PERC*BoardPanel.dimension.getWidth()));
	}

}
