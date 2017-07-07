package it.polimi.ingsw.ps19.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 * The Panel that lets the player choose the resources given by a council privilege
 *
 * @author Mirko
 *
 */
public class ChoosePrivilegePanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new choose privilege panel.
	 *
	 * @param resourceWidth the resource width
	 * @param listener the listener
	 */
	public ChoosePrivilegePanel(int resourceWidth, GamePanel listener){
//		setSize(new Dimension(resourceWidth, 1000));
		setMaximumSize(new Dimension(resourceWidth, 32767));
		
		
		
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setForeground(UIManager.getColor("ArrowButton.disabledText"));
		
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 40, 100));
	
//		resources=new HashMap<ResourceType,JResource>();
		
		JResource woodStone = new JResource("woodstone",resourceWidth);
		woodStone.setAmount(1);
		woodStone.setName("0");
		add(woodStone);
		woodStone.addMouseListener(listener);
		
		JResource servant = new JResource("servant",resourceWidth);
		servant.setAmount(2);
		add(servant);
		servant.setName("1");
//		resources.put(ResourceType.STONE, stone);
		servant.addMouseListener(listener);
		
		JResource coin = new JResource("coin",resourceWidth);
		coin.setAmount(2);
		add(coin);
		coin.setName("2");
//		resources.put(ResourceType.COIN, coin);
		coin.addMouseListener(listener);
		
		JResource military = new JResource("military",resourceWidth);
		military.setAmount(2);
		add(military);
		military.setName("3");
		military.addMouseListener(listener);
		
		JResource faith = new JResource("faith",resourceWidth);
		faith.setAmount(1);
		add(faith);
		faith.setName("4");
		faith.addMouseListener(listener);
//		resources.put(ResourceType.SERVANT, servant);
	}

}
