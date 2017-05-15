package it.polimi.ingsw.ps19;

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
	 * this method was needed to apply the AtomicExchangeEffect
	 * @author Mirko
	 * @param resource to subtract
	 */
	public void subResource(Resource resource) {
		getResourceInChest(resource).sub(resource.getAmount());
	}
	
	
	/**
	 * this method was needed to apply the AtomicExchangeEffect
	 * @author Mirko
	 * @param resource to subtract
	 */
	public void addResource(Resource resource) {
		getResourceInChest(resource).add(resource.getAmount());
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
	
	
	
	

}
