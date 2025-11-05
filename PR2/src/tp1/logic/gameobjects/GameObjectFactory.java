package tp1.logic.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tp1.logic.GameWorld;

public class GameObjectFactory {

	private static final List<GameObject> aviableObjects=Arrays.asList(
			new Land(),
			new ExitDoor(),
			new Goombas(),
			new Mario(),
			new Box(),
			new Mushroom()
	);
			
	
	public static GameObject parse(String objWords[],GameWorld game) {
		
		for(GameObject obj:aviableObjects) {
			GameObject objeto=obj.parse(objWords,game);	//Llama a los parses de cada objeto y ve que concuerda con su estructura
			if(objeto!=null) {
				return objeto;	//Devuelve la instancia creada desde el parse si es distinto de null
			}
		}
		return null;	//Sino devuelve null
	}
			
			
	
	
}
