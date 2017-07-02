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
import java.util.ArrayList;
import java.util.List;

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

import it.polimi.ingsw.ps19.model.resource.Resource;



/**
 * The content of the entire frame (MyFrame) during the GamePlay
 * @author Mirko
 *
 */
public class GamePanel extends JPanel implements ActionListener {
	
	protected static Dimension screenDim;
	
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField textField;
	private BoardPanel boardPanel;
	
	private JButton sendChat;
	private JButton showPersonalBoard;
	private JButton strategyEditorButton;
	private JButton endRoundButton;
	private JButton showLeaderCardsButton;
	
	private JTextArea textArea;
	private PlayerResources playerResources;
	private final Font buttonsFont= new Font("SansSerif", Font.BOLD, 16);
	
	private List<CardButton> cards;

	private Container actionContentPane;
	
	private ActionPanel actionPanel;
	private ChooseAction chooseAction;
	private StrategyEditor strategyEditor;
	private EndOrDiscardPanel endOrDiscardPanel;
	
	private ArrayList<String> actionConstructor;
	
	private GraphicalUserInterface GUI;
	
	private Component currentActionPanel;
	

	
	public GamePanel(String playerColor){
		
		
		cards=new ArrayList<CardButton>();

		screenDim=toolkit.getScreenSize();
//		this.set(true);
//		setExtendedState(Frame.MAXIMIZED_BOTH);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		
		//BOARD
		
		boardPanel = new BoardPanel();
		add(boardPanel, BorderLayout.WEST);
		boardPanel.setLayout(null);
		
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.setContentAreaFilled(false);
//		btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		btnNewButton.setBounds(72, 72, 105, 176);
//		boardPanel.add(btnNewButton);
		
//		JButton btnNewButton_2 = new CardButton(boardPanel.getPreferredSize(),1,0,55);
//		btnNewButton_2.setBounds(268, 256, 105, 170);
//		boardPanel.add(btnNewButton_2);
		
		
		
		System.out.println("BoardPanel preferredSize: "+boardPanel.getPreferredSize().getHeight()+" "+
				boardPanel.getPreferredSize().getWidth());
		
		//RIGHT SCROLLBAR
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setSize(new Dimension(screenDim.width-panel.getPreferredSize().width, 100));
		scrollPane.setPreferredSize(new Dimension(screenDim.width-boardPanel.getPreferredSize().width, 500));
		add(scrollPane, BorderLayout.EAST);
		
		//PANEL CONTAINED IN THE SCROLLBAR
		
		JPanel rightScrollbarContainer = new JPanel();
		rightScrollbarContainer.setMaximumSize(new Dimension(1000, 1000));
		scrollPane.setViewportView(rightScrollbarContainer);
		rightScrollbarContainer.setLayout(new BoxLayout(rightScrollbarContainer, BoxLayout.Y_AXIS));
		
		//CHAT INTERNALFRAME
		
		JInternalFrame internalFrame = new JInternalFrame("Chat");
		internalFrame.setBackground(SystemColor.controlHighlight);
		rightScrollbarContainer.add(internalFrame);
		internalFrame.setPreferredSize(new Dimension(screenDim.width-boardPanel
				.getPreferredSize().width,screenDim.height/3));
		internalFrame.setMaximumSize(new Dimension(screenDim.width-boardPanel
				.getPreferredSize().width,screenDim.height/3));

		//CHAT TEXTAREA
		
		textArea = new JTextArea();
		textArea.setMargin(new Insets(1, 1, 1, 1));
		textArea.setText("ciaooo");
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		textArea.setBackground(new Color(245, 200, 86));
		textArea.setFont(new Font("Consolas", 0, 20));
		
		//SCROLLPANE FOR CHAT
		
		JScrollPane scrPane = new JScrollPane(textArea);
		Border border = BorderFactory.createLoweredBevelBorder();
		scrPane.setBorder(border);

		JScrollBar vertical = scrPane.getVerticalScrollBar();
		vertical.setPreferredSize(new Dimension(0, 0));
		
		internalFrame.getContentPane().add(scrPane, BorderLayout.CENTER);
		
		JPanel chatInputPanel = new JPanel();
		internalFrame.getContentPane().add(chatInputPanel, BorderLayout.SOUTH);
		chatInputPanel.setLayout(new BorderLayout(0, 0));
		
		//CHAT INPUT BUTTON
		
		sendChat = new JButton("Send");
		sendChat.setForeground(new Color(255, 255, 255));
		sendChat.setBackground(new Color(102, 51, 51));
		sendChat.setFont(buttonsFont);
		sendChat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendChat.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		chatInputPanel.add(sendChat, BorderLayout.EAST);
		
		//TEXTFIELD
		
		JPanel textFieldOuterPanel = new JPanel();
		textFieldOuterPanel.setBackground(new Color(160, 82, 45));
		chatInputPanel.add(textFieldOuterPanel, BorderLayout.CENTER);
		textFieldOuterPanel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 26));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOuterPanel.add(textField);
		textField.setColumns(50);
		
		//PLAYER RESOURCES PANEL
		
		playerResources = new PlayerResources(screenDim.width-boardPanel.getPreferredSize().width,playerColor);
