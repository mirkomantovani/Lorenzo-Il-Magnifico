package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * The Class ChatMessageServerCommand.
 *
 * @author Mirko
 */
public class ChatMessageServerCommand extends ServerToClientCommand {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6099362758469332623L;
	
	/** The text. */
	private String text;
	
	/**
	 * Instantiates a new chat message server command.
	 *
	 * @param text the text
	 */
	public ChatMessageServerCommand(String text){
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);

	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}
