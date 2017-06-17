package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.Period;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.area.Board;

public class InitializeTurnCommand extends ServerToClientCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1569847639899896757L;
	
	private Board board;
	
	private Period period;
	
	private int turn;

	
	public InitializeTurnCommand(Board board, Period period, int turn) {
		this.board = board;
		this.period = period;
		this.turn = turn;
	}
	
	
	public Board getBoard() {
		return board;
	}

	public Period getPeriod() {
		return period;
	}


	public int getTurn() {
		return turn;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		// TODO Auto-generated method stub

	}

}