//		playerResources.setPreferredSize(new Dimension(screenDim.width-panel.getPreferredSize().width,400));
//		playerResources.setMaximumSize(new Dimension(screenDim.width-panel.getPreferredSize().width,800));
		
		rightScrollbarContainer.add(playerResources);
		
		//ACTIONS INTERNAL FRAME
		
		JInternalFrame actionsInternalFrame = new JInternalFrame("Game actions");
		actionsInternalFrame.setMaximizable(true);
		actionsInternalFrame.getContentPane().setBackground(new Color(160, 82, 45));
		actionsInternalFrame.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		actionsInternalFrame.setResizable(true);
//		internalFrame_1.setIconifiable(true);
//		internalFrame_1.setClosable(true);
//		internalFrame_1.setResizable(true);
		
		
		
//		setBorder(val ? null : border);

//		internalFrame_1.setRootPaneCheckingEnabled(false);
//		internalFrame_1.getUI().setNorthPane(val ? null : northPane);
//		internalFrame_1.setRootPaneCheckingEnabled(true);
		actionsInternalFrame.setBounds(new Rectangle(0, 0, 500, 0));
		rightScrollbarContainer.add(actionsInternalFrame);
		actionsInternalFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		actionContentPane=actionsInternalFrame.getContentPane();
		
		//ACTION PANELS
		
		actionPanel=new ActionPanel(this);
		actionPanel.setBackground(new Color(204, 153, 51));
		actionPanel.setVisible(false);
//		actionContentPane.add(actionPanel);
		
		chooseAction=new ChooseAction(this);
		chooseAction.setBackground(new Color(204, 153, 51));
		chooseAction.setVisible(false);
//		actionContentPane.add(chooseAction);
//		actionsInternalFrame.getContentPane().add(actionPanel);
		
		strategyEditor=new StrategyEditor();
		strategyEditor.setVisible(false);
