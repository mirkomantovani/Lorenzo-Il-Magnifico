package it.polimi.ingsw.ps19.view.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * @author Mirko
 *
 */
public class LeadersPanel extends JPanel {
	
	private List<JLeaderCard> leaders;
	
	public LeadersPanel() {
		
		
		
		leaders=new ArrayList<>();
		
//		JLeaderCard first = new JLeaderCard("Francesco Sforza");
//		add(first);
//		
//		JLeaderCard second = new JLeaderCard("Francesco Sforza");
//		add(second);
//		
//		JLeaderCard third = new JLeaderCard("Francesco Sforza");
//		add(third);
//		
//		JLeaderCard fourth = new JLeaderCard("Francesco Sforza");
//		add(fourth);
	}
	
	public void addLeaderCards(Map<String,LeaderCard> leaderCards){
		ArrayList<LeaderCard> leaderArray = new ArrayList<LeaderCard>(leaderCards.values());
		
		for(LeaderCard leader: leaderArray){
			JLeaderCard jLeaderCard=new JLeaderCard(leader.getName());
			leaders.add(jLeaderCard);
			add(jLeaderCard);
		}
	
	}
	
	public void refreshLeaderCards(Map<String,LeaderCard> leaderCards){
		leaders.clear();
		this.removeAll();
		this.addLeaderCards(leaderCards);
	}

	/**
	 * @param size the number of leader cards
	 * @return true if the number of leader cards in the panel is equal to size
	 */
	public boolean areLeaderCards(int size) {
		return leaders.size()==size;
	}

	public void addLeaderCards(ArrayList<LeaderCard> leaderCards) {
		for(LeaderCard leader: leaderCards){
			JLeaderCard jLeaderCard=new JLeaderCard(leader.getName());
			jLeaderCard.setToolTipText(leader.toString());
			leaders.add(jLeaderCard);
			add(jLeaderCard);
		}
	}

	public void addLeaderCardsWithListener(ArrayList<LeaderCard> leaderCards, GamePanel actionListener) {
		for(LeaderCard leader: leaderCards){
			JLeaderCard jLeaderCard=new JLeaderCard(leader.getName());
			jLeaderCard.setToolTipText(leader.toString());
			leaders.add(jLeaderCard);
			add(jLeaderCard);
			jLeaderCard.addActionListener(actionListener);
		}
	}

	public void deleteLeaders() {
		this.removeAll();
//		for(JLeaderCard leader: leaders){
//			this.remove
//		}
		
	}

}
