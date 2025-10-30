//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {

	private static final String NAME=Messages.EXITDOOR_NAME;

	
	public ExitDoor(GameWorld game,Position pos) {
		super(game,pos,NAME);
	}
	public ExitDoor() {
		super(NAME);
	}
//--------------------------------------------------
	@Override
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	@Override
	protected ExitDoor createInstance(Position pos, GameWorld game) {
		return new ExitDoor(game,pos);
	}
//--------------------------------------------------
	@Override
	public boolean isInPosition(Position pos) {
		

		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}

//--------------------------------------------------
	@Override
	public  boolean isSolid() {return false;}

//--------------------------------------------------
	@Override
	public boolean interactWith(GameItem other) {
		boolean canInteract=other.isInPosition(pos);
		if(canInteract&& this.isAlive()) {
			other.receiveInteraction(this);
		}
		return canInteract;
	}
//--------------------------------------------------
	@Override
		public  void receiveInteraction(Land obj) {
		}
//--------------------------------------------------
		@Override
		public  void receiveInteraction(ExitDoor obj) {
		}
//--------------------------------------------------
		@Override
		public void  receiveInteraction(Mario obj) {
			if(obj.isInPosition(pos)&& this.isAlive()) {
				game.marioExited();	//Si al estar hecho en gameWorld por nosotros puede llamar a la funcion del Game
			}
		}
//--------------------------------------------------
		@Override
		public  void receiveInteraction(Goombas obj) {	
		}

}
