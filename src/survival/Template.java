package survival;

public class Template
{
	public static void main(String[] args)
	{
		Person person = new Person("Person");
		
		while(person.isAlive())
		{
			System.out.println("Day " + person.getDaysSurvived());
			
			// YOUR CODE STARTS HERE
			
			
			// YOUR CODE ENDS HERE
			
			person.nextDay();
		}
	}
}
