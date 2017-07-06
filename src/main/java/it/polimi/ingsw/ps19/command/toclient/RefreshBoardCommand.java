package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.area.Board;

/**
 * The Class RefreshBoardCommand.
 */
public class RefreshBoardCommand extends ServerToClientCommand {

	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5062730484649123660L;
	
	/** The board. */
	private Board board;
	

	/**
	 * Instantiates a new refresh board command.
	 *
	 * @param board the board
	 */
	public RefreshBoardCommand(Board board) {
		super();
		this.board = board;
	}


	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);

	}

}
