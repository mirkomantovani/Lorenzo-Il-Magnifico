package it.polimi.ingsw.ps19.model.card;

public enum CardType {
	
	TERRITORY,BUILDING,CHARACTER,VENTURE, ANY;  //Mirko: I modified the order that HAS to stay like this (conventions)
	
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
