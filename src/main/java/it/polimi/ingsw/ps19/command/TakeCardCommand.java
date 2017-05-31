package it.polimi.ingsw.ps19.command;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.area.Floor;
import it.polimi.ingsw.ps19.model.card.Card;
import it.polimi.ingsw.ps19.model.resource.Servant;

public class TakeCardCommand extends ClientToServerCommand{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549395717779803669L;
	
	FamilyMember familyMember;
	Floor floor;
	Servant paidServants;
	
	
	public TakeCardCommand(FamilyMember familyMember, Floor floor, Servant paidServants) {
		this.familyMember = familyMember;
		this.floor = floor;
		this.paidServants = paidServants;
	}

	@Override
	public void processCommand(ServerCommandHandler serverCommandHandler) {
		serverCommandHandler.applyCommand(this);
	}

	
	

}
