package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.area.Board;

public class InitializeTurnCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1569847639899896757L;
	
	
	private Period period;
	
	private int turn;

	
	public InitializeTurnCommand(Period period, int turn) {
	
		this.period = period;
		this.turn = turn;
	}
	
	
	public Period getPeriod() {
		return period;
	}


	public int getTurn() {
		return turn;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

}
