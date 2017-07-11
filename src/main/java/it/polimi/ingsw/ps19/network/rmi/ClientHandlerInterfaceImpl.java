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
import it.polimi.ingsw.ps19.command.toserver.ReconnectionAnswerCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.Server;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver;
import it.polimi.ingsw.ps19.server.rmi.ServerRMIListener;

/**
 * The Class ClientHandlerInterfaceImpl.
 */
public class ClientHandlerInterfaceImpl extends ClientHandler implements ClientHandlerInterface {

	/** The server. */
	private ServerRMIListener server;
	
	/** The client. */
	private ClientInterface client;
	
	/** The match handler observer. */
	private MatchHandlerObserver matchHandlerObserver;
	
	/** The server command handler. */
	private ServerCommandHandler serverCommandHandler;
	
	private Server serverListener;

	/**
	 * Instantiates a new client handler interface impl.
	 *
	 * @param server the server
	 * @param id the id
	 */
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
	 * the ones which don't.
	 *
	 * @author Mirko
	 * @param command the command
	 * @throws RemoteException the remote exception
	 */
	@Override
	public void notifyServer(ClientToServerCommand command) throws RemoteException {
		if (command instanceof RequestClosureCommand)
			closedByClient();
		else if (command instanceof ReconnectionAnswerCommand) {
			serverListener.notifyReconnectionAnswer((ReconnectionAnswerCommand) command, this);
		}

		else if (command instanceof SendCredentialsCommand || command instanceof ChosenLeaderCardCommand
				|| command instanceof ChatMessageClientCommand || command instanceof ChurchSupportCommand)
			serverCommandHandler.notifyNewCommand(command);
		else if (matchHandlerObserver != null && matchHandlerObserver.isAllowed(player)) {

			serverCommandHandler.notifyNewCommand(command);
		}
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#closedByServer()
	 */
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

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.rmi.ClientHandlerInterface#addClient(int)
	 */
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

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#sendCommand(it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand)
	 */
	@Override
	public void sendCommand(ServerToClientCommand command) throws RemoteException {
		client.notifyClient(command);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#closedByClient()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("ClientHandleInterfaceImpl is running");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#addObserver(it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver)
	 */
	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		this.matchHandlerObserver = matchObserver;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#addCommandObserver(it.polimi.ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void addCommandObserver(ServerCommandHandler commandHandler) {
		this.serverCommandHandler = commandHandler;
	}

	@Override
	public void addCommandObserver(Server server) {
		this.serverListener=server;
	}

}
