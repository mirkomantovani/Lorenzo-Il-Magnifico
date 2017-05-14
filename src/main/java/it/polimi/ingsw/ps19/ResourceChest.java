package it.polimi.ingsw.ps19;

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

	public ResourceChest(int coin, int wood, int stone, int serv, int fp, int vp, int mp) {
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
	
	public void pourInto(ResourceChest resourceChest){
		resourceChest.getVictoryPoint().add(this.victoryPoint.getAmount());
		resourceChest.getMilitaryPoint().add(this.militaryPoint.getAmount());
		resourceChest.getFaithPoint().add(this.faithPoint.getAmount());
		resourceChest.getCoins().add(this.coins.getAmount());
		resourceChest.getServants().add(this.servants.getAmount());
		resourceChest.getWoods().add(this.woods.getAmount());
		resourceChest.getStones().add(this.stones.getAmount());
	}
}
