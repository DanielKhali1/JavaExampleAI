package survival;

import java.util.ArrayList;
import java.util.List;

/**
 * A simulation of a a person living in a desert with a supply of fresh water nearby.
 *  
 * @author AI Club
 */
public class Person
{
	private int satiation;
	private int hydration;
	private int energy;
	private String name;
	private int daysSurvived;
	private boolean actionTaken;
	private List<Boolean> foodInventory;
	
	/**
	 * Creates a new person with the provided name.
	 * 
	 * @param name - name of the person
	 */
	public Person(String name)
	{
		this.name = name;
		actionTaken = false;
		satiation = 100;
		hydration = 100;
		energy = 100;
		daysSurvived = 0;
		foodInventory = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			foodInventory.add(Math.random() < 0.8); // 80% good, 20% bad
		}
	}
	
	/**
	 * Proceeds the world to the next day. If the person has not performed any actions, they will {@link #rest()}.
	 * 
	 * <p><b>
	 * Every day, the person:
	 * <ul>
	 * <li>Loses 1 energy</li>
	 * <li>Loses 2 satiation</li>
	 * <li>Loses 3 hydration</li>
	 * </ul>
	 * </b></p>
	 */

	public void nextDay()
	{
		if(!isAlive(false))
			return;
		
		satiation = Math.max(0, satiation - 2);
		hydration = Math.max(0, hydration - 3);
		energy = Math.max(0, energy - 1);
		
		if(!actionTaken)
			rest();
		
		daysSurvived++;
		actionTaken = false;
		System.out.println(name + "'s Satiation: " + getSatiation() + "%\tHydration: " + getHydration() + "%\tEnergy: " + getEnergy() + "%\tFood Left: " + getFoodCount());
		System.out.print("Inventory: [");
		for(int i = 0; i < getFoodCount(); i++)
		{
			System.out.print(isHealthyFood(i) ? "Food" : "Poison");
			
			if(i != getFoodCount() - 1)
				System.out.print(", ");
		}
		System.out.println("]\n");
	}
	
	/**
	 * Discards the specified food index in the inventory. This will consume an action.
	 * An index of 0 refers to the first item in the inventory. The index ranges from 0 to {@link #getFoodCount()}-1.
	 * 
	 * <p><b>
	 * This costs no energy.
	 * </b></p>
	 * 
	 * @param foodIndex - the index of the food to discard (zero-based)
	 */
	
	public void discardFood(int foodIndex)
	{
		if(actionTaken)
		{
			System.out.println("[WARNING] Cannot discard food. Already did action.");
			return;
		}
		
		if(getFoodCount() == 0)
		{
			System.out.println("[WARNING] Cannot discard food. Inventory is empty.");
			return;
		}
		
		if(foodIndex < 0 || foodIndex >= getFoodCount())
		{
			System.out.println("[WARNING] Cannot discard food. Index (" + foodIndex + ") is out of bounds.");
			return;
		}
		
		actionTaken = true;
		System.out.println(name + " is discarding " + (isHealthyFood(foodIndex)?"healthy food." : "poisonous food."));
		foodInventory.remove(foodIndex);
	}
	
	/**
	 * <p><b>
	 * Loses 2 energy and if food is healthy, gains 12 satiation, otherwise loses 50 satiation.
	 * </b></p>
	 * 
	 * Eats the specified food in the inventory. This will consume an action.
	 * An index of 0 refers to the first item in the inventory. The index ranges from 0 to {@link #getFoodCount()}-1.
	 * 
	 * @param foodIndex - the index of the food to eat (zero-based)
	 */
	public void eat(int foodIndex)
	{
		if(actionTaken)
		{
			System.out.println("[WARNING] Cannot eat. Already did action.");
			return;
		}
		
		if(foodIndex < 0 || foodIndex >= getFoodCount())
		{
			System.out.println("[WARNING] Cannot eat. Index (" + foodIndex + ") is out of bounds.");
			return;
		}
		
		actionTaken = true;
		System.out.println(name + " is eating.");
		boolean isHealthy = isHealthyFood(foodIndex);
		if(isHealthy)
		{
			satiation = Math.min(100, satiation + 12);
		}
		else
		{
			satiation = Math.max(0, satiation - 50);
			System.out.println(name + " was poisoned by eating bad food!");
		}
		foodInventory.remove(foodIndex);
		energy = Math.max(0, energy - 2);
	}
	
	/**
	 * <p><b>
	 * Loses 1 energy and gains 13 hydration. 
	 * </b></p>
	 * 
	 * This will consume an action.
	 */
	public void drink()
	{
		if(actionTaken)
		{
			System.out.println("[WARNING] Cannot drink. Already did action.");
			return;
		}
		
		actionTaken = true;
		hydration = Math.min(100, hydration + 13);
		energy = Math.max(0, energy - 1);
		System.out.println(name + " is drinking.");
	}
	
	/**
	 * <p><b>
	 * Gains 11 energy. 
	 * </b></p>
	 * 
	 * This will consume an action.
	 */
	public void rest()
	{
		if(actionTaken)
		{
			System.out.println("[WARNING] Cannot rest. Already did action.");
			return;
		}
		
		actionTaken = true;
		energy = Math.min(100, energy + 11);
		System.out.println(name + " is resting.");
	}
	
	/**
	 * <p><b>
	 * Loses 20 energy and gains random food in the inventory. 
	 * </b></p>
	 * 
	 * <p><b>
	 * The quantity of food gained can be 0 to 4.
	 * The food itself has a 60% chance to be food and 40% chance to be poison.
	 * </b></p>
	 * 
	 * This will consume an action.
	 */
	public void hunt()
	{
		if(actionTaken)
		{
			System.out.println("[WARNING] Cannot hunt. Already did action.");
			return;
		}
		
		actionTaken = true;
		int huntGainCount = (int) (Math.random() * 4);
		for(int i = 0; i < huntGainCount; i++)
		{
			foodInventory.add(Math.random() < 0.6); // 60% good, 40% bad
		}
		energy = Math.max(0, energy - 20);
		System.out.println(name + " is hunting. Found " + huntGainCount + " food.");
	}
	
	/**
	 * Returns true if the person is alive, and false if dead.
	 * 
	 * <p><b>
	 * A person is dead if any of the following conditions are met:
	 * <ol>
	 * <li>Satiation reaches 0.</li>
	 * <li>Hydration reaches 0.</li>
	 * <li>Energy reaches 0.</li>
	 * </ol>
	 * </b></p>
	 * 
	 * @return true if alive, false if dead.
	 */
	public boolean isAlive()
	{
		return isAlive(true);
	}
	
	/**
	 * Returns true if the person is alive, and false if dead.
	 * 
	 * <p><b>
	 * A person is dead if any of the following conditions are met:
	 * <ol>
	 * <li>Satiation reaches 0.</li>
	 * <li>Hydration reaches 0.</li>
	 * <li>Energy reaches 0.</li>
	 * </ol>
	 * </b></p>
	 * 
	 * @return true if alive, false if dead.
	 */
	private boolean isAlive(boolean print)
	{
		if(energy <= 0)
		{
			if(print) System.out.println(name + " died of delirious hallucination at day " + daysSurvived + ".");
			return false;
		}
		
		if(satiation <= 0)
		{
			if(print) System.out.println(name + " died of starvation at day " + daysSurvived + ".");
			return false;
		}
		
		if(hydration <= 0)
		{
			if(print) System.out.println(name + " died of dehydration at day " + daysSurvived + ".");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns true if an action has been taken on this turn.
	 * 
	 * <p><b>
	 * The following are the possible actions:
	 * <ul>
	 * <li>{@link #eat()} or {@link #eat(int)}</li>
	 * <li>{@link #drink()}</li>
	 * <li>{@link #discardFood()} or {@link #discardFood(int)}</li>
	 * <li>{@link #rest()}</li>
	 * </ul>
	 * </b></p>
	 * 
	 * <p><b>
	 * Note: If no actions have been taken this turn, then the person will rest.
	 * </b></p>
	 * 
	 * @return true if action has been taken, false otherwise
	 */
	public boolean hasTakenAction()
	{
		return actionTaken;
	}
	
	/**
	 * Returns the satiation of the person. It is a value from 0 (starving) to 100 (well-fed).
	 * Once it reaches 0, the person dies.
	 * 
	 * @return satiation of person
	 */
	public int getSatiation()
	{
		return satiation;
	}
	
	/**
	 * Returns the hydration of the person. It is a value from 0 (thirsty) to 100 (hydrated).
	 * Once it reaches 0, the person dies.
	 * 
	 * @return hydration of person
	 */
	public int getHydration()
	{
		return hydration;
	}

	/**
	 * Returns the energy of the person. It is a value from 0 (tired) to 100 (energetic).
	 * Once it reaches 0, the person dies.
	 * 
	 * @return energy of person
	 */
	public int getEnergy()
	{
		return energy;
	}
	

	/**
	 * Returns the name of the person.
	 * 
	 * @return name of person
	 */
	public String getName()
	{
		return name;
	}
	

	/**
	 * Returns the number of days survived by the person.
	 * 
	 * @return survival days
	 */
	public int getDaysSurvived()
	{
		return daysSurvived;
	}

	/**
	 * Returns an array of the current food inventory.
	 * The array is of type boolean, where true represents healthy food and false represents poisonous food.
	 * See {@link #isHealthyFood(int)}.
	 * 
	 * @return the food inventory
	 */
	public boolean[] getFoodInventory()
	{
		boolean[] inventory = new boolean[foodInventory.size()];
		for(int i = 0; i < foodInventory.size(); i++)
		{
			inventory[i] = foodInventory.get(i);
		}
		return inventory;
	}
	
	/**
	 * Returns true if the specified food is healthy, false otherwise.
	 * 
	 * @param foodIndex the food to check if healthy
	 * 
	 * @return true if healthy, false otherwise
	 */
	public boolean isHealthyFood(int foodIndex)
	{
		if(foodIndex < 0 || foodIndex >= getFoodCount())
			return false;
		
		return foodInventory.get(foodIndex);
	}
	
	/**
	 * Returns the number of food in the inventory. Note food can be poisonous.
	 * 
	 * @return size of inventory
	 */
	public int getFoodCount()
	{
		return foodInventory.size();
	}
}