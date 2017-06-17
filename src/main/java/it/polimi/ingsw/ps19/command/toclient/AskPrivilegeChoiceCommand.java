package it.polimi.ingsw.ps19.command.toclient;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;

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
	private ArrayList<ResourceChest> privilegeResources;
	
	public AskPrivilegeChoiceCommand(int numberOfPrivilege, ArrayList<ResourceChest> privilegeResources){
		this.numberOfPrivilege = numberOfPrivilege;
		this.privilegeResources = privilegeResources;
	}

	@Override
	public void processCommand(ClientCommandHandler clientCommandHandler) {
		clientCommandHandler.applyCommand(this);
		
	}

	public int getNumberOfPrivilege() {
		return numberOfPrivilege;
	}
	
	public ArrayList<ResourceChest> getPrivilegeResources(){
		return privilegeResources;
	}
	

}
