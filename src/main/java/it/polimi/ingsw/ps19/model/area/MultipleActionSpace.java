package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.HashSet;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

/**
 * The Class MultipleActionSpace.
 *  This class is the generic multiple action space, the one that characterizes the council palace
 *  and the big production and harvest areas. This class is different from the single action space
 *  because it can store more than one familiar per time.
 */
public class MultipleActionSpace extends ActionSpace {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3421639191735530503L;
	
	/** The members. */
	protected ArrayList<FamilyMember> members;
	
	/**
	 * Instantiates a new multiple action space.
	 *
	 * @param actionValueRequired the action value required
	 * @param effect the effect
	 */
	public MultipleActionSpace(int actionValueRequired, Effect effect) {
		super(actionValueRequired, effect);
		members = new ArrayList<FamilyMember>();
	}
																			
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#isOccupied()
	 */
	@Override
	public boolean isOccupied() {
		return members.size() > 0;
	}

	/**
	 * Check availability.
	 *
	 * @param familyMember the family member
	 * @return true if there is no family member with the same player as the familyMember given to the function
	 */
	private boolean checkAvailability(FamilyMember familyMember){
		
		boolean available = true;
		System.out.println("\n\nFamily member color's " + familyMember.getColor().toString() + "\n\n");
		
		for(FamilyMember member : members){
			//Se esiste un family member dentro members che ha come player lo stesso di quello passato
			//come parametro, e il cui colore non sia neutral allora non è possibile farlo
			if(member.getPlayer() == familyMember.getPlayer() && familyMember.getDice().getColor() != Color.NEUTRAL){
				available = false;
				break;  					 //L'ho visto fare da qualche parte nel corso :-)
			}
		}
		System.out.println("available: " + available + "\n\n");
		return available || familyMember.getDice().getColor() == Color.NEUTRAL;
	}
	
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#isOccupable(it.polimi.ingsw.ps19.FamilyMember)
	 */
	
	public boolean isOccupable(FamilyMember familyMember, int paidServants) {
		if(!checkAvailability(familyMember))
			System.out.println("Non occupabile");
		System.out.println("Family member action value: " + familyMember.getActionValue());
		System.out.println("Action value required " + this.actionValueRequired);
		System.out.println("Checkavailabity " + checkAvailability(familyMember));
		return familyMember.getActionValue() + paidServants > this.actionValueRequired && checkAvailability(familyMember);
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#occupiedByMember()
	 */
	public ArrayList<FamilyMember> occupiedByMember() {	
		return members;
	}

	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.Occupable#occupiedByPlayer()
	 */
	public HashSet<Player> occupiedByPlayer() {
		
		HashSet<Player> forInterface = new HashSet<Player>();
		
		for(FamilyMember member : members){
			forInterface.add(member.getPlayer());
		}
		
		return forInterface;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.model.area.ActionSpace#setFamilyMember(it.polimi.ingsw.ps19.FamilyMember)
	 */
	@Override
	public void setFamilyMember(FamilyMember familyMember) {
//		if(isOccupable(familyMember)){
			this.members.add(familyMember);
//			System.out.println("Successfully added");
			
//		}
	}

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
	public ArrayList<FamilyMember> getMembers() {
		return members;
	}

	/**
	 * Sets the members.
	 *
	 * @param members the new members
	 */
	public void setMembers(ArrayList<FamilyMember> members) {
		this.members = members;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Members here:\n");
		for(FamilyMember f : members){
			builder.append(f.toString());
			builder.append("familiar of the ");
			builder.append(f.getPlayer().getColor());
			builder.append(" player\n");
		}
		return builder.toString();
	}

	@Override
	public boolean isOccupable(FamilyMember familyMember) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
