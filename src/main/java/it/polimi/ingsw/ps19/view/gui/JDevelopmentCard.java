package it.polimi.ingsw.ps19.view.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.polimi.ingsw.ps19.model.card.CardType;

/**
 * @author Mirko
 *
 */
public class JDevelopmentCard extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardType cardType;
	private int id;

	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();

	private int cardWidth;
	private int cardHeight;
	private int applicationPointX;
	private int applicationPointY;
	private int cardNumber;
	private int personalH;
	private int personalW;

	private final double ratioCardW = 0.076486406;
	private final double ratioSpaceW = 0.00657305;

	private Dimension screenDimension;

	private final double ratio = 0.11875;
	private final double ratioHighBorder=0.025;
	private final double ratioSecondHighBorder=0.464;
	private final double ratioLeftBorder=0.006;
	private final double ratioSecondLeftBorder=0.505;
	
	private final double northBorder = 0.052083333;
	private final double leftBorder = 0.069813176;
	private final double floorSpace = 0.006944444;
	private final double towerSpace = 0.089529007;

	ImageIcon icon;
	private String path;
	private String cardToString;

	private Image zoomedImage;
	private int h;
	private int w;
	private Image img;
	
	private double personalRatio=2.789166667;
;

	/**
	 * @param cardType
	 * @param id
	 * @param cardNumber the number of cards of the same type the player already has in his personal board
	 */
	public JDevelopmentCard(CardType cardType, int id, int cardNumber) {

		screenDimension = toolkit.getScreenSize();
		
		personalW=screenDimension.width;
		personalH=(int)(personalW/personalRatio);
		
//		System.out.println("personal W e H: "+personalW+"  "+personalH);

		img = null;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.id = id;

		

		// setBounds(72, 72, 105, 176);
		// System.out.println((int)(leftBorder*boardPanelPrefSize.width)+" "+
		// (int)(northBorder*boardPanelPrefSize.height)+" "+cardWidth+"
		// "+cardHeigth);

		path = "/devcards_f_en_c_";
		path = path + id;
		path = path + ".png";

		// System.out.println(path);
		//
		// System.out.println(getClass().getResource("/").getPath());
		// System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation().toString());

		try {
			zoomedImage = ImageIO.read(getClass().getResource(path));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		h = zoomedImage.getHeight(null);
		w = zoomedImage.getWidth(null);

		int newW = (int) (ratioCardW * screenDimension.width);
		int newH = newW * h / w;
		
		cardWidth=newW;
		cardHeight=newH;

		// System.out.println((int)(ratio*boardPanelPrefSize.height)+"
		// "+(int)(ratio*1017));
		img = zoomedImage.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

		setIcon(new ImageIcon(img));
		
		calculateApplicationPoint();

		
		System.out.println("setting bounds: "+applicationPointX+" "+applicationPointY);
		setBounds(applicationPointX, applicationPointY, newW, newH);

		this.addMouseListener(this);

		// icon = new ImageIcon(path);
		// this.setIcon(icon);
	}

	private void calculateApplicationPoint() {
		setInitialApplicationPoint();
//		System.out.println("InitialapplicationPoint: x: "+applicationPointX+"  y: "+applicationPointY);
		applicationPointX+=cardNumber*(cardWidth+ratioSpaceW*personalW);

	}

	private void setInitialApplicationPoint() {
		switch (cardType) {
		case BUILDING:
			applicationPointX=(int)(ratioLeftBorder*personalW);
			applicationPointY=(int)(ratioHighBorder*personalH);
			break;
		case VENTURE:
			applicationPointX=(int)(ratioSecondLeftBorder*personalW);
			applicationPointY=(int)(ratioHighBorder*personalH);
			break;
		case TERRITORY:
			applicationPointX=(int)(ratioLeftBorder*personalW);
			applicationPointY=(int)(ratioSecondHighBorder*personalH);
			break;
		case CHARACTER:
			applicationPointX=(int)(ratioSecondLeftBorder*personalW);
			applicationPointY=(int)(ratioSecondHighBorder*personalH);
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// popup = new JWindow();
		// popup.setLayout(new BorderLayout());
		// popup.add(this);
		// popup.pack();

		// this.getParent().getComponentZOrder(this);

		this.getParent().setComponentZOrder(this, 0);

		setBounds(applicationPointX, applicationPointY, w, h);
		setIcon(new ImageIcon(zoomedImage));
		// revalidate();
		repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		setBounds(applicationPointX, applicationPointY, cardWidth, cardHeight);
		setIcon(new ImageIcon(img));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
