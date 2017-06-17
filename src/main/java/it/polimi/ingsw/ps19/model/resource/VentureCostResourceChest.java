package it.polimi.ingsw.ps19.model.resource;


public class VentureCostResourceChest extends ResourceChest {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6427700276453722966L;
	private int requiredMilitaryPoints;
	private int militaryPointsCost;
	
	public int getRequiredMilitaryPoints() {
		return requiredMilitaryPoints;
	}
	public void setRequiredMilitaryPoints(int requiredMilitaryPoints) {
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}
	public int getMilitaryPointsCost() {
		return militaryPointsCost;
	}
	public void setMilitaryPointsCost(int militaryPointsCost) {
		this.militaryPointsCost = militaryPointsCost;
	}
	
	public VentureCostResourceChest(int coin, int wood, int stone, int servant, int faithPoint, int victoryPoint, int militaryPoint,int militaryPointsCost, int requiredMilitaryPoints){
		
		super(coin,wood,stone, servant, faithPoint,  victoryPoint, militaryPoint);
		this.militaryPointsCost = militaryPointsCost;
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

}
