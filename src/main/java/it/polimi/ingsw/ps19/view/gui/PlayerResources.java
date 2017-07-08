package it.polimi.ingsw.ps19.view.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.text.html.StyleSheet;

import it.polimi.ingsw.ps19.FamilyMember;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceType;
import java.awt.Dimension;

/**
 * The panel showing the resources of the player, note that the points aren't shown here
 * because everyone can see them in the board panel
 * @author Mirko
 *
 */
public class PlayerResources extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The resources. */
	private Map<ResourceType,JResource> resources;
	
	/** The family members. */
	private Map<it.polimi.ingsw.ps19.Color,JLabel> familyMembers;
	
	/** The family grid panel. */
	private JPanel familyGridPanel;
	
	private JLabel lblPlayername;
	
	
	/**
	 * Instantiates a new player resources.
	 *
	 * @param resourceWidth the resource width
	 * @param playerColor the player color
	 */
	public PlayerResources(int resourceWidth,String playerColor) {
		
		
		setSize(new Dimension(resourceWidth, 1000));
		setMaximumSize(new Dimension(resourceWidth, 32767));
		
		familyMembers=new HashMap<it.polimi.ingsw.ps19.Color,JLabel>();
		
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
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		lblPlayername = new JLabel("Waiting for Authentication");
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
		
		familyGridPanel = new JPanel();
		familyGridPanel.setBackground(new Color(r, g, b));
		add(familyGridPanel);
		familyGridPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel neutralFam = new JLabel("");
		ImageIcon neutral = new ImageIcon(ActionPanel.class.getResource("/"+playerColor.toLowerCase()+"neutralFamiliar.png"));
		Image img = neutral.getImage();
		img = img.getScaledInstance(img.getWidth(null) / 4, img.getHeight(null) / 4, java.awt.Image.SCALE_SMOOTH);
		neutral = new ImageIcon(img);
		neutralFam.setIcon(neutral);
		familyMembers.put(it.polimi.ingsw.ps19.Color.NEUTRAL,neutralFam);
		
		JLabel whiteFam = new JLabel("");
		ImageIcon white = new ImageIcon(ActionPanel.class.getResource("/"+playerColor.toLowerCase()+"whiteFamiliar.png"));
		img = white.getImage();
		img = img.getScaledInstance(img.getWidth(null) / 4, img.getHeight(null) / 4, java.awt.Image.SCALE_SMOOTH);
		white = new ImageIcon(img);
		whiteFam.setIcon(white);
		familyMembers.put(it.polimi.ingsw.ps19.Color.WHITE,whiteFam);
		
		JLabel orangeFam = new JLabel("");
		ImageIcon orange = new ImageIcon(ActionPanel.class.getResource("/"+playerColor.toLowerCase()+"orangeFamiliar.png"));
		img = orange.getImage();
		img = img.getScaledInstance(img.getWidth(null) / 4, img.getHeight(null) / 4, java.awt.Image.SCALE_SMOOTH);
		orange = new ImageIcon(img);
		orangeFam.setIcon(orange);
		familyMembers.put(it.polimi.ingsw.ps19.Color.ORANGE,orangeFam);
		
		JLabel blackFam = new JLabel("");
		ImageIcon black = new ImageIcon(ActionPanel.class.getResource("/"+playerColor.toLowerCase()+"blackFamiliar.png"));
		img = black.getImage();
		img = img.getScaledInstance(img.getWidth(null) / 4, img.getHeight(null) / 4, java.awt.Image.SCALE_SMOOTH);
		black = new ImageIcon(img);
		blackFam.setIcon(black);
		familyMembers.put(it.polimi.ingsw.ps19.Color.BLACK,blackFam);
		
		addAllFamilyMembers();
	}
	

	
	/**
	 * Refresh resource.
	 *
	 * @param r the r
	 */
	public void refreshResource(Resource r){
		resources.get(r.getResourceType()).setAmount(r.getAmount());
	}
	
	/**
	 * Removes the all family members.
	 */
	public void removeAllFamilyMembers(){
		familyGridPanel.removeAll();
	}
	
	/**
	 * Adds the all family members.
	 */
	public void addAllFamilyMembers(){
		
		for(int i=0;i<it.polimi.ingsw.ps19.Color.values().length;i++)
		familyGridPanel.add(familyMembers.get(it.polimi.ingsw.ps19.Color.values()[i]));
	}



	/**
	 * Refresh family members.
	 *
	 * @param families the families
	 */
	public void refreshFamilyMembers(HashMap<it.polimi.ingsw.ps19.Color, FamilyMember> families) {
		this.removeAllFamilyMembers();
//		for(FamilyMember mem : families.values()){
//			familyGridPanel.add(familyMembers.get(mem.getColor()));
//		}
		
		
		 Iterator<it.polimi.ingsw.ps19.Color> iterator = families.keySet().iterator(); //ottengo l'iterator tipizzato delle chiavi
		         
		 for (;iterator.hasNext();) //ciclo con l'iterator
		           familyGridPanel.add(familyMembers.get(iterator.next()));
		      
	}
	
	public void setUsername(String name){
		lblPlayername.setText(name);
	}
	
	
	
}
