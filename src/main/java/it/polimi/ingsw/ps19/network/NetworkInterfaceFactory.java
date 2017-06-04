package it.polimi.ingsw.ps19.network;


/**
 * @author matteo
 * 
 *   this class returns the different kind of connection based on the user choice input
 *
 */
public class NetworkInterfaceFactory {
	
	public static NetworkInterface getNetworkInterface(int choice){
		
		switch(choice){
		case 1: return new ClientSocketInterface();
//		case 2: return new ClientRMIInterface();
		default : return new ClientSocketInterface();
		}
	}
	

}
