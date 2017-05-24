package it.polimi.ingsw.ps19.model.excommunicationtile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceFactory;

/**
 * @author Mirko
 *
 */
public class ExcommunicationTilesCreator {

	
	
	
	/** The buffered reader used to read from file */
	private static BufferedReader buffReader;
	
	/** The line read from file */
	private static String lineRead;
	
	public static ExcommunicationTile[] createExcommunicationTiles(String filePath, int tiles) throws IOException{
		
		Period period; 
		Resource r1;
		Resource r2;
		int rt1;
		int rt2;
		int n1;
		int t;
		int n2;
		CardType cardType;
		
		buffReader = new BufferedReader(new FileReader(filePath));
		lineRead = buffReader.readLine();  //line 1  	
		while (lineRead!=null) {
			
			
			n1=Integer.parseInt(lineRead);   
			rt1=Integer.parseInt(buffReader.readLine()); //line 2
			r1=ResourceFactory.getResource(rt1,n1);
			
			n1=Integer.parseInt(buffReader.readLine()); //line 3
			rt1=Integer.parseInt(buffReader.readLine());  //line 4
			r1=ResourceFactory.getResource(rt1,n1);
			
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
							cardType=CardType.values()[t];
							if(n1==0){
								n1=Integer.parseInt(lineRead); //line 11
								if(n1==0){
									n1=Integer.parseInt(lineRead); //line 12
									if(n1==0){
										n1=Integer.parseInt(lineRead); //line 13
										if(n1==0){
											t=Integer.parseInt(lineRead); //line 14
											cardType=CardType.values()[t];
											if(t==0){
												n1=Integer.parseInt(lineRead); //line 15
												n2=Integer.parseInt(lineRead); //line 16
												rt1=Integer.parseInt(lineRead); //line 17
												if(n1==0){
													t=Integer.parseInt(lineRead); //line 18
													cardType=CardType.values()[t];
													if(t==0){
														n1=Integer.parseInt(lineRead); //line 19
														
														//perdi n1 punti vittoria per ogni risorsa (legno, pietra servitori e monete) nella tua plancia
														
													}
													else {//perdi un punto vittoria per ogni legno e pietra sulle carte di tipo t in tuo possesso

														buffReader.readLine();  //line 19
													}
												}
												else { //perdi n1 punti vittoria per ogni n2 risorse di tipo rt1  (perdi risorsa r1 per ogni r2)
													r1=ResourceFactory.getResource(6,n1);
													r2=ResourceFactory.getResource(rt1,n2);

													for(int i=18;i<20;i++)buffReader.readLine();  //lines 18 to 19
												}
											}
											else {
												//no final points for cartType type card (can't be building type)

												for(int i=15;i<20;i++)buffReader.readLine();  //lines 15 to 19
											}
										}
										else {
											//salta turno

											
											for(int i=14;i<20;i++)buffReader.readLine();  //lines 14 to 19
										}
									}
									else {
										//fattore moltiplicativo servitori su aumento valore azione

										
										for(int i=13;i<20;i++)buffReader.readLine();  //lines 13 to 19
									}
								}
								else {
									//non puoi piazzare in mercato
									
									for(int i=12;i<20;i++)buffReader.readLine();  //lines 12 to 19
								}
							}
							else { //malus di n1 su valore azione per carte di tipo cardType

								for(int i=11;i<20;i++)buffReader.readLine();  //lines 11 to 19
							}
							
						}
						else {
							//malus valore familiari colorati
							
							for(int i=9;i<20;i++)buffReader.readLine();  //lines 9 to 19
						}	
					}
					else {
						//malus produzione di n1
						
						for(int i=8;i<20;i++)buffReader.readLine();  //lines 8 to 19
					}	
				}
				else{ //malus raccolto di n1
					
					for(int i=7;i<20;i++)buffReader.readLine();  //lines 7 to 19
				}
			}
			else{ //effetto risorse in meno
				
				for(int i=6;i<20;i++)buffReader.readLine();   //lines 6 to 19
			}
		}

		return null;
	}
	
	
}
