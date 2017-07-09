package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.NotBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand;
import it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.client.ServerToClientCommandObserver;
import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.network.NetworkInterface;

/**
 * This class connects the client and the server using RMI. Implementing a direct ClientInterfaceImpl
 * class would have made the close() method a little difficult so I merged the clientImplementation
 * with the network interface
 * 
 * @author Jimmy
 *
 */
public class ClientRMIInterface implements ClientInterface, NetworkInterface{

	/** The name. */
	private String clientHandlerName;
	
	/** The second name. */
	private String clientName;
	
	/** The client handler registry. */
	private Registry clientHandlerRegistry;
	
	/** The client registry. */
	private Registry clientRegistry;
	
	/** The client handler. */
	private ClientHandlerInterface clientHandler;
	
	/** The client. */
	private ClientInterface client; 
	
	/** The observer. */
	private ServerToClientCommandObserver observer;
	
	/**
	 * Instantiates a new client RMI interface.
	 */
	public ClientRMIInterface(){
		this.clientHandlerName = "ClientHandler";
		this.clientName = "Client";
		this.client = this;          
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#connect()
	 */
	@Override
	public void connect() throws Exception {
		
		
		clientHandlerRegistry = LocateRegistry
				.getRegistry(NetworkConstants.RMICLIENTHANDLERPORT);
//		System.out.println("Registry accessed");
		clientHandler = (ClientHandlerInterface) clientHandlerRegistry.lookup(clientHandlerName); //Va a recuperare il clientHandler
//		System.out.println("Got the remote ClientHandler");
		ClientInterface clientStub = (ClientInterface) UnicastRemoteObject		//Esporta l'oggetto
				.exportObject(client, 0);    //could have passed directly this
//		System.out.println("Got the remote reference");

		try {
			clientRegistry = LocateRegistry.getRegistry(NetworkConstants.RMICLIENTPORT);
//			System.out.println("Accessed client registry");
			clientRegistry.rebind(clientName, clientStub);
//			System.out.println("Added this in the registry");
		} catch (Exception e) {              //In questo caso non ci sarà il registro
//			System.out.println("There is no registry at port: "+ NetworkConstants.RMICLIENTPORT + ", so i'm creating one");
			clientRegistry = LocateRegistry.createRegistry(NetworkConstants.RMICLIENTPORT);  //Allora crea il registro su cui inserire il riferimento
			clientRegistry.bind(clientName, clientStub);
//			System.out.println("Created and client stub bound");
		}
		// the client handler will add the client to the server
//		System.out.println("Preparing for the add in the registry at port: " + NetworkConstants.RMICLIENTPORT);
		clientHandler.addClient(NetworkConstants.RMICLIENTPORT);  //QUI C'E' L'ERRORE
//		System.out.println("Horaay, client seems to be added");
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#sendCommand(it.polimi.ingsw.ps19.command.toserver.ClientToServerCommand)
	 */
	@Override
	public void sendCommand(ClientToServerCommand command) throws Exception {
		clientHandler.notifyServer(command);
	}


	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.rmi.ClientInterface#notifyClient(it.polimi.ingsw.ps19.command.toclient.ServerToClientCommand)
	 */
	//Questo metodo è implementato sia da clientInterface che da networkInterface chiedere info
	@Override
	public void notifyClient(ServerToClientCommand command){
		this.observer.notifyNewCommand(command);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.rmi.ClientInterface#closeConnection()
	 */
	@Override
	public void closeConnection() {
		try {
			//In order to close the connection we must remove the client from the registry
			//and free it.
			clientRegistry.unbind(clientName);
			clientRegistry = null;

		} catch (NullPointerException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.network.NetworkInterface#addCommandObserver(it.polimi.ingsw.ps19.client.ClientCommandHandler)
	 */
	@Override
	public void addCommandObserver(ClientCommandHandler handler) {
		this.observer = handler;		
	}
	
	

}
