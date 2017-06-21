package it.polimi.ingsw.ps19;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author matteo
 *
 */
public class FamilyMember implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026210740412805279L;
//	private Dice dice;
	private Map<Dice,Integer> dice;
	private Color diceColor;
	private Player player;
	private int actionValueVariation;
	private int actionValueImposition;

	public FamilyMember(Dice d,Player player) {
		dice=new EnumMap<Dice,Integer>(Dice.class);
		this.diceColor=d.getColor();
		this.player=player;
		this.dice.put(d, d.getUpperFaceValue());
		this.actionValueVariation=0;
	}

	public int getActionValueImposition() {
		return actionValueImposition;
	}
	
	public void refreshDiceValue(){
		this.dice.put(getDice(),getDice().getUpperFaceValue());
	}

	public void setActionValueImposition(int actionValueImposition) {
		this.actionValueImposition = actionValueImposition;
	}

	public void setActionValueVariation(int actionValueVariation) {
		this.actionValueVariation = actionValueVariation;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Dice getDice() {

		return (Dice)(dice.keySet().toArray()[0]);
//		return (Dice)(dice.values().toArray()[0]);
		}

	public int getActionValue() {
		if(actionValueImposition == 0)
			return dice.get(getDice())+ actionValueVariation;
		else 
			return actionValueImposition;
	}

	public void addActionValueVariation(int value) {
		this.actionValueVariation = this.actionValueVariation + value;
	}
	
	public Color getColor(){
		return this.diceColor;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append(this.getColor().toString());
		builder.append(" family member, of the ");
		builder.append(player.getColor());
		builder.append(" player, with an action value of ");
		builder.append(this.getActionValue());
		builder.append(" ");
		return builder.toString();
	}
	
	

}
