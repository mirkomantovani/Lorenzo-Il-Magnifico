package it.polimi.ingsw.ps19;

import it.polimi.ingsw.ps19.model.card.DevelopmentCard;
import it.polimi.ingsw.ps19.model.effect.Effect;

public class CardFactory {                 //STILL TO DEVELEP

	private static final int TERRITORY=1;
	private static final int BUILDING=2;
	private static final int CHARACTER=3;
	private static final int VENTURE=4;
	private static final int ANY=5;         //Still not sure about this
	
	public static DevelopmentCard getCard(int id,String name,Period period,ResourceChest cost,Effect immediateEffect,Effect permanentEffect){
		switch(id){
		case TERRITORY:
			return null;
		case BUILDING:
			return null;
		case CHARACTER:
			return null;
		case VENTURE:
			return null;
		case ANY:
			return null;
		case 0:
			return null;
			default:
				throw new CardIdException();
		}
	}
	
}
