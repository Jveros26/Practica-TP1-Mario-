//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

import tp1.logic.Position;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Goombas;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Goombas;
import java.util.ArrayList;

public class GameObjectContainer {
	private ArrayList<GameObject> gameObjects;
	private ArrayList<GameObject> buffer;
	
	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
		buffer=new ArrayList<>();
	}
//--------------------------------------------------

	public void add(GameObject object) {
		this.gameObjects.add(object);
	}
	public void addDelayed(GameObject object) {
		this.buffer.add(object);
	}
	
	public String positionToString(Position pos) { 
	StringBuilder buffer=new StringBuilder();
		
		for(GameObject obj: gameObjects) {
			if(obj.isInPosition(pos)) buffer.append(obj.getIcon());
		}
		return buffer.toString();
	}
//--------------------------------------------------

	public void update() {	
		for(GameObject obj: gameObjects) {	//Recorre cada objeto haciendo su update correspondiente
			obj.update();
		}	
		
		clear();	//Se borran aquellos que esten muertos
		
		if (!buffer.isEmpty()) {	//Si el buffer esta vacio no hace falta modificar la lista
	        gameObjects.addAll(buffer);	//Sino lo que hace es copiar el buffer en gameObjects
	        buffer.clear();  // vaciamos el buffer después
	    }

	}
//--------------------------------------------------

	public boolean isSolid(Position pos) {
		boolean issolid=false;
		for(GameObject obj: gameObjects) {
			if(obj.isInPosition(pos)) {
				if(obj.isSolid()) {
					return true;
				}
			}
		}
		return issolid;	//Devuelve si hay un goomba/land-->objetos solidos
	}
//--------------------------------------------------

	public void clear() {
		for(int i=gameObjects.size()-1;i>=0;i--) {
			GameObject obj=gameObjects.get(i);
			if(!(obj.isAlive())) {
				gameObjects.remove(i);
			}
		}
	}

	
	private void doInteractionsFrom(Mario mario) {
		for(GameObject obj: gameObjects) {
			obj.receiveInteraction(mario);
		}
	}
	
	public void doInteractions() {
		for(int i=0;i<gameObjects.size();i++) {	//Recorre cada elemento de la lista
			GameObject obj1=gameObjects.get(i);	//Coge el objeto que toque
			for (int j = i + 1; j < gameObjects.size(); j++) {	//Y lo comprueba comprueba con el resto de elementos con los que no haya interactuado
				GameObject obj2 = gameObjects.get(j);	//Coge cada uno de los objetos
				if (obj1.isAlive() && obj2.isAlive()) {	//Compruebo si ambos estan vivos, sino no se realiza interacio bidireccional pq 
		           obj1.interactWith(obj2);	//Interactua bidireccionalmente
		           obj2.interactWith(obj1);
				}
			}
			
		}
	}

}

