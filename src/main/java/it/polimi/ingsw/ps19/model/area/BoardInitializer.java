package it.polimi.ingsw.ps19.model.area;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceFactory;
import it.polimi.ingsw.ps19.model.resource.ResourceType;


/**
 * @author matteo
 *
 */
public class BoardInitializer {
	
	private final static String BONUSES_TERRITORY_TOWER = "src/main/resources/files/fileboardterritorybonuses.txt";
	private final static String BONUSES_BUILDING_TOWER = "src/main/resources/files/fileboardbuildingbonuses.txt";
	private final static String BONUSES_CHARACTER_TOWER = "src/main/resources/files/fileboardcharacterbonuses.txt";
	private final static String BONUSES_VENTURE_TOWER = "src/main/resources/files/fileboardventurebonuses.txt";
	private final static String BONUSES_COUNCIL_PALACE = "src/main/resources/files/fileboardcouncilpalacebonuses.txt";
	private final static String TERRITORYCARD_PLAYER_BONUSES = "src/main/resources/files/fileplayerboardbonusesforterritory.txt";
	private final static String TERRITORYCARD_PLAYER_REQUIREMENTS = "src/main/resources/files/fileplayerboardrequirementsforterritory.txt";
	private final static String CHARACTERCARD_PLAYER_BONUSES = "src/main/resources/files/fileplayerboardbonusesforcharacter.txt";
	private final static String CHURCH_BONUSES = "src/main/resources/files/filefaithpointbonuses.txt";
	
	
	public static ArrayList<Resource> territoryBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> territoryBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_TERRITORY_TOWER));
		
		while(reader.readLine() != null){
		territoryBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
		territoryBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
		territoryBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
		territoryBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
		
		}
		
		return territoryBonuses;
	}
	
	public static ArrayList<Resource> buildingBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> buildingBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_BUILDING_TOWER));
		
		while(reader.readLine() != null){
			buildingBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			buildingBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			buildingBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			buildingBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			
		}
		
		return buildingBonuses;
	}
	
	public static ArrayList<Resource> characterBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> characterBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_CHARACTER_TOWER));
		
		while(reader.readLine() != null){
			characterBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			characterBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			characterBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			characterBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			
		}
		
		return characterBonuses;
	}
	
	public static ArrayList<Resource> ventureBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> ventureBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_VENTURE_TOWER));
		
		while(reader.readLine() != null){
			ventureBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			ventureBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			ventureBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			ventureBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine()) - 1], Integer.parseInt(reader.readLine())));
			
		}
		
		return ventureBonuses;
	}
	
	public static ArrayList<Integer> councilPalaceBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Integer> councilPalaceBonuses = new ArrayList<Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_COUNCIL_PALACE));
		
		while(reader.readLine() != null){
		councilPalaceBonuses.add(Integer.parseInt(reader.readLine()));
		}
		
		return councilPalaceBonuses;
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
