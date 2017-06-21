package it.polimi.ingsw.ps19.view.gui;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventListener implements AWTEventListener{

	
	@Override
	public void eventDispatched(AWTEvent event) {
	      if(event instanceof MouseEvent){
	          MouseEvent evt = (MouseEvent)event;
	          if(evt.getID() == MouseEvent.MOUSE_CLICKED){
	             new MouseEvent(((MouseEvent) event).getComponent(), MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, false);
	          }
		
	      }
	}
	


	
}
