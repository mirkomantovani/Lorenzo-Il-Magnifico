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

/**
 * This class represents a general leader card, it displays it and gives the
 * functionalities offered by a JButton
 *
 * @author Mirko
 */
public class JLeaderCard extends JButton implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The toolkit. */
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	/** The icon. */
	ImageIcon icon;
	
	/** The path. */
	private String path;
	
	/** The leader name. */
	private String leaderName;
	
	/** The screen dimension. */
	private Dimension screenDimension;
	
	/** The zoomed image. */
	private Image zoomedImage;
	
	
	/** The img. */
	private Image img;
	
	/**
	 * Instantiates a new j leader card.
	 *
	 * @param leaderName the leader name
	 */
	public JLeaderCard(String leaderName) {
		
		this.leaderName=leaderName;

		screenDimension = toolkit.getScreenSize();
		
//		personalW=screenDimension.width;
//		personalH=(int)(personalW/personalRatio);
		

		img = null;
	

		path = "/"+leaderName+".jpg";
//		path="Francesco Sforza.jpg";

		try {
			zoomedImage = ImageIO.read(getClass().getResource(path));

		} catch (Exception ex) {
		}
		
		zoomedImage = zoomedImage.getScaledInstance(screenDimension.width/8,screenDimension.width/8*500/300, Image.SCALE_SMOOTH);


//		int newW = (int) (ratioCardW * screenDimension.width);
//		int newH = newW * h / w;
		
//		cardWidth=newW;
//		cardHeight=newH;

		// "+(int)(ratio*1017));
		//300,500
//		img = zoomedImage.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		img = zoomedImage.getScaledInstance(screenDimension.width/10,screenDimension.width/10*500/300, Image.SCALE_SMOOTH);

		setIcon(new ImageIcon(img));
		
//		calculateApplicationPoint();

		this.addMouseListener(this);

//		this.addMouseListener(this);

		// icon = new ImageIcon(path);
		// this.setIcon(icon);
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

//		this.getParent().setComponentZOrder(this, 0);

//		setBounds(applicationPointX, applicationPointY, w, h);
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

//		setBounds(applicationPointX, applicationPointY, cardWidth, cardHeight);
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

	/**
	 * Gets the leader name.
	 *
	 * @return the leader name
	 */
	public String getLeaderName() {
		return leaderName;
	}
	
	
	
}
