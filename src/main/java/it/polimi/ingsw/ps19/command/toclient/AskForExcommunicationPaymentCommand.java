package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class AskForExcommunicationPaymentCommand.
 *
 * @author matteo 
 * This class represent the message from server to client to ask
 *         if the player wants to pay to prevent excommunication.
 */
public class AskForExcommunicationPaymentCommand extends ServerToClientCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2282290893400038423L;

	/** The excommunication effect. */
	private String excommunicationEffect;

	/**
	 * Instantiates a new ask for excommunication payment command.
	 *
	 * @param excommunicationEffect the excommunication effect
	 */
	public AskForExcommunicationPaymentCommand(String excommunicationEffect) {
		this.excommunicationEffect = excommunicationEffect;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
	}

	/**
	 * Gets the excommunication effect.
	 *
	 * @return the excommunication effect
	 */
	public String getExcommunicationEffect() {
		return excommunicationEffect;
	}

}