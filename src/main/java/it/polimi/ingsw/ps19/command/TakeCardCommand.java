package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.model.card.Card;

public class TakeCardCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549395717779803669L;
	
	private Card card;
	
	public TakeCardCommand(Card card){
		this.card = card;
	}

	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
		
	}

	
	

}
