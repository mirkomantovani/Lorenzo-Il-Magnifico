package it.polimi.ingsw.ps19.model.area;

import java.util.ArrayList;
import java.util.HashSet;

import it.polimi.ingsw.ps19.Color;
import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.Player;
import it.polimi.ingsw.ps19.model.effect.Effect;

public class MultipleActionSpace extends ActionSpace {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3421639191735530503L;
	protected ArrayList<FamilyMember> members;
	
	public MultipleActionSpace(int actionValueRequired, Effect effect) {
		super(actionValueRequired, effect);
		members = new ArrayList<FamilyMember>();
	}
																			
	@Override
	public boolean isOccupied() {
		return members.size() > 0;
	}

	/**
	 * @param familyMember
	 * @return true if there is no family member with the same player as the familyMember given to the function
	 * 
	 *
	 */
	private boolean checkAvailability(FamilyMember familyMember){
		
		boolean available = true;
		
		for(FamilyMember member : members){
			//Se esiste un family member dentro members che ha come player lo stesso di quello passato
			//come parametro, e il cui colore non sia neutral allora non è possibile farlo
			if(member.getPlayer() == familyMember.getPlayer() && member.getDice().getColor() != Color.NEUTRAL){
				available = false;
				break;  					 //L'ho visto fare da qualche parte nel corso :-)
			}
		}
		
		return available || familyMember.getDice().getColor() == Color.NEUTRAL;
	}
	
	
	@Override
	public boolean isOccupable(FamilyMember familyMember) {
		if(!checkAvailability(familyMember))
			System.out.println("Non occupabile");
		return familyMember.getActionValue() > this.actionValueRequired && checkAvailability(familyMember);
	}
	
	public ArrayList<FamilyMember> occupiedByMember() {	
		return members;
	}

	
	public HashSet<Player> occupiedByPlayer() {
		
		HashSet<Player> forInterface = new HashSet<Player>();
		
		for(FamilyMember member : members){
			forInterface.add(member.getPlayer());
		}
		
		return forInterface;
	}

	@Override
	public void setFamilyMember(FamilyMember familyMember) {
		if(isOccupable(familyMember)){
			this.members.add(familyMember);
			System.out.println("Successfully added");
			
		}
	}

	public ArrayList<FamilyMember> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<FamilyMember> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("In this space there are these familiars: \n");
		for(FamilyMember f : members){
		builder.append(f.toString());
		builder.append("familiar of the ");
		builder.append(f.getPlayer().getColor());
		builder.append(" player ");
		}
		return builder.toString();
	}
	
	
	
	

}
