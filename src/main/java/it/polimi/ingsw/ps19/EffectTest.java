package it.polimi.ingsw.ps19;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.HarvestBonusEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.effect.NoFloorBonusEffect;
import it.polimi.ingsw.ps19.model.effect.ProductionBonusEffect;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

public class EffectTest {
	
	private Player player;
	
	@Before
	public void setUp(){
		player = new Player("matteo", "blue");
		player.setResources(new ResourceChest(0,0,0,0,0,0,0));
		player.setCouncilPrivilege(0);
	
	}
	
	@Test
	public void testInstantResourcesEffect(){
		ResourceChest rc = new ResourceChest(1,1,1,1,1,1,1);
		Effect effect = new InstantResourcesEffect(rc);
		effect.applyEffect(player);
		player.getResourceChest().subChest(rc);
		assertTrue(player.getResourceChest().isEmpty());
		
	}
	
	@Test 
	public void testCouncilPrivilegeEffect(){
		Effect effect = new CouncilPrivilegeEffect(2);
		effect.applyEffect(player);
		assertTrue(player.getCouncilPrivilege() == 2);
	}
	
//	@Test 
//	public void testTakeCardEffect(){
//		Effect effect = new TakeCardEffect(CardType.BUILDING, 100, new ResourceChest(0,0,0,0,0,0,0));
//		effect.applyEffect(player);
//		assertTrue(player.getDeckOfType(CardType.BUILDING).size() == 1);
//	}
	
	@Test
	public void HarvestBonusEffectTest(){
		Effect effect = new HarvestBonusEffect(34);
		effect.applyEffect(player);
		assertTrue(player.getBonuses().getHarvestVariation() == 34);
	}
	
	@Test
	public void ProductionBonusEffectTest(){
		Effect effect = new ProductionBonusEffect(34);
		effect.applyEffect(player);
		assertTrue(player.getBonuses().getProductionVariation() == 34);
	}
	
	@Test
	public void NofloorBonusEffectTest(){
		Effect effect = new NoFloorBonusEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isNoFloorBonus());
	}
	

	
	
}
