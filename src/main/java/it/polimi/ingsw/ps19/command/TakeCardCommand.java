package it.polimi.ingsw.ps19.command;



import it.polimi.ingsw.ps19.server.ServerCommandHandler;

public class TakeCardCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549395717779803669L;
	
	private String familyMember;
	private int floor;
	private int paidServants;
	
	
	public TakeCardCommand(String familyMember, int floor, int paidServants) {
		this.familyMember = familyMember;
		this.floor = floor;
		this.paidServants = paidServants;
	}


	@Override
	public void processCommand(ServerCommandHandler serverHandlerCommand) {
		serverHandlerCommand.applyCommand(this);
		
	}}
