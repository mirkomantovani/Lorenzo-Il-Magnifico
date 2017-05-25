package it.polimi.ingsw.ps19.model.action;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;

public class ProductionAction extends Action {

	public ProductionAction(Player player,FamilyMember familyMember) {
		super(familyMember);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isApplicable() {

		return false;
	}

}
