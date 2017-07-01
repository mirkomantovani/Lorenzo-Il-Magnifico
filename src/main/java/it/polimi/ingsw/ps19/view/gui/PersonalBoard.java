package it.polimi.ingsw.ps19.view.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PersonalBoard extends JFrame {
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalBoard frame = new PersonalBoard();
					frame.setVisible(true);
					
//					frame.getGamePanel().addCard();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PersonalBoard() {
		setUndecorated(true);
		setResizable(false);
		
		JPanel panel = new PersonalBoardPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(panel.getPreferredSize());
		setPreferredSize(panel.getPreferredSize());
	}

}
