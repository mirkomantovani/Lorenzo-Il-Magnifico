package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import it.polimi.ingsw.ps19.constant.ImagesConstants;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.area.Tower;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceType;

/**
 * This is the main frame of the game.
 *
 * @author Mirko
 */
public class MyFrame extends JFrame {

	/** The player color. */
	private String playerColor;
	
	private static Clip clip;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);

					frame.removeInitialImage();
					GamePanel gameP = new GamePanel("black",2);
					frame.setContentPane(gameP);
					// frame.getGamePanel().addCard();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The toolkit. */
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	/** The screen dimension. */
	private Dimension screenDimension = new Dimension();
	
	/** The height. */
	private int height;
	
	/** The width. */
	private int width;
	
	/** The tool bar height. */
	private int toolBarHeight;
	
	/** The img. */
	private Image img;
	
	/** The game panel. */
	private GamePanel gamePanel;

	/** The content. */
	private Container content;
	
	/** The board. */
	private BoardPanel board;
	// private PlayerMoves moves;
	/** The initial panel. */
	// private GameConsole console;
	private InitialPanel initialPanel;

	/**
	 * Instantiates a new my frame.
	 * @throws  
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public MyFrame() {
		super("Lorenzo Il Magnifico");
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("/MJMLogoTransparent.png")));

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
	 setUndecorated(true);
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

	/**
	 * Adds the initial panel.
	 *
	 * @param initialImage the initial image
	 */
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

	/**
	 * Removes the initial image.
	 */
	public void removeInitialImage() {
		remove(initialPanel);
	}

	/**
	 * Initialize game frame.
	 *
	 * @param numPlayers the num players
	 */
	public void initializeGameFrame(int numPlayers) {
		gamePanel = new GamePanel(playerColor,numPlayers);
		setContentPane(gamePanel);

	}

	/**
	 * Refresh board.
	 *
	 * @param board the board
	 */
	public void refreshBoard(Board board) {
//		gamePanel.removeCards();
		addCards(board);
	}

	/**
	 * Adds the cards.
	 *
	 * @param board the board
	 */
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
						
//						if(!gamePanel.isContained(id))
						gamePanel.addCard(j, i, id, descr);
					} else{
						System.out.println("removing card: tower"+j+" floor:"+i);
						gamePanel.removeCard(j,i);
						
					}
					
				}

			}
		}
	}

	

	/**
	 * Gets the game panel.
	 *
	 * @return the game panel
	 */
	public GamePanel getGamePanel() {

		return gamePanel;
	}

	/**
	 * Refresh player status.
	 *
	 * @param p the p
	 */
	public void refreshPlayerStatus(Player p) {
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.COIN));
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.WOOD));
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.STONE));
		gamePanel.addResourceToPlayerStatus(p.getResourceChest().getResourceInChest(ResourceType.SERVANT));
		
		gamePanel.addFamilyMembersToPlayerStatus(p.getFamilyMembers());
		
	}

	/**
	 * Sets the player color.
	 *
	 * @param playerColor the new player color
	 */
	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}
	
	/**
	 * Adds the order marker disks.
	 *
	 * @param players the players
	 */
	public void addOrderMarkerDisks(Player[] players){
		for(int i = 0; i < players.length; i++){
			gamePanel.add(new OrderMarkerDisk(players[i].getColor()));
		}
	}

	/**
	 * Show choose action.
	 */
	public void showChooseAction() {
//		gamePanel.hideActionPanels();
		gamePanel.showChooseAction();
	}

	/**
	 * Show end or discard.
	 */
	public void showEndOrDiscard() {
		gamePanel.showEndOrDiscard();
		
	}

	/**
	 * Show privilege choice.
	 */
	public void showPrivilegeChoice() {
		gamePanel.showChoosePrivilege();
		
	}

	/**
	 * Show excommunication panel.
	 */
	public void showExcommunicationPanel() {
		gamePanel.showExcommunicationPanel();
	}

	/**
	 * Refresh leader cards.
	 *
	 * @param leaderCards the leader cards
	 */
	public void refreshLeaderCards(Map<String, LeaderCard> leaderCards) {
		gamePanel.refreshLeaders(leaderCards);
	}
	
	public static void startMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{

		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/main/resources/song.wav"));
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.loop(1);

	}
	
	public static void stopMusic(){
		        clip.stop();
	}

}
