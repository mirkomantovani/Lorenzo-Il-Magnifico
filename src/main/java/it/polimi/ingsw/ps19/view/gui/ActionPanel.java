package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

/**
 * The panel that allows the construction of an action, letting the user choose
 * between the family members and the servants he wants to use in the action
 * 
 * @author Mirko
 *
 */
public class ActionPanel extends JPanel {

	private ButtonGroup familyGroup;

	/**
	 * Create the panel.
	 */
	public ActionPanel() {
		setBackground(new Color(222, 184, 135));
		familyGroup = new ButtonGroup();

		URL neutral = ActionPanel.class.getResource("/redneutralFamiliar.png");
		URL orange = ActionPanel.class.getResource("/redorangeFamiliar.png");
		URL white = ActionPanel.class.getResource("/redwhiteFamiliar.png");
		URL black = ActionPanel.class.getResource("/redblackFamiliar.png");
		
		String html = "<html><body><img src='" + neutral.toString() + "'width=200 height=200>";

		setLayout(new BorderLayout(0, 0));
		Font font = new Font("Serif", Font.ITALIC, 15);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 184, 135));
		add(panel, BorderLayout.NORTH);

		JRadioButton neutralB = new JRadioButton(html);
		panel.add(neutralB);

		html = "<html><body><img src='" + orange.toString() + "'width=200 height=200>";

		familyGroup.add(neutralB);

		JRadioButton whiteB = new JRadioButton(html);
		panel.add(whiteB);
		familyGroup.add(whiteB);

		html = "<html><body><img src='" + white.toString() + "'width=200 height=200>";

		JRadioButton orangeB = new JRadioButton(html);
		panel.add(orangeB);
		familyGroup.add(orangeB);

		html = "<html><body><img src='" + black.toString() + "'width=200 height=200>";

		JRadioButton blackB = new JRadioButton(html);
		panel.add(blackB);
		familyGroup.add(blackB);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(222, 184, 135));
		add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(null);
		
				JLabel label = new JLabel("");
				label.setBounds(225, 16, 202, 350);
				panel_1.add(label);
				ImageIcon servant=new ImageIcon(ActionPanel.class.getResource("/servant.png"));
				
				Image img=servant.getImage();
				
				img=img.getScaledInstance(img.getWidth(null)/2, img.getHeight(null)/2, java.awt.Image.SCALE_SMOOTH);
					
				servant=new ImageIcon(img);
						
				label.setIcon(servant);
				
				
						JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
						slider.setBounds(534, 172, 200, 71);
						panel_1.add(slider);
						slider.setMajorTickSpacing(2);
						slider.setMinorTickSpacing(1);
						slider.setPaintTicks(true);
						slider.setPaintLabels(true);
						slider.setSnapToTicks(true);
						slider.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
						slider.setFont(font);

	}

}
