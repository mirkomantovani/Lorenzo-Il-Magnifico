package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.area.Board;

public class RefreshBoardCommand extends ServerToClientCommand {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062730484649123660L;
	
	private Board board;
	

	public RefreshBoardCommand(Board board) {
		super();
		this.board = board;
	}


	public Board getBoard() {
		return board;
	}


	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);

	}

}
