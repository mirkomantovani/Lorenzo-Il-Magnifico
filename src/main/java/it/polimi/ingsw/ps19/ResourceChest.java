package it.polimi.ingsw.ps19;

/**
 * @author matteo
 *
 */
public class ResourceChest {

	private VictoryPoint victoryPoint;
	private MilitaryPoint militaryPoint;
	private FaithPoint faithPoint;
	private Coin coins;
	private Servant servants;
	private Wood woods;
	private Stone stones;

	public ResourceChest() {

		victoryPoint = new VictoryPoint(0);
		militaryPoint = new MilitaryPoint(0);
		faithPoint = new FaithPoint(0);
		coins = new Coin(0);
		servants = new Servant(0);
		woods = new Wood(0);
		stones = new Stone(0);

	}

	public ResourceChest(int vp, int mp, int fp, int coin, int serv, int wood, int stone) {

		victoryPoint = new VictoryPoint(vp);
		militaryPoint = new MilitaryPoint(mp);
		faithPoint = new FaithPoint(fp);
		coins = new Coin(coin);
		servants = new Servant(serv);
		woods = new Wood(wood);
		stones = new Stone(stone);
	}

	public VictoryPoint getVictoryPoint() {
		return victoryPoint;
	}

	public MilitaryPoint getMilitaryPoint() {
		return militaryPoint;
	}

	public FaithPoint getFaithPoint() {
		return faithPoint;
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

	public void setVictoryPoint(VictoryPoint victoryPoint) {
		this.victoryPoint = victoryPoint;
	}

	public void setMilitaryPoint(MilitaryPoint militaryPoint) {
		this.militaryPoint = militaryPoint;
	}

	public void setFaithPoint(FaithPoint faithPoint) {
		this.faithPoint = faithPoint;
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
