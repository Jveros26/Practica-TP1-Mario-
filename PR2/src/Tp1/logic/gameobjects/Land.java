//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;


public class Land extends GameObject{

	private static final String NAME=Messages.LAND_NAME;
	private static final String SHORTCUT=Messages.LAND_SHORTCUT;


	
	public Land(GameWorld game,Position pos) {
		super(game,pos,NAME,SHORTCUT);
	}
	public Land() {
		super(NAME,SHORTCUT);
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
//--------------------------------------------------
	protected Land createInstance(Position pos, GameWorld game) {
		return new Land(game,pos);
	}
//--------------------------------------------------

public  boolean isSolid() {return true;}
	
//--------------------------------------------------
	public boolean interactWith(GameItem other) {
		boolean canInteract=other.isInPosition(pos);
		if(canInteract && this.isAlive()) {
			other.receiveInteraction(this);
		}
		return canInteract;
	}
//--------------------------------------------------
	public boolean receiveInteraction(Land obj) {
		return false;
	}
//--------------------------------------------------
	public  boolean receiveInteraction(ExitDoor obj) {
		return false;
	}
//--------------------------------------------------
	public boolean  receiveInteraction(Mario obj) {
		return false;
	}
//--------------------------------------------------
	public boolean receiveInteraction(Goombas obj) {
		return false;
		
	}
//--------------------------------------------------
	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}

}
