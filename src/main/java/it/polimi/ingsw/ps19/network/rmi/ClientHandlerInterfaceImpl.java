package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.ps19.command.toclient.CloseClientCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ChatMessageClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChurchSupportCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver;
import it.polimi.ingsw.ps19.server.observers.CommandObserver;
import it.polimi.ingsw.ps19.server.rmi.ServerRMIListener;

public class ClientHandlerInterfaceImpl extends ClientHandler implements ClientHandlerInterface {

	private ServerRMIListener server;
	private ClientInterface client;
	private MatchHandlerObserver matchHandlerObserver;
	private ServerCommandHandler serverCommandHandler;

	public ClientHandlerInterfaceImpl(ServerRMIListener server, int id) {
		this.server = server;
		this.code = id;
		this.code = hashCode();
		this.closed = false;
		this.matchHandlerObserver = null;
	}

	/**
	 * We distinguished commands that can be sent in an asyncronous way from the
	 * clients and are always valid and managed by the ServerCommandHandler, and
	 * the ones which don't
	 * 
	 * @author Mirko
	 * 
	 */
	@Override
	public void notifyServer(ClientToServerCommand command) throws RemoteException {
		if (command instanceof RequestClosureCommand)
			closedByClient();

		else if (command instanceof SendCredentialsCommand || command instanceof ChosenLeaderCardCommand
				|| command instanceof ChatMessageClientCommand || command instanceof ChurchSupportCommand)
			serverCommandHandler.notifyNewCommand(command);
		else if (matchHandlerObserver != null && matchHandlerObserver.isAllowed(player)) {

			serverCommandHandler.notifyNewCommand(command);
		}
	}

	@Override
	public void closedByServer() throws RemoteException {
		if (!closed) {
			try {
				client.notifyClient(new CloseClientCommand()); // this command
																// still has to
																// be
																// implemented
			} catch (RemoteException e) {
				closedByClient();
			}
		}
		closed = true;
	}

	@Override
	public void addClient(int port) throws RemoteException {
		System.out.println("In ClientHandlerInterfaceImpl addClient function");

		try {
//			System.out.println("Starting to add the client");
			Registry registry = LocateRegistry.getRegistry(port);
//			System.out.println("Accessed the registry at port: " + NetworkConstants.RMICLIENTPORT);
			client = (ClientInterface) registry.lookup("Client");
//			System.out.println("Read from the registry");
			server.addClient(this);
//			System.out.println("Client has been successfully enqueued"); // Non
																			// sono
																			// proprio
																			// così
																			// sicuro
																			// che
																			// debba
																			// notificare
																			// il
																			// server
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
		System.out.println("ClientHandleInterfaceImpl is running");
	}

	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		this.matchHandlerObserver = matchObserver;
	}

	@Override
	public void addCommandObserver(ServerCommandHandler commandHandler) {
		this.serverCommandHandler = commandHandler;
	}

}
