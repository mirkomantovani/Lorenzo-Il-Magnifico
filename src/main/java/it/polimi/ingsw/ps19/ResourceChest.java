package it.polimi.ingsw.ps19;

public class ResourceChest {

	VictoryPoint victoryPoint;

	MilitaryPoint militaryPoint;

	FaithPoint faithPoint;

	Coin coins;

	Servant servants;

	Wood woods;

	Stone stones;

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

}
