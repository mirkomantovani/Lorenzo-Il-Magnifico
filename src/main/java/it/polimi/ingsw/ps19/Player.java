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
 * @author matteo
 *
 */
public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7163473671124501218L;
	
	private String name;
	private String color;
	private HashMap<Color,FamilyMember> familyMembers;
	private ResourceChest resources;
	private HashMap<CardType, ArrayList<DevelopmentCard>> decks;
	private Bonus bonuses;
	private transient MatchObserver observer;
	private int councilPrivilege;
	private HashMap<String,LeaderCard> leaderCards;
	
	
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
	 * This method adds a cart of a generic Type to the correct Deck of the player
	 * @author Mirko
	 * @param card
	 */
	public void addCard(DevelopmentCard card){
		
		
		this.getDeckOfType(card.getCardType()).add(card);
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
	public ArrayList<DevelopmentCard> getDeckOfType(CardType cardType){
		return decks.get(cardType);
		
	}


	public Bonus getBonuses() {
		return bonuses;
	}

	public void setBonuses(Bonus bonuses) {
		this.bonuses = bonuses;
	}


	public void setResources(ResourceChest resources) {
		this.resources = resources;
	}
	
	
	public HashMap<Color, FamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	
	/**
	 * Just pass the color's String of the family member and get the right one
	 * @author Mirko
	 * @return
	 */
	public FamilyMember getFamilyMember(String color){
		return this.familyMembers.get(Color.valueOf(Color.class, color.toUpperCase()));
		
	}
	
	/**
	 * @author Mirko
	 * @param color 
	 *           :the Color of the familyMember you wanna get
	 * @return
	 */
	public FamilyMember getFamilyMember(Color color){
		return this.familyMembers.get(color);
		
	}

	public void setFamilyMembers(HashMap<Color, FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Player : " + this.getName() + "\n" + "Color : " + this.getColor() +
						"\n" + "Resources : " + this.getResourceChest().toString() + "\n" + "Cards taken : \n\t Territory cards :"
								+ this.getDeckOfType(CardType.TERRITORY).toString() + "\n\t Character cards : "
								+ this.getDeckOfType(CardType.CHARACTER).toString() + "\n\t Building cards : " 
								+ this.getDeckOfType(CardType.BUILDING).toString() + "\n\t Venture cards : " 
								+ this.getDeckOfType(CardType.VENTURE).toString() );

		string.append("\nFamily members:\n");
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
	 * This clone method returns a visible to all players Player
	 * @author Mirko
	 * @return
	 */
	public Player maskedClone() {
		
		Player maskedPlayer=new Player(this.name, this.color);
		ResourceChest maskedRC=new ResourceChest();
		
		maskedRC.addResource(this.getResourceChest().getResourceInChest(ResourceType.MILITARYPOINT));
		maskedRC.addResource(this.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT));
		maskedRC.addResource(this.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT));
		
		maskedPlayer.setResources(maskedRC);
		
		return maskedPlayer;
		
	}
	/**
	 * The observer is needed to notify to the virtual view any change in the player status
	 * @author Mirko
	 * @param observer
	 */
	public void addObserver(MatchObserver observer) {
		this.observer=observer;
	}
	
	public void addResources(ResourceChest resourceChest){
		this.resources.addChest(resourceChest);
		
//		System.out.println("aggiunte risorse, nuovo status player:"+this.toString());
		if(observer!=null)
		this.observer.notifyPlayerStatusChange(this);
	}
	
	public void subResources(ResourceChest resourceChest){
		this.resources.subChest(resourceChest);
		if(observer!=null)
		this.observer.notifyPlayerStatusChange(this);
	}
	public int getCouncilPrivilege() {
		return councilPrivilege;
	}
	public void setCouncilPrivilege(int councilPrivilege) {
		this.councilPrivilege = councilPrivilege;
	}
	public Map<String,LeaderCard> getLeaderCards() {
		return leaderCards;
	}
	
	public void setLeaderCards(HashMap<String,LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}
	public void removeLeaderCard(String leaderName) {
		this.leaderCards.remove((String)leaderName);
		if(observer!=null)
			this.observer.notifyPlayerStatusChange(this);
	}
	public void addLeaderCards(LeaderCard leaderCard){
	
//		System.out.println("player: aggiungo leadercard");
		
		this.leaderCards.put(leaderCard.getName(), leaderCard);
		if(observer!=null)
//			System.out.println("player: aggiunta leader, notifico cambio stato");
//			System.out.println(this.toString());
     		this.observer.notifyPlayerStatusChange(this);
	}
	public void activateLeaderCard(String name){
		this.leaderCards.get(name).getSpecialEffect().applyEffect(this);
		if(observer!=null)
			this.observer.notifyPlayerStatusChange(this);
	}
	
	public void payFaithPoint(){
		ResourceChest rc = new ResourceChest();
		rc.addResource(this.getResourceChest().getResourceInChest(ResourceType.FAITHPOINT));
		this.subResources(rc);
		this.observer.notifyPlayerStatusChange(this);
	}
	
	public void addFamilyMembers(){
		for(int i = 0; i < Color.values().length; i++){ //NOTE that if Dice and Color values aren't in the same order, this won't work!
		familyMembers.put(Color.values()[i], new FamilyMember(Dice.values()[i],this));
		}
	}
	
	public void removeFamilyMember(Color color){
		this.familyMembers.remove(color);
		this.observer.notifyPlayerStatusChange(this);
	}
	public void resetPrivileges() {
this.councilPrivilege=0;		
	}

	
	
}