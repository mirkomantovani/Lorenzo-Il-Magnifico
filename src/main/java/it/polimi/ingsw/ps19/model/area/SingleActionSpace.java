package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.HashSet;

import it.polimi.ingsw.ps19.model.FamilyMember;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class SingleActionSpace.
 * This class is the generic single action space that you can find in floors, markets and in small
 * production and harvest area. This action space can store only one familiar per time. 
 * 
 */
public class SingleActionSpace extends ActionSpace{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9069295031617624276L;
	
	/** The family member. */
	private FamilyMember familyMember;
	
	/**
	 * Instantiates a new single action space.
	 *
	 * @param actionValueRequired the action value required
	 * @param effect the effect
	 */
	public SingleActionSpace(int actionValueRequired, Effect effect) {
		super(actionValueRequired, effect);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#isOccupied()
	 */
	@Override
	public boolean isOccupied() {
		return familyMember!=null;
	}
	
	
	//This method returns an arraylist with a single family member, in order to access the
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#occupiedByMember()
	 */
	//attribute use the getFamilyMember method
	@Override
	public ArrayList<FamilyMember> occupiedByMember() {
		ArrayList<FamilyMember> forInterface = new ArrayList<FamilyMember>();
		forInterface.add(familyMember);
		return forInterface;
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#occupiedByPlayer()
	 */
	@Override
	public HashSet<Player> occupiedByPlayer() {
		HashSet<Player> forInterface = new HashSet<Player>();
		forInterface.add(familyMember.getPlayer());
		return forInterface;
	}


	/**
	 * Gets the family member.
	 *
	 * @return the family member
	 */
	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.ActionSpace#setFamilyMember(it.polimi.ingsw.ps19.FamilyMember)
	 */
	public void setFamilyMember(FamilyMember familyMember) {
//		if(!isOccupied())
			this.familyMember = familyMember;
//		else
//			System.out.println("Non occupabile!");
	}


	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#isOccupable(it.polimi.ingsw.ps19.FamilyMember)
	 */
	
	public boolean isOccupable(FamilyMember familyMember, int paidServants, int industrialValueVariation) {

		System.out.println("\nsono nella is Occupable\n");
		System.out.println("\nSINGLE ACTION SPACE è occupato?: " + isOccupied());
		System.out.println("\nSINGLE ACTION SPACE: FamilyMember action value: " + (familyMember.getActionValue() + paidServants));
		System.out.println("\nSINGLE ACTION SPACE: action value required: " + actionValueRequired);
		System.out.println("Result:" + (!isOccupied() && familyMember.getActionValue() + paidServants + industrialValueVariation > this.actionValueRequired));
		return !isOccupied() && familyMember.getActionValue() + paidServants + industrialValueVariation> this.actionValueRequired;

	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#isOccupable(it.polimi.ingsw.ps19.FamilyMember)
	 */
	@Override
	public boolean isOccupable(FamilyMember familyMember) {

		

		System.out.println("\nsono nella is Occupable\n");
		System.out.println("\nSINGLE ACTION SPACE è occupato?: " + isOccupied());
		System.out.println("\nSINGLE ACTION SPACE: FamilyMember action value: " + familyMember.getActionValue());
		System.out.println("\nSINGLE ACTION SPACE: action value required: " + actionValueRequired);

		return !isOccupied();

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(familyMember != null){
			builder.append("The area is occupied by the ");
			builder.append(familyMember.toString());
			builder.append(" family member of the ");
			builder.append(familyMember.getPlayer().getColor());
			builder.append(" player\n");
		}
		return builder.toString();
	}
	
	
}