package it.polimi.ingsw.ps19;

import it.polimi.ingsw.ps19.Dice;
import java.util.ArrayList;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.BuildingCard;

public class Player {
	
	String name;
	
	Color color;
	
	FamilyMember orangeMember;
	
	FamilyMember blackMember;
	
	FamilyMember whiteMember;
	
	FamilyMember neutralMember;
	
	ResourceChest resources;
	
	ArrayList<TerritoryCard> territoryDeck;
	
	ArrayList<BuildingCard>  buildingDeck;
	
	ArrayList<CharacterCard> characterDeck;
	
	ArrayList<VentureCard> ventureDeck;
	
	int harvestModification; //it means the increase/decrease of the Harvest value
	
	int productionModification; 
	
	int excommunicationStatus; //the value indicates the Period of excomm., 0 = no excomm. 
	
	public Player(String name, Color color){
		
		resources = new ResourceChest();
		
		orangeMember = new FamilyMember(Dice.ORANGE_DICE);
		
		blackMember = new FamilyMember(Dice.BLACK_DICE);
		
		whiteMember = new FamilyMember(Dice.WHITE_DICE);
		
		neutralMember = new FamilyMember(Dice.NEUTRAL_DICE);
		
		territoryDeck = new ArrayList<TerritoryCard>();
		
		buildingDeck = new ArrayList<BuildingCard>();
		
		characterDeck = new ArrayList<CharacterCard>();
		
		ventureDeck = new ArrayList<VentureCard>();
		
	}
	
	
}
