package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;

public class ExitDoor {
	private Position pos;
	public ExitDoor(Position pos) {
		this.pos=pos;
	}
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	public int getRow() {
		return pos.getRow();
	}
	public int getCol() {
		return pos.getCol();
	}
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
