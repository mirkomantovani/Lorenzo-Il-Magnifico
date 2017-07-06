package it.polimi.ingsw.ps19;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * The Class FamilyMember.
 *
 * @author matteo
 */
public class FamilyMember implements Serializable  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4026210740412805279L;

/** The dice. */
//	private Dice dice;
	private Map<Dice,Integer> dice;
	
	/** The dice color. */
	private Color diceColor;
	
	/** The player. */
	private Player player;
	
	/** The action value variation. */
	private int actionValueVariation;
	
	/** The action value imposition. */
	private int actionValueImposition;

	/**
	 * Instantiates a new family member.
	 *
	 * @param d the d
	 * @param player the player
	 */
	public FamilyMember(Dice d,Player player) {
		dice=new EnumMap<Dice,Integer>(Dice.class);
		this.diceColor=d.getColor();
		this.player=player;
		this.dice.put(d, d.getUpperFaceValue());
		this.actionValueVariation=0;
	}

	/**
	 * Gets the action value imposition.
	 *
	 * @return the action value imposition
	 */
	public int getActionValueImposition() {
		return actionValueImposition;
	}
	
	/**
	 * Refresh dice value.
	 */
	public void refreshDiceValue(){
		this.dice.put(getDice(),getDice().getUpperFaceValue());
	}

	/**
	 * Sets the action value imposition.
	 *
	 * @param actionValueImposition the new action value imposition
	 */
	public void setActionValueImposition(int actionValueImposition) {
		this.actionValueImposition = actionValueImposition;
	}

	/**
	 * Sets the action value variation.
	 *
	 * @param actionValueVariation the new action value variation
	 */
	public void setActionValueVariation(int actionValueVariation) {
		this.actionValueVariation = actionValueVariation;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Gets the dice.
	 *
	 * @return the dice
	 */
	public Dice getDice() {

		return (Dice)(dice.keySet().toArray()[0]);
//		return (Dice)(dice.values().toArray()[0]);
		}

	/**
	 * Gets the action value.
	 *
	 * @return the action value
	 */
	public int getActionValue() {
		if(actionValueImposition == 0)
			return dice.get(getDice())+ actionValueVariation;
		else 
			return actionValueImposition;
	}

	/**
	 * Adds the action value variation.
	 *
	 * @param value the value
	 */
	public void addActionValueVariation(int value) {
		this.actionValueVariation = this.actionValueVariation + value;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor(){
		return this.diceColor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

	/**
	 * Gets the action value variation.
	 *
	 * @return the action value variation
	 */
	public int getActionValueVariation() {
		// TODO Auto-generated method stub
		return actionValueVariation;
	}
	
	

}
