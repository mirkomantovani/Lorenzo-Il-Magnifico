package it.polimi.ingsw.ps19.server.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.CloseClientCommand;
import it.polimi.ingsw.ps19.command.InvalidCommand;
import it.polimi.ingsw.ps19.command.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.MatchHandlerObserver;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;
import it.polimi.ingsw.ps19.server.observers.CommandObserver;

/**
 * @author Mirko
 *
 */
public class ClientHandlerSocket extends ClientHandler {

	private Socket socket;
	private ObjectInputStream inSocket;
	private ObjectOutputStream outSocket;
	private int clientNumber;
//	private ServerCommandHandler commandHandler;
	private CommandObserver commandHandler;
	private ServerInterface creator;
	private MatchHandlerObserver matchObserver;

	public ClientHandlerSocket(Socket socket, int number, ServerInterface serverStarter) {
		// allower = null;
		clientNumber = number;
		this.socket = socket;
		// code = hashCode();
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

	@Override
	public void sendCommand(ServerToClientCommand command) throws IOException {
		outSocket.writeObject(command);
		outSocket.flush();
		outSocket.close();
	}

	@Override
	public void closedByServer() {
		try {
			outSocket.writeObject(new CloseClientCommand());
		} catch (IOException e) {
		}
		close();
	}

	@Override
	public void closedByClient() {
		if (matchObserver != null)
			matchObserver.removeClient(this);
		else
			creator.removeClient(this);
		close();
	}

	@Override
	public boolean isClosed() {
		return false;
	}

	
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
	
	@Override
	public void run() {
		ClientToServerCommand command;

		while (true) {
			command = null;
			try {
				command = (ClientToServerCommand) inSocket.readObject();

			} catch (ClassNotFoundException | IOException e) {
				close();
				break;
			}
			if (command instanceof RequestClosureCommand)
				closedByClient();
			else if (matchObserver != null && matchObserver.isAllowed(command, player)) {
//				processCommand(command);  //notify al controller (server command handler
				commandHandler.notifyNewCommand(command);
			} else if (!matchObserver.isAllowed(command, player) || matchObserver == null) {
				try {
					sendCommand(new InvalidCommand());
				} catch (IOException e) {
					closedByClient();
				}
			}
		}

	}

//	private void processCommand(ClientToServerCommand command) {
//		command.processCommand(commandHandler);
//	}

	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		this.matchObserver=matchObserver;
	}
	

	@Override
	public void addCommandObserver(ServerCommandHandler commandHandler) {
		this.commandHandler = commandHandler;		
	}

}
