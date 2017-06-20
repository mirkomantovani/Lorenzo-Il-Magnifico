package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * This class represent the message from server to client to ask if the player 
 * wants to pay to prevent excommunication. 
 *
 */
public class askForExcommunicationPaymentCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3914108980493530597L;
	
	private String excommunicationEffect;
	
	public askForExcommunicationPaymentCommand(String excommunicationEffect){
		this.excommunicationEffect = excommunicationEffect;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

	public String getExcommunicationEffect() {
		return excommunicationEffect;
	}
	
	

}
