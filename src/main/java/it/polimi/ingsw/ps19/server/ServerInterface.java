package it.polimi.ingsw.ps19.server;



public interface ServerInterface {

	
	public void addClient(ClientHandler clientHandler);

	public void removeClient(ClientHandler clientHandler);

	
	public void closeMatch(MatchHandler matchHandler);
}
