package it.polimi.ingsw.ps19;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DeckCreator {

	private BufferedReader bufferedReader;
	private String lineRead;

	public BuildingCard[] createBuildingCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		BuildingCard[] deck = new BuildingCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		while (lineRead!=null) {
			for (int line = 0; line < CardConstants.BUILDINGCARD_FILE_ENTRIES; line++) {
				//code to instance a card
			}
			lineRead=bufferedReader.readLine();
			cardId++;
		}
		return deck;
	}
	
	public TerritoryCard[] createTerritoryCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		TerritoryCard[] deck = new TerritoryCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		while (lineRead!=null) {
			for (int line = 0; line < CardConstants.BUILDINGCARD_FILE_ENTRIES; line++) {
				//code to instance a card
			}
			lineRead=bufferedReader.readLine();
			cardId++;
		}
		return deck;
	}
	
	public VentureCard[] createVentureCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		VentureCard[] deck = new VentureCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		while (lineRead!=null) {
			for (int line = 0; line < CardConstants.BUILDINGCARD_FILE_ENTRIES; line++) {
				//code to instance a card
			}
			lineRead=bufferedReader.readLine();
			cardId++;
		}
		return deck;
	}
	
	public CharacterCard[] createCharacterCardDeck(String filePath, int deckLength) throws IOException {

		int cardId=0;
		CharacterCard[] deck = new CharacterCard[deckLength];

		bufferedReader = new BufferedReader(new FileReader(filePath));
		while (lineRead!=null) {
			for (int line = 0; line < CardConstants.BUILDINGCARD_FILE_ENTRIES; line++) {
				//code to instance a card
			}
			lineRead=bufferedReader.readLine();
			cardId++;
		}
		return deck;
	}

	// private Effect createEffect(){
	//
	// }

}
