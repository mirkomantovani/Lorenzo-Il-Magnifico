package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.ImagesConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.area.Tower;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceType;

/**
 * This is the main frame of the game
 * 
 * @author Mirko
 *
 */
public class MyFrame extends JFrame {

	private String playerColor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);

					frame.removeInitialImage();
					GamePanel gameP = new GamePanel("red");
					frame.setContentPane(gameP);
					// frame.getGamePanel().addCard();

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
	// private PlayerMoves moves;
	// private GameConsole console;
	private InitialPanel initialPanel;

	public MyFrame() {
		super("Lorenzo Il Magnifico");

		// Setting the LookAndFeel theme
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}
//		 setUndecorated(true);
		setLayout(new BorderLayout());
		content = this.getContentPane();

		screenDimension = toolkit.getScreenSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;

		width = screenDimension.width;
		height = screenDimension.height - toolBarHeight;

		addInitialPanel(ImagesConstants.INITIAL_IMAGE);
		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void addInitialPanel(String initialImage) {

		// ImageIcon backgroundImg = new ImageIcon(this.getClass().getResource(
		// initialImage));
		//
		// int imageWidth = backgroundImg.getIconWidth();
		// int imgHeight = backgroundImg.getIconHeight();
		//
		initialPanel = new InitialPanel(initialImage);

		content.add(initialPanel, BorderLayout.CENTER);
		initialPanel.setLayout(null);
		// initialPanel.setSize(new Dimension(imageWidth, imgHeight));
		// initialPanel.setPreferredSize(new Dimension(imageWidth, imgHeight));
		// content.add(initialPanel);
		// pack();
		// setVisible(true);
		// setResizable(false);
		// setLocationRelativeTo(null);
		//
		// Runnable repaintFrame = new Runnable() {
		// @Override
		// public void run(){
		// content.repaint();
		// }
		// };
		//
		// SwingUtilities.invokeLater(repaintFrame);
	}

	public void removeInitialImage() {
		remove(initialPanel);
	}

	public void initializeGameFrame() {
		gamePanel = new GamePanel(playerColor);
		setContentPane(gamePanel);

	}

	public void refreshBoard(Board board) {
		gamePanel.removeCards();
		addCards(board);
	}

	private void addCards(Board board) {
		System.out.println("adding cards");

		for (int j = 0; j < CardType.values().length; j++) {
			CardType c = CardType.values()[j];
			if (c != CardType.ANY) {
				Tower t = board.getTower(c);
				for (int i = 0; i < t.getFloors().size(); i++) {
					if (t.getFloor(i).getCard() != null) {
						int id = t.getFloor(i).getCard().getId();
						String descr = t.getFloor(i).getCard().toString();
						gamePanel.addCard(j, i, id, descr);
					}
				}

			}
		}
	}

	public GamePanel getGamePanel() {

		return gamePanel;
	}

	public void refreshPlayerStatus(Player p) {
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.COIN));
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.WOOD));
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.STONE));
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.SERVANT));
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}
	
	public void addOrderMarkerDisks(Player[] players){
		for(int i = 0; i < players.length; i++){
			gamePanel.add(new OrderMarkerDisk(players[i].getColor()));
		}
	}

	public void showChooseAction() {
//		gamePanel.hideActionPanels();
		gamePanel.showChooseAction();
	}

	public void showEndOrDiscard() {
		gamePanel.showEndOrDiscard();
		
	}
	

}
