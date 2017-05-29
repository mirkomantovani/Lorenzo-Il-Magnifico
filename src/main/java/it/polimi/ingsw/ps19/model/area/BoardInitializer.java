package it.polimi.ingsw.ps19.model.area;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author matteo
 *
 */
public class BoardInitializer {
	
	
	public static ArrayList<Integer> actionSpacesBonuses(String boardActionSpacesPath) throws FileNotFoundException, IOException{
		
		//This method initializes an arrayList order by descending floors and following the tower order set in CONVENTIONS
		//After the tower bonuses there are the bonuses given by the council palace 
		// and the two related to the Military Point placements
		ArrayList<Integer> bonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(boardActionSpacesPath));
		
		while(reader.readLine() != null){
		bonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return bonuses;
	}
	
	public static int[] churchBonuses(String boardBonusesPath) throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(boardBonusesPath));
		
		int[] bonuses = new int[15];
		
		int i = 0;
		
		while(reader.readLine()!=null){
			
			bonuses[i] = Integer.parseInt(reader.readLine());
			
		}
		
		return bonuses;
	}
	
	public static ArrayList<Integer> playerBoardBonuses(String playerBoardBonusesPath) throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(playerBoardBonusesPath));
		
		ArrayList<Integer> bonuses = new ArrayList<Integer>();
		
		while (reader.readLine() != null){
			
			bonuses.add(Integer.parseInt(reader.readLine()));
			
		}

	return bonuses;
	}
}
