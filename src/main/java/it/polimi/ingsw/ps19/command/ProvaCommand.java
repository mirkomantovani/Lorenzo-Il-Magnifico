package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class ProvaCommand extends ClientToServerCommand {

	private static final long serialVersionUID = 3837601158473260534L;
	
	public ProvaCommand(Servant servant) {
		super();
		this.servant = servant;
	}	
	
	Servant servant;
	
	public Servant getServant() {
		return servant;
	}

//	@Override
//	public void processCommand(ClientCommandHandler clientCommandHandler) {
//
//	}
public void processCommand(ServerCommandHandler serverHandlerCommand) {
	// TODO Auto-generated method stub
	
}


	
	

}
