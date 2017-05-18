package it.polimi.ingsw.ps19.model.deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;
import it.polimi.ingsw.ps19.model.effect.AtomicExchangeEffect;
import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.ForEachTypeCardEffect;
import it.polimi.ingsw.ps19.model.effect.InstantHarvestActionEffect;
import it.polimi.ingsw.ps19.model.effect.InstantProductionActionEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.effect.ProductionEffect;
import it.polimi.ingsw.ps19.model.effect.ResourcesExchangeEffect;
import it.polimi.ingsw.ps19.model.effect.TakeCardEffect;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceFactory;
import it.polimi.ingsw.ps19.model.resource.VentureCostResourceChest;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 * The Class DeckCreator.
 */
/**
 * @author Matteo, Jimmy, Mirko
 *
 */
/**
 * @author matteo
 *
 */
/**
 * @author matteo
 *
 */
public class DeckCreator {

	/** The buffered reader used to read from file */
	private static BufferedReader buffReader;
	
	/** The line read from file */
	private static String lineRead;

	/**
	 * Creates the building card deck from the file, see template FileTemplateBuildingsCardV1.xlsx
	 * 
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the building card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Mirko
	 */
	public static BuildingCard[] createBuildingCardDeck(String filePath, int deckLength) throws IOException {

		int id;
		String name;
		Period period; 
		ResourceChest cost;
		ResourceChest instantChest;
		Effect immediateEffect;
		Effect permanentEffect;  //this should be a ProductionEffect, but we still have to create the class
		ProductionEffect productionEffect;
		int productionActivationCost;
		int cardIndex=0;
		
		
		
		
		BuildingCard[] deck = new BuildingCard[deckLength];

		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();  //line 1  	//The lineRead variable stores the first line of a card and uses it to check the while condition
		while (lineRead!=null) {

			id=Integer.parseInt(lineRead);  
			name= buffReader.readLine();    //line 2
			
			
			period=Period.values()[Integer.parseInt(buffReader.readLine())-1];  //line 3
			

			
			
			//lines 4-5-6-7
			cost=new ResourceChest(Integer.parseInt(buffReader.readLine()),Integer.parseInt(buffReader.readLine()),
					Integer.parseInt(buffReader.readLine()),Integer.parseInt(buffReader.readLine()),0,0,0);
			
			
			instantChest=new ResourceChest(0,0,0,0,Integer.parseInt(buffReader.readLine()),
					Integer.parseInt(buffReader.readLine()),0);  //lines 8-9
			
			
			immediateEffect=new InstantResourcesEffect(instantChest);
			
			productionActivationCost=Integer.parseInt(buffReader.readLine());  //line 10
			
			permanentEffect=calculateProductionEffectFromFile();  //lines 11 to 38
			
			productionEffect=new ProductionEffect(permanentEffect);
			
			
			deck[cardIndex]=new BuildingCard(id,name,period,cost,immediateEffect,productionEffect,productionActivationCost);
			cardIndex++;
			
			lineRead = buffReader.readLine();   //line 1
			System.out.println("id: "+lineRead);
			
		}
		return deck;
	}
	
	
	/**
	 * Calculates the production effect from file. (It's only for building cards)
	 *
	 * @author Mirko
	 * @return the effect
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Effect calculateProductionEffectFromFile() throws IOException {
		int privilege;
		int mPoint;
		int vPoint;
		int coin;
		int cardType;
		CardType cType;
		ResourceChest instantChest;
		CouncilPrivilegeEffect privilegeEffect;
		ForEachTypeCardEffect forEachEffect;
		
		ArrayList<Resource> choices;
		
		AtomicExchangeEffect atomicExchange1,atomicExchange2;
		
		privilege=Integer.parseInt(buffReader.readLine());   //lines 11-12-13-14
		mPoint=Integer.parseInt(buffReader.readLine());
		vPoint=Integer.parseInt(buffReader.readLine());
		coin=Integer.parseInt(buffReader.readLine());
		
		if(privilege==0&&mPoint==0&&vPoint==0&&coin==0){
			coin=Integer.parseInt(buffReader.readLine());  //lines 15-16
			cardType=Integer.parseInt(buffReader.readLine());
			if(coin==0&&cardType==0){
				vPoint=Integer.parseInt(buffReader.readLine());    //lines 17-18
				cardType=Integer.parseInt(buffReader.readLine());
				if(coin==0&&vPoint==0){  
					atomicExchange1=calculateAtomicExchangeFromFile();   // lines 19 to 28
					atomicExchange2=calculateAtomicExchangeFromFile();  //lines 29 to 38
					
					return new ResourcesExchangeEffect(atomicExchange1,atomicExchange2);
				}
				else {

					cType=CardType.values()[cardType];
					forEachEffect=new ForEachTypeCardEffect(new VictoryPoint(vPoint), cType);
					for(int i=19;i<39;i++)buffReader.readLine();
					return forEachEffect;

				}
				
			}
			else {

				cType=CardType.values()[cardType];
				forEachEffect=new ForEachTypeCardEffect(new Coin(coin), cType);
				for(int i=17;i<39;i++)buffReader.readLine();
				return forEachEffect;

			}
		}
		else {
			
			choices = new ArrayList<Resource>();
			
			for(int i = 0; i<privilege; i++){
				choices.add(null);
			}
				
			privilegeEffect=new CouncilPrivilegeEffect(choices);
			
			instantChest=new ResourceChest(coin,0,0,0,0,vPoint,mPoint); 
			
			for(int i=15;i<39;i++)buffReader.readLine();
			
			return new InstantResourcesEffect(instantChest,privilegeEffect);  //instant needs a constructor with the possibility to have a privilege effect
		}
		
	}



	/**
	 * Calculates the atomic exchange effect from file. The atomic effect is needed to instantiate
	 * the exchange effect. (only for building cards)
	 *
	 * @author Mirko
	 * @return the atomic exchange effect
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static AtomicExchangeEffect calculateAtomicExchangeFromFile() throws IOException {
		int numberOfResource;
		
		numberOfResource=Integer.parseInt(buffReader.readLine()); //19 or 29
		if(numberOfResource==0){  //it means it has no atomicExchangeEffect
			for(int i=0;i<9;i++)buffReader.readLine();  //line 20 to 28
			return null;
		}
		else{ //it means it has an atomic exchange effect

			int resourceId;
			Resource resourceOut1,resourceOut2,resourceOut3;  //resources to give
			Resource resourceIn1,resourceIn2;     //resources to get
			
			resourceId=Integer.parseInt(buffReader.readLine());  //line 20 or 31
			
			resourceOut1=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());  //line 21
			resourceId=Integer.parseInt(buffReader.readLine());  //line 22
			
			resourceOut2=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());  // line 23
			resourceId=Integer.parseInt(buffReader.readLine());   //line24
			
			resourceOut3=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());  //line25
			resourceId=Integer.parseInt(buffReader.readLine());     //line 26
			
			resourceIn1=ResourceFactory.getResource(resourceId,numberOfResource);
			
			//la linea 292 del file è un 8 non 5 ma ho messo 5 perchè non possiamo trattare la privilege come risorsa ancora
			
			numberOfResource=Integer.parseInt(buffReader.readLine());   //line 27
			resourceId=Integer.parseInt(buffReader.readLine());   //line 28s
			
			resourceIn2=ResourceFactory.getResource(resourceId,numberOfResource);
			
			return new AtomicExchangeEffect(resourceOut1,resourceOut2,resourceOut3,resourceIn1,resourceIn2);
			
			
		}
	}



	/**
	 * Creates the territory card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the territory card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static TerritoryCard[] createTerritoryCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		TerritoryCard[] deck = new TerritoryCard[deckLength];

		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();
		
		
		while (lineRead!=null) {

		}
		return deck;
	}
	
	/**
	 * Creates the venture card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the venture card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static VentureCard[] createVentureCardDeck(String filePath, int deckLength) throws IOException {

		int id;
		String name;
		Period period;
		
		ResourceChest cost;
		ResourceChest addings;
		ResourceChest victoryPointsChest;
		
		Effect immediateEffect;
		Effect permanentEffect;  //this should be a ProductionEffect, but we still have to create the class
		

		int coins;
		int faithPoints;
		int victoryPoints;
		int woods;
		int stones;
		int servants;
		int militaryPoints;
		int privilege;
		int productionBonus;
		int harvestBonus;
		int takeCardType;
		int takeCardCost;
		
		ArrayList<Resource> choices;
		
		int index = 0;
		
		VentureCard[] deck = new VentureCard[deckLength];

		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();
		
		while (lineRead!=null) {
			
		id = Integer.parseInt(buffReader.readLine());
		
		name = buffReader.readLine();
		
		period = Period.values()[Integer.parseInt(buffReader.readLine()) - 1];
		
		cost = new VentureCostResourceChest(Integer.parseInt(buffReader.readLine()), Integer.parseInt(buffReader.readLine()),
				Integer.parseInt(buffReader.readLine()), Integer.parseInt(buffReader.readLine()),
				0,0,0,Integer.parseInt(buffReader.readLine()),Integer.parseInt(buffReader.readLine()));
				
		
		coins = Integer.parseInt(buffReader.readLine());
		woods = Integer.parseInt(buffReader.readLine());
		stones = Integer.parseInt(buffReader.readLine());
		servants = Integer.parseInt(buffReader.readLine());
		faithPoints = Integer.parseInt(buffReader.readLine());
		victoryPoints = Integer.parseInt(buffReader.readLine());
		militaryPoints = Integer.parseInt(buffReader.readLine());
		
		addings = new ResourceChest(coins,woods,stones,servants,faithPoints,0,militaryPoints);
		
		privilege = Integer.parseInt(buffReader.readLine());
		productionBonus = Integer.parseInt(buffReader.readLine());
		harvestBonus = Integer.parseInt(buffReader.readLine());
		takeCardType = Integer.parseInt(buffReader.readLine());
		takeCardCost = Integer.parseInt(buffReader.readLine());

		victoryPointsChest = new ResourceChest(0,0,0,0,0,victoryPoints,0);
		
		if(privilege != 0){
			choices = new ArrayList<Resource>();
			for(int i = 0; i<privilege;i++){
				choices.add(null);
			}
			immediateEffect = new CouncilPrivilegeEffect(choices);
		}else if (takeCardType != 0){
			immediateEffect = new TakeCardEffect(CardType.convertCardType(takeCardType), takeCardCost);
		} else if (productionBonus != 0){
			immediateEffect = new InstantProductionActionEffect(productionBonus);
		}else if (harvestBonus != 0){
			immediateEffect = new InstantHarvestActionEffect(harvestBonus);
		}else {
			immediateEffect = null;
		}
		
		permanentEffect = new InstantResourcesEffect(victoryPointsChest);
		
		deck[index] = new VentureCard(id,name,period,cost,immediateEffect,permanentEffect);
		index++;
		}
		return deck;
	}
	
	
	/**
	 * Creates the character card deck.
	 *
	 * @param filePath the file path
	 * @param deckLength the deck length
	 * @return the character card[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static CharacterCard[] createCharacterCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		CharacterCard[] deck = new CharacterCard[deckLength];

		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();
		
		while (lineRead!=null) {
			
			cardId++;
		}
		return deck;
	}
	
	

	// private Effect createEffect(){
	//
	// }

}
