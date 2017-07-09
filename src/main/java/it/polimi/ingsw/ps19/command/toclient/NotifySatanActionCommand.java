package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;

public class NotifySatanActionCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1233194644751446601L;
	
	private String color;

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}
	
	public NotifySatanActionCommand(String color){
		color = this.color;
	}
	
	public String getColor(){
		return this.color;
	}
	

}
