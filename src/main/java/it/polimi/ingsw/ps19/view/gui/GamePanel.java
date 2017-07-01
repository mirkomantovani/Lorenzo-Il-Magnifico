package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;

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
public class GamePanel extends JPanel {
	
	private Dimension screenDim;
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField textField;
	private JPanel boardPanel;
	private JButton sendChat;
	private JTextArea txtrCiaooo;
	private PlayerResources playerResources;
	private final Font buttonsFont= new Font("SansSerif", Font.BOLD, 16);

	
	public GamePanel(String playerColor){
		screenDim=toolkit.getScreenSize();
//      setUndecorated(true);
//		setExtendedState(Frame.MAXIMIZED_BOTH);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setSize(new Dimension(screenDim.width-panel.getPreferredSize().width, 100));
		scrollPane.setPreferredSize(new Dimension(screenDim.width-boardPanel.getPreferredSize().width, 500));
		add(scrollPane, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(1000, 1000));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JInternalFrame internalFrame = new JInternalFrame("Chat");
		internalFrame.setBackground(SystemColor.controlHighlight);
		panel_1.add(internalFrame);
		internalFrame.setPreferredSize(new Dimension(screenDim.width-boardPanel
				.getPreferredSize().width,screenDim.height/3));
		internalFrame.setMaximumSize(new Dimension(screenDim.width-boardPanel
				.getPreferredSize().width,screenDim.height/3));
//		internalFrame.setSize(new Dimension(20,50));
		
//		JInternalFrame internalFrame2 = new JInternalFrame("Chat");
//		internalFrame.setBackground(SystemColor.controlHighlight);
//		panel_1.add(internalFrame2);
		
		
		txtrCiaooo = new JTextArea();
		txtrCiaooo.setMargin(new Insets(1, 1, 1, 1));
		txtrCiaooo.setText("ciaooo");
		txtrCiaooo.setEditable(true);
		txtrCiaooo.setLineWrap(true);
		txtrCiaooo.setBorder(new EmptyBorder(5, 5, 5, 5));
		txtrCiaooo.setBackground(new Color(245, 200, 86));
		txtrCiaooo.setFont(new Font("Consolas", 0, 20));
		
		
		JScrollPane scrPane = new JScrollPane(txtrCiaooo);
		Border border = BorderFactory.createLoweredBevelBorder();
		scrPane.setBorder(border);
//		scrollPane.setSize(new Dimension(getWidth(), getHeight() * 1 / 3 - 10));
//		scrollPane.setPreferredSize(new Dimension(getWidth(),
//				getHeight() * 1 / 3 - 10));
		JScrollBar vertical = scrPane.getVerticalScrollBar();
		vertical.setPreferredSize(new Dimension(0, 0));
		
		internalFrame.getContentPane().add(scrPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		internalFrame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		sendChat = new JButton("Send");
		sendChat.setForeground(new Color(255, 255, 255));
		sendChat.setBackground(new Color(102, 51, 51));
		sendChat.setFont(buttonsFont);
		sendChat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendChat.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(sendChat, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(160, 82, 45));
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 26));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(textField);
		textField.setColumns(50);
		
		//Player Resources Panel
		playerResources = new PlayerResources(screenDim.width-boardPanel.getPreferredSize().width,playerColor);
//		playerResources.setPreferredSize(new Dimension(screenDim.width-panel.getPreferredSize().width,400));
//		playerResources.setMaximumSize(new Dimension(screenDim.width-panel.getPreferredSize().width,800));
		
		panel_1.add(playerResources);
		
		
		
		JInternalFrame internalFrame_1 = new JInternalFrame("Game actions");
		internalFrame_1.setMaximizable(true);
		internalFrame_1.getContentPane().setBackground(new Color(160, 82, 45));
		internalFrame_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		internalFrame_1.setResizable(true);
//		internalFrame_1.setIconifiable(true);
//		internalFrame_1.setClosable(true);
//		internalFrame_1.setResizable(true);
		
//		setBorder(val ? null : border);

//		internalFrame_1.setRootPaneCheckingEnabled(false);
//		internalFrame_1.getUI().setNorthPane(val ? null : northPane);
//		internalFrame_1.setRootPaneCheckingEnabled(true);
		internalFrame_1.setBounds(new Rectangle(0, 0, 500, 0));
		panel_1.add(internalFrame_1);
		internalFrame_1.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 153, 51));
		internalFrame_1.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		

		
//		JPanel personalBoard = new PersonalBoardPanel();
//		panel_4.add(personalBoard);
//		personalBoard.setLayout(null);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(new Color(210, 180, 140));
		panel_1.add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_3 = new JButton("Show Leader Cards");
		btnNewButton_3.setFont(buttonsFont);
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(102, 51, 51));
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Show Personal Board");
		btnNewButton_5.setFont(buttonsFont);
		btnNewButton_5.setBackground(new Color(102, 51, 51));
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("End Round");
		btnNewButton_4.setFont(buttonsFont);
		btnNewButton_4.setBackground(new Color(102, 51, 51));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(btnNewButton_4);
		
		JButton btnStrategyEditor = new JButton("Strategy editor");
		btnStrategyEditor.setFont(buttonsFont);
		btnStrategyEditor.setBackground(new Color(102, 51, 51));
		btnStrategyEditor.setForeground(new Color(255, 255, 255));
		btnStrategyEditor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(btnStrategyEditor);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);
//		internalFrame2.setVisible(true);
		MarkerDisk Marker = new MarkerDisk("red");
	}
	
	public void addCard(int tower,int floor,int id, String descr){
		JButton card = new CardButton(boardPanel.getPreferredSize(),tower,floor,id);
//		btnNewButton_2.setBounds(268, 256, 105, 170);
		card.setToolTipText(descr);
		boardPanel.add(card);
	}

	public JButton getSendChat() {
		return sendChat;
	}
	
	public String getAndDeleteChatInput(){
		String m=this.textField.getText();
		this.textField.setText("");
		return m;
		
	}

	public void addMessageToConsole(String message) {
		message="\n"+message;
		this.txtrCiaooo.append(message);
		this.txtrCiaooo.setCaretPosition(this.txtrCiaooo.getDocument().getLength());
	}
	
	public void setMarkerOrder(ArrayList<MarkerDisk> markers){
		for(MarkerDisk m : markers){
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
	
}
