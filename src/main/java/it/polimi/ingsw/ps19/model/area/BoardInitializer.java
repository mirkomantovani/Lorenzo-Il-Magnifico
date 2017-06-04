package it.polimi.ingsw.ps19.model.area;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.model.card.CardConstants;
import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.effect.MultipleEffect;
import it.polimi.ingsw.ps19.model.effect.NoEffect;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
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
	private final static String PERSONAL_BONUS_TILES = "src/main/resources/files/filepersonalbonustiles.txt";
	
	
	public static ArrayList<Resource> territoryBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> territoryBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_TERRITORY_TOWER));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		territoryBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		
		return territoryBonuses;
	}
	
	public static ArrayList<Resource> buildingBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> buildingBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_BUILDING_TOWER));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		buildingBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		return buildingBonuses;
	}
	
	public static ArrayList<Resource> characterBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> characterBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_CHARACTER_TOWER));
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		characterBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		
		return characterBonuses;
	}
	
	public static ArrayList<Resource> ventureBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> ventureBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_VENTURE_TOWER));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		ventureBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		
		return ventureBonuses;
	}
	
	public static MultipleEffect councilPalaceBonuses() throws FileNotFoundException, IOException{
		

		BufferedReader reader = new BufferedReader(new FileReader(BONUSES_COUNCIL_PALACE));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			if(Integer.parseInt(lineRead)== 8){
				CouncilPrivilegeEffect council = new CouncilPrivilegeEffect(Integer.parseInt(reader.readLine()));
				ResourceChest chest = new ResourceChest();
				Resource resource = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine())-1],Integer.parseInt(reader.readLine()));
				chest.addResource(resource);
				return new MultipleEffect(council,new InstantResourcesEffect(chest));
			} else {
				ResourceChest chest = new ResourceChest();
				Resource resource = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead)-1],Integer.parseInt(reader.readLine()));
				chest.addResource(resource);
				if(Integer.parseInt(reader.readLine())==8){
					CouncilPrivilegeEffect council = new CouncilPrivilegeEffect(Integer.parseInt(reader.readLine()));
					return new MultipleEffect(new InstantResourcesEffect(chest),council);
				} else {
					ResourceChest chest2 = new ResourceChest();
					Resource resource2 = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine())-1],Integer.parseInt(reader.readLine()));
					chest.addResource(resource2);
					return new MultipleEffect(new InstantResourcesEffect(chest), new InstantResourcesEffect(chest2));
				}
				
				
			}
			
			
		
		}
			return new MultipleEffect(new NoEffect(),new NoEffect());
		
	}
	
	public static int[] churchBonuses() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(CHURCH_BONUSES));
		
		int[] bonuses = new int[15];
		
		int i = 0;
		
		String lineRead = reader.readLine();
		
		while(lineRead !=null){
			
			bonuses[i] = Integer.parseInt(reader.readLine());
			lineRead = reader.readLine();
		}
		
		return bonuses;
	}
	
	public static ArrayList<Integer> playerBoardBonusesForTerritory() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(TERRITORYCARD_PLAYER_BONUSES));
		
		ArrayList<Integer> bonusesForTerritory = new ArrayList<Integer>();
		
		String lineRead = reader.readLine();
		
		while (lineRead != null){
			
			bonusesForTerritory.add(Integer.parseInt(reader.readLine()));
			lineRead = reader.readLine();
		}

	return bonusesForTerritory;
	}
	
	public static ArrayList<Integer> playerBoardBonusesForCharacter() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(CHARACTERCARD_PLAYER_BONUSES));
		
		ArrayList<Integer> bonusesForCharacter = new ArrayList<Integer>();
		String lineRead = reader.readLine();
		
		while (lineRead != null){
			
			bonusesForCharacter.add(Integer.parseInt(reader.readLine()));
			lineRead = reader.readLine();
		}

	return bonusesForCharacter;
	}
	
	public static ArrayList<Integer> playerBoardRequirementsForTerritory() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(TERRITORYCARD_PLAYER_REQUIREMENTS));
		
		ArrayList<Integer> requirementsForTerritory = new ArrayList<Integer>();
		String lineRead = reader.readLine();
		
		
		while (lineRead != null){
			
			requirementsForTerritory.add(Integer.parseInt(reader.readLine()));
			lineRead = reader.readLine();
		}

	return requirementsForTerritory;
	}
	
	public static PersonalBonusTile[] createPersonalBonusTiles(int tilesNumber) throws NumberFormatException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(PERSONAL_BONUS_TILES));
		PersonalBonusTile[] tiles = new PersonalBonusTile[tilesNumber];
		InstantResourcesEffect productionEffect;
		InstantResourcesEffect harvestEffect;
		int index = 0;
		
		String lineRead = reader.readLine();
		while(lineRead != null){
			productionEffect = new InstantResourcesEffect(new ResourceChest(
						Integer.parseInt(lineRead), Integer.parseInt(reader.readLine()),
						Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
						Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
						Integer.parseInt(reader.readLine())));
		
	        harvestEffect = new InstantResourcesEffect(new ResourceChest(
						Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
						Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
						Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()),
						Integer.parseInt(reader.readLine())));
			
			lineRead = reader.readLine();
			tiles[index] = new PersonalBonusTile(productionEffect, harvestEffect);
			index++;
		}
		return tiles;
	}
	
	
}
