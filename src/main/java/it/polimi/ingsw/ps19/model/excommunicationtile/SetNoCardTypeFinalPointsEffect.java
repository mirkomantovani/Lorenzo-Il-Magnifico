package it.polimi.ingsw.ps19.model.excommunicationtile;

import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * @author Mirko
 *
 */
public class SetNoCardTypeFinalPointsEffect extends Effect {
 
	private CardType cardType;
	
	public SetNoCardTypeFinalPointsEffect(CardType cardType) {
		this.cardType = cardType;
	}

	@Override
	public void applyEffect(Player player) {
		player.getBonuses().setNoCardTypeFinalPoints(cardType);

	}

}
