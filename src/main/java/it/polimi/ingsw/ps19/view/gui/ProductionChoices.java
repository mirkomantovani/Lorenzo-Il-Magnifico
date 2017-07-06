package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

/**
 * The Class ProductionChoices.
 *
 * @author Mirko
 */
public class ProductionChoices extends JPanel implements ActionListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cards. */
	private List<CardButton> cards;
	
	/** The toggles. */
	private List<JToggleButton> toggles;
	
	/** The observer. */
	private GamePanel observer;

	/**
	 * Instantiates a new production choices.
	 *
	 * @param observer the observer
	 */
	public ProductionChoices(GamePanel observer) {
		this.observer=observer;
		
		toggles=new ArrayList<>();

		cards = new ArrayList<>();

	}

	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	private ArrayList<Integer> getChoices() {
		ArrayList<Integer> choices=new ArrayList<Integer>();
		for(JToggleButton toggle: toggles){
			if(toggle.isSelected()){
				choices.add(2);
			}
			else{
				choices.add(1);
			}
		}
		return choices;
		
	}

	/**
	 * Adds the card and toggle.
	 *
	 * @param cardId the card id
	 */
	private void addCardAndToggle(int cardId) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 51));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		CardButton c = new CardButton(cardId);
		panel.add(c, BorderLayout.NORTH);

		JToggleButton toggleButton = new JToggleButton("Normal Effect");
		panel.add(toggleButton, BorderLayout.SOUTH);
		toggleButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		toggleButton.setBackground(new Color(102, 51, 51));
		toggleButton.setForeground(new Color(255, 255, 255));
		toggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		toggles.add(toggleButton);
		
		
		toggleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (toggleButton.isSelected()) {
					toggleButton.setText("Alternative effect");
				} else {
					toggleButton.setText("Normal Effect");
				}
			}
		});

	}

	/**
	 * Adds the cards.
	 *
	 * @param cardIds the card ids
	 */
	public void addCards(ArrayList<Integer> cardIds) {

		for (Integer cardId : cardIds) {

			this.addCardAndToggle(cardId);
		}
		
		JButton activateProductionButton = new JButton("Activate Production!");

		activateProductionButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		activateProductionButton.setBackground(new Color(102, 51, 51));
		activateProductionButton.setForeground(new Color(255, 255, 255));
		activateProductionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		activateProductionButton.addActionListener(this);
		add(activateProductionButton);

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		observer.notifyActivateProduction(this.getChoices());
		
	}
}

