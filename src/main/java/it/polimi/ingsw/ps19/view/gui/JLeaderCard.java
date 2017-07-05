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
 * @author Mirko
 *
 */
public class JLeaderCard extends JButton implements MouseListener {
	
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	ImageIcon icon;
	private String path;
	private String leaderName;
	
	private Dimension screenDimension;
	
	private Image zoomedImage;
	private int h;
	private int w;
	private Image img;
	
	public JLeaderCard(String leaderName) {
		
		this.leaderName=leaderName;

		screenDimension = toolkit.getScreenSize();
		
//		personalW=screenDimension.width;
//		personalH=(int)(personalW/personalRatio);
		
//		System.out.println("personal W e H: "+personalW+"  "+personalH);

		img = null;
	

		path = "/"+leaderName+".jpg";
//		path="Francesco Sforza.jpg";

		try {
			zoomedImage = ImageIO.read(getClass().getResource(path));

		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		zoomedImage = zoomedImage.getScaledInstance(screenDimension.width/8,screenDimension.width/8*500/300, Image.SCALE_SMOOTH);

		h = zoomedImage.getHeight(null);
		w = zoomedImage.getWidth(null);

//		int newW = (int) (ratioCardW * screenDimension.width);
//		int newH = newW * h / w;
		
//		cardWidth=newW;
//		cardHeight=newH;

		// System.out.println((int)(ratio*boardPanelPrefSize.height)+"
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

//		this.getParent().setComponentZOrder(this, 0);

//		setBounds(applicationPointX, applicationPointY, w, h);
		setIcon(new ImageIcon(zoomedImage));
		// revalidate();
		repaint();

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

//		setBounds(applicationPointX, applicationPointY, cardWidth, cardHeight);
		setIcon(new ImageIcon(img));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public String getLeaderName() {
		return leaderName;
	}
	
	
	
}
