package it.polimi.ingsw.ps19.model.deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.Resource;
import it.polimi.ingsw.ps19.ResourceChest;
import it.polimi.ingsw.ps19.ResourceFactory;
import it.polimi.ingsw.ps19.model.card.BuildingCard;
import it.polimi.ingsw.ps19.model.card.CharacterCard;
import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.card.TerritoryCard;
import it.polimi.ingsw.ps19.model.card.VentureCard;
import it.polimi.ingsw.ps19.model.effect.AtomicExchangeEffect;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.ResourcesExchangeEffect;
import it.polimi.ingsw.ps19.model.effect.TakeCardEffect;


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
		Effect immediateEffect;
		Effect permanentEffect;  //this should be a ProductionEffect, but we still have to create the class
		int productionActivationCost;
		int cardIndex=0;
		
		
		BuildingCard[] deck = new BuildingCard[deckLength];

		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();    	//The lineRead variable stores the first line of a card and uses it to check the while condition
		while (lineRead!=null) {
			id=Integer.parseInt(lineRead);
			name= buffReader.readLine(); 
			//period, use enum props
			
			cost=new ResourceChest(Integer.parseInt(buffReader.readLine()),Integer.parseInt(buffReader.readLine()),
					Integer.parseInt(buffReader.readLine()),Integer.parseInt(buffReader.readLine()),0,0,0);
			
			//waiting for immediateEffect class creation, 2 lines to read
			
			productionActivationCost=Integer.parseInt(buffReader.readLine());
			
			permanentEffect=calculateProductionEffectFromFile();
			
			
			lineRead = buffReader.readLine(); 
			lineRead = buffReader.readLine(); 
			
			
//			deck[cardIndex]=new BuildingCard(id,name,period,cost,immediateEffect,permanentEffect,productionActivationCost);
			cardIndex++;
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
		
		AtomicExchangeEffect atomicExchange1,atomicExchange2;
		
		privilege=Integer.parseInt(buffReader.readLine());
		mPoint=Integer.parseInt(buffReader.readLine());
		vPoint=Integer.parseInt(buffReader.readLine());
		coin=Integer.parseInt(buffReader.readLine());
		
		if(privilege==0&&mPoint==0&&vPoint==0&&coin==0){
			coin=Integer.parseInt(buffReader.readLine());
			cardType=Integer.parseInt(buffReader.readLine());
			if(coin==0&&cardType==0){
				vPoint=Integer.parseInt(buffReader.readLine());
				cardType=Integer.parseInt(buffReader.readLine());
				if(coin==0&&vPoint==0){  //avrò l'effetto di ResourceExchange
					atomicExchange1=calculateAtomicExchangeFromFile();
					atomicExchange2=calculateAtomicExchangeFromFile();
					
					return new ResourcesExchangeEffect(atomicExchange1,atomicExchange2);
				}
				else {
					//avrò l'effetto di ricevere vPoint victory point per ogni carta di tipo cardType
				}
				
			}
			else {
				//avrò l'effetto di ricevere coin soldi per ogni carta di tipo cardType //serve CardFactory//avrò l'effetto di ricevere coin soldi per ogni carta di tipo cardType
				
			}
		}
		else {
			//avrò un instant effect come productionEffect, aspetto lo sviluppo di quella classe
		}
		
		
		return null;
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
		
		numberOfResource=Integer.parseInt(buffReader.readLine());
		if(numberOfResource==0){  //it means it has no atomicExchangeEffect
			for(int i=0;i<9;i++)buffReader.readLine();
			return null;
		}
		else{ //it means it has an atomic exchange effect

			int resourceId;
			Resource resourceOut1,resourceOut2,resourceOut3;  //resources to give
			Resource resourceIn1,resourceIn2;     //resources to get
			
			resourceId=Integer.parseInt(buffReader.readLine());
			
			resourceOut1=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());
			resourceId=Integer.parseInt(buffReader.readLine());
			
			resourceOut2=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());
			resourceId=Integer.parseInt(buffReader.readLine());
			
			resourceOut3=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());
			resourceId=Integer.parseInt(buffReader.readLine());
			
			resourceIn1=ResourceFactory.getResource(resourceId,numberOfResource);
			
			numberOfResource=Integer.parseInt(buffReader.readLine());
			resourceId=Integer.parseInt(buffReader.readLine());
			
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
		ResourceChest alternativeCost;
		ResourceChest addings;
		
		Effect immediateEffect;
		Effect permanentEffect;  //this should be a ProductionEffect, but we still have to create the class
		
		int militaryPointsRequired;
		int militaryPointsRequired2;
		int coins;
		int faithPoints;
		int victoryPoints;
		int woods;
		int stones;
		int servants;
		int militaryPoints;
		int privilege;
		int privilege2;
		
		VentureCard[] deck = new VentureCard[deckLength];

		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();
		
		while (lineRead!=null) {
			
		id = Integer.parseInt(buffReader.readLine());
		
		name = buffReader.readLine();
		
		period = Period.values()[Integer.parseInt(buffReader.readLine()) - 1];
		
		coins = Integer.parseInt(buffReader.readLine());
		woods = Integer.parseInt(buffReader.readLine());
		servants = Integer.parseInt(buffReader.readLine());
		stones = Integer.parseInt(buffReader.readLine());
		militaryPointsRequired = Integer.parseInt(buffReader.readLine());
		militaryPoints = Integer.parseInt(buffReader.readLine());
		cost = new ResourceChest(coins,woods,stones,servants,0,0,militaryPoints);
		
		coins = Integer.parseInt(buffReader.readLine());
		servants = Integer.parseInt(buffReader.readLine());
		stones = Integer.parseInt(buffReader.readLine());
		woods = Integer.parseInt(buffReader.readLine());
		militaryPoints = Integer.parseInt(buffReader.readLine());
		militaryPointsRequired2 = Integer.parseInt(buffReader.readLine());
		alternativeCost = new ResourceChest(coins,woods,stones,servants,0,0,militaryPoints);
		
		coins = Integer.parseInt(buffReader.readLine());
		woods = Integer.parseInt(buffReader.readLine());
		servants = Integer.parseInt(buffReader.readLine());
		faithPoints = Integer.parseInt(buffReader.readLine());
		victoryPoints = Integer.parseInt(buffReader.readLine());
		militaryPoints = Integer.parseInt(buffReader.readLine());
		privilege = Integer.parseInt(buffReader.readLine());
		privilege2 = Integer.parseInt(buffReader.readLine());
		
		
		
		
		
		
		
		
		
		
			
			
			
		}
		return deck;
	}
	
	/**
	 * @author matteo
	 * @return
	 * @throws IOException
	 */
	private static Effect calculateTakeCardEffectFromFile() throws IOException{
		int cardCost;
		int cardType;
		
		TerritoryCard territoryCard = null;
		CharacterCard characterCard = null;
		BuildingCard buildingCard = null;
		VentureCard ventureCard = null;
		DevelopmentCard developmentCard = null;
		
		
		cardCost = Integer.parseInt(buffReader.readLine());
		cardType = Integer.parseInt(buffReader.readLine());
		
		
		if(cardType == 1) {
			return new TakeCardEffect(territoryCard,cardCost);
		}else if(cardType == 2)
			{
			return new TakeCardEffect(characterCard,cardCost);
			}else if(cardType == 3)
				{
				return new TakeCardEffect(buildingCard,cardCost);
				}else if(cardType == 4)
					{
					return new TakeCardEffect(ventureCard,cardCost);
					}else if(cardType == 5)
						{
						return new TakeCardEffect(developmentCard,cardCost);
						}
	
			return null;
		
		
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
