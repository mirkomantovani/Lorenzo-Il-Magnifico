package it.polimi.ingsw.ps19.view.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.constant.ImagesConstants;
import it.polimi.ingsw.ps19.model.area.Board;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.ResourceType;


/**
 * This JPanel extension is the left-side of the frame, the one containing the board image with 
 * all his components, at the moment of the creation the board image is scaled mantaining the
 * proportions to adapt it to any kind of screen, components put into this BoardPanel must
 * set the bounds for themselves and scale their dimensions to create a responsive UI
 * @author Mirko
 *
 */
public class BoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;
	static Dimension dimension;
	private transient Toolkit toolkit = Toolkit.getDefaultToolkit();
	private int imgHeight;
	private int imgWidth;
	private List<OrderMarkerDisk> orderMarkers;
	private Map<String,VictoryPointMarkerDisk> victoryMarkers;
	private Map<String,MilitaryPointMarkerDisk> militaryMarkers;
	private Map<String,FaithPointMarkerDisk> faithMarkers;


	public BoardPanel() {
		super(new GridBagLayout());  //metto a null
		
//		 JButton button;
////			setLayout(new GridBagLayout());
//			GridBagConstraints c = new GridBagConstraints();
//		button = new JButton("Button 2");
//		c.ipady = 10; 
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 1;
//		c.gridx = 1;
//		c.gridy = 0;
//		button.setOpaque(false);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		this.add(button, c);
		
		
		
		dimension = toolkit.getScreenSize();
		
		try {
			img = ImageIO.read(this.getClass().getResource(ImagesConstants.BOARD));
		} catch (IOException e) {
		}
		System.out.println(dimension.width+" e "+ dimension.height);
		imgHeight=img.getHeight(null);
		imgWidth=img.getWidth(null);
		float ratio=imgWidth/imgHeight;
		System.out.println(imgHeight+" e "+ imgWidth);
		img = img.getScaledInstance(dimension.height*imgWidth/imgHeight, dimension.height,
				Image.SCALE_SMOOTH);
		dimension.setSize(dimension.height*imgWidth/imgHeight, dimension.height);
		setPreferredSize(dimension);
		
		orderMarkers = new ArrayList<OrderMarkerDisk>();
		victoryMarkers = new HashMap<String,VictoryPointMarkerDisk>();
		militaryMarkers = new HashMap<String,MilitaryPointMarkerDisk>();
		faithMarkers = new HashMap<String,FaithPointMarkerDisk>();
	
	
	}

	public ArrayList<OrderMarkerDisk> getOrderMarkers() {
		return (ArrayList<OrderMarkerDisk>) orderMarkers;
	}

	public Map<String, VictoryPointMarkerDisk> getVictoryMarkers() {
		return victoryMarkers;
	}

	public Map<String, MilitaryPointMarkerDisk> getMilitaryMarkers() {
		return militaryMarkers;
	}

	public Map<String, FaithPointMarkerDisk> getFaithMarkers() {
		return faithMarkers;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}

	public Dimension getDimension() {
		return dimension;
	}
	
	public void PlaceFamiliars(Board board){

		
		ArrayList<FamilyMemberPawn> familiars = new ArrayList<FamilyMemberPawn>();
		
		for(int i = 0; i < board.getCouncilPalace().getMembers().size();i++){
			familiars.add(new FamilyMemberPawn(board.getCouncilPalace().getMembers().get(i).getColor().toString(),
					board.getCouncilPalace().getMembers().get(i).getPlayer().getColor()));
			this.add(familiars.get(i));
			familiars.get(i).PlaceFamiliarIntoCouncilPalace();
		}
		for(FamilyMemberPawn f:familiars){
			f.ResetAllCounters();
		}
		familiars.clear();
		
		for(int i=1; i < board.getPlayerOrder().size(); i++){
			if(board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember()!=null){
			familiars.add(new FamilyMemberPawn(board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember().getColor().toString(),
					board.getMarket().getMarktActionSpace(String.valueOf(i)).getFamilyMember().getPlayer().getColor()));
			this.add(familiars.get(i));
			familiars.get(i-1).PlaceFamiliarIntoMarket(i);
			}
		}
		for(FamilyMemberPawn f:familiars){
			f.ResetAllCounters();
		}
		familiars.clear();
		
		for(int i=0 ; i<board.getHarvestArea().getMultipleActionSpace().getMembers().size();i++){
			familiars.add(new FamilyMemberPawn(board.getHarvestArea().getMultipleActionSpace().getMembers().get(i).getColor().toString(),
					board.getHarvestArea().getMultipleActionSpace().getMembers().get(i).getPlayer().getColor()));
			this.add(familiars.get(i));
			familiars.get(i).PlaceFamiliarIntoHarvestArea("2");
		}
		for(FamilyMemberPawn f:familiars){
			f.ResetAllCounters();
		}
		familiars.clear();
		if(board.getHarvestArea().getSingleActionSpace().getFamilyMember()!=null){
		familiars.add(new FamilyMemberPawn(board.getHarvestArea().getSingleActionSpace().getFamilyMember().getColor().toString(),
				board.getHarvestArea().getSingleActionSpace().getFamilyMember().getPlayer().getColor()));
		this.add(familiars.get(0));
		familiars.get(0).PlaceFamiliarIntoHarvestArea("1");
		for(FamilyMemberPawn f:familiars){
			f.ResetAllCounters();
		}
		familiars.clear();
		}
		for(int i=0 ; i<board.getProductionArea().getMultipleActionSpace().getMembers().size();i++){
			familiars.add(new FamilyMemberPawn(board.getProductionArea().getMultipleActionSpace().getMembers().get(i).getColor().toString(),
					board.getProductionArea().getMultipleActionSpace().getMembers().get(i).getPlayer().getColor()));
			this.add(familiars.get(i));
			familiars.get(i).PlaceFamiliarIntoProductionArea("2");
		}
		for(FamilyMemberPawn f:familiars){
			f.ResetAllCounters();
		}
		familiars.clear();
		if(board.getProductionArea().getSingleActionSpace().getFamilyMember()!=null){
		familiars.add(new FamilyMemberPawn(board.getProductionArea().getSingleActionSpace().getFamilyMember().getColor().toString(),
				board.getProductionArea().getSingleActionSpace().getFamilyMember().getPlayer().getColor()));
		this.add(familiars.get(0));
		familiars.get(0).PlaceFamiliarIntoProductionArea("1");
		for(FamilyMemberPawn f:familiars){
			f.ResetAllCounters();
		}
		familiars.clear();
		}
		for(CardType c : CardType.values()){
			if(c!=CardType.ANY){
				for(int i=0;i < board.getTower(c).getFloors().size();i++){
					if(board.getFloor(c, i).getActionSpace().getFamilyMember()!= null){
					familiars.add(new FamilyMemberPawn(board.getFloor(c, i).getActionSpace().getFamilyMember().getColor().toString(),
							board.getFloor(c, i).getActionSpace().getFamilyMember().getPlayer().getColor()));
					this.add(familiars.get(i));
					familiars.get(i).PlaceFamiliarInTower(c.toString().toLowerCase(Locale.ROOT), i);
					}
				}
				for(FamilyMemberPawn f:familiars){
					f.ResetAllCounters();
				}
				familiars.clear();
			}
		}
			
		
		
	}
	
	public void setPointsMarkers(Player p){

		victoryMarkers.get(p.getColor()).setVictoryPointsAmount(
				p.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount());
		militaryMarkers.get(p.getColor()).setMilitaryPointsAmount(
				p.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT).getAmount());
		faithMarkers.get(p.getColor()).setFaithPointsAmount(
				p.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT).getAmount());
	}
	
	public void updateOrder(Board board){
		for(int i = 0; i<board.getPlayerOrder().size();i++){
			for(int j = 0; j<orderMarkers.size(); j++){
			if(orderMarkers.get(j).getSrc() == board.getPlayerOrder().get(i)){
				OrderMarkerDisk flag = orderMarkers.get(i);
				OrderMarkerDisk old = orderMarkers.get(j);
				orderMarkers.set(j, flag);
				orderMarkers.set(i,old);
			}
			}
			
		}
	}

}
