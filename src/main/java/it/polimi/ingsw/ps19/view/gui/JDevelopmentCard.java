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
 * This class represents a general development card, it displays it and gives the
 * functionalities offered by a JButton
 *
 * @author Mirko
 */
public class JDevelopmentCard extends JButton implements MouseListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The card type. */
	private CardType cardType;
	
	/** The id. */
	private int id;

	/** The toolkit. */
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();

	/** The card width. */
	private int cardWidth;
	
	/** The card height. */
	private int cardHeight;
	
	/** The application point X. */
	private int applicationPointX;
	
	/** The application point Y. */
	private int applicationPointY;
	
	/** The card number. */
	private int cardNumber;
	
	/** The personal H. */
	private int personalH;
	
	/** The personal W. */
	private int personalW;

	/** The ratio card W. */
	private final double ratioCardW = 0.076486406;
	
	/** The ratio space W. */
	private final double ratioSpaceW = 0.00657305;

	/** The screen dimension. */
	private Dimension screenDimension;

	/** The ratio high border. */
	private final double ratioHighBorder=0.025;
	
	/** The ratio second high border. */
	private final double ratioSecondHighBorder=0.464;
	
	/** The ratio left border. */
	private final double ratioLeftBorder=0.006;
	
	/** The ratio second left border. */
	private final double ratioSecondLeftBorder=0.505;
	

	/** The icon. */
	ImageIcon icon;
	
	/** The path. */
	private String path;
	

	/** The zoomed image. */
	private Image zoomedImage;
	
	/** The h. */
	private int h;
	
	/** The w. */
	private int w;
	
	/** The img. */
	private Image img;
	
	/** The personal ratio. */
	private double personalRatio=2.789166667;
;

	/**
	 * Instantiates a new j development card.
	 *
	 * @param cardType the card type
	 * @param id the id
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

	/**
	 * Calculate application point.
	 */
	private void calculateApplicationPoint() {
		setInitialApplicationPoint();
//		System.out.println("InitialapplicationPoint: x: "+applicationPointX+"  y: "+applicationPointY);
		applicationPointX+=cardNumber*(cardWidth+ratioSpaceW*personalW);

	}

	/**
	 * Sets the initial application point.
	 */
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
		default:
			applicationPointX=(int)(ratioLeftBorder*personalW);
			applicationPointY=(int)(ratioHighBorder*personalH);
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
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

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		setBounds(applicationPointX, applicationPointY, cardWidth, cardHeight);
		setIcon(new ImageIcon(img));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
