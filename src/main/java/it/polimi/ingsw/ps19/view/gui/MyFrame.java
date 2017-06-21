package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps19.constant.ImagesConstants;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension screenDimension = new Dimension();
	private int height;
	private int width;
	private int toolBarHeight;
	private Image img;

	private Container content;
//	private GameBoard board;
//	private PlayerMoves moves;
//	private GameConsole console;
	private InitialPanel initialPanel;
	
	public MyFrame() {
		super("Lorenzo Il Magnifico");

		setUndecorated(true);
		setLayout(new BorderLayout());
		content = this.getContentPane();

		screenDimension = toolkit.getScreenSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration()).bottom;

		width = screenDimension.width;
		height = screenDimension.height - toolBarHeight;

		addInitialPanel(ImagesConstants.INITIAL_IMAGE);
		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void addInitialPanel(String initialImage) {
		
		
//		ImageIcon backgroundImg = new ImageIcon(this.getClass().getResource(
//				initialImage));
//		
//		int imageWidth = backgroundImg.getIconWidth();
//		int imgHeight = backgroundImg.getIconHeight();
//		
		initialPanel = new InitialPanel(initialImage);
		
		content.add(initialPanel, BorderLayout.CENTER);
		initialPanel.setLayout(null);
//		initialPanel.setSize(new Dimension(imageWidth, imgHeight));
//		initialPanel.setPreferredSize(new Dimension(imageWidth, imgHeight));
//		content.add(initialPanel);
//		pack();
//		setVisible(true);
//		setResizable(false);
//		setLocationRelativeTo(null);	
//		
//		Runnable repaintFrame = new Runnable() {
//			@Override
//			public void run(){
//				content.repaint();
//			}
//		};
//		
//		SwingUtilities.invokeLater(repaintFrame);
	}	

}
