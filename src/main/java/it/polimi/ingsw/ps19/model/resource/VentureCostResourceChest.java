package it.polimi.ingsw.ps19.model.resource;


/**
 * The Class VentureCostResourceChest.
 */
public class VentureCostResourceChest extends ResourceChest {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6427700276453722966L;
	
	/** The required military points. */
	private int requiredMilitaryPoints;
	
	/** The military points cost. */
	private int militaryPointsCost;
	
	/**
	 * Gets the required military points.
	 *
	 * @return the required military points
	 */
	public int getRequiredMilitaryPoints() {
		return requiredMilitaryPoints;
	}
	
	/**
	 * Sets the required military points.
	 *
	 * @param requiredMilitaryPoints the new required military points
	 */
	public void setRequiredMilitaryPoints(int requiredMilitaryPoints) {
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}
	
	/**
	 * Gets the military points cost.
	 *
	 * @return the military points cost
	 */
	public int getMilitaryPointsCost() {
		return militaryPointsCost;
	}
	
	/**
	 * Sets the military points cost.
	 *
	 * @param militaryPointsCost the new military points cost
	 */
	public void setMilitaryPointsCost(int militaryPointsCost) {
		this.militaryPointsCost = militaryPointsCost;
	}
	
	/**
	 * Instantiates a new venture cost resource chest.
	 *
	 * @param coin the coin
	 * @param wood the wood
	 * @param stone the stone
	 * @param servant the servant
	 * @param faithPoint the faith point
	 * @param victoryPoint the victory point
	 * @param militaryPoint the military point
	 * @param militaryPointsCost the military points cost
	 * @param requiredMilitaryPoints the required military points
	 */
	public VentureCostResourceChest(int coin, int wood, int stone, int servant, int faithPoint, int victoryPoint, int militaryPoint,int militaryPointsCost, int requiredMilitaryPoints){
		
		super(coin,wood,stone, servant, faithPoint,  victoryPoint, militaryPoint);
		this.militaryPointsCost = militaryPointsCost;
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

}
