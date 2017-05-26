package it.polimi.ingsw.ps19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Player {
	
	private String name;
	
	private Color color;
	
	private Set<FamilyMember> familyMembers;
	
	ResourceChest resources;
	
	/*List<TerritoryCard> territoryDeck;
	List<BuildingCard>  buildingDeck;
	List<CharacterCard> characterDeck;
	List<VentureCard> ventureDeck;*/
	
	private Map<CardType, List<DevelopmentCard>> decks;

	ArrayList<DevelopmentCard> d;
	
	private Bonus bonuses;
	
	private boolean excommunicationStatusPeriod1; //true if the player is excommunicated for the first period
	private boolean excommunicationStatusPeriod2;
	private boolean excommunicationStatusPeriod3; 
	
	public Player(String name, Color color){
		
		d.add(new BuildingCard(0, name, null, resources, null, null, 0));
		
		familyMembers=new HashSet<>();
		decks = new HashMap<>();
		
		for(int i=0;i<Dice.values().length;i++){
		familyMembers.add(new FamilyMember(Dice.values()[i]));
		}
		
		resources = new ResourceChest();
		/*
		territoryDeck = new ArrayList<TerritoryCard>();
		buildingDeck = new ArrayList<BuildingCard>();
		characterDeck = new ArrayList<CharacterCard>();
		ventureDeck = new ArrayList<VentureCard>();
		*/
		decks.put(CardType.TERRITORY, new ArrayList<DevelopmentCard>());
//		decks.put(CardType.BUILDING, new ArrayList<BuildingCard>());
//		decks.put(CardType.VENTURE, new ArrayList<VentureCard>());
//		decks.put(CardType.CHARACTER, new ArrayList<CharacterCard>());
		

		
		this.name=name;
		this.color=color;
		
	}
	
	/**
	 * This method adds a cart of a generic Type to the correct Deck of the player
	 * @author Mirko
	 * @param card
	 */
	public void addCard(DevelopmentCard card){
		this.getRightArrayList(card.getCardType()).add(card);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	public boolean isExcommunicationStatusPeriod1() {
		return excommunicationStatusPeriod1;
	}

	public void setExcommunicationStatusPeriod1(boolean excommunicationStatusPeriod1) {
		this.excommunicationStatusPeriod1 = excommunicationStatusPeriod1;
	}

	public boolean isExcommunicationStatusPeriod2() {
		return excommunicationStatusPeriod2;
	}

	public void setExcommunicationStatusPeriod2(boolean excommunicationStatusPeriod2) {
		this.excommunicationStatusPeriod2 = excommunicationStatusPeriod2;
	}

	public boolean isExcommunicationStatusPeriod3() {  //What does it mean Teo?
		return excommunicationStatusPeriod3;
	}

	public void setExcommunicationStatusPeriod3(boolean excommunicationStatusPeriod3) {
		this.excommunicationStatusPeriod3 = excommunicationStatusPeriod3;
	}

	/**
	 * 
	 * @return
	 */
	public ResourceChest getResourceChest() {
		
		return this.resources;
	}
	
	
	/*
	
	private void harvestAction(FamilyMember f){
		int actionValue = harvestModification + f.getDice().getUpperFaceValue();
		
		for(TerritoryCard c : territoryDeck){
			if(c.canActivateHarvestWith(actionValue)){
				//metodo azione raccolto
			}
		}
		//metodo che distribuisce le risorse bonus
	} */
	
	/**
	 * @author Jimmy
	 * This method returns the arrayList associated with the cardType
	 * 
	 * @param cardType
	 * @return
	 * 
	 * 
	 */
	public List<DevelopmentCard> getRightArrayList(CardType cardType){
		return decks.get(cardType);
		
	}


	public Bonus getBonuses() {
		return bonuses;
	}

	public void setBonuses(Bonus bonuses) {
		this.bonuses = bonuses;
	}

	
	
}
