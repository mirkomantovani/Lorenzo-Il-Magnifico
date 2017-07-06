package it.polimi.ingsw.ps19.model.area;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import it.polimi.ingsw.ps19.PersonalBonusTile;
import it.polimi.ingsw.ps19.constant.CardConstants;
import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.model.effect.CouncilPrivilegeEffect;
import it.polimi.ingsw.ps19.model.effect.InstantResourcesEffect;
import it.polimi.ingsw.ps19.model.effect.MultipleEffect;
import it.polimi.ingsw.ps19.model.effect.NoEffect;
import it.polimi.ingsw.ps19.model.resource.MilitaryPoint;
import it.polimi.ingsw.ps19.model.resource.Resource;
import it.polimi.ingsw.ps19.model.resource.ResourceChest;
import it.polimi.ingsw.ps19.model.resource.ResourceFactory;
import it.polimi.ingsw.ps19.model.resource.ResourceType;


/**
 * The Class BoardInitializer.
 *
 * @author matteo
 */
public class BoardInitializer {
	
	
	
	
	/**
	 * Territory bonuses.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Resource> territoryBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> territoryBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.BONUSES_TERRITORY_TOWER));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		territoryBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		reader.close();
		return territoryBonuses;
	}
	
	/**
	 * Building bonuses.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Resource> buildingBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> buildingBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.BONUSES_BUILDING_TOWER));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		buildingBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		reader.close();
		return buildingBonuses;
	}
	
	/**
	 * Character bonuses.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Resource> characterBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> characterBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.BONUSES_CHARACTER_TOWER));
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		characterBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		reader.close();
		return characterBonuses;
	}
	
	/**
	 * Venture bonuses.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Resource> ventureBonuses() throws FileNotFoundException, IOException{
		
		ArrayList<Resource> ventureBonuses = new ArrayList<Resource>();
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.BONUSES_VENTURE_TOWER));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			
		ventureBonuses.add(ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead) - 1], Integer.parseInt(reader.readLine())));

		lineRead = reader.readLine();
		
		}
		reader.close();
		return ventureBonuses;
	}
	
	/**
	 * Council palace bonuses.
	 *
	 * @return the multiple effect
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MultipleEffect councilPalaceBonuses() throws FileNotFoundException, IOException{
		

		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.BONUSES_COUNCIL_PALACE));
		
		String lineRead = reader.readLine();
		
		while(lineRead != null){
			if(Integer.parseInt(lineRead)== 8){
				CouncilPrivilegeEffect council = new CouncilPrivilegeEffect(Integer.parseInt(reader.readLine()));
				ResourceChest chest = new ResourceChest();
				Resource resource = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine())-1],Integer.parseInt(reader.readLine()));
				chest.addResource(resource);
				reader.close();
				return new MultipleEffect(council,new InstantResourcesEffect(chest));
			} else {
				ResourceChest chest = new ResourceChest();
				Resource resource = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead)-1],Integer.parseInt(reader.readLine()));
				chest.addResource(resource);
				if(Integer.parseInt(reader.readLine())==8){
					CouncilPrivilegeEffect council = new CouncilPrivilegeEffect(Integer.parseInt(reader.readLine()));
					reader.close();
					return new MultipleEffect(new InstantResourcesEffect(chest),council);
				} else {
					ResourceChest chest2 = new ResourceChest();
					Resource resource2 = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine())-1],Integer.parseInt(reader.readLine()));
					chest.addResource(resource2);
					reader.close();
					return new MultipleEffect(new InstantResourcesEffect(chest), new InstantResourcesEffect(chest2));
				}
				
				
			}
			
			
		
		}
			reader.close();
			return new MultipleEffect(new NoEffect(),new NoEffect());
		
	}
	
	/**
	 * Church bonuses.
	 *
	 * @return the military point[]
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MilitaryPoint[] churchBonuses() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.CHURCH_BONUSES));
		
		int[] bonuses = new int[15];
		
		int i = 0;
		
		String lineRead = reader.readLine();
		
		while(lineRead !=null){
			
			bonuses[i] = Integer.parseInt(reader.readLine());
			lineRead = reader.readLine();
		}

		reader.close();

		
		MilitaryPoint[] military = new MilitaryPoint[15];
		
		for(int j = 0; j<bonuses.length; j++){
			military[j] = new MilitaryPoint(bonuses[j]);
		}
		
		return military;

	}
	
	/**
	 * Player board bonuses for territory.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Integer> playerBoardBonusesForTerritory() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.TERRITORYCARD_PLAYER_BONUSES));
		
		ArrayList<Integer> bonusesForTerritory = new ArrayList<Integer>();
		
		String lineRead = reader.readLine();
		
		while (lineRead != null){
			
			bonusesForTerritory.add(Integer.parseInt(reader.readLine()));
			lineRead = reader.readLine();
		}
		reader.close();
		return bonusesForTerritory;
	}
	
	/**
	 * Player board bonuses for character.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Integer> playerBoardBonusesForCharacter() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.CHARACTERCARD_PLAYER_BONUSES));
		
		ArrayList<Integer> bonusesForCharacter = new ArrayList<Integer>();
		String lineRead = reader.readLine();
		
		while (lineRead != null){
			
			bonusesForCharacter.add(Integer.parseInt(reader.readLine()));
			lineRead = reader.readLine();
		}
		reader.close();
		return bonusesForCharacter;
	}
	
	/**
	 * Player board requirements for territory.
	 *
	 * @return the array list
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ArrayList<Integer> playerBoardRequirementsForTerritory() throws FileNotFoundException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.TERRITORYCARD_PLAYER_REQUIREMENTS));
		
		ArrayList<Integer> requirementsForTerritory = new ArrayList<Integer>();
		String lineRead = reader.readLine();
		
		
		while (lineRead != null){
			
			requirementsForTerritory.add(Integer.parseInt(lineRead));
			lineRead = reader.readLine();
		}
		reader.close();
		return requirementsForTerritory;
	}
	
	/**
	 * Creates the personal bonus tiles.
	 *
	 * @param tilesNumber the tiles number
	 * @return the personal bonus tile[]
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static PersonalBonusTile[] createPersonalBonusTiles(int tilesNumber) throws NumberFormatException, IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.PERSONAL_BONUS_TILES));
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
		reader.close();
		return tiles;
	}
	
	/**
	 * Creates the privilege resources.
	 *
	 * @param resourcesNumber the resources number
	 * @return the resource chest[]
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ResourceChest[] createPrivilegeResources(int resourcesNumber) throws FileNotFoundException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(FileConstants.PRIVILEGE_RESOURCES));
		ResourceChest[] privilegeResources = new ResourceChest[resourcesNumber];
		int index = 0;
		
		String lineRead = reader.readLine();
		while(lineRead!=null){
			Resource rt1 = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(lineRead)], Integer.parseInt(reader.readLine()));
			Resource rt2 = ResourceFactory.getResource(ResourceType.values()[Integer.parseInt(reader.readLine())], Integer.parseInt(reader.readLine()));
			ResourceChest resourceChest = new ResourceChest();
			resourceChest.addResource(rt1);
			resourceChest.addResource(rt2);
			privilegeResources[index] = resourceChest;
			index++;
			lineRead = reader.readLine();
		}
		
		return privilegeResources;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		ResourceChest[] rc = null;
		try {
			rc = BoardInitializer.createPrivilegeResources(CardConstants.PRIVILEGE_RESOURCES);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i< rc.length; i++){
			System.out.println(rc[i]);
		}
	}
	
}
