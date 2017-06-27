package it.polimi.ingsw.ps19.view.gui;

import javax.swing.JPanel;

public class MarkerDisk extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409734849829937305L;
	
	private String src; //red,blue,green,yellow
	private final static int DISC_DIAM = 36;
	
	
	

	
	public MarkerDisk(String color){
		this.src = color;
		
	}
	

}
