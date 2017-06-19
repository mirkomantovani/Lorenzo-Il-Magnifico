package it.polimi.ingsw.ps19.view.gui;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

public class MainProvaGui {
	
	public static void main(String[] argv){
		
		List<ImageButton> buttons;
		
		
		JDialog leaderFrame = new JDialog();
		
		leaderFrame.setResizable(false);
		leaderFrame.setTitle("Choose one leader card from the following:");
		buttons = new ArrayList<ImageButton>();
		
		
		leaderFrame.setVisible(true);
		leaderFrame.setContentPane(new Container());
	
		
		
		for(int i = 0; i<4; i++){
		
		buttons.add(new ImageButton("src/main/resources/leaders_f_c_01.jpg"));
		leaderFrame.getContentPane().add(buttons.get(i), buttons.get(i).getUI());
		buttons.get(i).setLocation(((i)*300), 0);
		}
		
		leaderFrame.setResizable(true);
		leaderFrame.setBounds(0, 0, 300*4+23, 555);
		

		
		
		
		
		
		
	}

}
