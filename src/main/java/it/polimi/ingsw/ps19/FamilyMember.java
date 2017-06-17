package it.polimi.ingsw.ps19;

import java.io.Serializable;

/**
 * @author matteo
 *
 */
public class FamilyMember implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026210740412805279L;
	private Dice dice;
	private Player player;
	private int actionValueVariation;
	private int actionValueImposition;

	public FamilyMember(Dice d,Player player) {
		this.player=player;
		this.dice = d;
		this.actionValueVariation=0;
	}

	public int getActionValueImposition() {
		return actionValueImposition;
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

		return this.dice;
	}

	public int getActionValue() {
		return this.dice.getUpperFaceValue() + actionValueVariation;
	}

	public void addActionValueVariation(int value) {
		this.actionValueVariation = this.actionValueVariation + value;
	}

}
