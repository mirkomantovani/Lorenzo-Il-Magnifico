package it.polimi.ingsw.ps19.view.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * A development card button that positions itself on the board based on the parameters passed
 * to the constructor
 * @author Mirko
 *
 */
public class CardButton extends JButton implements MouseListener {


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
	private String path;
	private String cardToString;
	
	private Image zoomedImage;
	private int h;
	private int w;
	private Image img;
	

	public CardButton(Dimension boardPanelPrefSize,int tower,int floor,int id) {
		
		img = null;
		this.tower=tower;
		this.floor=floor;
		this.id=id;
		this.boardPanelPrefSize=boardPanelPrefSize;
		
		int cardCut;
		if((GamePanel.screenDim.getWidth()/GamePanel.screenDim.getHeight())<1.6)
			cardCut=15;
		else cardCut=12;
		
		cardWidth=(int)(ratio*boardPanelPrefSize.width-cardCut);
		cardHeight=(int)(ratio*boardPanelPrefSize.height);
		
		calculateApplicationPoint();
		
//		setBounds(72, 72, 105, 176);
//		System.out.println((int)(leftBorder*boardPanelPrefSize.width)+" "+
//				(int)(northBorder*boardPanelPrefSize.height)+" "+cardWidth+" "+cardHeigth);
		setBounds(applicationPointX,applicationPointY, cardWidth, cardHeight);
		
		path= "/devcards_f_en_c_";
		path=path+id;
		path=path+".png";
		
//		System.out.println(path);
//		
//		System.out.println(getClass().getResource("/").getPath());
//		System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation().toString());
		
		try {
		    zoomedImage = ImageIO.read(getClass().getResource(path));
		    
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
	    h=zoomedImage.getHeight(null);
	    w=zoomedImage.getWidth(null);
//		System.out.println((int)(ratio*boardPanelPrefSize.height)+"  "+(int)(ratio*1017));
		img = zoomedImage.getScaledInstance((int)(ratio*boardPanelPrefSize.width),(int)(ratio*boardPanelPrefSize.height),
				Image.SCALE_SMOOTH);
		
		
		setIcon(new ImageIcon(img));
		
		this.addMouseListener(this);
	
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

	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		 this.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		 popup = new JWindow();
//	      popup.setLayout(new BorderLayout());
//	      popup.add(this);
//	      popup.pack();
		 
//		 this.getParent().getComponentZOrder(this);
		 
		 this.getParent().setComponentZOrder(this, 0);
		 
		 
		 setBounds(applicationPointX,applicationPointY, w, h);
		 setIcon(new ImageIcon(zoomedImage));
//		 revalidate();
		 repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		setBounds(applicationPointX,applicationPointY, cardWidth, cardHeight);
		setIcon(new ImageIcon(img));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getTower() {
		return tower;
	}

	public int getFloor() {
		return floor;
	}

	public int getId() {
		return id;
	}
	
	
	

	

}