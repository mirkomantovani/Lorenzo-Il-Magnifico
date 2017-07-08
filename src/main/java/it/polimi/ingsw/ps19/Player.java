package it.polimi.ingsw.ps19;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.card.LeaderCard;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.server.observers.MatchObserver;

/**
 * The Class Player.
 * This is the player class, everyone in the match is linked with an instance of this class
 *
 * @author matteo
 */
public class Player implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7163473671124501218L;
	
	/** The name. */
	private String name;
	
	/** The color. */
	private String color;
	
	/** The family members. */
	private HashMap<Color,FamilyMember> familyMembers;
	
	/** The resources. */
	private ResourceChest resources;
	
	/** The decks. */
	private HashMap<CardType, ArrayList<DevelopmentCard>> decks;
	
	/** The bonuses. */
	private Bonus bonuses;
	
	/** The observer. */
	private transient MatchObserver observer;
	
	/** The council privilege. */
	private int councilPrivilege;
	
	/** The leader cards. */
	private HashMap<String,LeaderCard> leaderCards;
	
	/** The is excommunicated first. */
	private boolean isExcommunicatedFirst;
	
	/** The is excommunicated second. */
	private boolean isExcommunicatedSecond;
	
	/** The is excommunicated third. */
	private boolean isExcommunicatedThird;
	
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 * @param color the color
	 */
	public Player(String name, String color){
		
		
		familyMembers=new HashMap<>();
		decks = new HashMap<>();
		
		resources = new ResourceChest();
		
		for(int i = 0; i < Color.values().length; i++){ //NOTE that if Dice and Color values aren't in the same order, this won't work!
			decks.put(CardType.values()[i], new ArrayList<DevelopmentCard>());
		}
		
		leaderCards=new HashMap<String,LeaderCard>();

		
		this.name=name;
		this.color=color;
		
		this.bonuses = new Bonus();
		
	
	}
	
	/**
	 * This method adds a cart of a generic Type to the correct Deck of the player.
	 *
	 * @author Mirko
	 * @param card the card
	 */
	public void addCard(DevelopmentCard card){
		
		
		this.getDeckOfType(card.getCardType()).add(card);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}


	
	/**
	 * Gets the resource chest.
	 *
	 * @return the resource chest
	 */
	public ResourceChest getResourceChest() {
		
		return this.resources;
	}
	
	
	
	
	/**
	 * Gets the deck of type.
	 *
	 * @author Jimmy
	 * This method returns the arrayList associated with the cardType
	 * @param cardType the card type
	 * @return the deck of type
	 */
	public ArrayList<DevelopmentCard> getDeckOfType(CardType cardType){
		return decks.get(cardType);
		
	}


	/**
	 * Gets the bonuses.
	 *
	 * @return the bonuses
	 */
	public Bonus getBonuses() {
		return bonuses;
	}

	/**
	 * Sets the bonuses.
	 *
	 * @param bonuses the new bonuses
	 */
	public void setBonuses(Bonus bonuses) {
		this.bonuses = bonuses;
	}


	/**
	 * Sets the resources.
	 *
	 * @param resources the new resources
	 */
	public void setResources(ResourceChest resources) {
		this.resources = resources;
	}
	
	
	/**
	 * Gets the family members.
	 *
	 * @return the family members
	 */
	public HashMap<Color, FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	/**
	 * Just pass the color's String of the family member and get the right one.
	 *
	 * @author Mirko
	 * @param color the color
	 * @return the family member
	 */
	public FamilyMember getFamilyMember(String color){
		System.out.println("player: getfamilymember, prendo dalla mappa");
		return this.familyMembers.get(Color.valueOf(Color.class, color.toUpperCase()));
	}
	
	/**
	 * Gets the family member.
	 *
	 * @author Mirko
	 * @param color           :the Color of the familyMember you wanna get
	 * @return the family member
	 */
	public FamilyMember getFamilyMember(Color color){
		return this.familyMembers.get(color);
		
	}

	/**
	 * Sets the family members.
	 *
	 * @param familyMembers the family members
	 */
	public void setFamilyMembers(HashMap<Color, FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("PLAYER:\n" + this.getName() + "\n" + "Color : " + this.getColor() +
						"\n" + "Resources : " + this.getResourceChest().toString() + "\n" + "Cards taken : \n\nTERRITORY CARDS:\n"
								+ this.getDeckOfType(CardType.TERRITORY).toString() + "\nCHARACTER CARDS:\n"
								+ this.getDeckOfType(CardType.CHARACTER).toString() + "\nBUILDING CARDS:\n" 
								+ this.getDeckOfType(CardType.BUILDING).toString() + "\nVENTURE CARDS:\n" 
								+ this.getDeckOfType(CardType.VENTURE).toString() );

		string.append("\n\nFamily members:\n");
		for(FamilyMember mem : this.getFamilyMembers().values()){
			string.append(mem.toString() + "\n");
		}
		
		if(!leaderCards.isEmpty()){
			string.append("Leader cards : \n");
			for(int i = 0; i < this.leaderCards.values().toArray().length; i++){
				string.append(leaderCards.values().toArray()[i].toString());
				
		
				
//		 Iterator it = leaderCards.entrySet().iterator();
//		    while (it.hasNext()) {
//		        Map.Entry pair = (Map.Entry)it.next();
//		        string.append(pair.getValue());
//		        it.remove(); // avoids a ConcurrentModificationException
	    }
		}

		string.append(leaderCards.size() + "questo è il size");
			

//		} else 
//			string.append("\nThe player hasn't any leader card");
	
		
		return string.toString();
	}
	 

	
	/**
	 * This clone method returns a visible to all players Player.
	 *
	 * @author Mirko
	 * @return the player
	 */
	public Player maskedClone() {
		
		Player maskedPlayer=new Player(this.name, this.color);
		ResourceChest maskedRC=new ResourceChest();
		
		maskedPlayer.setExcommunicatedFirst(this.isExcommunicatedFirst);
		maskedPlayer.setExcommunicatedSecond(this.isExcommunicatedSecond);
		maskedPlayer.setExcommunicatedThird(this.isExcommunicatedThird);
		
		maskedRC.addResource(this.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT));
		maskedRC.addResource(this.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT));
		maskedRC.addResource(this.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT));
		
		maskedPlayer.setResources(maskedRC);
		
		return maskedPlayer;
		
	}
	
	/**
	 * The observer is needed to notify to the virtual view any change in the player status.
	 *
	 * @author Mirko
	 * @param observer the observer
	 */
	public void addObserver(MatchObserver observer) {
		this.observer=observer;
	}
	
	/**
	 * Adds the resources.
	 *
	 * @param resourceChest the resource chest
	 */
	public void addResources(ResourceChest resourceChest){
		this.resources.addChest(resourceChest);
		
//		System.out.println("aggiunte risorse, nuovo status player:"+this.toString());
		if(observer!=null)
		this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Sub resources.
	 *
	 * @param resourceChest the resource chest
	 */
	public void subResources(ResourceChest resourceChest){
		this.resources.subChest(resourceChest);
		if(observer!=null)
		this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Gets the council privilege.
	 *
	 * @return the council privilege
	 */
	public int getCouncilPrivilege() {
		return councilPrivilege;
	}
	
	/**
	 * Sets the council privilege.
	 *
	 * @param councilPrivilege the new council privilege
	 */
	public void setCouncilPrivilege(int councilPrivilege) {
		this.councilPrivilege = councilPrivilege;
	}
	
	/**
	 * Gets the leader cards.
	 *
	 * @return the leader cards
	 */
	public Map<String,LeaderCard> getLeaderCards() {
		return leaderCards;
	}
	
	/**
	 * Sets the leader cards.
	 *
	 * @param leaderCards the leader cards
	 */
	public void setLeaderCards(HashMap<String,LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}
	
	/**
	 * Removes the leader card.
	 *
	 * @param leaderName the leader name
	 */
	public void removeLeaderCard(String leaderName) {
		this.leaderCards.remove((String)leaderName);
		if(observer!=null)
			this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Adds the leader cards.
	 *
	 * @param leaderCard the leader card
	 */
	public void addLeaderCards(LeaderCard leaderCard){
	
//		System.out.println("player: aggiungo leadercard");
		
		this.leaderCards.put(leaderCard.getName(), leaderCard);
		if(observer!=null)
//			System.out.println("player: aggiunta leader, notifico cambio stato");
//			System.out.println(this.toString());
     		this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Activate leader card.
	 *
	 * @param name the name
	 */
	public void activateLeaderCard(String name){
		this.leaderCards.get(name).setActivationState(true);
		this.leaderCards.get(name).getSpecialEffect().applyEffect(this);
		if(observer!=null)
			this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Pay faith point.
	 */
	public void payFaithPoint(){
		ResourceChest rc = new ResourceChest();
		rc.addResource(this.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT));
		this.subResources(rc);
		this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Adds the family members.
	 */
	public void addFamilyMembers(){
		for(int i = 0; i < Color.values().length; i++){ //NOTE that if Dice and Color values aren't in the same order, this won't work!
		familyMembers.put(Color.values()[i], new FamilyMember(Dice.values()[i],this));
		}
	}
	
	/**
	 * Removes the family member.
	 *
	 * @param color the color
	 */
	public void removeFamilyMember(Color color){
		System.out.println("colore family member da eliminare:"+color.toString());
		this.familyMembers.remove(color);
		System.out.println("rimosso family member");
		for(FamilyMember mem : this.getFamilyMembers().values()){
			System.out.println(mem.toString());
		}
		this.observer.notifyPlayerStatusChange(this);
	}
	
	/**
	 * Reset privileges.
	 */
	public void resetPrivileges() {
		this.councilPrivilege=0;		
	}
	
	/**
	 * Refresh family member values.
	 */
	public void refreshFamilyMemberValues() {
	

		for(Map.Entry<Color, FamilyMember > entry : familyMembers.entrySet()) {
		    Color key = entry.getKey();
		    FamilyMember fam = entry.getValue();
		    
		    fam.refreshDiceValue();

		}
	
		
	}
	
	/**
	 * Checks if is excommunicated first.
	 *
	 * @return true, if is excommunicated first
	 */
	public boolean isExcommunicatedFirst() {
		return isExcommunicatedFirst;
	}
	
	/**
	 * Checks if is excommunicated second.
	 *
	 * @return true, if is excommunicated second
	 */
	public boolean isExcommunicatedSecond() {
		return isExcommunicatedSecond;
	}
	
	/**
	 * Checks if is excommunicated third.
	 *
	 * @return true, if is excommunicated third
	 */
	public boolean isExcommunicatedThird() {
		return isExcommunicatedThird;
	}
	
	/**
	 * Sets the excommunicated first.
	 *
	 * @param isExcommunicatedFirst the new excommunicated first
	 */
	public void setExcommunicatedFirst(boolean isExcommunicatedFirst) {
		this.isExcommunicatedFirst = isExcommunicatedFirst;
	}
	
	/**
	 * Sets the excommunicated second.
	 *
	 * @param isExcommunicatedSecond the new excommunicated second
	 */
	public void setExcommunicatedSecond(boolean isExcommunicatedSecond) {
		this.isExcommunicatedSecond = isExcommunicatedSecond;
	}
	
	/**
	 * Sets the excommunicated third.
	 *
	 * @param isExcommunicatedThird the new excommunicated third
	 */
	public void setExcommunicatedThird(boolean isExcommunicatedThird) {
		this.isExcommunicatedThird = isExcommunicatedThird;
	}
	
	/**
	 * Gets the observer.
	 *
	 * @return the observer
	 */
	public MatchObserver getObserver() {
		return observer;
	}
	
	
	
}