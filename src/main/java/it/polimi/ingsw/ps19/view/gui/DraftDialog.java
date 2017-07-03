package it.polimi.ingsw.ps19.view.gui;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

public class DraftDialog extends JDialog{

	public DraftDialog(ArrayList<String> leaders){
	List<ImageButton> buttons;
	
	setResizable(false);
	setTitle("Choose one leader card from the following:");
	buttons = new ArrayList<ImageButton>();
	
	
	setVisible(true);
	setContentPane(new Container());

	
	
	for(int i = 0; i<leaders.size(); i++){
	
	buttons.add(new ImageButton("src/main/resources/leadercardimages/"+ leaders.get(i) +".jpg"));
	getContentPane().add(buttons.get(i), buttons.get(i).getUI());
	buttons.get(i).setLocation(((i)*300), 0);
	
	}
	
	setResizable(false);
	setBounds(0, 0, 300*4, 550);
	}
}
