package it.polimi.ingsw.ps19.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import it.polimi.ingsw.ps19.command.ProvaCommand;
import it.polimi.ingsw.ps19.model.resource.Servant;

public class MainServer {

	private ClientHandler clientHandler;
	private static ObjectInputStream inSocket;
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSock= new ServerSocket(2325);
		ProvaCommand p=null;
//		InputStream is=new InputStream()
//		inSocket = new ObjectInputStream();
		 ObjectInputStream inSocket;
		Socket clientSocket=serverSock.accept();
			
		System.out.println("client connesso");
		
		inSocket = new ObjectInputStream(clientSocket.getInputStream());
		try {
			p=(ProvaCommand)inSocket.readObject();
			inSocket.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sono il server e ho ricevuto l'oggetto:"+p.toString());
		Servant s=p.getServant();
		s.add(3);
		System.out.println(s.toString());
		
	}

}
