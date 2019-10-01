
public class Main 
{
	
	final static String ANIMAL_NAME = "Steve";
	
	public static void main(String[] args)
	{
		
		Animal animal = new Animal(ANIMAL_NAME);
		
		while(!animal.isDead())
		{
			//animal needs to eat to survive
			
			//animal needs to drink to survive
			
			//animal needs plenty of rest to maintain it's energy
			
			animal.step();
		}
	
	}

}
