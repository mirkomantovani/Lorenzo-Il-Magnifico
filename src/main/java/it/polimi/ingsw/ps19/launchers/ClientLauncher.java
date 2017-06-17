package it.polimi.ingsw.ps19.launchers;

import java.util.Scanner;

import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.network.NetworkInterfaceFactory;
import it.polimi.ingsw.ps19.view.UserInterface;
import it.polimi.ingsw.ps19.view.UserInterfaceFactory;

/**
 * @author matteo
 * 
1 * this is the client, it should have a main to ask the user which 
 * connection and which UI he wants to use, and to set up the choice
 *
 */
public class ClientLauncher {
	

	static Scanner  i;
	public static void main(String[] args){
		
		NetworkInterface networkInterface;
		UserInterface userInterface;
		ClientCommandHandler handler;
		ClientController controller;
		
		i=new Scanner(System.in);
	
		System.out.println("Select the Connection mode: \n1 - Socket\n2 - RMI ");
		
		int choice = i.nextInt();
			
		networkInterface = NetworkInterfaceFactory.getNetworkInterface(choice) ;
		
		
		
		System.out.println("Choose user interface: \n1 - Command Line Interface\n2 - Graphic User Interface");
		
		controller = new ClientController();
		choice = i.nextInt();
		
		userInterface = UserInterfaceFactory.getUserInterface(choice, controller);
		
		handler = new ClientCommandHandler(userInterface,networkInterface);
		
		try {
			System.out.println("I'm trying to connect");
			networkInterface.connect();
			System.out.println("Client Connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("YOU SHOULD NOT BE HERE");
			e.printStackTrace();
		}
		
		System.out.println("client: addcommandobserver");
		networkInterface.addCommandObserver(handler);
	
		
		
		//TODO we should istantiate a client controller to manage the view interactions
		
		
		
		

//		int num=i.nextInt();
//		if(num==1){
//		try {
//			client.sendCommand(new ProvaCommand(serv));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("ho inviato il comando sullo stream");
//		}
		
	}

	
	

}
