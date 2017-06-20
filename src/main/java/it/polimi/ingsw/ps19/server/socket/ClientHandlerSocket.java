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
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.toserver.RequestClosureCommand;
import it.polimi.ingsw.ps19.command.toserver.SendCredentialsCommand;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.ServerCommandHandler;
import it.polimi.ingsw.ps19.server.ServerInterface;
import it.polimi.ingsw.ps19.server.controller.MatchHandlerObserver;
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
	private CommandObserver commandHandler;
	private ServerInterface creator;
	private MatchHandlerObserver matchObserver;

	public ClientHandlerSocket(Socket socket, int number, ServerInterface serverStarter) {
		clientNumber = number;
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

	@Override
	public void sendCommand(ServerToClientCommand command) throws IOException {
		outSocket.writeObject(command);
		outSocket.flush();
		outSocket.reset();
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
				
				if(command instanceof ChosenLeaderCardCommand)
					System.out.println("clhandsock ho ricevuto chosenleadercard");
				

			} catch (ClassNotFoundException | IOException e) {
				close();
				break;
			}
			if (command instanceof RequestClosureCommand)
				closedByClient();
			
			//commands that can be sent in an asyncronous way from the clients and are always valid
			//and managed by the ServerCommandHandler
			else if(command instanceof SendCredentialsCommand || 
					command instanceof ChosenLeaderCardCommand ||
					command instanceof ChatMessageClientCommand)
				commandHandler.notifyNewCommand(command);
			//commands that need a check, if they are from the current player they are allowed
			else if (matchObserver != null && matchObserver.isAllowed(player)) {

				commandHandler.notifyNewCommand(command);
//				notifyCommand(command);

			} else if (!matchObserver.isAllowed(player) || matchObserver == null) {
				try {
					sendCommand(new InvalidCommand());
				} catch (IOException e) {
					closedByClient();
				}
			}
		}

	}



	@Override
	public void addObserver(MatchHandlerObserver matchObserver) {
		this.matchObserver = matchObserver;
	}

	@Override
	public void addCommandObserver(ServerCommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

}
