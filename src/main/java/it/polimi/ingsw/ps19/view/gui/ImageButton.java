package it.polimi.ingsw.ps19.view.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

	private Image img;
	private ClickListener listener;


	public ImageButton(String img) {
		this.img = new ImageIcon(img).getImage().getScaledInstance(300,500, 700);
		this.setBounds(0, 0, 300, 500);
		this.setVisible(true);
		listener = new ClickListener();
	}

	public ImageButton(Image img) {
		this.img = img;
		Dimension size = new Dimension(20, 20);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	class ClickListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
	}
	
	public ClickListener getCLickListener(){
		return this.listener;
	}

}