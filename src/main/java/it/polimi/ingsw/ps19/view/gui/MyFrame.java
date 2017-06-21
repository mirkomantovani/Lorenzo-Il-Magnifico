package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.constant.ImagesConstants;

/**
 * @author Mirko
 *
 */
public class MyFrame extends JFrame {
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
					
					frame.removeInitialImage();
					frame.initializeGameFrame();
					frame.getGamePanel().addCard();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
	private GamePanel gamePanel;

	private Container content;
	private BoardPanel board;
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

	public void removeInitialImage() {
		remove(initialPanel);
	}

	public void initializeGameFrame() {
		gamePanel=new GamePanel();
		setContentPane(gamePanel);
		
	}

	public GamePanel getGamePanel() {
		
		return gamePanel;
	}

	


	
	
	
	

}
