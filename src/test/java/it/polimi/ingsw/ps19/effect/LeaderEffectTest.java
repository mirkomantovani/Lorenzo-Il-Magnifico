package it.polimi.ingsw.ps19.effect;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;
import it.polimi.ingsw.ps19.model.effect.leader.CesareBorgiaEffect;
import it.polimi.ingsw.ps19.model.effect.leader.FedericoDaMontefeltroEffect;
import it.polimi.ingsw.ps19.model.effect.leader.FilippoBrunelleschiEffect;
import it.polimi.ingsw.ps19.model.effect.leader.LucreziaBorgiaEffect;
import it.polimi.ingsw.ps19.model.effect.leader.LudovicoIlMoroEffect;
import it.polimi.ingsw.ps19.model.effect.leader.PicoDellaMirandolaEffect;
import it.polimi.ingsw.ps19.model.effect.leader.SantaRitaEffect;
import it.polimi.ingsw.ps19.model.effect.leader.SigismondoMalatestaEffect;
import it.polimi.ingsw.ps19.model.effect.leader.SistoIVEffect;

/**
 * The Class LeaderEffectTest.
 */
/**
 * @author matteo
 *
 */
public class LeaderEffectTest {
	
	/** The player. */
	private Player player;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp(){
		
		player = new Player("matteo","blue");
		player.addFamilyMembers();
	}
	
	/**
	 * Cesare borgia effect test.
	 */
	@Test
	public void cesareBorgiaEffectTest(){
		Effect effect = new CesareBorgiaEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isNoMilitaryPointsRequiredForTerritories());
	}
	
	/**
	 * Federico da montefeltro effect test.
	 */
	@Test 
	public void federicoDaMontefeltroEffectTest(){
		Effect effect = new FedericoDaMontefeltroEffect();
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			assertEquals(player.getFamilyMembers().get(f.getColor()).getActionValueImposition(), 6);
		}
		
	}
	
	/**
	 * Filippo brunelleschi effect test.
	 */
	@Test
	public void filippoBrunelleschiEffectTest(){
		Effect effect = new FilippoBrunelleschiEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isDiscountOccupiedTower());
	}
	
	/**
	 * Lucrezia borgia effect test.
	 */
	@Test
	public void lucreziaBorgiaEffectTest(){
		Effect effect = new LucreziaBorgiaEffect();
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			assertEquals(player.getFamilyMembers().get(f.getColor()).getActionValueVariation(), 2);
		}
	}
	
	/**
	 * Ludovico il moro effect test.
	 */
	@Test
	public void ludovicoIlMoroEffectTest(){
		Effect effect = new LudovicoIlMoroEffect();
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			
			assertEquals(player.getFamilyMembers().get(f.getColor()).getActionValueImposition(), 5);
		}
	}
	
	/**
	 * Pico della mirandola effect test.
	 */
	@Test
	public void picoDellaMirandolaEffectTest(){
		Effect effect = new PicoDellaMirandolaEffect();
		effect.applyEffect(player);
		assertEquals(player.getBonuses().getCardCostCoinDiscount(), 3);
	}
	
	/**
	 * Santa rita effect test.
	 */
	@Test
	public void santaRitaEffectTest(){
		Effect effect = new SantaRitaEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isDoubleResourcesFromCards());
	}
	
	/**
	 * Sigismondo malatesta effect test.
	 */
	@Test
	public void sigismondoMalatestaEffectTest(){
		Effect effect = new SigismondoMalatestaEffect();
		effect.applyEffect(player);
		assertEquals(player.getFamilyMember(Color.NEUTRAL).getActionValueVariation(), 3);
	}
	
	/**
	 * Sisto IV effect test.
	 */
	@Test
	public void sistoIVEffectTest(){
		Effect effect = new SistoIVEffect();
		effect.applyEffect(player);
		assertEquals(player.getBonuses().getChurchSupportBonus(), 5);
	}

}
