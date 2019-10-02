package survival;

public class Template
{
	/**
	 *  
	 * A survivor's plane has crash landed on an island. He/She is the only one who survived.
	 * Nearby is a creek filled with fresh water and the survivor has found 10 meals in their plane's husk.
	 * 
	 * Your goal for this programming challenge is to survive as long as possible by strategically controlling your resources.
	 * 
	 * Every day, the survivor:
	 * 		Loses 1 energy
	 * 		Loses 2 satiation
	 * 		Loses 3 hydration
	 * 
	 * Energy, satiation, and hydration will all begin at 100%.
	 * Keep these values above 0% or else your survivor will die.
	 * 
	 * Your survivor has only so much food ( 10 ) in the beginning.
	 * Some of it has gone bad and is not safe for consumption (poisonous).
	 * 
	 * Your survivor carries all of their food with them (inside an inventory), all of which can be eaten, but if it is
	 * poisonous it is probably best to discard it. (poisonous food will subtract 50% from your Satiation).
	 * 
	 * To get more food your survivor must hunt for food.
	 * Hunting doesn't always reward your survivor with the same results.
	 * It takes 20% of their energy away and has a chance of dropping [0,4] (inclusive) quantity of food.
	 * The food dropped has a 40% chance of being poisonous.
	 * 
	 * In total, your survivor has 5 actions,
	 * - eating
	 * - resting
	 * - drinking
	 * - discarding food from inventory
	 * - hunting
	 * 
	 * of which your survivor can only do 1 per day.
	 * 
	 * If the survivor doesn't specify an action, the survivor will rest.
	 * 
	 * 
	 * HOW LONG WILL YOU LAST?
	 */
	public static void main(String[] args)
	{
		Person survivor = new Person("Person");
		
		while(survivor.isAlive())
		{
			System.out.println("Day " + survivor.getDaysSurvived());
			
			
			// -------------------------- YOUR CODE STARTS HERE  --------------------------//
			
			
			
			// -------------------------- YOUR CODE ENDS HERE  ---------------------------//
			
			survivor.nextDay();
		}
	}
}
