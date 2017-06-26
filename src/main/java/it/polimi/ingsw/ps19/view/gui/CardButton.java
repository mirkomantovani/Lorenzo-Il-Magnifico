package it.polimi.ingsw.ps19.view.gui;


import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.polimi.ingsw.ps19.constant.ImagesConstants;

/**
 * @author Mirko
 *
 */
public class CardButton extends JButton {


	private static final long serialVersionUID = 1L;
	
//	private static final String path="/images/devcards_f_en_c_1.png";
	
	private int tower;
	private int floor;
	private int id;
	private Dimension boardPanelPrefSize;
	
	private int cardWidth;
	private int cardHeight;
	private int applicationPointX;
	private int applicationPointY;
	
	private final double ratio=0.11875;
	private final double northBorder=0.052083333;
	private final double leftBorder=0.069813176;
	private final double floorSpace=0.006944444;
	private final double towerSpace=0.089029007;
	
	ImageIcon icon;

	public CardButton(Dimension boardPanelPrefSize,int tower,int floor,int id) {
		Image img = null;
		this.tower=tower;
		this.floor=floor;
		this.id=id;
		this.boardPanelPrefSize=boardPanelPrefSize;
		
		cardWidth=(int)(ratio*boardPanelPrefSize.width-15);
		cardHeight=(int)(ratio*boardPanelPrefSize.height);
		
		calculateApplicationPoint();
		
//		setBounds(72, 72, 105, 176);
//		System.out.println((int)(leftBorder*boardPanelPrefSize.width)+" "+
//				(int)(northBorder*boardPanelPrefSize.height)+" "+cardWidth+" "+cardHeigth);
		setBounds(applicationPointX,applicationPointY, cardWidth, cardHeight);
		
		String path= "/devcards_f_en_c_";
		path=path+id;
		path=path+".png";
		
//		System.out.println(path);
//		
//		System.out.println(getClass().getResource("/").getPath());
//		System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation().toString());
		
		try {
		    img = ImageIO.read(getClass().getResource(path));
		    
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
//		System.out.println((int)(ratio*boardPanelPrefSize.height)+"  "+(int)(ratio*1017));
		img = img.getScaledInstance((int)(ratio*boardPanelPrefSize.width),(int)(ratio*boardPanelPrefSize.height),
				Image.SCALE_SMOOTH);
		
		
		setIcon(new ImageIcon(img));
		
	
//		icon = new ImageIcon(path);
//		this.setIcon(icon);
	}

	private void calculateApplicationPoint() {
		int revertFloor=3-this.floor;
		applicationPointX=(int)(this.tower*towerSpace*boardPanelPrefSize.width+
				leftBorder*boardPanelPrefSize.width+this.tower*cardWidth);
		applicationPointY=(int)(revertFloor*floorSpace*boardPanelPrefSize.height+
				northBorder*boardPanelPrefSize.height+revertFloor*cardHeight);
//		System.out.println("appl point:"+applicationPointX+ " "+applicationPointY);
		
		
		
		
	}
	

	

}