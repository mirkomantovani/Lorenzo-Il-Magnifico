package it.polimi.ingsw.ps19.model.card;

/**
 * The Enum CardType.
 */
public enum CardType {
	
	/** The territory. */
	TERRITORY,/** The building. */
BUILDING,/** The character. */
CHARACTER,/** The venture. */
VENTURE, /** The any. */
 ANY;  //Mirko: I modified the order that HAS to stay like this (conventions)
	
	/**
   * Convert card type.
   *
   * @param cardType the card type
   * @return the card type
   */
  public static CardType convertCardType(int cardType){
		switch(cardType){
		case 1: return TERRITORY;
		case 2: return BUILDING;
		case 3: return CHARACTER;
		case 4: return VENTURE;
		case 5: return ANY;
		default: return ANY;
		}
	}
}
