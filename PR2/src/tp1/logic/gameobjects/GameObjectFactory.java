package tp1.logic.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;

import tp1.logic.GameWorld;

public class GameObjectFactory {

	private static final ArrayList<GameObject> aviableObjects=Arrays.asList(
			new Land(),
			new ExitDoor(),
			new Goomba(),
			new Mario()
			
			//new Box(),
			//new Mushroom()
	);
			
	
	public static GameObject parse(String strsObject[],GameWorld game) {
		
		for(GameObject obj:aviableObjects) {
			GameObject objeto=obj.parse(strsObject,game);
			if(objeto!=null) {
				return objeto;
			}
		}
		return null;
	}
			
			
	
	
}
