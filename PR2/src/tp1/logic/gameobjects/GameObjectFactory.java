package tp1.logic.gameobjects;


import java.util.Arrays;

public class GameObjectFactory {

	private static final List<GameObject> aviableObjects=Arrays.asList(
			new Land(),
			new ExitDoor(),
			new Goomba(),
			new Mario()
	);
			
			
			
	
	
}
