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
	
	private final static String BONUSES_TERRITORY_TOWER = "src/main/resources/fileboardterritorybonuses.txt";
	private final static String BONUSES_BUILDING_TOWER = "src/main/resources/fileboardbuildingbonuses.txt";
	private final static String BONUSES_CHARACTER_TOWER = "src/main/resources/fileboardcharacterbonuses.txt";
	private final static String BONUSES_VENTURE_TOWER = "src/main/resources/fileboardventurebonuses.txt";
	private final static String BONUSES_COUNCIL_PALACE = "src/main/resources/fileboardcouncilpalacebonuses.txt";
	private final static String TERRITORYCARD_PLAYER_BONUSES = "src/main/resources/fileplayerboardbonusesforterritory.txt";
	private final static String TERRITORYCARD_PLAYER_REQUIREMENTS = "src/main/resources/fileplayerboardrequirementsforterritory.txt";
	private final static String CHARACTERCARD_PLAYER_BONUSES = "src/main/resources/fileplayerboardbonusesforcharacter.txt";
	private final static String CHURCH_BONUSES = "src/main/resources/filefaithpointbonuses.txt";
	
	
	public static ArrayList<Integer> territoryBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> territoryBonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_TERRITORY_TOWER));
		
		while(reader.readLine() != null){
		territoryBonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return territoryBonuses;
	}
	
	public static ArrayList<Integer> buildingBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> buildingBonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_BUILDING_TOWER));
		
		while(reader.readLine() != null){
		buildingBonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return buildingBonuses;
	}
	
	public static ArrayList<Integer> characterBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> characterBonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_CHARACTER_TOWER));
		
		while(reader.readLine() != null){
		characterBonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return characterBonuses;
	}
	
	public static ArrayList<Integer> ventureBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> ventureBonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_VENTURE_TOWER));
		
		while(reader.readLine() != null){
		ventureBonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return ventureBonuses;
	}
	
	public static ArrayList<Integer> privilegeBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> privilegeBonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_COUNCIL_PALACE));
		
		while(reader.readLine() != null){
		privilegeBonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return privilegeBonuses;
	}
	
	public static int[] churchBonuses() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(CHURCH_BONUSES));
		
		int[] bonuses = new int[15];
		
		int i = 0;
		
		while(reader.readLine()!=null){
			
			bonuses[i] = Integer.parseInt(reader.readLine());
			
		}
		
		return bonuses;
	}
	
	public static ArrayList<Integer> playerBoardBonusesForTerritory() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(TERRITORYCARD_PLAYER_BONUSES));
		
		ArrayList<Integer> bonusesForTerritory = new ArrayList<Integer>();
		
		while (reader.readLine() != null){
			
			bonusesForTerritory.add(Integer.parseInt(reader.readLine()));
			
		}

	return bonusesForTerritory;
	}
	
	public static ArrayList<Integer> playerBoardBonusesForCharacter() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(CHARACTERCARD_PLAYER_BONUSES));
		
		ArrayList<Integer> bonusesForCharacter = new ArrayList<Integer>();
		
		while (reader.readLine() != null){
			
			bonusesForCharacter.add(Integer.parseInt(reader.readLine()));
			
		}

	return bonusesForCharacter;
	}
	
	public static ArrayList<Integer> playerBoardRequirementsForTerritory(String playerBoardBonusesPath) throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(TERRITORYCARD_PLAYER_REQUIREMENTS));
		
		ArrayList<Integer> requirementsForTerritory = new ArrayList<Integer>();
		
		while (reader.readLine() != null){
			
			requirementsForTerritory.add(Integer.parseInt(reader.readLine()));
			
		}

	return requirementsForTerritory;
	}
}
