package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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


/**
 * @author Mirko
 *
 */
public class GamePanel extends JPanel {
	
	private Dimension screenDim;
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField textField;
	private JPanel boardPanel;
	private JButton sendChat;
	JTextArea txtrCiaooo;

	
	public GamePanel(){
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
		panel_2.add(sendChat, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 26));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(textField);
		textField.setColumns(50);
		
		JPanel playerResources = new JPanel();
		panel_1.add(playerResources);
		
		JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
		internalFrame_1.setMaximizable(true);
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
		internalFrame_1.getContentPane().add(panel_4);
//		panel_4.setSize(new Dimension(500, 800));
//		panel_4.setBounds(1000,500,500,500);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_4.add(scrollPane_1);
		
//		JPanel personalBoard = new PersonalBoardPanel();
//		panel_4.add(personalBoard);
//		personalBoard.setLayout(null);
		
		JPanel buttonsPanel = new JPanel();
		panel_1.add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_3 = new JButton("Show Leader Cards");
		buttonsPanel.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Show Personal Board");
		buttonsPanel.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("End Round");
		buttonsPanel.add(btnNewButton_4);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);
//		internalFrame2.setVisible(true);
	}
	
	public void addCard(int tower,int floor,int id){
		JButton btnNewButton_2 = new CardButton(boardPanel.getPreferredSize(),tower,floor,id);
//		btnNewButton_2.setBounds(268, 256, 105, 170);
		boardPanel.add(btnNewButton_2);
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
		}
	}
	
	public void setMarkerVictoryPoints(){
		
	}
	
	public void setMarkerMilitaryPoint(){
		
	}
	
	public void setChurchMarker(){
		
	}
	
}
