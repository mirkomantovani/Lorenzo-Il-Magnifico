package it.polimi.ingsw.ps19;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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

public class LeaderEffectTest {
	
	private Player player;
	
	@Before
	public void setUp(){
		
		player = new Player("matteo","blue");
		player.addFamilyMembers();
	}
	
	@Test
	public void cesareBorgiaEffectTest(){
		Effect effect = new CesareBorgiaEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isNoMilitaryPointsRequiredForTerritories());
	}
	
	@Test 
	public void federicoDaMontefeltroEffectTest(){
		Effect effect = new FedericoDaMontefeltroEffect();
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			assertTrue(player.getFamilyMembers().get(f.getColor()).getActionValueImposition() == 6);
		}
		
	}
	
	@Test
	public void filippoBrunelleschiEffectTest(){
		Effect effect = new FilippoBrunelleschiEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isDiscountOccupiedTower());
	}
	
	@Test
	public void lucreziaBorgiaEffectTest(){
		Effect effect = new LucreziaBorgiaEffect();
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			assertTrue(player.getFamilyMembers().get(f.getColor()).getActionValueVariation() == 2);
		}
	}
	
	@Test
	public void ludovicoIlMoroEffectTest(){
		Effect effect = new LudovicoIlMoroEffect();
		effect.applyEffect(player);
		for(FamilyMember f : player.getFamilyMembers().values()){
			
			assertTrue(player.getFamilyMembers().get(f.getColor()).getActionValueImposition() == 5);
		}
	}
	
	@Test
	public void picoDellaMirandolaEffectTest(){
		Effect effect = new PicoDellaMirandolaEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().getCardCostCoinDiscount() == 3);
	}
	
	@Test
	public void santaRitaEffectTest(){
		Effect effect = new SantaRitaEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().isDoubleResourcesFromCards());
	}
	
	@Test
	public void sigismondoMalatestaEffectTest(){
		Effect effect = new SigismondoMalatestaEffect();
		effect.applyEffect(player);
		assertTrue(player.getFamilyMember(Color.NEUTRAL).getActionValueVariation() == 3);
	}
	
	@Test
	public void sistoIVEffectTest(){
		Effect effect = new SistoIVEffect();
		effect.applyEffect(player);
		assertTrue(player.getBonuses().getChurchSupportBonus() == 5);
	}

}