//		actionContentPane.add(strategyEditor);
		
		endOrDiscardPanel=new EndOrDiscardPanel(this);
		endOrDiscardPanel.setBackground(new Color(204, 153, 51));
		endOrDiscardPanel.setVisible(false);
		
	
		//FINAL BUTTONS PANEL
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(210, 180, 140));
		rightScrollbarContainer.add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showLeaderCardsButton = new JButton("Show Leader Cards");
		showLeaderCardsButton.setFont(buttonsFont);
		showLeaderCardsButton.setForeground(new Color(255, 255, 255));
		showLeaderCardsButton.setBackground(new Color(102, 51, 51));
		showLeaderCardsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(showLeaderCardsButton);
		
		showPersonalBoard = new JButton("Show Personal Board");
		showPersonalBoard.setFont(buttonsFont);
		showPersonalBoard.setBackground(new Color(102, 51, 51));
		showPersonalBoard.setForeground(new Color(255, 255, 255));
		showPersonalBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(showPersonalBoard);
		
		endRoundButton = new JButton("End Round");
		endRoundButton.setFont(buttonsFont);
		endRoundButton.setBackground(new Color(102, 51, 51));
		endRoundButton.setForeground(new Color(255, 255, 255));
		endRoundButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(endRoundButton);
		
		strategyEditorButton = new JButton("Strategy editor");
		strategyEditorButton.setFont(buttonsFont);
		strategyEditorButton.setBackground(new Color(102, 51, 51));
		strategyEditorButton.setForeground(new Color(255, 255, 255));
		strategyEditorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		strategyEditorButton.addActionListener(this);
		buttonsPanel.add(strategyEditorButton);
		actionsInternalFrame.setVisible(true);
		internalFrame.setVisible(true);
//		internalFrame2.setVisible(true);
//		OrderMarkerDisk redMarker = new OrderMarkerDisk("red");
//		boardPanel.add(redMarker);
//		OrderMarkerDisk blueMarker = new OrderMarkerDisk("blue");
//		boardPanel.add(blueMarker);
//		OrderMarkerDisk greenMarker = new OrderMarkerDisk("green");
//		boardPanel.add(greenMarker);
		
