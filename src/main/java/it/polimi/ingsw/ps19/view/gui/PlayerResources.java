package it.polimi.ingsw.ps19.view.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.text.html.StyleSheet;

import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceType;

public class PlayerResources extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<ResourceType,JResource> resources;
	
	
	
	public PlayerResources(int resourceWidth,String playerColor) {
		
		
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setForeground(UIManager.getColor("ArrowButton.disabledText"));
		
		StyleSheet s = new StyleSheet();
		String rgb=playerColor.toLowerCase();   //I'll set this to playeColor.tolowercase, the color shade can change dynamically based on the resources for example
		Color c1 = s.stringToColor(rgb);
		int r=c1.getRed();
		int g=c1.getGreen();
		int b=c1.getBlue();
		
		if(r==0)r+=80;
		if(g==0)g+=60;
		if(b==0)b+=60;
		
		setBackground(new Color(r, g, b));
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPlayername = new JLabel("PlayerName");
		lblPlayername.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(lblPlayername);
		
		resources=new HashMap<ResourceType,JResource>();
		
		JResource stone = new JResource("stone",resourceWidth);
		add(stone);
		resources.put(ResourceType.STONE, stone);
		
		JResource wood = new JResource("wood",resourceWidth);
		add(wood);
		resources.put(ResourceType.WOOD, wood);
		
		JResource coin = new JResource("coin",resourceWidth);
		add(coin);
		resources.put(ResourceType.COIN, coin);
		
		JResource servant = new JResource("servant",resourceWidth);
		add(servant);
		resources.put(ResourceType.SERVANT, servant);
	}
	
//	public void setBackgroundColor(String color){
//		StyleSheet s = new StyleSheet();
//		Color c1 = s.stringToColor(color);
//		int r=c1.getRed();
//		int g=c1.getGreen();
//		int b=c1.getBlue();
//		
//		if(r==0)r+=100;
//		if(g==0)g+=100;
//		if(b==0)b+=100;
//		
//		setBackground(new Color(r, g, b));
//	}
	
	public void refreshResource(Resource r){
		resources.get(r.getResourceType()).setAmount(r.getAmount());
	}
	
	
	
}
