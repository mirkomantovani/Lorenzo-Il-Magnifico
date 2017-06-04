package it.polimi.ingsw.ps19.command;


public interface ServerCommandHandler {
	
	public void applyCommand(PlaceIntoMarketCommand placeIntoMarketCommand);
	
	public void applyCommand(TakeCardCommand takeCardCommand);

}