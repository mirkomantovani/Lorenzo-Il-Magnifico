package it.polimi.ingsw.ps19.model.resource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class ResourceChest.
 *
 * @author Mirko
 */
public class ResourceChest implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -768950657123798317L;
	
	/** The resources. */
	private HashMap<ResourceType, Resource> resources;

	/**
	 * Instantiates a new resource chest.
	 */
	public ResourceChest() {
		resources = new HashMap<>();
		for (int i = 0; i < ResourceType.values().length; i++) {
			resources.put(ResourceType.values()[i], ResourceFactory.getResource(ResourceType.values()[i], 0));
		}

	}

	/**
	 * Instantiates a new resource chest.
	 *
	 * @param coin the coin
	 * @param wood the wood
	 * @param stone the stone
	 * @param serv the serv
	 * @param fp the fp
	 * @param vp the vp
	 * @param mp the mp
	 */
	public ResourceChest(int coin, int wood, int stone, int serv, int fp, int vp, int mp) {

		int[] res = new int[7];
		res[0] = coin;
		res[1] = wood;
		res[2] = stone;
		res[3] = serv;
		res[4] = fp;
		res[5] = vp;
		res[6] = mp;

		resources = new HashMap<>();
		for (int i = 0; i < ResourceType.values().length; i++) {
			resources.put(ResourceType.values()[i], ResourceFactory.getResource(ResourceType.values()[i], res[i]));
		}
	}

	/**
	 * Instantiates a new resource chest.
	 *
	 * @param res the res
	 */
	public ResourceChest(int[] res) {

		resources = new HashMap<>();
		for (int i = 0; i < ResourceType.values().length; i++) {
			resources.put(ResourceType.values()[i], ResourceFactory.getResource(ResourceType.values()[i], res[i]));
		}
	}

	/**
	 * Instantiates a new resource chest.
	 *
	 * @param InitialChest the initial chest
	 */
	public ResourceChest(ResourceChest InitialChest) {
		resources = new HashMap<>();
		for (int i = 0; i < ResourceType.values().length; i++) {
			resources.put(ResourceType.values()[i], ResourceFactory.getResource
					(ResourceType.values()[i],InitialChest.getResourceInChest
							(ResourceType.values()[i]).getAmount()));
		}
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		for (int i = 0; i < ResourceType.values().length; i++) {
			if (this.resources.get(ResourceType.values()[i]).getAmount() != 0)
				return false;
		}
		return true;
	}

	/**
	 * This method subtracts every Resource of the Chest passed by parameter to
	 * the Chest it is called upon.
	 *
	 * @author Mirko
	 * @param resourceChest the resource chest
	 */
	public void subChest(ResourceChest resourceChest) {

		for (int i = 0; i < ResourceType.values().length; i++) {
			this.resources.get(ResourceType.values()[i]).sub(resourceChest.resources.get(ResourceType.values()[i]));
		}

	}

	/**
	 * This method adds every Resource of the Chest passed by parameter to the
	 * Chest it is called upon.
	 *
	 * @author Mirko
	 * @param resourceChest the resource chest
	 */
	public void addChest(ResourceChest resourceChest) {
		for (int i = 0; i < ResourceType.values().length; i++) {
			this.resources.get(ResourceType.values()[i]).add(resourceChest.resources.get(ResourceType.values()[i]));
		}
	}

	/**
	 * This method subtracts the generic resource passed by parameter to the
	 * right resource field of the chest (this method was needed to apply the
	 * AtomicExchangeEffect).
	 *
	 * @author Mirko
	 * @param resource            to subtract
	 */
	public void subResource(Resource resource) {

		this.resources.get(resource.getResourceType()).sub(resource);

	}

	/**
	 * This method adds the generic resource passed by parameter to the right
	 * resource field of the chest (this method was needed to apply the
	 * AtomicExchangeEffect).
	 *
	 * @author Mirko
	 * @param resource            to subtract
	 */
	public void addResource(Resource resource) {
		if (resource != null) {
			getResourceInChest(resource).add(resource);
		}
	}

	/**
	 * this method is used from subResource and addResource to find and
	 * return the dynamic type of the resource.
	 *
	 * @author Mirko
	 * @param resource the resource
	 * @return the resource in chest
	 */
	public Resource getResourceInChest(Resource resource) {

		return this.resources.get(resource.getResourceType());

	}
	
	/**
	 * Gets the resource in chest.
	 *
	 * @param resourceType the resource type
	 * @return the resource in chest
	 */
	public Resource getResourceInChest(ResourceType resourceType) {

		return this.resources.get(resourceType);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ResourceType.values().length; i++) {
			Resource r;
			r = this.resources.get(ResourceType.values()[i]);
			if (r.getAmount() > 0)
				builder.append(r.toString());
		}
		return builder.toString();
	}

	/**
	 * This method returns true if every resource contained in the chest has an
	 * amount greater or equal to the corresponding resource in the chest passed
	 * by parameter.
	 *
	 * @author Mirko
	 * @param resourceChest the resource chest
	 * @return true, if is greater equal than
	 */
	public boolean isGreaterEqualThan(ResourceChest resourceChest) {

		for (int i = 0; i < ResourceType.values().length; i++) {
			if (this.resources.get(ResourceType.values()[i]).getAmount() < resourceChest.resources
					.get(ResourceType.values()[i]).getAmount())
				return false;
		}
		return true;

	}
	
	/**
	 * Clone chest.
	 *
	 * @return the resource chest
	 */
	public ResourceChest cloneChest(){
		return new ResourceChest(this);
		
	}

	

	/**
	 * This method sums the amount of wood,stone,servants,coins in the chest,
	 * used for LosePointsBa.
	 *
	 * @return the int
	 */
	public int sumResources() {
		return this.resources.get(ResourceType.COIN).getAmount() + this.resources.get(ResourceType.WOOD).getAmount()
				+ this.resources.get(ResourceType.STONE).getAmount()
				+ this.resources.get(ResourceType.SERVANT).getAmount();
	}

	/**
	 * Gets the wood amount.
	 *
	 * @return the wood amount
	 */
	public int getWoodAmount() {

		return this.resources.get(ResourceType.WOOD).getAmount();
	}

	/**
	 * Gets the stone amount.
	 *
	 * @return the stone amount
	 */
	public int getStoneAmount() {
		return this.resources.get(ResourceType.STONE).getAmount();
	}

}
