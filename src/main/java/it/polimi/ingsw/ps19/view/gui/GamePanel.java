package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import it.polimi.ingsw.ps19.Dice;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceType;

/**
 * The content of the entire frame (MyFrame) during the GamePlay.
 *
 * @author Mirko
 */
public class GamePanel extends JPanel implements ActionListener, MouseListener {

	/** The Constant CHAT_BACKGROUND_COLOR. */
	private static final Color CHAT_BACKGROUND_COLOR = new Color(245, 200, 86);

	/** The Constant BACKGROUND_PANELS_COLOR. */
	private static final Color BACKGROUND_PANELS_COLOR = new Color(204, 153, 51);

	/** The screen dim. */
	protected static Dimension screenDim;

	/** Can be: draft, none, activate, discard. */
	private String leaderState = "draft";

	/** The toolkit. */
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	/** The text field. */
	private JTextField textField;
	
	/** The board panel. */
	private BoardPanel boardPanel;

	/** The send chat. */
	private JButton sendChat;
	
	/** The show personal board. */
	private JButton showPersonalBoard;
	
	/** The strategy editor button. */
	private JButton strategyEditorButton;
	
	/** The quit game button. */
	private JButton quitGameButton;
	
	/** The show leader cards button. */
	private JButton showLeaderCardsButton;

	/** The first market. */
	private MarketButton firstMarket;
	
	/** The second market. */
	private MarketButton secondMarket;
	
	/** The third market. */
	private MarketButton thirdMarket;
	
	/** The fourth market. */
	private MarketButton fourthMarket;
	
	/** The council button. */
	private CouncilButton councilButton;
	
	/** The single harvest button. */
	private SingleHarvestButton singleHarvestButton;
	
	/** The single production button. */
	private SingleProductionButton singleProductionButton;
	
	/** The multiple harvest button. */
	private MultipleHarvestButton multipleHarvestButton;
	
	/** The multiple production button. */
	private MultipleProductionButton multipleProductionButton;
	

	/** The text area. */
	private JTextArea textArea;
	
	/** The player resources. */
	private PlayerResources playerResources;
	
	/** The buttons font. */
	private final Font buttonsFont = new Font("SansSerif", Font.BOLD, 16);

	/** The cards. */
	private List<CardButton> cards;

	/** The action content pane. */
	private Container actionContentPane;

	/** The action panel. */
	private ActionPanel actionPanel;
	
	/** The choose action. */
	private ChooseAction chooseAction;
	
	/** The leaders panel. */
	private LeadersPanel leadersPanel;
	
	/** The strategy editor. */
	private StrategyEditor strategyEditor;
	
	/** The production choices. */
	private ProductionChoices productionChoices;
	
	/** The end or discard panel. */
	private EndOrDiscardPanel endOrDiscardPanel;
	
	/** The choose privilege panel. */
	private ChoosePrivilegePanel choosePrivilegePanel;
	
	/** The choose excommunication panel. */
	private ChooseExcommunicationPanel chooseExcommunicationPanel;

	/** The draft panel. */
	private LeadersPanel draftPanel;
	
	private AskAuthenticationPanel askAuthenticationPanel;

	/** The action constructor. */
	private ArrayList<String> actionConstructor;

	/** The gui. */
	private GraphicalUserInterface GUI;

	/** The current action panel. */
	private Component currentActionPanel;

	/** The order markers. */
	private List<OrderMarkerDisk> orderMarkers;
	
	/** The victory markers. */
	private Map<String, VictoryPointMarkerDisk> victoryMarkers;
	
	/** The military markers. */
	private Map<String, MilitaryPointMarkerDisk> militaryMarkers;
	
	/** The faith markers. */
	private Map<String, FaithPointMarkerDisk> faithMarkers;
	
	/** The familiars. */
	private Map<String, FamilyMemberPawn> familiars; // the key is
														
														/** The dices. */
														// diceColor+playerColor
	private Map<String, DicePanel> dices; // (String) diceValue + diceColor
	
	private int currentNumberOfPrivilege;
	
	private ArrayList<String> chosenPrivileges;

