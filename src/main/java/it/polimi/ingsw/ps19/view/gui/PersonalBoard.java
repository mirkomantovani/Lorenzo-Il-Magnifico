package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.model.card.CardType;

public class PersonalBoard extends JFrame {
	
	private int numBuilding;
	private int numVenture;
	private int numTerritory;
	private int numCharacter;
	
	private PersonalBoardPanel personalBoardPanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalBoard frame = new PersonalBoard();
					frame.setVisible(true);
					
					
//					frame.getGamePanel().addCard();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PersonalBoard() {
		setUndecorated(true);
		setResizable(false);
		
		personalBoardPanel = new PersonalBoardPanel();
		getContentPane().add(personalBoardPanel, BorderLayout.CENTER);
		setSize(personalBoardPanel.getPreferredSize());
		setPreferredSize(personalBoardPanel.getPreferredSize());
		
//		JDevelopmentCard j=new JDevelopmentCard(CardType.CHARACTER, 25, 2);
//		personalBoardPanel.add(j);
	}

	public int getNumBuilding() {
		return numBuilding;
	}

	public void setNumBuilding(int numBuilding) {
		this.numBuilding = numBuilding;
	}

	public int getNumVenture() {
		return numVenture;
	}

	public void setNumVenture(int numVenture) {
		this.numVenture = numVenture;
	}

	public int getNumTerritory() {
		return numTerritory;
	}

	public void setNumTerritory(int numTerritory) {
		this.numTerritory = numTerritory;
	}

	public int getNumCharacter() {
		return numCharacter;
	}

	public void setNumCharacter(int numCharacter) {
		this.numCharacter = numCharacter;
	}

	public int getRightNum(CardType cardType) {
		switch (cardType) {
		case BUILDING:
			return getNumBuilding();
		case VENTURE:
			return getNumVenture();
		case TERRITORY:
			return getNumTerritory();
		case CHARACTER:
			return getNumCharacter();
		case ANY:
			break;
		default:
			break;
		}
		return 0;
	}

	public void incrementRightNum(CardType cardType) {
		switch (cardType) {
		case BUILDING:
			this.setNumBuilding(getNumBuilding()+1);
			break;
		case VENTURE:
			this.setNumVenture(getNumVenture()+1);
			break;
		case TERRITORY:
			this.setNumTerritory(getNumTerritory()+1);
			break;
		case CHARACTER:
			this.setNumCharacter(getNumCharacter()+1);
			break;
		case ANY:
			break;
		default:
			break;
		}
	}

	public PersonalBoardPanel getPersonalBoardPanel() {
		return personalBoardPanel;
	}
	
	

}
