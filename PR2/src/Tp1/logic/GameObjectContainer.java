//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1.logic;

import Tp1.logic.Position;
import Tp1.logic.gameobjects.Land;
import Tp1.logic.gameobjects.Goombas;
import Tp1.logic.gameobjects.ExitDoor;
import Tp1.logic.gameobjects.Mario;
import Tp1.logic.gameobjects.Goombas;
import java.util.ArrayList;

public class GameObjectContainer {
	private ArrayList<GameObject> gameObjects;

	
	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
//--------------------------------------------------

	public String positionToString(Position pos) { 
		StringBuilder buffer=new StringBuilder();
		for(Land land:lands) {
			if(land.isInPosition(pos)) buffer.append(land.getIcon());
		}
		for(Goombas goombas:goombas) {
			if(goombas.isInPosition(pos)) buffer.append(goombas.getIcon());
		}
		if(mario.isInPosition(pos)) buffer.append(mario.getIcon());
		if(exitdoor.isInPosition(pos)) buffer.append(exitdoor.getIcon());
		
		
		return buffer.toString();
	}
//--------------------------------------------------

	public void update() {	//Hacemos doble clear e interactWith para que mario interaccione si es grande y asciende con los goombas que cae
							//Asi cuando el goomba cae en el mario grande ya se limpia del juego y desaparece
		
		mario.update();	//actualiza mario--->aqui como avisa que mario muere
		if(!checkMarioInExit()) {	//Si mario esta en la puerta pasa de actualizar esto
			doInteractionsFrom(mario);
			clear();
			for(Goombas goomba:goombas) {	//Actualiza goombas
				goomba.update();
			}
			doInteractionsFrom(mario);
		
			clear();	//limpia goombas muertos
		}
	}
//--------------------------------------------------

	public boolean isSolid(Position pos) {
		boolean issolid=false;
		for(List<GameObject> obj: gameObjects) {
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
		for(Goombas goomba:goombas) {
			mario.Interactwith(goomba);
		}
	}
	
	public void doInteraction(GameItem other) {
		for(gameObjects obj: gameObjects) {
			other.interactWith(this);
			this.interactWith(other);
		}
	}
}

