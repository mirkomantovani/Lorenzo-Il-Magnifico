package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.MatchHandlerObserver;
import it.polimi.ingsw.ps19.server.rmi.ServerRMIListener;

public class ClientHandlerInterfaceImpl extends ClientHandler implements ClientHandlerInterface{

	ServerRMIListener server;
	ClientInterface client;
	
	public ClientHandlerInterfaceImpl(ServerRMIListener server, int code) {
		this.server = server;
		closed = false;
	}
	
	@Override
	public void notifyServer(ClientToServerCommand command) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closedByServer() throws RemoteException {
		// TODO Auto-generated method stub		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	



	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		// TODO Auto-generated method stub
		
	}

}
