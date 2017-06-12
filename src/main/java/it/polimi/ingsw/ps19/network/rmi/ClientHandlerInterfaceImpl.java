package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.CloseClientCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.MatchHandlerObserver;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.rmi.ServerRMIListener;

public class ClientHandlerInterfaceImpl extends ClientHandler implements ClientHandlerInterface{

	ServerRMIListener server;
	ClientInterface client;
	MatchHandlerObserver matchHandlerObserver;
	ServerCommandHandler serverCommandHandler;
	
	
	public ClientHandlerInterfaceImpl(ServerRMIListener server, int code) {
		this.server = server;
		this.code = code;
		this.code = hashCode();
		this.closed = false;
		this.matchHandlerObserver = null;
	}
	
	@Override
	public void notifyServer(ClientToServerCommand command) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closedByServer() throws RemoteException {
		if (!closed) {
			try {
				client.notifyClient(new CloseClientCommand());   //this command still has to be implemented
			} catch (RemoteException e) {
				closedByClient();
			}
		}
		closed = true;		
	}

	@Override
	public void addClient(int port) throws RemoteException {
		
		try {
			Registry registry = LocateRegistry.getRegistry(port);
			client = (ClientInterface) registry.lookup("Client");
			server.addClient(this);
		} catch (NotBoundException e) {
			System.err.println("Failed to join a game");
		}
		
	}

	@Override
	public void sendCommand(ServerToClientCommand command) throws RemoteException {
		client.notifyClient(command);
	}

	@Override
	public void closedByClient() {
		if (!closed) {
			if (this.matchHandlerObserver != null)
				this.matchHandlerObserver.removeClient(this);
			else
				server.removeWaitingClient(this);
		}
		closed = true;
		
	}

	@Override
	public void run() {
//		System.out.println("ClientHandleInterfaceImpl is running");
	}


	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		this.matchHandlerObserver=matchObserver;
	}

	@Override
	public void addCommandHandler(ServerCommandHandler commandHandler) {
		this.serverCommandHandler = commandHandler;
	}

}
