package it.polimi.ingsw.ps19.model.resource;

/**
 * @author matteo
 *
 */
public class ResourceChest {

	private VictoryPoint victoryPoints;
	private MilitaryPoint militaryPoints;
	private FaithPoint faithPoints;
	private Coin coins;
	private Servant servants;
	private Wood woods;
	private Stone stones;

	public ResourceChest() {

		victoryPoints = new VictoryPoint(0);
		militaryPoints = new MilitaryPoint(0);
		faithPoints = new FaithPoint(0);
		coins = new Coin(0);
		servants = new Servant(0);
		woods = new Wood(0);
		stones = new Stone(0);

	}

	public ResourceChest(int coin, int wood, int stone, int serv, int fp, int vp, int mp) {

		victoryPoints = new VictoryPoint(vp);
		militaryPoints = new MilitaryPoint(mp);
		faithPoints = new FaithPoint(fp);
		coins = new Coin(coin);
		servants = new Servant(serv);
		woods = new Wood(wood);
		stones = new Stone(stone);
	}

	public VictoryPoint getVictoryPoint() {
		return victoryPoints;
	}

	public MilitaryPoint getMilitaryPoint() {
		return militaryPoints;
	}

	public FaithPoint getFaithPoint() {
		return faithPoints;
	}

	public Coin getCoins() {
		return coins;
	}

	public Servant getServants() {
		return servants;
	}

	public Wood getWoods() {
		return woods;
	}

	public Stone getStones() {
		return stones;
	}
	
	/**
	 * @author Jimmy
	 * @param resourceChest
	 */
	public void pourInto(ResourceChest resourceChest){
		resourceChest.getVictoryPoint().add(this.victoryPoints.getAmount());
		resourceChest.getMilitaryPoint().add(this.militaryPoints.getAmount());
		resourceChest.getFaithPoint().add(this.faithPoints.getAmount());
		resourceChest.getCoins().add(this.coins.getAmount());
		resourceChest.getServants().add(this.servants.getAmount());
		resourceChest.getWoods().add(this.woods.getAmount());
		resourceChest.getStones().add(this.stones.getAmount());
	}
	
	
	/**
	 * This method subtracts every Resource of the Chest passed by parameter to the Chest it is called upon
	 * @author Mirko
	 * @param resourceChest
	 */
	public void subChest(ResourceChest resourceChest){
		
		this.victoryPoints.sub(resourceChest.victoryPoints);
		this.militaryPoints.sub(resourceChest.militaryPoints);
		this.faithPoints.sub(resourceChest.faithPoints);
		this.coins.sub(resourceChest.coins);
		this.servants.sub(resourceChest.servants);
		this.woods.sub(resourceChest.woods);
		this.stones.sub(resourceChest.stones);
		
	}
	
	/**
	 * This method adds every Resource of the Chest passed by parameter to the Chest it is called upon
	 * @author Mirko
	 * @param resourceChest
	 */
	public void addChest(ResourceChest resourceChest){
		
		this.victoryPoints.add(resourceChest.victoryPoints);
		this.militaryPoints.add(resourceChest.militaryPoints);
		this.faithPoints.add(resourceChest.faithPoints);
		this.coins.add(resourceChest.coins);
		this.servants.add(resourceChest.servants);
		this.woods.add(resourceChest.woods);
		this.stones.add(resourceChest.stones);
		
	}
	

	/**
	 * This method subtracts the generic resource passed by parameter to the right resource field of the chest
	 * (this method was needed to apply the AtomicExchangeEffect)
	 * @author Mirko
	 * @param resource to subtract
	 */
	public void subResource(Resource resource) {
		getResourceInChest(resource).sub(resource);
	}
	
	
	/**
	 * This method adds the generic resource passed by parameter to the right resource field of the chest
	 *(this method was needed to apply the AtomicExchangeEffect)
	 * @author Mirko
	 * @param resource to subtract
	 */
	public void addResource(Resource resource) {
		getResourceInChest(resource).add(resource);
	}
	

	/**
	 * this private method is used from subResource and addResource to find and return the dynamic type of the resource
	 * @author Mirko
	 * @param resource
	 */
	private Resource getResourceInChest(Resource resource) {
		
		if(resource instanceof VictoryPoint)return victoryPoints;
		if(resource instanceof MilitaryPoint)return militaryPoints;
		if(resource instanceof FaithPoint)return faithPoints;
		if(resource instanceof Coin)return coins;
		if(resource instanceof Wood)return woods;
		if(resource instanceof Stone)return stones;
		if(resource instanceof Servant)return servants;
		return null;
		
	}

	public void setVictoryPoint(VictoryPoint victoryPoint) {
		this.victoryPoints = victoryPoint;
	}

	public void setMilitaryPoint(MilitaryPoint militaryPoint) {
		this.militaryPoints = militaryPoint;
	}

	public void setFaithPoint(FaithPoint faithPoint) {
		this.faithPoints = faithPoint;
	}

	public void setCoins(Coin coins) {
		this.coins = coins;
	}

	public void setServants(Servant servants) {
		this.servants = servants;
	}

	public void setWoods(Wood woods) {
		this.woods = woods;
	}

	public void setStones(Stone stones) {
		this.stones = stones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
	
		builder.append(victoryPoints);
		
		builder.append(militaryPoints);
		
		builder.append(faithPoints);
		
		builder.append(coins);
		
		builder.append(servants);
		
		builder.append(woods);
		
		builder.append(stones);
		
		return builder.toString();
	}

	/**
	 * This method returns true if every resource contained in the chest has an amount greater
	 * or equal to the corresponding resource in the chest passed by parameter
	 * @author Mirko
	 * @param cost
	 * @return
	 */
	public boolean isGreaterEqualThan(ResourceChest resourceChest) {
		if(this.victoryPoints.getAmount()<resourceChest.victoryPoints.getAmount())return false;
		if(this.militaryPoints.getAmount()<resourceChest.militaryPoints.getAmount())return false;
		if(this.faithPoints.getAmount()<resourceChest.faithPoints.getAmount())return false;
		if(this.coins.getAmount()<resourceChest.coins.getAmount())return false;
		if(this.servants.getAmount()<resourceChest.servants.getAmount())return false;
		if(this.woods.getAmount()<resourceChest.woods.getAmount())return false;
		if(this.stones.getAmount()<resourceChest.stones.getAmount())return false;
		return true;
	}
	
	
}
