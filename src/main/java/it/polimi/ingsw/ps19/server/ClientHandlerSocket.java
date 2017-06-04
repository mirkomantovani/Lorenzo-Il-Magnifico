package it.polimi.ingsw.ps19.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public class ClientHandlerSocket extends ClientHandler{

	
	private Socket socket;
	private ObjectInputStream inSocket;
	private ObjectOutputStream outSocket;
	
	public ClientHandlerSocket(Socket socket) {
		
		this.socket = socket;
		System.out.println("ciao");
		try {
//			inSocket = new ObjectInputStream(this.socket.getInputStream());
			outSocket = new ObjectOutputStream(this.socket.getOutputStream());
			System.out.println("nel try");
			outSocket.flush();
		} catch (IOException e) {
			e.printStackTrace();
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
		
	}

	@Override
	public void closedByClient() {
		
	}

	@Override
	public boolean isClosed() {
		return false;
	}
	
//	private void close() {
////		if (!closed) {
////			try {
////				closed = true;
//		try{
//				this.socket.close();
//				this.inSocket.close();
//				this.outSocket.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}


	@Override
	public void Close() {
		try{
			this.socket.close();
			this.inSocket.close();
			this.outSocket.close();
	}catch(Exception e){
		e.printStackTrace();
	}		
	}

}