	/**
	 * Instantiates a new game panel.
	 *
	 * @param playerColor the player color
	 * @param numPlayers the num players
	 */
	public GamePanel(String playerColor, int numPlayers) {
		
		chosenPrivileges=new ArrayList<String>();

		cards = new ArrayList<CardButton>();

		screenDim = toolkit.getScreenSize();
		// this.set(true);
		// setExtendedState(Frame.MAXIMIZED_BOTH);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		// contentPane = new JPanel();
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));

		// BOARD

		boardPanel = new BoardPanel();
		add(boardPanel, BorderLayout.WEST);
		boardPanel.setLayout(null);

		// JButton btnNewButton = new JButton("New button");
		// btnNewButton.setContentAreaFilled(false);
		// btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
		// null, null, null));
		// btnNewButton.setBounds(72, 72, 105, 176);
		// boardPanel.add(btnNewButton);

		// JButton btnNewButton_2 = new
		// CardButton(boardPanel.getPreferredSize(),1,0,55);
		// btnNewButton_2.setBounds(268, 256, 105, 170);
		// boardPanel.add(btnNewButton_2);

		// MarketButton market=new MarketButton();
		// market.setBounds(200,200,50,50);
		// boardPanel.add(market);

		System.out.println("BoardPanel preferredSize: " + boardPanel.getPreferredSize().getHeight() + " "
				+ boardPanel.getPreferredSize().getWidth());

		
		// RIGHT SCROLLBAR

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setSize(new
		// Dimension(screenDim.width-panel.getPreferredSize().width, 100));
		scrollPane.setPreferredSize(new Dimension(screenDim.width - boardPanel.getPreferredSize().width, 500));
		add(scrollPane, BorderLayout.EAST);

		// PANEL CONTAINED IN THE SCROLLBAR

		JPanel rightScrollbarContainer = new JPanel();
		rightScrollbarContainer
				.setMaximumSize(new Dimension(screenDim.width - boardPanel.getPreferredSize().width, 20000));
		rightScrollbarContainer
				.setPreferredSize(new Dimension(screenDim.width - boardPanel.getPreferredSize().width, 1000));
		scrollPane.setViewportView(rightScrollbarContainer);
		rightScrollbarContainer.setLayout(new BoxLayout(rightScrollbarContainer, BoxLayout.Y_AXIS));

		// CHAT INTERNALFRAME

		JInternalFrame internalFrame = new JInternalFrame("Chat");
		internalFrame.setBackground(SystemColor.controlHighlight);
		rightScrollbarContainer.add(internalFrame);
		internalFrame.setPreferredSize(
				new Dimension(screenDim.width - boardPanel.getPreferredSize().width, screenDim.height / 3));
		internalFrame.setMaximumSize(
				new Dimension(screenDim.width - boardPanel.getPreferredSize().width, screenDim.height / 3));

		// CHAT TEXTAREA

		textArea = new JTextArea();
		textArea.setMargin(new Insets(1, 1, 1, 1));
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		textArea.setBackground(CHAT_BACKGROUND_COLOR);
		textArea.setFont(new Font("Consolas", 0, 20));

		// SCROLLPANE FOR CHAT

		JScrollPane scrPane = new JScrollPane(textArea);
		Border border = BorderFactory.createLoweredBevelBorder();
		scrPane.setBorder(border);

		JScrollBar vertical = scrPane.getVerticalScrollBar();
		vertical.setPreferredSize(new Dimension(0, 0));

		internalFrame.getContentPane().add(scrPane, BorderLayout.CENTER);

		JPanel chatInputPanel = new JPanel();
		internalFrame.getContentPane().add(chatInputPanel, BorderLayout.SOUTH);
		chatInputPanel.setLayout(new BorderLayout(0, 0));

		// CHAT INPUT BUTTON

		sendChat = new JButton("Send");
		sendChat.setForeground(new Color(255, 255, 255));
		sendChat.setBackground(new Color(102, 51, 51));
		sendChat.setFont(buttonsFont);
		sendChat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendChat.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		chatInputPanel.add(sendChat, BorderLayout.EAST);

		// TEXTFIELD

		JPanel textFieldOuterPanel = new JPanel();
		textFieldOuterPanel.setBackground(new Color(160, 82, 45));
		chatInputPanel.add(textFieldOuterPanel, BorderLayout.CENTER);
		textFieldOuterPanel.setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 26));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOuterPanel.add(textField);
		textField.setColumns(50);

		// PLAYER RESOURCES PANEL

		playerResources = new PlayerResources(screenDim.width - boardPanel.getPreferredSize().width, playerColor);
		// playerResources.setPreferredSize(new
		// Dimension(screenDim.width-panel.getPreferredSize().width,400));
		// playerResources.setMaximumSize(new
		// Dimension(screenDim.width-panel.getPreferredSize().width,800));

		rightScrollbarContainer.add(playerResources);

		// ACTIONS INTERNAL FRAME

		JInternalFrame actionsInternalFrame = new JInternalFrame("Game actions");
		actionsInternalFrame.setMaximizable(true);
		actionsInternalFrame.getContentPane().setBackground(new Color(160, 82, 45));
		actionsInternalFrame.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		actionsInternalFrame.setResizable(true);
		// internalFrame_1.setIconifiable(true);
		// internalFrame_1.setClosable(true);
		// internalFrame_1.setResizable(true);

		// setBorder(val ? null : border);

		// internalFrame_1.setRootPaneCheckingEnabled(false);
		// internalFrame_1.getUI().setNorthPane(val ? null : northPane);
		// internalFrame_1.setRootPaneCheckingEnabled(true);
		actionsInternalFrame.setBounds(new Rectangle(0, 0, 500, 0));
		rightScrollbarContainer.add(actionsInternalFrame);
		actionsInternalFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		actionContentPane = actionsInternalFrame.getContentPane();

		// BUTTONS: Market, council, production and harvest

		firstMarket = new MarketButton(1);
		firstMarket.addActionListener(this);
		boardPanel.add(firstMarket);

		secondMarket = new MarketButton(2);
		secondMarket.addActionListener(this);
		boardPanel.add(secondMarket);

		if (numPlayers > 3) {
			thirdMarket = new MarketButton(3);
			thirdMarket.addActionListener(this);
			boardPanel.add(thirdMarket);

			fourthMarket = new MarketButton(4);
			fourthMarket.addActionListener(this);
			boardPanel.add(fourthMarket);
		}

		councilButton = new CouncilButton();
		councilButton.addActionListener(this);
		boardPanel.add(councilButton);

		singleHarvestButton = new SingleHarvestButton();
		singleHarvestButton.addActionListener(this);
		boardPanel.add(singleHarvestButton);

		singleProductionButton = new SingleProductionButton();
		singleProductionButton.addActionListener(this);
		boardPanel.add(singleProductionButton);

		if (numPlayers > 2) {
			multipleHarvestButton = new MultipleHarvestButton();
			multipleHarvestButton.addActionListener(this);
			boardPanel.add(multipleHarvestButton);

			multipleProductionButton = new MultipleProductionButton();
			multipleProductionButton.addActionListener(this);
			boardPanel.add(multipleProductionButton);
		}

		// ACTION PANELS

		actionPanel = new ActionPanel(this, playerColor);
		actionPanel.setBackground(BACKGROUND_PANELS_COLOR);
		actionPanel.setVisible(false);
		// actionContentPane.add(actionPanel);

		chooseAction = new ChooseAction(this);
		chooseAction.setBackground(BACKGROUND_PANELS_COLOR);
		chooseAction.setVisible(false);
		// actionContentPane.add(chooseAction);
		// actionsInternalFrame.getContentPane().add(actionPanel);

		strategyEditor = new StrategyEditor();
		strategyEditor.setVisible(false);
		// actionContentPane.add(strategyEditor);

		endOrDiscardPanel = new EndOrDiscardPanel(this);
		endOrDiscardPanel.setBackground(BACKGROUND_PANELS_COLOR);
		endOrDiscardPanel.setVisible(false);

		choosePrivilegePanel = new ChoosePrivilegePanel(screenDim.width - boardPanel.getPreferredSize().width, this);
		choosePrivilegePanel.setBackground(BACKGROUND_PANELS_COLOR);
		choosePrivilegePanel.setVisible(false);

		chooseExcommunicationPanel = new ChooseExcommunicationPanel(this);
		chooseExcommunicationPanel.setBackground(BACKGROUND_PANELS_COLOR);
		chooseExcommunicationPanel.setVisible(false);

		leadersPanel = new LeadersPanel();
		leadersPanel.setBackground(BACKGROUND_PANELS_COLOR);
		leadersPanel.setVisible(false);

		draftPanel = new LeadersPanel();
		draftPanel.setBackground(BACKGROUND_PANELS_COLOR);
		draftPanel.setVisible(false);
		
		productionChoices=new ProductionChoices(this);
		productionChoices.setBackground(BACKGROUND_PANELS_COLOR);
		productionChoices.setVisible(false);
		
		askAuthenticationPanel=new AskAuthenticationPanel(this);
		askAuthenticationPanel.setBackground(BACKGROUND_PANELS_COLOR);
		askAuthenticationPanel.setVisible(false);

		// FINAL BUTTONS PANEL

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(210, 180, 140));
		rightScrollbarContainer.add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		showLeaderCardsButton = new JButton("Show Leader Cards");
		showLeaderCardsButton.setFont(buttonsFont);
		showLeaderCardsButton.setForeground(new Color(255, 255, 255));
		showLeaderCardsButton.setBackground(new Color(102, 51, 51));
		showLeaderCardsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showLeaderCardsButton.addActionListener(this);
		buttonsPanel.add(showLeaderCardsButton);

		showPersonalBoard = new JButton("Show Personal Board");
		showPersonalBoard.setFont(buttonsFont);
		showPersonalBoard.setBackground(new Color(102, 51, 51));
		showPersonalBoard.setForeground(new Color(255, 255, 255));
		showPersonalBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(showPersonalBoard);

		strategyEditorButton = new JButton("Strategy editor");
		strategyEditorButton.setFont(buttonsFont);
		strategyEditorButton.setBackground(new Color(102, 51, 51));
		strategyEditorButton.setForeground(new Color(255, 255, 255));
		strategyEditorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		strategyEditorButton.addActionListener(this);
		buttonsPanel.add(strategyEditorButton);

		quitGameButton = new JButton("Quit Game");
		quitGameButton.setFont(buttonsFont);
		quitGameButton.setBackground(new Color(20, 20, 0));
		quitGameButton.setForeground(new Color(255, 255, 255));
		quitGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.notifyCloseGame();
			}
		});
		buttonsPanel.add(quitGameButton);

		actionsInternalFrame.setVisible(true);
		internalFrame.setVisible(true);

		orderMarkers = new ArrayList<OrderMarkerDisk>();
		victoryMarkers = new HashMap<String, VictoryPointMarkerDisk>();
		militaryMarkers = new HashMap<String, MilitaryPointMarkerDisk>();
		faithMarkers = new HashMap<String, FaithPointMarkerDisk>();
		familiars = new HashMap<String, FamilyMemberPawn>();
		dices = new HashMap<String, DicePanel>();
		createDices();
	}

	/**
	 * Adds the card.
	 *
	 * @param tower the tower
	 * @param floor the floor
	 * @param id the id
	 * @param descr the descr
	 */
	public void addCard(int tower, int floor, int id, String descr) {
		if (tower == 1)
			tower = 2;
		else if (tower == 2)
			tower = 1;
		CardButton card = new CardButton(boardPanel.getPreferredSize(), tower, floor, id);
		// btnNewButton_2.setBounds(268, 256, 105, 170);
		card.addActionListener(this);
		card.setToolTipText(descr);
		boardPanel.add(card);
		cards.add(card);

	}

	/**
	 * Gets the send chat.
	 *
	 * @return the send chat
	 */
	public JButton getSendChat() {
		return sendChat;
	}

	/**
	 * Gets the show personal board.
	 *
	 * @return the show personal board
	 */
	public JButton getShowPersonalBoard() {
		return showPersonalBoard;
	}

	/**
	 * Gets the and delete chat input.
	 *
	 * @return the and delete chat input
	 */
	public String getAndDeleteChatInput() {
		String m = this.textField.getText();
		this.textField.setText("");
		return m;

	}

	/**
	 * Adds the message to console.
	 *
	 * @param message the message
	 */
	public void addMessageToConsole(String message) {
		message = "\n" + message;
		this.textArea.append(message);
		this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
	}

	/**
	 * Write game message.
	 *
	 * @param string the string
	 */
	private void writeGameMessage(String string) {
		addMessageToConsole("\n<-GAME-> " + string + "\n");
	}

	/**
	 * Sets the marker order.
	 *
	 * @param markers the new marker order
	 */
	public void setMarkerOrder(ArrayList<OrderMarkerDisk> markers) {
		for (OrderMarkerDisk m : markers) {
			paint(m.getGraphics());
			m.setAlignmentX((float) 782.23);
			m.setAlignmentY(792);
		}
	}

	/**
	 * Sets the marker victory points.
	 */
	public void setMarkerVictoryPoints() {

	}

	/**
	 * Sets the marker military point.
	 */
	public void setMarkerMilitaryPoint() {

	}

	/**
	 * Sets the church marker.
	 */
	public void setChurchMarker() {

	}

	/**
	 * Adds the resource to player status.
	 *
	 * @param resourceInChest the resource in chest
	 */
	public void addResourceToPlayerStatus(Resource resourceInChest) {
		playerResources.refreshResource(resourceInChest);
	}

	/**
	 * Adds the family members to player status.
	 *
	 * @param familyMembers the family members
	 */
	public void addFamilyMembersToPlayerStatus(HashMap<it.polimi.ingsw.ps19.Color, FamilyMember> familyMembers) {
		playerResources.refreshFamilyMembers(familyMembers);
	}

	/**
	 * Removes the cards.
	 */
	public void removeCards() {
		cards.forEach(card -> boardPanel.remove(card));

	}

	/**
	 * Gets the board panel.
	 *
	 * @return the board panel
	 */
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	/**
	 * Show action panel.
	 *
	 * @param panel the panel
	 */
	private void showActionPanel(Component panel) {
		// rimuovo tutti gli altri panels
		// chooseAction.setVisible(false);
		// actionContentPane.remove(chooseAction);
		setEveryoneInvisible(this.actionContentPane.getComponents());
		this.actionContentPane.removeAll();
		this.actionContentPane.add(panel);
		this.actionContentPane.repaint();
		panel.setVisible(true);
	}

	/**
	 * Sets the everyone invisible.
	 *
	 * @param components the new everyone invisible
	 */
	private void setEveryoneInvisible(Component[] components) {
		for (int i = 0; i < components.length; i++)
			components[i].setVisible(false);
	}

	/**
	 * Removes the action panel.
	 */
	private void removeActionPanel() {
		this.actionContentPane.removeAll();
		actionContentPane.repaint();
	}

	/**
	 * Show choose action.
	 */
	public void showChooseAction() {
		System.out.println("show choose action");
		this.currentActionPanel = chooseAction;
		this.showActionPanel(chooseAction);

	}

	/**
	 * Sets the observer.
	 *
	 * @param GUI the new observer
	 */
	public void setObserver(GraphicalUserInterface GUI) {
		this.GUI = GUI;
	}

	/**
	 * Notify action click.
	 */
	public void notifyActionClick() {
		writeGameMessage("Choose the family member and the amount of servants "
				+ "you intend to use in this action, then press the area"
				+ "you want to place your family member into");
		this.currentActionPanel = actionPanel;
		this.showActionPanel(actionPanel);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof CardButton) {
			CardButton card = (CardButton) e.getSource();
			constructAction(card);
			this.GUI.notifyTakeCard(actionConstructor);

		} else if (e.getSource() == strategyEditorButton) {
			if (!strategyEditor.isVisible())
				showActionPanel(strategyEditor);
			else
				backToCurrentAction();

		} else if (e.getSource() instanceof MarketButton) {
			String market = ((MarketButton) e.getSource()).getName();
			constructAction(market);
			if (actionConstructor.size() == 4) {
				this.GUI.notifyMarketAction(actionConstructor);
			} else {
			}

		} else if (e.getSource() instanceof CouncilButton) {
			constructCouncilAction();
			this.GUI.notifyCouncilAction(actionConstructor);

		} else if (e.getSource() instanceof SingleHarvestButton) {
			addFamServToConstructor();
			actionConstructor.add("harvest");
			actionConstructor.add("1");
			this.GUI.notifyHarvest(actionConstructor);

		} else if (e.getSource() instanceof SingleProductionButton) {
			addFamServToConstructor();
			actionConstructor.add("production");
			actionConstructor.add("1");
			this.GUI.notifyProduction(actionConstructor);

		} else if (e.getSource() instanceof MultipleHarvestButton) {
			addFamServToConstructor();
			actionConstructor.add("harvest");
			actionConstructor.add("2");
			this.GUI.notifyHarvest(actionConstructor);

		} else if (e.getSource() instanceof MultipleProductionButton) {
			addFamServToConstructor();
			actionConstructor.add("production");
			actionConstructor.add("2");
			this.GUI.notifyProduction(actionConstructor);

		} else if (e.getSource() instanceof JLeaderCard) {
//			System.out.println("leader card clicked");
			JLeaderCard leader = (JLeaderCard) (e.getSource());
			this.removeActionPanel();
			leadersPanel.setVisible(true);

			if (leaderState.equals("draft")) {
				this.GUI.notifyChosenLeaderInDraft(leader.getLeaderName());
				draftPanel = new LeadersPanel();
				draftPanel.setBackground(BACKGROUND_PANELS_COLOR);
				draftPanel.setVisible(false);
			} else if (leaderState.equals("activate")) {
				// System.out.println("activate leader");
				this.GUI.notifyActivateLeader(leader.getLeaderName());
				leaderState = "none";
				leadersPanel.setVisible(false);

			} else if (leaderState.equals("discard")) {
				// System.out.println("discard leader");
				this.GUI.notifyDiscardLeader(leader.getLeaderName());
				leaderState = "none";
				// this.removeActionPanel();
				leadersPanel.setVisible(false);

			}
		} else if (e.getSource() == showLeaderCardsButton) {
			if (!leadersPanel.isVisible()) {
				showActionPanel(leadersPanel);
			} else {
				backToCurrentAction();
			}
		}

	}

	/**
	 * Adds the fam serv to constructor.
	 */
	private void addFamServToConstructor() {
		actionConstructor = new ArrayList<String>();
		String familyMember;
		String servants; // number

		familyMember = actionPanel.getFamilyMember();

		if (familyMember == "none") {
			invalidInputMessage("Select a family member");
			return;
		}

		servants = actionPanel.getServants();

		actionConstructor.add(familyMember);
		actionConstructor.add(servants);
	}

	/**
	 * Construct council action.
	 */
	private void constructCouncilAction() {
		addFamServToConstructor();

	}

	/**
	 * Construct action.
	 *
	 * @param market the market
	 */
	private void constructAction(String market) {
		System.out.println("gamepanel:constructing marketaction");
		actionConstructor = new ArrayList<String>();

		String familyMember;
		String servants; // number
		String actionSpace = market; // FIRST,SECOND,THIRD,FOURTH as the place
										// of the marker spot from the left to
										// the
		// right on the board
		familyMember = actionPanel.getFamilyMember();

		if (familyMember == "none") {
			invalidInputMessage("Select a family member");
			return;
		}

		servants = actionPanel.getServants();

		// I have to adapt the order because it was made that way in the
		// ClietController
		// order: 0-family member, 1-servants, 2-doesn't matter (actiontype),
		// 3-actionspace

		actionConstructor.add(familyMember);
		actionConstructor.add(servants);
		actionConstructor.add("market");
		actionConstructor.add(actionSpace);

	}

	/**
	 * Back to current action.
	 */
	private void backToCurrentAction() {
		if (currentActionPanel != null)
			showActionPanel(currentActionPanel);
		else {
			setEveryoneInvisible(this.actionContentPane.getComponents());
			this.actionContentPane.removeAll();
		}
	}

	/**
	 * Construct action.
	 *
	 * @param card the card
	 */
	private void constructAction(CardButton card) {
		// order; 0-member, 1-servants, 2-unused, 3-cardtype,4-floor
		actionConstructor = new ArrayList<String>();

		String familyMember;
		String floor; // number
		String servants; // number
		int tower;
		String cardType; // number
		familyMember = actionPanel.getFamilyMember();

		if (familyMember == "none") {
			invalidInputMessage("Select a family member");
			return;
		}

		floor = "" + card.getFloor();
		tower = card.getTower();

		if (tower == 1)
			tower = 2;
		else if (tower == 2)
			tower = 1;

		tower = tower + 1; // to use the conventions of CLI...

		servants = actionPanel.getServants();
		cardType = "" + tower; // scambio 2 torri centrali

		System.out.println("GamePanel: costructAction: member:" + familyMember + " servants:" + servants
				+ " cardtype/tower:" + cardType + " floor:" + floor);

		actionConstructor.add(familyMember);
		actionConstructor.add(servants);
		actionConstructor.add("takecard");
		actionConstructor.add(cardType);
		actionConstructor.add(floor);
	}

	/**
	 * Invalid input message.
	 *
	 * @param string the string
	 */
	private void invalidInputMessage(String string) {
		this.addMessageToConsole("--INVALID INPUT--: " + string);

	}

	/**
	 * Notify end round.
	 */
	public void notifyEndRound() {
		writeGameMessage("Your round has ended");
		this.removeActionPanel();
		GUI.notifyEndRound();
		currentActionPanel = null;
	}

	/**
	 * Show end or discard.
	 */
	public void showEndOrDiscard() {
		this.currentActionPanel = endOrDiscardPanel;
		this.showActionPanel(endOrDiscardPanel);
	}

	/**
	 * Show choose privilege.
	 */
	public void showChoosePrivilege() {
		this.currentActionPanel = choosePrivilegePanel;
		this.showActionPanel(choosePrivilegePanel);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JResource) {
			String chosenP = ((JResource) e.getSource()).getName();
			chosenPrivileges.add(chosenP);
			// choices.add(Integer.parseInt(((JResource)e.getSource()).getName()));
//			System.out.println("choicepriv:" + chosenP);
			if(currentNumberOfPrivilege==chosenPrivileges.size()){
			this.removeActionPanel();
			
			this.GUI.notifyChosenPrivilege(chosenP);
			chosenPrivileges.clear();
		}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {

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

	}

	/**
	 * Creates the markers.
	 *
	 * @param board  This class creates the marker for the order and this should be done only one time at the 
	 *  beggining of the match
	 */
	public void createMarkers(Board board) {
		if (orderMarkers.isEmpty()) {
			for (int i = 0; i < board.getPlayerOrder().size(); i++) {
				orderMarkers.add(new OrderMarkerDisk(board.getPlayerOrder().get(i)));
				boardPanel.add(orderMarkers.get(i));
				victoryMarkers.put(board.getPlayerOrder().get(i),
						new VictoryPointMarkerDisk(board.getPlayerOrder().get(i)));
				militaryMarkers.put(board.getPlayerOrder().get(i),
						new MilitaryPointMarkerDisk(board.getPlayerOrder().get(i)));
				faithMarkers.put(board.getPlayerOrder().get(i),
						new FaithPointMarkerDisk(board.getPlayerOrder().get(i)));
				boardPanel.add(victoryMarkers.get(board.getPlayerOrder().get(i)));
				boardPanel.add(militaryMarkers.get(board.getPlayerOrder().get(i)));
				boardPanel.add(faithMarkers.get(board.getPlayerOrder().get(i)));
			}
		}
	}

	/**
	 * Place familiars.
	 *
	 * @param board the board
	 */
	public void PlaceFamiliars(Board board) {

		if (board.getCouncilPalace().getMembers().size() != FamilyMemberPawn.councilCounter) {
			FamilyMember last = board.getCouncilPalace().getMembers()
					.get(board.getCouncilPalace().getMembers().size() - 1);
			boardPanel.add(this.familiars.get(last.getColor().toString() + last.getPlayer().getColor()));
			this.familiars.get(last.getColor().toString() + last.getPlayer().getColor())
					.PlaceFamiliarIntoCouncilPalace();
		}

		for (int i = 1; i <= board.getPlayerOrder().size(); i++) {
			if (board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember() != null) {

				boardPanel.add(this.familiars.get(
						board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember().getColor().toString()
								+ board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember().getPlayer()
										.getColor()));
				this.familiars.get(
						board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember().getColor().toString()
								+ board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember().getPlayer()
										.getColor())
						.PlaceFamiliarIntoMarket(i);
			}
		}

		for (int i = 0; i < board.getHarvestArea().getMultipleActionSpace().getMembers().size(); i++) {

			boardPanel.add(this.familiars.get(
					board.getHarvestArea().getMultipleActionSpace().getMembers().get(i).getColor().toString() + board
							.getHarvestArea().getMultipleActionSpace().getMembers().get(i).getPlayer().getColor()));
			this.familiars.get(board.getHarvestArea().getMultipleActionSpace().getMembers().get(i).getColor().toString()
					+ board.getHarvestArea().getMultipleActionSpace().getMembers().get(i).getPlayer().getColor())
					.PlaceFamiliarIntoHarvestArea("2");
		}

		if (board.getHarvestArea().getSingleActionSpace().getFamilyMember() != null) {

			boardPanel.add(this.familiars
					.get(board.getHarvestArea().getSingleActionSpace().getFamilyMember().getColor().toString()
							+ board.getHarvestArea().getSingleActionSpace().getFamilyMember().getPlayer().getColor()));
			this.familiars
					.get(board.getHarvestArea().getSingleActionSpace().getFamilyMember().getColor().toString()
							+ board.getHarvestArea().getSingleActionSpace().getFamilyMember().getPlayer().getColor())
					.PlaceFamiliarIntoHarvestArea("1");

		}
		for (int i = 0; i < board.getProductionArea().getMultipleActionSpace().getMembers().size(); i++) {

			boardPanel.add(this.familiars.get(
					board.getProductionArea().getMultipleActionSpace().getMembers().get(i).getColor().toString() + board
							.getProductionArea().getMultipleActionSpace().getMembers().get(i).getPlayer().getColor()));
			this.familiars.get(
					board.getProductionArea().getMultipleActionSpace().getMembers().get(i).getColor().toString() + board
							.getProductionArea().getMultipleActionSpace().getMembers().get(i).getPlayer().getColor())
					.PlaceFamiliarIntoProductionArea("2");
		}

		if (board.getProductionArea().getSingleActionSpace().getFamilyMember() != null) {

			boardPanel.add(this.familiars.get(
					board.getProductionArea().getSingleActionSpace().getFamilyMember().getColor().toString() + board
							.getProductionArea().getSingleActionSpace().getFamilyMember().getPlayer().getColor()));
			familiars
					.get(board.getProductionArea().getSingleActionSpace().getFamilyMember().getColor().toString()
							+ board.getProductionArea().getSingleActionSpace().getFamilyMember().getPlayer().getColor())
					.PlaceFamiliarIntoProductionArea("1");

		}
		for (CardType c : CardType.values()) {
			if (c != CardType.ANY) {
				for (int i = 0; i < board.getTower(c).getFloors().size(); i++) {
					if (board.getFloor(c, i).getActionSpace().getFamilyMember() != null) {

						boardPanel.add(this.familiars.get(
								board.getFloor(c, i).getActionSpace().getFamilyMember().getColor().toString() + board
										.getFloor(c, i).getActionSpace().getFamilyMember().getPlayer().getColor()));
						this.familiars.get(board.getFloor(c, i).getActionSpace().getFamilyMember().getColor().toString()
								+ board.getFloor(c, i).getActionSpace().getFamilyMember().getPlayer().getColor())
								.PlaceFamiliarInTower(c.toString().toLowerCase(Locale.ROOT), i);
					}

				}

			}
		}

	}

	/**
	 * Sets the points markers.
	 *
	 * @param p the new points markers
	 */
	public void setPointsMarkers(Player p) {

		victoryMarkers.get(p.getColor())
				.setVictoryPointsAmount(p.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount());
		militaryMarkers.get(p.getColor()).setMilitaryPointsAmount(
				p.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT).getAmount());
		faithMarkers.get(p.getColor())
				.setFaithPointsAmount(p.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT).getAmount());
	}

	/**
	 * Update order.
	 *
	 * @param board the board
	 */
	public void updateOrder(Board board) {

		ArrayList<String> playerColor = board.getPlayerOrder();
		for (int i = 0; i < playerColor.size(); i++) {
			getOrderFromColor(playerColor.get(i)).setOrderMarkers();
			boardPanel.add(getOrderFromColor(playerColor.get(i)));
		}

	}

	/**
	 * Gets the order from color.
	 *
	 * @param color the color
	 * @return the order from color
	 */
	private OrderMarkerDisk getOrderFromColor(String color) {
		for (int i = 0; i < orderMarkers.size(); i++) {
			if (orderMarkers.get(i).getSrc().equals(color)) {
				return orderMarkers.get(i);
			}
		}
		return null;

	}

	/**
	 * Populate familiars.
	 *
	 * @param board the board
	 */
	public void populateFamiliars(Board board) {
		if (familiars.isEmpty()) {
			for (String s : board.getPlayerOrder()) {
				for (Dice d : Dice.values()) {

					familiars.put(d.getColor().toString() + s, new FamilyMemberPawn(d.getColor().toString(), s));

				}
			}
		}
	}

	/**
	 * Gets the order markers.
	 *
	 * @return the order markers
	 */
	public ArrayList<OrderMarkerDisk> getOrderMarkers() {
		return (ArrayList<OrderMarkerDisk>) orderMarkers;
	}

	/**
	 * Gets the victory markers.
	 *
	 * @return the victory markers
	 */
	public Map<String, VictoryPointMarkerDisk> getVictoryMarkers() {
		return victoryMarkers;
	}

	/**
	 * Gets the military markers.
	 *
	 * @return the military markers
	 */
	public Map<String, MilitaryPointMarkerDisk> getMilitaryMarkers() {
		return militaryMarkers;
	}

	/**
	 * Gets the faith markers.
	 *
	 * @return the faith markers
	 */
	public Map<String, FaithPointMarkerDisk> getFaithMarkers() {
		return faithMarkers;
	}

	/**
	 * Show support.
	 *
	 * @param showSupportDecision the show support decision
	 */
	public void showSupport(boolean showSupportDecision) {
		this.GUI.notifyExcommunicationChoice(showSupportDecision);
		currentActionPanel=null;
		this.removeActionPanel();

	}

	/**
	 * Show excommunication panel.
	 */
	public void showExcommunicationPanel() {
		this.currentActionPanel = chooseExcommunicationPanel;
		this.showActionPanel(chooseExcommunicationPanel);
	}

	/**
	 * Reset familiars.
	 */
	public void resetFamiliars() {
		for (FamilyMemberPawn f : familiars.values()) {
			if (!familiars.isEmpty())
				boardPanel.remove(f);
		}
	}

	/**
	 * Refresh leaders.
	 *
	 * @param leaderCards the leader cards
	 */
	public void refreshLeaders(Map<String, LeaderCard> leaderCards) {
		if (!leadersPanel.areLeaderCards(leaderCards.size())) {
			leadersPanel.refreshLeaderCards(leaderCards, this);
		}
	}

	/**
	 * Show choose leader draft.
	 *
	 * @param leaderCards the leader cards
	 */
	public void showChooseLeaderDraft(ArrayList<LeaderCard> leaderCards) {

		draftPanel.addLeaderCardsWithListener(leaderCards, this);
		showActionPanel(draftPanel);
		this.currentActionPanel = draftPanel;
	}

	/**
	 * Repaint resources.
	 */
	public void repaintResources() {
		playerResources.repaint();
	}

	/**
	 * Repaint board.
	 */
	public void repaintBoard() {
		boardPanel.repaint();
	}

	/**
	 * Checks if is contained.
	 *
	 * @param id the id
	 * @return true if the card with the specified id is contained in the board
	 */
	public boolean isContained(int id) {
		for (CardButton card : cards) {
			if (card.getId() == id)
				return true;
		}
		return false;
	}

	/**
	 * Removes the card.
	 *
	 * @param tower the tower
	 * @param floor the floor
	 */
	public void removeCard(int tower, int floor) {
		if (tower == 1)
			tower = 2;
		else if (tower == 2)
			tower = 1;

		for (CardButton card : cards) {
			if (card.getTower() == tower && card.getFloor() == floor) {
				boardPanel.remove(card);
			}
		}

	}

	/**
	 * Notify activate click.
	 */
	public void notifyActivateClick() {
		leaderState = "activate";
		currentActionPanel = leadersPanel;
		showActionPanel(leadersPanel);
	}

	/**
	 * Notify discard click.
	 */
	public void notifyDiscardClick() {

		leaderState = "discard";
		currentActionPanel = leadersPanel;
		showActionPanel(leadersPanel);
	}

	/**
	 * Sets the leader state.
	 *
	 * @param string the new leader state
	 */
	public void setLeaderState(String string) {
		leaderState = string;
	}

	/**
	 * Sets the excomm tiles.
	 *
	 * @param board the new excomm tiles
	 */
	public void setExcommTiles(Board board) {
		if (familiars.isEmpty()) {
			boardPanel.add(new ExcommTileFirstPeriod(board.getChurch().getExcommunicationFirst().getId(),
					board.getChurch().getExcommunicationFirst().toString()));
			boardPanel.add(new ExcommTileSecondPeriod(board.getChurch().getExcommunicationSecond().getId(),
					board.getChurch().getExcommunicationSecond().toString()));
			boardPanel.add(new ExcommTileThirdPeriod(board.getChurch().getExcommunicationThird().getId(),
					board.getChurch().getExcommunicationThird().toString()));

		}

	}

	/**
	 * Adds the excommunication cubes.
	 *
	 * @param p the p
	 */
	public void addExcommunicationCubes(Player p) {
		if (p.isExcommunicatedFirst()) {
			ExcommCube first = new ExcommCube(1, p.getColor());
			boardPanel.add(first);
			first.getParent().setComponentZOrder(first, 0);
		}

		if (p.isExcommunicatedSecond()) {
			ExcommCube second = new ExcommCube(2, p.getColor());
			boardPanel.add(second);
			second.getParent().setComponentZOrder(second, 0);
		}

		if (p.isExcommunicatedThird()) {
			ExcommCube third = new ExcommCube(3, p.getColor());
			boardPanel.add(third);
			third.getParent().setComponentZOrder(third, 0);
		}

	}

	/**
	 * Player disconnected.
	 */
	public void playerDisconnected() {
		this.currentActionPanel = null;
		this.removeActionPanel();

	}

	/**
	 * Creates the dices.
	 */
	public void createDices() {
		for (int i = 1; i < 7; i++) {
			dices.put(String.valueOf(i) + "black", new DicePanel(i, "black"));
			dices.put(String.valueOf(i) + "white", new DicePanel(i, "white"));
			dices.put(String.valueOf(i) + "orange", new DicePanel(i, "orange"));
		}
	}

	/**
	 * Sets the dices.
	 *
	 * @param board the new dices
	 */
	public void setDices(Board board) {
		if (board.getDices().get(Dice.BLACK_DICE) != 0 && board.getDices().get(Dice.WHITE_DICE) != 0
				&& board.getDices().get(Dice.ORANGE_DICE) != 0) {
			DicePanel black = dices.get(String.valueOf(board.getDices().get(Dice.BLACK_DICE)) + "black");
			DicePanel white = dices.get(String.valueOf(board.getDices().get(Dice.WHITE_DICE)) + "white");
			DicePanel orange = dices.get(String.valueOf(board.getDices().get(Dice.ORANGE_DICE)) + "orange");

			boardPanel.add(black);
			boardPanel.add(orange);
			boardPanel.add(white);
		}
	}

	/**
	 * Removes the dices and markers.
	 */
	public void removeDicesAndMarkers() {
		for (int i = 0; i < orderMarkers.size(); i++) {
			boardPanel.remove(orderMarkers.get(i));
		}

		for (DicePanel d : dices.values()) {
			if (d.getParent() == boardPanel) {
				boardPanel.remove(d);
			}
		}

	}

	/**
	 * Notify activate production.
	 *
	 * @param choices the choices
	 */
	public void notifyActivateProduction(ArrayList<Integer> choices) {
		this.GUI.notifyActivateProduction(choices);
		productionChoices=new ProductionChoices(this);
		productionChoices.setBackground(BACKGROUND_PANELS_COLOR);
		productionChoices.setVisible(false);
	}

	/**
	 * Show choose production effects.
	 *
	 * @param cardsIds the cards ids
	 */
	public void showChooseProductionEffects(ArrayList<Integer> cardsIds) {
		productionChoices.addCards(cardsIds);
		currentActionPanel=productionChoices;
		this.showActionPanel(productionChoices);
		
	}

	public void showAskAuthentication() {
		currentActionPanel=askAuthenticationPanel;
		this.showActionPanel(askAuthenticationPanel);
	}

	public void notifyAuthenticateClick(String username, String password) {
		this.GUI.notifyAuthenticationRequest(username,password);
		this.currentActionPanel=null;
		this.removeActionPanel();
	}

	public void setUsername(String username) {
		this.askAuthenticationPanel=null; //freeing up memory by "invoking" garbage collector
		playerResources.setUsername(username);
	}

	public void setCurrentNumberOfPrivilege(int numberOfPrivilege) {
		currentNumberOfPrivilege=numberOfPrivilege;
	}
}
