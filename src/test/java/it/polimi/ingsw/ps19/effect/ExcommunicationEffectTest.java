package it.polimi.ingsw.ps19.effect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.model.Color;
import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Match;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.CardType;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.excommunicationtile.ColoredFamiliarsVariationEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.LosePointsBasedOnResourcesEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.LosePointsEveryWoodStoneEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.LosePointsForEveryResourceEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.ResourceMalusEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.SetNoCardTypeFinalPointsEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.SetNoMarketActionEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.SetServantsDividerEffect;
import it.polimi.ingsw.ps19.model.excommunicationtile.SetSkipRoundEffect;
import it.polimi.ingsw.ps19.model.resource.Coin;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import it.polimi.ingsw.ps19.model.resource.VictoryPoint;

/**
 * The Class ExcommunicationEffectTest.
 */
/**
 * @author matteo
 *
 */
public class ExcommunicationEffectTest {
	
	/** The player. */
	private Player player;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		player = new Player("matteo","blue");
	}
	
	/**
	 * Colored familiars variation effect test.
	 */
	@Test
	public void coloredFamiliarsVariationEffectTest(){
		Effect effect = new ColoredFamiliarsVariationEffect(18);
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			if(f.getColor()!= Color.NEUTRAL)
			assertEquals(player.getFamilyMembers().get(f.getColor()).getActionValueVariation(), 18);
		}
	}
	
	/**
	 * Lose points based on resources effect.
	 */
	@Test
	public void losePointsBasedOnResourcesEffect(){
		player.addResources(new ResourceChest(10,0,0,0,0,100,0));
		
		Effect effect = new LosePointsBasedOnResourcesEffect(new Coin(2) , 
				new VictoryPoint(3));
		effect.applyEffect(player);
		
		assertEquals(player.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount(),85);
		
	}
	
	
	/**
	 * Lose points every wood stone effect test.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void losePointsEveryWoodStoneEffectTest() throws FileNotFoundException, IOException{
		player.addResources(new ResourceChest(100,100,100,0,0,100,0));
		Match match = new Match(2,null);
		match.getBoard().changeCardInTowers();
		try{
			
			player.addCard(match.getBoard().getTower(CardType.BUILDING).getFloor(0).getCard());
		
		}catch(NullPointerException e){
			
		}
		Effect effect = new LosePointsEveryWoodStoneEffect(new VictoryPoint(2), CardType.BUILDING);
		effect.applyEffect(player);
		assertEquals(player.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount(), 100 
				-2*(match.getBoard().getFloor(CardType.BUILDING, 0).getCard().getCost().getStoneAmount() + 
						match.getBoard().getFloor(CardType.BUILDING, 0).getCard().getCost().getWoodAmount()));
		
	}
	
	/**
	 * Lose points for every resource effect test.
	 */
	@Test
	public void losePointsForEveryResourceEffectTest(){
		player.addResources(new ResourceChest(2,2,2,2,0,100,0));
		Effect effect = new LosePointsForEveryResourceEffect(new VictoryPoint(2));
		effect.applyEffect(player);
		assertEquals(player.getResourceChest().getResourceInChest(ResourceType.VICTORYPOINT).getAmount(), 84);
	}
	
	/**
	 * Resource malus effect test.
	 */
	@Test
	public void resourceMalusEffectTest(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(new VictoryPoint(2));
		
		Effect effect = new ResourceMalusEffect(resources);
		effect.applyEffect(player);
		assertEquals(player.getBonuses().getResourceMalus().get(0).getAmount(), resources.get(0).getAmount());
	}
	
	/**
	 * Sets the no market action effect test.
	 */
	@Test
	public void setNoMarketActionEffectTest(){
		Effect effect = new SetNoMarketActionEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isNoMarketActionActive());
	}
	
	/**
	 * Sets the no card type final points effect test.
	 */
	@Test
	public void setNoCardTypeFinalPointsEffectTest(){
		Effect effect = new SetNoCardTypeFinalPointsEffect(CardType.BUILDING);
		effect.applyEffect(player);
		assertTrue(player.getBonuses().getNoCardTypeFinalPoints(CardType.BUILDING));
	}
	
	/**
	 * Sets the servants divider effect test.
	 */
	@Test
	public void setServantsDividerEffectTest(){
		Effect effect = new SetServantsDividerEffect(2);
		effect.applyEffect(player);
		assertEquals(player.getBonuses().getServantsDivider(), 2);
	}
	
	/**
	 * Sets the skip round effect test.
	 */
	@Test
	public void setSkipRoundEffectTest(){
		Effect effect = new SetSkipRoundEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isSkipRoundActive());
	}
}
