package it.polimi.ingsw.ps19.view.gui;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener interface for receiving event events.
 * The class that is interested in processing a event
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addEventListener<code> method. When
 * the event event occurs, that object's appropriate
 * method is invoked.
 *
 * @see EventEvent
 */
public class EventListener implements AWTEventListener{

	
	/* (non-Javadoc)
	 * @see java.awt.event.AWTEventListener#eventDispatched(java.awt.AWTEvent)
	 */
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
