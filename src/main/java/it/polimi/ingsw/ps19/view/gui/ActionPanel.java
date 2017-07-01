package it.polimi.ingsw.ps19.view.gui;

import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ActionPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ActionPanel() {
		 URL neutral = ActionPanel.class.getResource("/redneutralFamiliar.png");
		 URL orange = ActionPanel.class.getResource("/redorangeFamiliar.png");
		 URL white = ActionPanel.class.getResource("/redwhiteFamiliar.png");
		 URL black = ActionPanel.class.getResource("/redblackFamiliar.png");
	     String html = "<html><body><img src='" + neutral.toString() +"'width=128 height=128>";

		JRadioButton neutralB = new JRadioButton(html);
		
		  html = "<html><body><img src='" + orange.toString() +"'width=128 height=128>";

		JRadioButton orangeB = new JRadioButton(html);
		
		  html = "<html><body><img src='" + white.toString() +"'width=128 height=128>";

		JRadioButton whiteB = new JRadioButton(html);
		
		  html = "<html><body><img src='" + black.toString() +"'width=128 height=128>";

		JRadioButton blackB = new JRadioButton(html);
		
//		rdbtnNewRadioButton.setIcon(new ImageIcon(ActionPanel.class.getResource("/blueDisc.png")));
		add(neutralB);
		add(whiteB);
		add(orangeB);
		add(blackB);

	}

}
