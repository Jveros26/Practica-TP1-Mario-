//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Land {
	private Position pos;
//--------------------------------------------------

	public Land(Position pos) {
		this.pos=pos;
	}
//--------------------------------------------------

	public String getIcon() {
		return Messages.LAND;
	}
//--------------------------------------------------

	public boolean isInPosition(Position pos) {
		
		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
}
