//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1.logic.gameobjects;

import Tp1.logic.Game;
import Tp1.logic.GameItem;
import Tp1.logic.Position;
import Tp1.view.Messages;

public class ExitDoor extends GameObject {

	public ExitDoor(GameWorld game,Position pos) {
		super(game,pos);
	}
//--------------------------------------------------

	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	
	protected ExitDoor createInstance(Position pos, GameWorld game) {
		return new ExitDoor(game,pos);
	}
//--------------------------------------------------

	public boolean isInPosition(Position pos) {
		

		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
//--------------------------------------------------	
	public Position posicion() {
		return this.pos;
	}
//--------------------------------------------------
	public  boolean isSolid() {return false;}

//--------------------------------------------------	
	public boolean interactWith(GameItem other) {
		boolean canInteract=other.isInPosition(pos);
		if(canInteract) {
			other.recieveInteraction(this);
		}
		return canInteract;
	}
//--------------------------------------------------
		public  void receiveInteraction(Land obj) {
		}
//--------------------------------------------------
		public  void receiveInteraction(ExitDoor obj) {
		}
//--------------------------------------------------
		public void  receiveInteraction(Mario obj) {
			if(obj.isInPosition(pos)) {
				game.marioExited();
			}
		}
//--------------------------------------------------
		public  void receiveInteraction(Goombas obj) {	
		}

}
