package it.polimi.ingsw.ps19.view.gui;

import java.awt.AWTEvent;
import java.awt.Container;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

public class MainProvaGui implements MouseListener{
	
	private static ArrayList<EventListener> eventListener;
	
	public static void main(String[] argv){
		
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run(){
		
				List<ImageButton> buttons;
				
				eventListener = new ArrayList<EventListener>();
					
			
				JDialog leaderFrame = new JDialog();
				
				leaderFrame.setResizable(false);
				leaderFrame.setTitle("Choose one leader card from the following:");
				buttons = new ArrayList<ImageButton>();
				
				
				leaderFrame.setVisible(true);
				leaderFrame.setContentPane(new Container());
			
				
				
				for(int i = 0; i<4; i++){
				
				buttons.add(new ImageButton("src/main/resources/leadercardimages/Ludovico Ariosto.jpg"));
				leaderFrame.getContentPane().add(buttons.get(i), buttons.get(i).getUI());
				buttons.get(i).setLocation(((i)*300), 0);
				
				}
				
				leaderFrame.setResizable(false);
				leaderFrame.setBounds(0, 0, 300*4, 550);
				
				
//		        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
//		            public void eventDispatched(AWTEvent event) {
//		                if(event instanceof MouseEvent){
//		                    MouseEvent evt = (MouseEvent)event;
//		                    if(evt.getID() == MouseEvent.MOUSE_CLICKED){
//		                        if(evt.getComponent() == buttons.get(0)){
//		                        	System.out.println("ho cliccato il primo bottone");
//		                        }
//		                    }
//		                }
//		            }
//		        }, AWTEvent.MOUSE_EVENT_MASK);
				
			}
		
		});
		
	  }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	}


