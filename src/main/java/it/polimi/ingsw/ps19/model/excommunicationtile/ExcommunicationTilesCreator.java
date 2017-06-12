package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.exception.IllegalCardTypeException;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.HarvestBonusEffect;
import it.polimi.ingsw.ps19.model.effect.ProductionBonusEffect;
import it.polimi.ingsw.ps19.model.effect.RaiseValueWithDiscountEffect;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceFactory;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 * @author Mirko
 *
 */
public class ExcommunicationTilesCreator {

	/** The buffered reader used to read from file */
	private static BufferedReader buffReader;
	
	/** The line read from file */
	private static String lineRead;
	
	private static final String filePath="src/main/resources/files/fileexcommunicationtiles.txt";
	
	public static ExcommunicationTile[] createExcommunicationTiles(int tiles) throws IOException{
		
		Period period; 
		String s;
		int index=0;
		Resource r1=null;
		Resource r2=null;
		Effect effect;
		int rt1;
		int rt2;
		int n1;
		int t;
		int n2;
		CardType cardType;
		ExcommunicationTile[] tilesArray=new ExcommunicationTile[tiles];
		
		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();  //line 1  	
		while (lineRead!=null) {
			
			
			
			n1=Integer.parseInt(lineRead);   
			rt1=Integer.parseInt(buffReader.readLine()); //line 2
			if(rt1!=0)
			r1=ResourceFactory.getResource(ResourceType.values()[rt1-1],n1);
			
			n1=Integer.parseInt(buffReader.readLine()); //line 3
			rt1=Integer.parseInt(buffReader.readLine());  //line 4
			if(rt1!=0)
			r2=ResourceFactory.getResource(ResourceType.values()[rt1-1],n1);
			
			period=Period.values()[Integer.parseInt(buffReader.readLine())-1];  //line 5
			
			if(r1==null){
				n1=Integer.parseInt(lineRead);  //line 6
				if(n1==0){
					n1=Integer.parseInt(lineRead); //line 7
					if(n1==0){
						n1=Integer.parseInt(lineRead); //line 8
						if(n1==0){
							n1=Integer.parseInt(lineRead); //line 9
							t=Integer.parseInt(lineRead); //line 10
							try{
							cardType=CardType.values()[t-1];
							}catch(Exception e){
								throw new IllegalCardTypeException();
							}
							if(n1==0){
								n1=Integer.parseInt(lineRead); //line 11
								if(n1==0){
									n1=Integer.parseInt(lineRead); //line 12
									if(n1==0){
										n1=Integer.parseInt(lineRead); //line 13
										if(n1==0){
											t=Integer.parseInt(lineRead); //line 14
											try{
												cardType=CardType.values()[t-1];
												}catch(Exception e){
													throw new IllegalCardTypeException();
												}
											if(t==0){
												n1=Integer.parseInt(lineRead); //line 15
												n2=Integer.parseInt(lineRead); //line 16
												rt1=Integer.parseInt(lineRead); //line 17
												if(n1==0){
													t=Integer.parseInt(lineRead); //line 18
													try{
														cardType=CardType.values()[t-1];
														}catch(Exception e){
															throw new IllegalCardTypeException();
														}
													if(t==0){
														n1=Integer.parseInt(lineRead); //line 19
														
														//perdi n1 punti vittoria per ogni risorsa (legno, pietra servitori e monete) nella tua plancia
														effect=new LosePointsForEveryResourceEffect(new VictoryPoint(n1));
														tilesArray[index]=new ExcommunicationTile(period, effect);
		
													}
													else {//perdi un punto vittoria per ogni legno e pietra sulle carte di tipo t in tuo possesso
														try{
															cardType=CardType.values()[t-1];
															}catch(Exception e){
																throw new IllegalCardTypeException();
															}
														effect=new LosePointsEveryWoodStoneEffect(new VictoryPoint(1), cardType);
														tilesArray[index]=new ExcommunicationTile(period, effect);
														
														s=buffReader.readLine();  //line 19
													}
												}
												else { //perdi n1 punti vittoria per ogni n2 risorse di tipo rt1  (perdi risorsa vp per ogni r2)
													//LosePointsBasedOnResourcesEffect
													VictoryPoint vp;
													r1=ResourceFactory.getResource(ResourceType.values()[6-1],n1);
													vp=(VictoryPoint)r1;
													r2=ResourceFactory.getResource(ResourceType.values()[rt1-1],n2);
													
													effect=new LosePointsBasedOnResourcesEffect(r2, vp);
													tilesArray[index]=new ExcommunicationTile(period, effect);
													

													for(int i=18;i<20;i++)s=buffReader.readLine();  //lines 18 to 19
												}
											}
											else {
												//no final points for cartType type card (can't be building type)
												effect=new SetNoCardTypeFinalPointsEffect(cardType);
												tilesArray[index]=new ExcommunicationTile(period, effect);

												for(int i=15;i<20;i++)s=buffReader.readLine();  //lines 15 to 19
											}
										}
										else {
											//salta turno
											effect=new SetSkipRoundEffect();
											tilesArray[index]=new ExcommunicationTile(period, effect);

											
											for(int i=14;i<20;i++)s=buffReader.readLine();  //lines 14 to 19
										}
									}
									else {
										//fattore moltiplicativo servitori su aumento valore azione
										effect=new SetServantsDividerEffect(n1);
										tilesArray[index]=new ExcommunicationTile(period, effect);
										
										for(int i=13;i<20;i++)s=buffReader.readLine();  //lines 13 to 19
									}
								}
								else {
									//non puoi piazzare in mercato
									effect=new SetNoMarketActionEffect();
									tilesArray[index]=new ExcommunicationTile(period, effect);
									
									for(int i=12;i<20;i++)s=buffReader.readLine();  //lines 12 to 19
								}
							}
							else { //malus di n1 su valore azione per carte di tipo cardType
//raise value with discount effect
								effect=new RaiseValueWithDiscountEffect(n1, cardType, false, false);
								tilesArray[index]=new ExcommunicationTile(period, effect);
								for(int i=11;i<20;i++)s=buffReader.readLine();  //lines 11 to 19
							}
							
						}
						else {	effect = new ColoredFamiliarsVariationEffect(-n1);
								tilesArray[index]=new ExcommunicationTile(period, effect);
							
							
							for(int i=9;i<20;i++)s=buffReader.readLine();  //lines 9 to 19
						}	
					}
					else {	effect = new ProductionBonusEffect(-n1);
					tilesArray[index]=new ExcommunicationTile(period, effect);
						
						for(int i=8;i<20;i++)s=buffReader.readLine();  //lines 8 to 19
					}	
				}
				else{ 	effect = new HarvestBonusEffect(-n1);
						tilesArray[index]=new ExcommunicationTile(period, effect);
					
					for(int i=7;i<20;i++)s=buffReader.readLine();  //lines 7 to 19
				}
			}
			else{ 	
				
				ArrayList<Resource> resource = new ArrayList<Resource>();
					resource.add(r1);
					if(r2!=null){
					resource.add(r2);
					}
					effect = new ResourceMalusEffect(resource);
					tilesArray[index]=new ExcommunicationTile(period, effect);
				
				for(int i=6;i<20;i++)s=buffReader.readLine();   //lines 6 to 19
			}
			index++;
			lineRead = buffReader.readLine();
		}

		return tilesArray;
	}
	
	
}