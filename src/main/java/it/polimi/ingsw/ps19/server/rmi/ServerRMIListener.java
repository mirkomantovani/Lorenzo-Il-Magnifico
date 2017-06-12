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

public class ServerRMIListener implements Runnable {

	private static int id = 0;
	private ClientHandlerInterface clientHandler;
	private Registry registry;
	private String name;
	private ServerInterface creator;
	
	public ServerRMIListener(Server server) {
		name = "ClientHandler";
		creator = server;
	}

	@Override
	public void run() {
		System.out.println("Running ServerRMIListener");
		createClientHandler();
	}
	
	private void createClientHandler(){		
		try {
			clientHandler = new ClientHandlerInterfaceImpl(this, id);
			System.out.println("Created the new ClientHandlerInterfaceImpl");
			ClientHandlerInterface stub = (ClientHandlerInterface) UnicastRemoteObject.exportObject(clientHandler, 0);
			System.out.println("Exported the remote reference");
			registry = LocateRegistry.createRegistry(NetworkConstants.RMICLIENTHANDLERPORT);   //THE PROBLEM IS HERE
			System.out.println("Created a new registry to save the ClientHadlerInterface stub");
			registry.bind(name, stub);
			System.out.println("The stub was correctly inserted in the registry");
			id++;
			System.out.println("Incrementing static variable id that counts the number of stub added, id: " + id);
			System.out.println("RMI Server listening");
		} catch (Exception e) {
			System.err.println("PLEASE DO NOT ENTER");
			closeListener();
		}
	}
	
	/**
	 * This method closes the RMI listener locking the resources so that no other thread can interfere
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
	 * This method adds a client once the registry is active so when the first one has already been instantiated
	 */
	public void addClient(ClientHandlerInterfaceImpl clientHandler){
		System.out.println("ServerRMIListener received an add call");
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
	
	public void removeWaitingClient(ClientHandler clientHandler) {
		creator.removeClient(clientHandler);
	}
}
