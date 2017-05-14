package it.polimi.ingsw.ps19;

public class Privilege {
	
	private Coin coin;
	private Stone stone;
	private FaithPoint faithPoint;
	private MilitaryPoint militaryPoint;
	private Servant servant;
	private Wood wood;
	
	
	private Privilege(){
		coin.setAmount(2);
		stone.setAmount(1);
		wood.setAmount(1);
		faithPoint.setAmount(1);
		servant.setAmount(2);
		militaryPoint.setAmount(2);
	}
	
	public void getPrivilege(Player p, int choose){
		if(choose == 1){
			 p.resources.;
		}
	}

}
