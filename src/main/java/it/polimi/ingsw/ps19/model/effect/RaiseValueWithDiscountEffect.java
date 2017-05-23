package it.polimi.ingsw.ps19.model.effect;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;

/**
 * @author Jimmy
 *
 */
public class RaiseValueWithDiscountEffect extends Effect{
	
	private int raiseAmount;
	private CardType cardType;
	private boolean buildingCardsBonus;
	private boolean characterCardsBonus;
	
	
	public RaiseValueWithDiscountEffect(int raiseAmount, CardType cardType, boolean buildingCardsBonus, boolean characterCardsBonus){
		this.raiseAmount = raiseAmount;
		this.cardType = cardType;
		this.buildingCardsBonus = buildingCardsBonus;
		this.characterCardsBonus = characterCardsBonus;
	}


	public void applyEffect(Player p) {
				switch(cardType){
				case TERRITORY:
					p.getBonuses().setActionValueTerritoryVariation(raiseAmount);
					break;
				case BUILDING:
					p.getBonuses().setActionValueBuildingVariation(raiseAmount);
					break;
				case CHARACTER:
					p.getBonuses().setActionValueCharacterVariation(raiseAmount);
					break;
				case VENTURE:
					p.getBonuses().setActionValueVentureVariation(raiseAmount);
					break;
				case ANY:    //I think that isn't a real case cause there's no card that has it 
					//TODO something
					break;
					
					default: 
						throw new IllegalArgumentException();  //Still to decide this case
				}
				
				if(buildingCardsBonus == true)
					p.getBonuses().setBuildingCardsDiscount(true);
				else if(characterCardsBonus == true)
					p.getBonuses().setCharacterCardsDiscount(true);
			    //Non è possibile che entrambi siano a true, ma è possibile che entrambi siano a false
				//In tal caso il metodo termina senza fare nulla
			
			}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		if(raiseAmount >= 0)
			string.append("You get a + " + raiseAmount + " value for a " + cardType.toString().toLowerCase() + " card");
		else
			string.append("You get a " + raiseAmount + " value for a " + cardType.toString().toLowerCase() + " card");
		
		if(buildingCardsBonus == true)
			string.append(" and you receive one wood and one stone discount on its cost");
		else if(characterCardsBonus == true)
			string.append(" and you receive one coin discount on its costs");

		return string.toString();
	}
		
		
		
		
	
	
	
}