//		boardPanel.add(new VictoryPointMarkerDisk("red"));
//		boardPanel.add(new VictoryPointMarkerDisk("blue"));
//		
//		boardPanel.add(new FaithPointMarkerDisk("green"));
//		boardPanel.add(new FaithPointMarkerDisk("yellow"));
//		
//		boardPanel.add(new MilitaryPointMarkerDisk("green"));
//		boardPanel.add(new MilitaryPointMarkerDisk("yellow"));
//		FaithPointMarkerDisk red = new FaithPointMarkerDisk("red");
//		boardPanel.add(red);
//	
//		red.setFaithPointsAmount(5);
//		
//		MilitaryPointMarkerDisk militaryBlue = new MilitaryPointMarkerDisk("blue");
//		boardPanel.add(militaryBlue);
//		militaryBlue.setMilitaryPointsAmount(10);
//		
//		VictoryPointMarkerDisk victoryYellow = new VictoryPointMarkerDisk("yellow");
//		boardPanel.add(victoryYellow);
//		victoryYellow.setVictoryPointsAmount(50);
//		
//		FamilyMemberPawn family = new FamilyMemberPawn("black","red");
//		boardPanel.add(family);
//		family.PlaceFamiliarInTower("venture", 2);
//		family.PlaceFamiliarIntoCouncilPalace();
//		FamilyMemberPawn family3 = new FamilyMemberPawn("black","blue");
//		boardPanel.add(family3);
//		family3.PlaceFamiliarIntoMarket(4);
//		FamilyMemberPawn family4 = new FamilyMemberPawn("black","blue");
//		boardPanel.add(family4);
//		family3.PlaceFamiliarIntoProductionArea("2");
//		FamilyMemberPawn family5 = new FamilyMemberPawn("black","blue");
//		boardPanel.add(family5);
//		family5.PlaceFamiliarIntoProductionArea("2");
		
		
	}
	
	public void addCard(int tower,int floor,int id, String descr){
		if(tower==1)
			tower=2;
		else if(tower==2)
			tower=1;
		CardButton card = new CardButton(boardPanel.getPreferredSize(),tower,floor,id);
//		btnNewButton_2.setBounds(268, 256, 105, 170);
		card.addActionListener(this);
		card.setToolTipText(descr);
		boardPanel.add(card);
		cards.add(card);
		
	}

	public JButton getSendChat() {
		return sendChat;
	}
	
	public JButton getShowPersonalBoard(){
		return showPersonalBoard;
	}
	
	public String getAndDeleteChatInput(){
		String m=this.textField.getText();
		this.textField.setText("");
		return m;
		
	}

	public void addMessageToConsole(String message) {
		message="\n"+message;
		this.textArea.append(message);
		this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
	}
	
	private void writeGameMessage(String string) {
		addMessageToConsole("\n<-GAME-> "+string+"\n");
	}
	
	public void setMarkerOrder(ArrayList<OrderMarkerDisk> markers){
		for(OrderMarkerDisk m : markers){
			paint(m.getGraphics());
			m.setAlignmentX((float) 782.23);
			m.setAlignmentY(792);
		}
	}
	
	public void setMarkerVictoryPoints(){
		
	}
	
	public void setMarkerMilitaryPoint(){
		
	}
	
	public void setChurchMarker(){
		
	}

	public void addResourceToPlayerStatus(Resource resourceInChest) {
		playerResources.refreshResource(resourceInChest);
	}

	public void removeCards() {
		cards.forEach(card -> boardPanel.remove(card));
		
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	
	private void showActionPanel(Component panel){
		//rimuovo tutti gli altri panels
//		chooseAction.setVisible(false);
//		actionContentPane.remove(chooseAction);
		setEveryoneInvisible(this.actionContentPane.getComponents());
		this.actionContentPane.removeAll();
		this.actionContentPane.add(panel);
		panel.setVisible(true);
	}
	
	private void setEveryoneInvisible(Component[] components) {
		for(int i=0;i<components.length;i++)
			components[i].setVisible(false);
	}

	private void removeActionPanel() {
		this.actionContentPane.removeAll();
	}

	public void showChooseAction() {
		System.out.println("show choose action");
		this.currentActionPanel=chooseAction;
		this.showActionPanel(chooseAction);
		
	}
	
	public void setObserver(GraphicalUserInterface GUI){
		this.GUI=GUI;
	}
	

	public void notifyActionClick() {
		writeGameMessage("Choose the family member and the amount of servants "
				+ "you intend to use in this action, then press the area"
				+ "you want to place your family member into");
		this.currentActionPanel=actionPanel;
		this.showActionPanel(actionPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof CardButton){
			CardButton card=(CardButton)e.getSource();
			constructAction(card);
			this.GUI.notifyTakeCard(actionConstructor);
			
		} else if(e.getSource()==strategyEditorButton){
			if(!strategyEditor.isVisible())
			showActionPanel(strategyEditor);
			else backToCurrentAction();
			
		} 
	}

	

	private void backToCurrentAction() {
       showActionPanel(currentActionPanel);		
	}

	private void constructAction(CardButton card) {
		//order; 0-member, 1-servants, 2-unused, 3-cardtype,4-floor
		actionConstructor=new ArrayList<String>();
		
		String familyMember;
		String floor;  //number
		String servants;  //number
		int tower;
		String cardType;  //number
		familyMember=actionPanel.getFamilyMember();
		
		if(familyMember=="none"){
	        invalidInputMessage("Select a family member");
	        return;
		}
		
		floor=""+card.getFloor();
		tower=card.getTower();
		
		if(tower==1)
			tower=2;
		else if(tower==2)
			tower=1;
		
		tower=tower+1; //to use the conventions of CLI...
		
		servants=actionPanel.getServants();
		cardType=""+tower;  //scambio 2 torri centrali
		
		System.out.println("GamePanel: costructAction: member:"+familyMember+" servants:"+servants+" cardtype/tower:"+cardType+" floor:"+floor);
		
		actionConstructor.add(familyMember);
		actionConstructor.add(servants);
		actionConstructor.add("takecard");
		actionConstructor.add(cardType);
		actionConstructor.add(floor);
	}

	private void invalidInputMessage(String string) {
		this.addMessageToConsole("--INVALID INPUT--: "+string);
		
	}

	public void notifyEndRound() {
		writeGameMessage("Your round has ended");
		GUI.notifyEndRound();
	}

	public void showEndOrDiscard() {
		this.currentActionPanel=endOrDiscardPanel;
		this.showActionPanel(endOrDiscardPanel);
	}


	
}
