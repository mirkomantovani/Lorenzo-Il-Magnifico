package it.polimi.ingsw.ps19.view.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class MainProvaGui {
	
	public static void main(String[] argv){
		
		List<ImageButton> buttons;
		
		Dimension leaderDim = new Dimension(20,20);
		
		JDialog leaderFrame = new JDialog();
		buttons = new ArrayList<ImageButton>();
		
		leaderFrame.setVisible(true);
		
		for(int i = 0; i<4; i++){
		
		buttons.add(new ImageButton("src/main/resources/leaders_f_c_01.jpg"));
		leaderFrame.add(buttons.get(i));
		buttons.get(i).setLocation(((i+1)*300), 0);
		}
		
		
		
		
		
		
		
	}

}
