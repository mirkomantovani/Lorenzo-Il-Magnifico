package it.polimi.ingsw.ps19.launchers;

import java.util.Scanner;

import it.polimi.ingsw.ps19.ReconnectionManager;
import it.polimi.ingsw.ps19.client.ClientCommandHandler;
import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.network.NetworkInterfaceFactory;
import it.polimi.ingsw.ps19.view.UserInterface;
import it.polimi.ingsw.ps19.view.UserInterfaceFactory;

/**
 * The Class ClientLauncher.
 *
 * @author matteo
 * 
 * 1 * this is the client, it should have a main to ask the user which 
 * connection and which UI he wants to use, and to set up the choice
 */
public class ClientLauncher {
	

	/** The i. */
	static Scanner  i;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
		
		
		choice = i.nextInt();
		
		controller = new ClientController(networkInterface);
		
		userInterface = UserInterfaceFactory.getUserInterface(choice, controller);
		
		handler = new ClientCommandHandler(userInterface,networkInterface);
		
		controller.setUserInterface(userInterface);
		controller.setCommandHandler(handler);
		
//		System.out.println("Would you join an existing Match? (Y/N)\n");
//		
//		String connChoice = i.next();
//		
//		if(connChoice.equals("Y")){
//			System.out.println("Please insert your name: \n");
//			String name = i.nextLine();
//			System.out.println("your Password: \n");
//			String pword = i.nextLine();
//			ReconnectionManager rm = new ReconnectionManager(handler,name,pword);
//		}
		
		try {
		
			networkInterface.connect();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("YOU SHOULD NOT BE HERE");
			e.printStackTrace();
		}
		
	
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
