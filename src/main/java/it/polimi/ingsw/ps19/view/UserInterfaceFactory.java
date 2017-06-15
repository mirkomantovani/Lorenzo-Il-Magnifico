package it.polimi.ingsw.ps19.view;

import it.polimi.ingsw.ps19.view.cli.CommandLineInterface;
import it.polimi.ingsw.ps19.view.gui.GraphicalUserInterface;

public class UserInterfaceFactory {
	
	public static UserInterface getUserInterface(int choice){
		switch(choice){
		case 2: return new GraphicalUserInterface();
		case 1: return new CommandLineInterface();
		default: return new GraphicalUserInterface();
		}
	}

}
