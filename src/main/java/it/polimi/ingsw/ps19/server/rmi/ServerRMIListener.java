package it.polimi.ingsw.ps19.server.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.network.rmi.ClientHandlerInterface;
import it.polimi.ingsw.ps19.network.rmi.ClientHandlerInterfaceImpl;
import it.polimi.ingsw.ps19.server.ClientHandler;
import it.polimi.ingsw.ps19.server.Server;
import it.polimi.ingsw.ps19.server.ServerInterface;

/**
 *
 * 
 */
public class ServerRMIListener implements Runnable {

	/** The id. */
	private static int id = 0;
	
	/** The client handler. */
	private ClientHandlerInterface clientHandler;
	
	/** The registry. */
	private Registry registry;
	
	/** The name. */
	private String name;
	
	/** The creator. */
	private ServerInterface creator;
	
	/**
	 * Instantiates a new server RMI listener.
	 *
	 * @param server the server
	 */
	public ServerRMIListener(Server server) {
		name = "ClientHandler";
		creator = server;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createClientHandler();
	}
	
	/**
	 * Creates the client handler.
	 */
	private void createClientHandler(){		
		try {
			clientHandler = new ClientHandlerInterfaceImpl(this, id);
			ClientHandlerInterface stub = (ClientHandlerInterface) UnicastRemoteObject.exportObject(clientHandler, 0);
			registry = LocateRegistry.createRegistry(NetworkConstants.RMICLIENTHANDLERPORT);   //THE PROBLEM IS HERE
			registry.bind(name, stub);
			id++;
		} catch (Exception e) {
			System.err.println("PLEASE DO NOT ENTER");
			closeListener();
		}
	}
	
	/**
	 * This method closes the RMI listener locking the resources so that no other thread can interfere.
	 */
	public synchronized void closeListener() {
		try {
			registry.unbind(name);
		} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
		}
		registry = null;
	}
	
	/**
	 * This method adds a client once the registry is active so when the first one has already been instantiated.
	 *
	 * @param clientHandler the client handler
	 */
	public void addClient(ClientHandlerInterfaceImpl clientHandler){
		creator.addClient(clientHandler); //creator represents the server that called this serverRMIListener
		//once the registry is created i do have to re-bind rather than bind 
		try{
			clientHandler = new ClientHandlerInterfaceImpl(this, id);
			ClientHandlerInterface stub = (ClientHandlerInterface) UnicastRemoteObject.exportObject(clientHandler, 0);
			registry.rebind(name, stub);
			id++;
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the waiting client.
	 *
	 * @param clientHandler the client handler
	 */
	public void removeWaitingClient(ClientHandler clientHandler) {
		creator.removeClient(clientHandler);
	}

	/**
	 * End listening.
	 */
	public void endListening() {
		try {
			registry.unbind(name);
		} catch (RemoteException | NotBoundException e) {
		}
		registry = null;		
	}
}
