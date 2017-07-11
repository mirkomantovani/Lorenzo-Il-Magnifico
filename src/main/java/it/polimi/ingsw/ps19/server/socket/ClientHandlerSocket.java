package it.polimi.ingsw.ps19.server.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps19.command.toclient.CloseClientCommand;
import it.polimi.ingsw.ps19.command.toclient.InvalidCommand;
import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ChatMessageClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ChosenLeaderCardCommand;
import it.polimi.ingsw.ps19.command.toserver.ChurchSupportCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.ReconnectionAnswerCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SatanChoiceCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.Server;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;
import it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver;
import it.polimi.ingsw.ps19.server.observers.CommandObserver;

/**
 * The Class that handles the communication between client and server,
 * server-side, via socket
 *
 * @author Mirko
 */
public class ClientHandlerSocket extends ClientHandler {

	/** The socket. */
	private Socket socket;

	/** The in socket. */
	private ObjectInputStream inSocket;

	/** The out socket. */
	private ObjectOutputStream outSocket;

	/** The command handler. */
	private CommandObserver commandHandler;

	/** The creator. */
	private ServerInterface creator;

	/** The match observer. */
	private MatchHandlerObserver matchObserver;

	private Server serverListener;

	/**
	 * Instantiates a new client handler socket.
	 *
	 * @param socket
	 *            the socket
	 * @param number
	 *            the number
	 * @param serverStarter
	 *            the server starter
	 */
	public ClientHandlerSocket(Socket socket, int number, ServerInterface serverStarter) {
		this.socket = socket;
		creator = serverStarter;
		closed = false;
		try {
			inSocket = new ObjectInputStream(this.socket.getInputStream());
			outSocket = new ObjectOutputStream(this.socket.getOutputStream());
			outSocket.flush();
		} catch (IOException e) {
			closedByServer();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.ingsw.ps19.server.ClientHandler#sendCommand(it.polimi.ingsw.
	 * ps19.command.toclient.ServerToClientCommand)
	 */
	@Override
	public void sendCommand(ServerToClientCommand command) throws IOException {
		outSocket.writeUnshared(command);
		outSocket.flush();
		outSocket.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#closedByServer()
	 */
	@Override
	public void closedByServer() {
		try {
			outSocket.writeUnshared(new CloseClientCommand());
		} catch (IOException e) {
		}
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#closedByClient()
	 */
	@Override
	public void closedByClient() {
		if (matchObserver != null)
			matchObserver.removeClient(this);
		else
			creator.removeClient(this);
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.polimi.ingsw.ps19.server.ClientHandler#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return false;
	}

	/**
	 * Close.
	 */
	public void close() {
		if (!closed) {
			try {
				closed = true;
				socket.close();
				inSocket.close();
				outSocket.close();
			} catch (Exception e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ClientToServerCommand command;

		while (true) {
			command = null;
			try {
				command = (ClientToServerCommand) inSocket.readObject();
				
				if (command instanceof ReconnectionAnswerCommand) {
					serverListener.notifyReconnectionAnswer((ReconnectionAnswerCommand) command, this);
				}


			} catch (ClassNotFoundException | IOException e) {
				close();
				break;
			}
			if (command instanceof RequestClosureCommand)
				closedByClient();

			// commands that can be sent in an asyncronous way from the clients
			// and are always valid
			// and managed by the ServerCommandHandler
			if (command instanceof SatanChoiceCommand)
			if (commandHandler == null) {
			}

			else if (command instanceof SendCredentialsCommand || command instanceof ChosenLeaderCardCommand
					|| command instanceof ChatMessageClientCommand || command instanceof ChurchSupportCommand
					|| command instanceof SatanChoiceCommand) {

				commandHandler.notifyNewCommand(command);
			} 
			// commands that need a check, if they are from the current player
			// they are allowed
			else if (matchObserver != null && matchObserver.isAllowed(player)) {

				commandHandler.notifyNewCommand(command);
				// notifyCommand(command);

			} else if (!matchObserver.isAllowed(player) || matchObserver == null) {
				try {
					sendCommand(new InvalidCommand());
				} catch (IOException e) {
					closedByClient();
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.ingsw.ps19.server.ClientHandler#addObserver(it.polimi.ingsw.
	 * ps19.server.controller.MatchHandlerObserver)
	 */
	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		this.matchObserver = matchObserver;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.polimi.ingsw.ps19.server.ClientHandler#addCommandObserver(it.polimi.
	 * ingsw.ps19.server.ServerCommandHandler)
	 */
	@Override
	public void addCommandObserver(ServerCommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@Override
	public void addCommandObserver(Server server) {
		this.serverListener=server;
	}

	

}
