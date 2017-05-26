package it.polimi.ingsw.ps19;

/**
 * @author matteo
 *
 */
public class FamilyMember {
	
	private Dice dice; 
	private Player player;
	private int actionValueVariation;
	
	public void setActionValueVariation(int actionValueVariation) {
		this.actionValueVariation = actionValueVariation;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public FamilyMember(Dice d){
		
		this.dice = d;
	}	
	
	public Dice getDice(){
		
		return this.dice;
	}
	
	public int getActionValue(){
		return this.dice.getUpperFaceValue()+actionValueVariation;
	}
	
	public void addActionValueVariation(int value){
		this.actionValueVariation = this.actionValueVariation + value;
	}
	
	

}



