package survival;

public class Template
{
	
	
	/**
	 *  
	 * A survivor's plane has crash landed on an island. He/She is the only one who survived.
	 * nearby is a creek filled with fresh water, He/She has found 10 meals in their plane's husk
	 * 
	 * your goal for this programming challenge is to survive as long as possible.
	 * remember to keep these values start at 100% and will decrease over time.
	 * 
	 * Loses 1 energy
	 * Loses 2 satiation
	 * Loses 3 hydration
	 * 
	 * every day.
	 * 
	 * energy, satiation, and hydration will will all begin at 100%
	 * keep these values above 100 % or else your survivor will die.
	 * 
	 * your survivor only have so much food ( 10 ) in the beginning
	 * some of it has gone bad and is not safe for consumption
	 * 
	 * your survivor carries all of their food with them (inside an inventory), all of which can be eaten, but if it is
	 * poisonous it is probably best to discard it. (poisonous food will subtract 50% from your Satiation
	 * 
	 * to get more food your survivor must hunt for food
	 * 
	 * hunting doesn't always reward your survivor with the same results, it takes 20% of their energy
	 * and has a chance of dropping [0,4] (inclusive) food.
	 * the food dropped has a 60% chance of being non-poisonous
	 * 
	 * in total, your survivor has 4 actions,
	 * - eating
	 * - resting
	 * - drinking
	 * - discarding food from inventory
	 * 
	 * of which your survivor can only do 1 per day.
	 */
	
	
	public static void main(String[] args)
	{
		Person survivor = new Person("Person");
		
		while(survivor.isAlive())
		{
			System.out.println("Day " + survivor.getDaysSurvived());
			
// -------------------------- YOUR CODE STARTS HERE  --------------------------//
			
			
// -------------------------- YOUR CODE ENDS HERE  --------------------------//
			
			survivor.nextDay();
		}
	}
}
