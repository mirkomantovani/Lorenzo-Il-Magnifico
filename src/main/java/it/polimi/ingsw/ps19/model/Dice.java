package it.polimi.ingsw.ps19.model;

import java.io.Serializable;
import java.util.Random;

/**
 * The Enum Dice.
 */
public enum Dice implements Serializable {
	
	/** The orange dice. */
	//could return an ActionValue instead of an int?
	ORANGE_DICE(Color.ORANGE,5), 
 /** The black dice. */
 BLACK_DICE(Color.BLACK,0), 
 /** The white dice. */
 WHITE_DICE(Color.WHITE,0), 
 /** The neutral dice. */
 NEUTRAL_DICE(Color.NEUTRAL,0);
	
	/** The color. */
	private Color color;   //Vediamo come vogliamo modellare il colore, è un attributo
						   
   						/** The displayed face. */
   						//o basta il nome? io provo così
	private int displayedFace;
	
	/**
	 * Instantiates a new dice.
	 *
	 * @param color the color
	 * @param val the val
	 */
	private Dice(Color color, int val){
		this.color = color;
		this.displayedFace = val;
	}
	
	/**
	 * Roll.
	 */
	public void roll(){     					
		Random random = new Random();	
		if(this.color != Color.NEUTRAL)
			this.displayedFace = random.nextInt(6)+1;
	}
	
	/**
	 * Gets the upper face value.
	 *
	 * @return the upper face value
	 */
	public int getUpperFaceValue() {
		return displayedFace;
	}
	
	/**
	 * Gets the random face value.
	 *
	 * @return the random face value
	 */
	public int getRandomFaceValue(){   
		this.roll();
		return this.getUpperFaceValue();
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
}
