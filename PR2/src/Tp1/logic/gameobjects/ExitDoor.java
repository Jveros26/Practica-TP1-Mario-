//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {

	private static final String name="EXITDOOR";
	private static final String shortcut="EX";
	public ExitDoor(GameWorld game,Position pos) {
		super(game,pos,name,shortcut);
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
