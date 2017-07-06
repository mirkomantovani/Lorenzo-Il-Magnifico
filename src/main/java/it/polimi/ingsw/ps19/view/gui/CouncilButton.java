package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

/**
 * @author matteo
 * This class represents the button to place a familiar into the market area
 *
 */
public class CouncilButton extends JButton {
	
	private final static double wCOUNCIL =0.46281102362204724409448818897638;
	private final static double hCOUNCIL = 0.55092592592592592592592592592593;
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	private final static double wDIM_PERC = 0.07874015748031496062992125984252;
	private final static double hDIM_PERC = 0.05555555555555555555555555555556;
	
	public CouncilButton() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setPosition();
		}
		
		private void setPosition() {
			widthRel = (int) (wCOUNCIL*BoardPanel.dimension.getWidth());
			heightRel = (int) (hCOUNCIL*BoardPanel.dimension.getHeight());
			setBounds(widthRel,heightRel,(int) (3.2*wDIM_PERC*BoardPanel.dimension.getWidth()), (int) (2*hDIM_PERC*BoardPanel.dimension.getWidth()));
			
		}

}
