
public class Animal 
{
	private int hunger, energy, thirst, foodLeft;
	
	private boolean sleep = true;
	private boolean ActionTaken = false;
	private int sleepTimer = 0;
	
	private String name;
	
	public int turns = 0;
	
	
	//constructor
	public Animal(String name)
	{
		this.name = name;
		this.setFoodLeft(50);
		this.setHunger(100);
		this.setEnergy(100);
		this.setThirst(100);
	}
	
	/**
	 * steps through time to the next turn. will decrement hunger by 2 will decrement energy by 1 and will decrement thirst by 4
	 * will print out all attributes and handles whether the animal is asleep or not
	 */
	public void step()
	{
		
		//decrements the sleep timer set if the animal is asleep
		sleepTimer -= (sleep) ? 1 : 0;
		//decrements the hunger by 2
		setHunger(getHunger() - 2);
		//decrements the energy by 1
		setEnergy(getEnergy() - 1);
		//decrements the thirst by 4
		setThirst(getThirst() - 4);
		
		//if the sleep timer is < 0 
		// then the animal is awake
		if(sleepTimer < 0)
			sleep = false;
		
		//if the animal is not asleep
		//it can take an action
		if(!sleep)
			ActionTaken = false;
		
		// if the animal is asleep
		// it increments the animals energy
		// prints out how much energy is being incremented by
		if(sleep)
		{
			energy += 5;
			System.out.println( name + " is asleep, energy is : " + getEnergy() + "% "+ name + "will be asleep for " + sleepTimer + "more turns");
		}
		
		// prints out current stats for the animal
		System.out.println(name + "'s hunger: " + getHunger() + "% thirst: " + getThirst() + "% energy: " + getEnergy() + "% food left: " + getFoodLeft());
		//increments the amount of turns
		turns++;
		
	}
	
	/**if the hunger is < 0 or
	 * if the energy is < 0 or
	 * if the thirst is < 0  
	 *  
	 *  then the method will return true
	 *  and the animal will be dead
	 * 
	 * @return if the animal has died (true or false)
	 */
	public boolean isDead()
	{
		//if hunger is < 0 
		//the animal will die
		if(getHunger() < 0) {System.out.println(name + " died of starvation at turn " + turns); return true;}
		//if the energy is < 0
		// the animal will die
		if(getEnergy() < 0) {System.out.println(name + " died of delirious hilucinations at turn " + turns); return true;}
		//if the thirst is < 0
		// the animal will die
		if(getThirst() < 0) {System.out.println(name + " died of dehydration at turn " + turns); return true;}
		
		//if it hasn't already died
		// then it isn't dead
		return false;
	}

	/**
	 * will feed the animal 20% of it's food back if they have any food left or haven't already taken an action
	 */
	public void eat()
	{
		if(getFoodLeft() > 0 && !ActionTaken)
		{
			if(getHunger() + 20 > 100) setHunger(100); 
			else setHunger(getHunger() + 20);
			
			setFoodLeft(getFoodLeft() - 1);
			
			System.out.println("\n" + name + " has eaten his current hunger is : " + getHunger() + "%\n");
			
			ActionTaken = true;
		}
	}
	/**
	 * will water the animal 20% of it's thirst back if they have haven't already taken an action
	 */
	public void drink()
	{
		if(!ActionTaken)
		{
			if(getThirst() + 20 > 100) setThirst(100); 
			else setThirst(getThirst() + 20);		
			ActionTaken = true;
			
			System.out.println("\n" + name + " has drank water, his current thirst is : " + getThirst() + "%\n");

		}

	}
	
	/**
	 * will put the animal to sleep if they haven't already taken an action
	 * will sleep for the amount of time provided in the argument
	 */	
	
	public void sleep(int timeStep)
	{
		if(!ActionTaken)
		{
			sleep = true;
			sleepTimer = timeStep;
			
			ActionTaken = true;
			
			System.out.println("\n" + name + " has fallen asleep\n");
		}		
	}

	//---------------------  Accessors and Modifiers	--------------------- //

	public int getHunger() {
		return hunger;
	}


	public void setHunger(int hunger) {
		this.hunger = hunger;
	}


	public int getEnergy() {
		return energy;
	}


	public void setEnergy(int energy) {
		this.energy = energy;
	}


	public int getThirst() {
		return thirst;
	}


	public void setThirst(int thirst) {
		this.thirst = thirst;
	}


	public int getFoodLeft() {
		return foodLeft;
	}


	public void setFoodLeft(int foodLeft) {
		this.foodLeft = foodLeft;
	}
	
}
