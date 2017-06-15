package it.polimi.ingsw.ps19.client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps19.command.ServerToClientCommand;

public interface ServerToClientCommandObserver {

	public void notifyNewCommand(ServerToClientCommand serverToClientCommand);



}
