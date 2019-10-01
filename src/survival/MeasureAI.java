package survival;

public class MeasureAI
{
	public static void main(String[] args)
	{
		int sum = 0;
		int iterations = 1000;
		for(int i = 0; i < iterations; i++)
		{
			Person person = new Person("MeasureAI");
			
			while(person.isAlive())
			{
				System.out.println("Day " + person.getDaysSurvived());
				
				// ADD CODE HERE
				
				
				// CODE ENDS HERE
				
				person.nextDay();
			}
			
			sum += person.getDaysSurvived();
		}
		
		System.out.println("Survival Average = " + (double) sum / iterations);
	}
}
