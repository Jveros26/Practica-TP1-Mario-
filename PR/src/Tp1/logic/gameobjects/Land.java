package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;

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
