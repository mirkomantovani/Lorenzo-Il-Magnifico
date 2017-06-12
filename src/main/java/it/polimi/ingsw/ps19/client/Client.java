package it.polimi.ingsw.ps19.client;

import java.util.Scanner;

import it.polimi.ingsw.ps19.command.ProvaCommand;
import it.polimi.ingsw.ps19.model.resource.Servant;
import it.polimi.ingsw.ps19.network.ClientSocketInterface;
import it.polimi.ingsw.ps19.network.NetworkInterface;
import it.polimi.ingsw.ps19.network.NetworkInterfaceFactory;

/**
 * @author matteo
 * 
 * this is the client, it should have a main to ask the user which 
 * connection and which UI he wants to use, and to set up the choice
 *
 */
public class Client {
	static Scanner  i;
	public static void main(String[] args){
		
		i=new Scanner(System.in);
		
		Servant serv = new Servant(3);
		
		System.out.println("Select the Connection mode: \n1 - Socket\n2 - RMI ");
		
		int choice = i.nextInt();
		
		
		NetworkInterface client = NetworkInterfaceFactory.getNetworkInterface(choice) ;
		
		try {
			System.out.println("I'm trying to connect");
			client.connect();
			System.out.println("Client Connected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("YOU SHOULD NOT BE HERE");
			e.printStackTrace();
		}

		int num=i.nextInt();
		if(num==1){
		try {
			client.sendCommand(new ProvaCommand(serv));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ho inviato il comando sullo stream");
		}
		
	}

}
