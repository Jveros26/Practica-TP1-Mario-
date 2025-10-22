//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
	private Position pos;
//--------------------------------------------------

	public ExitDoor(Position pos) {
		this.pos=pos;
	}
//--------------------------------------------------

	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
//--------------------------------------------------

	public Position exitDoorPos() {
		return this.pos;
	}
//--------------------------------------------------

	public boolean isInPosition(Position pos) {
		

		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
	public Position posicion() {
		return this.pos;
	}
}
