package it.polimi.ingsw.ps19.server;

import java.io.IOException;
import java.net.Socket;

import it.polimi.ingsw.ps19.command.ProvaCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;
import it.polimi.ingsw.ps19.model.resource.Servant;

public class Main {

	private static ClientHandler clientHandler;
	private static int port=2325;
	private static String indirizzo="127.0.0.1";

	public static void main(String[] args) throws IOException {
		/*
		Socket socket=new Socket(indirizzo, port);
//		clientHandler=new ClientHandlerSocket(socket);
		//doesn't work anymore
		System.out.println("qui");
		Servant s=new Servant(3);
		
//		ServerToClientCommand command=new ProvaCommand(s);
		System.out.println("provo a inviare oggetto");
//		clientHandler.sendCommand(command);
		System.out.println("Sono il client e ho inviato l'oggetto "+s.toString());
		*/
		
		Server server = Server.getInstance();
		server.run();
	}

}
