package it.polimi.ingsw.ps19.view.gui;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author matteo
 * This class represents the Pawn image for each family member in the game
 *
 */
public class FamilyMemberPawn extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8409734849829937305L;

	private String player; // red,blue,green,yellow
	private String color ;//white,black,neutral,orange
	private final static int DISC_DIAM = 36;
	private Image img;
	private final static double wDIM_PERC = 0.07874015748031496062992125984252;
	private final static double hDIM_PERC = 0.05555555555555555555555555555556;
	private int widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
	private int heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	private final static double wFIRST_SLOT = 0.17716535433070866141732283464567;
	private final static double hFIRST_SLOT = 0.08333333333333333333333333333333;
	private final static double wTOWER_OFFSET = 0.19028871391076115485564304461942;
	private final static double hTOWER_OFFSET = 0.17592592592592592592592592592593;
	static int councilCounter = 0;
	private final static double wCOUNCIL =0.51181102362204724409448818897638;
	private final static double hCOUNCIL = 0.55092592592592592592592592592593;
	private final static double wFirstMarket = 0.53149606299212598425196850393701;
	private final static double hFirstMarket = 0.79166666666666666666666666666667;
	private final static double wHarvest = 0.06561679790026246719160104986877;
	private final static double hHarvest = 0.81018518518518518518518518518519;
	private static int harvestCounter = 0;
	private final static double wBigHarvest = 0.17060367454068241469816272965879;
	private final static double hProduction = 0.88888888888888888888888888888889;
	private static int productionCounter = 0;
	

	public FamilyMemberPawn(String color,String player) {
		
		this.color = color;
		this.player = player;
		try {
			this.img = ImageIO.read(getClass().getResource("/"+player+color+"Familiar.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(widthRel, heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
		setFamilyMember();
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img.getScaledInstance((int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()), 0), 0, 0, this);
	}
	
	public void PlaceFamiliarInTower(String Tower, int Floor){
		 if (Tower.equals("building")){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT + 2*wTOWER_OFFSET*BoardPanel.dimension.getWidth());
		}else if (Tower.equals("character")){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT + wTOWER_OFFSET*BoardPanel.dimension.getWidth());
		}else if (Tower.equals("venture")){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT + 3*wTOWER_OFFSET*BoardPanel.dimension.getWidth());
		}
		heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT + (3-Floor)*hTOWER_OFFSET*BoardPanel.dimension.getWidth());
		setFamilyMember();
	}
	
	public void setFamilyMember(){
	
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(widthRel, heightRel, (int) (wDIM_PERC*BoardPanel.dimension.getWidth()),(int) (hDIM_PERC*BoardPanel.dimension.getHeight()));
	}
	
	public void PlaceFamiliarIntoCouncilPalace(){
		
		if(councilCounter<4){
		widthRel = (int) (wCOUNCIL*BoardPanel.dimension.getWidth() + councilCounter*0.5*wDIM_PERC*BoardPanel.dimension.getWidth());
		heightRel = (int) (hCOUNCIL*BoardPanel.dimension.getHeight());
		} else {
			widthRel = (int) (wCOUNCIL*BoardPanel.dimension.getWidth() + (councilCounter%4)*0.5*wDIM_PERC*BoardPanel.dimension.getWidth());
			heightRel = (int) (hCOUNCIL*BoardPanel.dimension.getHeight() + 0.7*hDIM_PERC*BoardPanel.dimension.getWidth());
		}
		setFamilyMember();
		widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
		heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
		if(councilCounter==7)
			councilCounter=0;
		else
			councilCounter++;
		
	}

	public void PlaceFamiliarIntoMarket(int market){
		if(market == 1){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket);
			setFamilyMember();
			
		} else if(market == 2){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket + BoardPanel.dimension.getWidth()*0.09);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket);
			setFamilyMember();
			
		} else if(market == 3){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket + BoardPanel.dimension.getWidth()*0.175);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket + BoardPanel.dimension.getHeight()*0.0175);
			setFamilyMember();
			
		} else if(market == 4){
			widthRel = (int) (BoardPanel.dimension.getWidth()*wFirstMarket + BoardPanel.dimension.getWidth()*0.235);
			heightRel = (int) (BoardPanel.dimension.getHeight()*hFirstMarket + BoardPanel.dimension.getHeight()*0.065);
			setFamilyMember();
		}
		widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
		heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	}

	public void PlaceFamiliarIntoHarvestArea(String area){
		if(area == "1"){
			widthRel = (int)(BoardPanel.dimension.getWidth()*wHarvest);
			heightRel = (int)(BoardPanel.dimension.getHeight()*hHarvest);
			setFamilyMember();
		} else if(area == "2"){
			widthRel = (int)(BoardPanel.dimension.getWidth()*wBigHarvest + harvestCounter*0.5*wDIM_PERC*BoardPanel.dimension.getWidth());
			heightRel = (int)(BoardPanel.dimension.getHeight()*hHarvest);
			setFamilyMember();
			harvestCounter++;
		}
		widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
		heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	}
	
	public void PlaceFamiliarIntoProductionArea(String area){
		if(area == "1"){
			widthRel = (int)(BoardPanel.dimension.getWidth()*wHarvest);
			heightRel = (int)(BoardPanel.dimension.getHeight()*hProduction);
			setFamilyMember();
		} else if(area == "2"){
			widthRel = (int)(BoardPanel.dimension.getWidth()*wBigHarvest + productionCounter*0.5*wDIM_PERC*BoardPanel.dimension.getWidth());
			heightRel = (int)(BoardPanel.dimension.getHeight()*hProduction);
			setFamilyMember();
			productionCounter++;
		}
		widthRel = (int) (BoardPanel.dimension.getWidth()*wFIRST_SLOT);
		heightRel = (int) (BoardPanel.dimension.getHeight()*hFIRST_SLOT);
	}
	
	public void ResetAllCounters(){
		this.productionCounter = 0;
		this.harvestCounter = 0;
		this.councilCounter = 0;
	}



}
