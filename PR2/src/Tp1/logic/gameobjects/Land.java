//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1.logic.gameobjects;

import Tp1.logic.GameItem;
import Tp1.logic.Position;
import Tp1.view.Messages;


public class Land extends GameObject{

	public Land(Game game,Position pos) {
		super(game,pos);
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
	}
//--------------------------------------------------
	public  void receiveInteraction(Goombas obj) {	
	}

}
