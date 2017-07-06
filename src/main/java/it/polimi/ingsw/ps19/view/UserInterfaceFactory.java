package it.polimi.ingsw.ps19.view;

import it.polimi.ingsw.ps19.client.ClientController;
import it.polimi.ingsw.ps19.view.cli.CommandLineInterface;
import it.polimi.ingsw.ps19.view.gui.GraphicalUserInterface;

/**
 * A factory for creating UserInterface objects.
 */
public class UserInterfaceFactory {
	
	/**
	 * Gets the user interface.
	 *
	 * @param choice the choice
	 * @param gameController the game controller
	 * @return the user interface
	 */
	public static UserInterface getUserInterface(int choice, ClientController gameController){
		switch(choice){
			case 2: return new GraphicalUserInterface(gameController);
			case 1: return new CommandLineInterface(gameController);
			default: return new GraphicalUserInterface(gameController);
		}
	}
}
