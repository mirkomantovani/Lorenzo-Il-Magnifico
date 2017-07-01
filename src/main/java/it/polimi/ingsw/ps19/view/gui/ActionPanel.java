package it.polimi.ingsw.ps19.view.gui;

import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ActionPanel.class.getResource("/servant.png")));
		add(label);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL,
                0, 10, 0);
		slider.setMajorTickSpacing(2);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		Font font = new Font("Serif", Font.ITALIC, 15);
		slider.setFont(font);
		add(slider);
		
		

	}

}
