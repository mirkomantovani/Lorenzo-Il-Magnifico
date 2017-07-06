package it.polimi.ingsw.ps19;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.excommunicationtile.ColoredFamiliarsVariationEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.LosePointsBasedOnResourcesEffect;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

public class ExcommunicationEffectTest {
	
	private Player player;
	
	@Before
	public void setUp(){
		player = new Player("matteo","blue");
	}
	
	@Test
	public void coloredFamiliarsVariationEffectTest(){
		Effect effect = new ColoredFamiliarsVariationEffect(18);
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			if(f.getColor()!= Color.NEUTRAL)
			assertTrue(player.getFamilyMembers().get(f.getColor()).getActionValueVariation() == 18);
		}
	}
	
	@Test
	public void losePointsBasedOnResourcesEffect(){
		player.addResources(new ResourceChest(10,0,0,0,0,100,0));
		
		Effect effect = new LosePointsBasedOnResourcesEffect(new Coin(2) , 
				new VictoryPoint(3));
		effect.applyEffect(player);
		
		assertTrue(player.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount() == 85);
		
	}

}
