package it.polimi.ingsw.ps19.command.toclient;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

/**
 * The Class AskPrivilegeChoiceCommand.
 *
 * @author matteo
 * 
 * this class manage the fact that 
 * every time a CouncilPrivilegeEffect is activated, the server should ask the client about the content he 
 * wants to receive
 */
public class AskPrivilegeChoiceCommand extends ServerToClientCommand{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7880502444669115290L;
	
	/** The number of privilege. */
	private int numberOfPrivilege;
	
	/** The privilege resources. */
	private ArrayList<ResourceChest> privilegeResources;
	
	/**
	 * Instantiates a new ask privilege choice command.
	 *
	 * @param numberOfPrivilege the number of privilege
	 * @param privilegeResources the privilege resources
	 */
	public AskPrivilegeChoiceCommand(int numberOfPrivilege, ArrayList<ResourceChest> privilegeResources){
		this.numberOfPrivilege = numberOfPrivilege;
		this.privilegeResources = privilegeResources;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand#processCommand(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

	/**
	 * Gets the number of privilege.
	 *
	 * @return the number of privilege
	 */
	public int getNumberOfPrivilege() {
		return numberOfPrivilege;
	}
	
	/**
	 * Gets the privilege resources.
	 *
	 * @return the privilege resources
	 */
	public ArrayList<ResourceChest> getPrivilegeResources(){
		return privilegeResources;
	}
	

}
