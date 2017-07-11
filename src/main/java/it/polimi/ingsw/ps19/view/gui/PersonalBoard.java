package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import it.polimi.ingsw.ps19.model.card.CardType;

/**
 * The player personal board, it extends JFrame, and it's the only "external" frame
 * in the GUI
 * @author Mirko
 *
 */
public class PersonalBoard extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The num building. */
	private int numBuilding;
	
	/** The num venture. */
	private int numVenture;
	
	/** The num territory. */
	private int numTerritory;
	
	/** The num character. */
	private int numCharacter;
	
	/** The personal board panel. */
	private PersonalBoardPanel personalBoardPanel;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
	
	/**
	 * Instantiates a new personal board.
	 */
	public PersonalBoard() {
		setUndecorated(true);
		setResizable(false);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("/MJMLogoTransparent.png")));
		
		personalBoardPanel = new PersonalBoardPanel();
		getContentPane().add(personalBoardPanel, BorderLayout.CENTER);
		setSize(personalBoardPanel.getPreferredSize());
		setPreferredSize(personalBoardPanel.getPreferredSize());
		
//		JDevelopmentCard j=new JDevelopmentCard(CardType.CHARACTER, 25, 2);
//		personalBoardPanel.add(j);
	}

	/**
	 * Gets the num building.
	 *
	 * @return the num building
	 */
	public int getNumBuilding() {
		return numBuilding;
	}

	/**
	 * Sets the num building.
	 *
	 * @param numBuilding the new num building
	 */
	public void setNumBuilding(int numBuilding) {
		this.numBuilding = numBuilding;
	}

	/**
	 * Gets the num venture.
	 *
	 * @return the num venture
	 */
	public int getNumVenture() {
		return numVenture;
	}

	/**
	 * Sets the num venture.
	 *
	 * @param numVenture the new num venture
	 */
	public void setNumVenture(int numVenture) {
		this.numVenture = numVenture;
	}

	/**
	 * Gets the num territory.
	 *
	 * @return the num territory
	 */
	public int getNumTerritory() {
		return numTerritory;
	}

	/**
	 * Sets the num territory.
	 *
	 * @param numTerritory the new num territory
	 */
	public void setNumTerritory(int numTerritory) {
		this.numTerritory = numTerritory;
	}

	/**
	 * Gets the num character.
	 *
	 * @return the num character
	 */
	public int getNumCharacter() {
		return numCharacter;
	}

	/**
	 * Sets the num character.
	 *
	 * @param numCharacter the new num character
	 */
	public void setNumCharacter(int numCharacter) {
		this.numCharacter = numCharacter;
	}

	/**
	 * Gets the right num.
	 *
	 * @param cardType the card type
	 * @return the right num
	 */
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

	/**
	 * Increment right num.
	 *
	 * @param cardType the card type
	 */
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

	/**
	 * Gets the personal board panel.
	 *
	 * @return the personal board panel
	 */
	public PersonalBoardPanel getPersonalBoardPanel() {
		return personalBoardPanel;
	}
	
	

}
