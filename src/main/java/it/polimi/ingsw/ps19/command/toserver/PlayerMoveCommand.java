package it.polimi.ingsw.ps19.command.toserver;

import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

/**
 * The Class PlayerMoveCommand.
 * 
 * The class to notify the server about the client move
 */
public class PlayerMoveCommand extends ClientToServerCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 667330673864025398L;

	/** The move. */
	private String move;
	
	/**
	 * Instantiates a new player move command.
	 *
	 * @param move the move
	 */
	public PlayerMoveCommand(String move){
		this.move = move;
	}
	
	/**
	 * Gets the move.
	 *
	 * @return the move
	 */
	public String getMove(){
		return move;
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand#processCommand(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		
	}
	
	/**
	 * Process command.
	 *
	 * @param serverHandlerCommand the server handler command
	 * @param clientHandler the client handler
	 */
	public void processCommand(ServerCommandHandler serverHandlerCommand,ClientHandler clientHandler) {
		serverHandlerCommand.applyCommand(this,clientHandler);
	}
	//ha senso? tanto ogni mossa prevede un cambiamento di stato e c'è gia un command per quello
	
}
