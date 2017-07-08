package it.polimi.ingsw.ps19.view.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import it.polimi.ingsw.ps19.model.card.LeaderCard;

/**
 * The panel that shows the player his leader cards
 *
 * @author Mirko
 */
public class LeadersPanel extends JPanel {
	
	/** The leaders. */
	private List<JLeaderCard> leaders;
	
	/**
	 * Instantiates a new leaders panel.
	 */
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
	
	/**
	 * Adds the leader cards.
	 *
	 * @param leaderCards the leader cards
	 */
	public void addLeaderCards(Map<String,LeaderCard> leaderCards){
		ArrayList<LeaderCard> leaderArray = new ArrayList<LeaderCard>(leaderCards.values());
		
		for(LeaderCard leader: leaderArray){
			JLeaderCard jLeaderCard=new JLeaderCard(leader.getName());
			jLeaderCard.setToolTipText(leader.toString());
			leaders.add(jLeaderCard);
			add(jLeaderCard);
		}
	
	}
	
	/**
	 * Refresh leader cards.
	 *
	 * @param leaderCards the leader cards
	 * @param gamePanel the game panel
	 */
	public void refreshLeaderCards(Map<String,LeaderCard> leaderCards, GamePanel gamePanel){
		ArrayList<LeaderCard> leaderArray = new ArrayList<LeaderCard>(leaderCards.values());
		
		leaders.clear();
		this.removeAll();
		this.addLeaderCardsWithListener(leaderArray, gamePanel);
	}

	/**
	 * Are leader cards.
	 *
	 * @param size the number of leader cards
	 * @return true if the number of leader cards in the panel is equal to size
	 */
	public boolean areLeaderCards(int size) {
		return leaders.size()==size;
	}

	/**
	 * Adds the leader cards.
	 *
	 * @param leaderCards the leader cards
	 */
	public void addLeaderCards(ArrayList<LeaderCard> leaderCards) {
		for(LeaderCard leader: leaderCards){
			JLeaderCard jLeaderCard=new JLeaderCard(leader.getName());
			jLeaderCard.setToolTipText(leader.toString());
			leaders.add(jLeaderCard);
			add(jLeaderCard);
		}
	}

	/**
	 * Adds the leader cards with listener.
	 *
	 * @param leaderCards the leader cards
	 * @param actionListener the action listener
	 */
	public void addLeaderCardsWithListener(ArrayList<LeaderCard> leaderCards, GamePanel actionListener) {
		for(LeaderCard leader: leaderCards){
			JLeaderCard jLeaderCard=new JLeaderCard(leader.getName());
			jLeaderCard.setToolTipText(leader.toString());
			leaders.add(jLeaderCard);
			add(jLeaderCard);
			jLeaderCard.addActionListener(actionListener);
		}
	}

	/**
	 * Delete leaders.
	 */
	public void deleteLeaders() {
		this.removeAll();
//		for(JLeaderCard leader: leaders){
//			this.remove
//		}
		
	}

}
