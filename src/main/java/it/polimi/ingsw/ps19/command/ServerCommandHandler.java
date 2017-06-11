package it.polimi.ingsw.ps19.command;


public interface ServerCommandHandler {
	
	public void applyCommand(ClientToServerCommand placeIntoCouncilPalaceCommand);
	
	public void applyCommand(TakeCardCommand takeCardCommand);

}