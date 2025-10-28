//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

import tp1.logic.Position;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Goombas;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Goombas;
import java.util.ArrayList;

public class GameObjectContainer {
	private ArrayList<GameObject> gameObjects;

	
	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
//--------------------------------------------------

	public String positionToString(Position pos) { 
	StringBuilder buffer=new StringBuilder();
		
		for(GameObject obj: gameObjects) {
			if(obj.isInPosition(pos)) buffer.append(obj.getIcon());
		}
		return buffer.toString();
	}
//--------------------------------------------------

	public void update() {	
		
		for(GameObject obj: gameObjects) {
			obj.update();
			doInteractionsFrom(obj);
			
		}
		clear();

	}
//--------------------------------------------------

	public boolean isSolid(Position pos) {
		boolean issolid=false;
		for(GameObject obj: gameObjects) {
			if(obj.isSolid()) {
				return true;
			}
		}
		return issolid;	//Devuelve si hay un goomba/land-->objetos solidos
	}
//--------------------------------------------------

	private void clear() {
		for(int i=goombas.size()-1;i>=0;i--) {
			Goombas goomba=goombas.get(i);
			if(!goomba.Alive()) {
				goombas.remove(i);
			}
		}
	}
	
	private boolean checkMarioInExit() {
		return mario.interactWith(exitdoor);
	}
	
	private void doInteractionsFrom(Mario mario) {
		for(GameObject obj: gameObjects) {
			mario.Interactwith(obj);
		}
	}
	
	public void doInteraction(GameItem other) {
		for(GameObject obj: gameObjects) {
			other.interactWith(this);
			this.interactWith(other);
		}
	}
}

