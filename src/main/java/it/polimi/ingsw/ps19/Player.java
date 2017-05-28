package it.polimi.ingsw.ps19;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * @author matteo
 *
 */
public class Player {
	
	private String name;
	
	private String color;
	
	private Map<Color,FamilyMember> familyMembers;
	
	private ResourceChest resources;

	
	private Map<CardType, List<DevelopmentCard>> decks;



	private Bonus bonuses;
	
	
	public Player(String name, String color){
		
		
		familyMembers=new HashMap<>();
		decks = new HashMap<>();
		
		for(int i = 0; i < Color.values().length; i++){ //NOTE that if Dice and Color values aren't in the same order, this won't work!
		familyMembers.put(Color.values()[i], new FamilyMember(Dice.values()[i]));
		}
		
		resources = new ResourceChest();
	
		for(int i = 0; i < Color.values().length; i++){ //NOTE that if Dice and Color values aren't in the same order, this won't work!
			decks.put(CardType.values()[i], new ArrayList<DevelopmentCard>());
			}

		
		this.name=name;
		this.color=color;
		
		this.bonuses = new Bonus();
		
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	
	/**
	 * 
	 * @return
	 */
	public ResourceChest getResourceChest() {
		
		return this.resources;
	}
	
	
	
	
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



	public ResourceChest getResources() {
		return resources;
	}

	public void setResources(ResourceChest resources) {
		this.resources = resources;
	}
	
	
	public Map<Color, FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Map<Color, FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}


	
	
}
