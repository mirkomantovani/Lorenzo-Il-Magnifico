package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.area.Board;

/**
 * The Class InitializeTurnCommand.
 * The message to notify the client that a turn has been initialized
 */
/**
 * @author matteo
 *
 */
public class InitializeTurnCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1569847639899896757L;
	
	
	/** The period. */
	private Period period;
	
	/** The turn. */
	private int turn;

	
	/**
	 * Instantiates a new initialize turn command.
	 *
	 * @param period the period
	 * @param turn the turn
	 */
	public InitializeTurnCommand(Period period, int turn) {
	
		this.period = period;
		this.turn = turn;
	}
	
	
	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public Period getPeriod() {
		return period;
	}


	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
