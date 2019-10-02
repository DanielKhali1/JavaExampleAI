package survival;

public class MeasureAI
{
	public static void main(String[] args)
	{
		int sum = 0;
		int iterations = 1000;
		int minSurvival = Integer.MAX_VALUE;
		int maxSurvival = Integer.MIN_VALUE;
		
		for(int j = 0; j < iterations; j++)
		{
			Person survivor = new Person("MeasureAI");
			
			while(survivor.isAlive())
			{
				System.out.println("Day " + survivor.getDaysSurvived());
				
				// -------------------------- YOUR CODE STARTS HERE  --------------------------//

				
				
				// -------------------------- YOUR CODE ENDS HERE  ---------------------------//
				
				survivor.nextDay();
			}
			
			sum += survivor.getDaysSurvived();
			minSurvival = Math.min(minSurvival, survivor.getDaysSurvived());
			maxSurvival = Math.max(maxSurvival, survivor.getDaysSurvived());
		}
		
		System.out.println();
		System.out.println("Survival Average = " + (double) sum / iterations);
		System.out.println("Survival Min = " + minSurvival);
		System.out.println("Survival Max = " + maxSurvival);
	}
}
