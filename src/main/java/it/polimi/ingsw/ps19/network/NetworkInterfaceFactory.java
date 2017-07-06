package it.polimi.ingsw.ps19.network;

import it.polimi.ingsw.ps19.network.rmi.ClientRMIInterface;

/**
 * A factory for creating NetworkInterface objects.
 *
 * @author matteo
 * 
 *   this class returns the different kind of connection based on the user choice input
 */
public class NetworkInterfaceFactory {
	
	/**
	 * Gets the network interface.
	 *
	 * @param choice the choice
	 * @return the network interface
	 */
	public static NetworkInterface getNetworkInterface(int choice){
		
		switch(choice){
		case 1: return new ClientSocketInterface();
		case 2: return new ClientRMIInterface();
		default : return new ClientSocketInterface();
		}
	}
	

}
