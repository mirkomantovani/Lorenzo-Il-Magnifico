package it.polimi.ingsw.ps19.network.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps19.command.ClientToServerCommand;
import it.polimi.ingsw.ps19.command.ServerToClientCommand;
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
public class RMIInterface implements ClientInterface, NetworkInterface{

	private String name;
	private String secondName;
	private Registry clientHandlerRegistry;
	private Registry clientRegistry;
	private ClientHandlerInterface clientHandler;
	private ClientInterface client;
	
	public RMIInterface(){
		this.name = "ClientHandler";
		this.secondName = "Client";
		this.client = this;
	}

	@Override
	public void connect() throws Exception {
		
		
		clientHandlerRegistry = LocateRegistry
				.getRegistry(NetworkConstants.RMICLIENTHANDLERPORT);
		clientHandler = (ClientHandlerInterface) clientHandlerRegistry.lookup(name); //Va a recuperare il clientHandler
		ClientInterface stub = (ClientInterface) UnicastRemoteObject		//Esporta l'oggetto
				.exportObject(client, 0);

		try {
			clientRegistry = LocateRegistry.getRegistry(NetworkConstants.RMICLIENTPORT);
			clientRegistry.rebind(secondName, stub);
		} catch (Exception e) {              //In questo caso non ci sarà il registro
			clientRegistry = LocateRegistry.createRegistry(NetworkConstants.RMICLIENTPORT);  //Allora crea il registro su cui inserire il riferimento
			clientRegistry.bind(secondName, stub);
		}
		// the client handler will add the client to the server
		clientHandler.addClient(NetworkConstants.RMICLIENTPORT);
	}

	@Override
	public void sendCommand(ClientToServerCommand command) throws Exception {
		clientHandler.notifyServer(command);
	}


	
	//Questo metodo è implementato sia da clientInterface che da networkInterface chiedere info
	@Override
	public void notifyClient(ServerToClientCommand command){
		//TODO this will have something like notifyObserver(command);
	}

	@Override
	public void closeConnection() {
		try {
			//In order to close the connection we must remove the client from the registry
			//and free it.
			clientRegistry.unbind(secondName);
			clientRegistry = null;

		} catch (NullPointerException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
