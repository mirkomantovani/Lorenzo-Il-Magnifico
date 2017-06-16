package it.polimi.ingsw.ps19.command.toclient;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;

/**
 * @author matteo
 * 
 * this class manage the fact that 
 * every time a CouncilPrivilegeEffect is activated, the server should ask the client about the content he 
 * wants to receive
 *
 */
public class AskPrivilegeChoiceCommand extends ServerToClientCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880502444669115290L;
	
	private int numberOfPrivilege;
	
	public AskPrivilegeChoiceCommand(int numberOfPrivilege){
		this.numberOfPrivilege = numberOfPrivilege;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

	public int getNumberOfPrivilege() {
		return numberOfPrivilege;
	}
	
	s

}
