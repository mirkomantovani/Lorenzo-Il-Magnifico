package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class PlayerMoveCommand extends ClientToServerCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 667330673864025398L;

	private String move;
	
	public PlayerMoveCommand(String move){
		this.move = move;
	}
	
	public String getMove(){
		return move;
	}
	
	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		System.out.println("PlayerMoveCommand: you should not be here");
		
	}
	
	public void processCommand(ServerCommandHandler serverHandlerCommand,ClientHandler clientHandler) {
		serverHandlerCommand.applyCommand(this,clientHandler);
	}
	//ha senso? tanto ogni mossa prevede un cambiamento di stato e c'è gia un command per quello
	
}
