package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;

public class Land {
	private Position pos;
	public Land(Position pos) {
		this.pos.setCol(pos.getCol());
		this.pos.setRow(pos.getRow());
	}
	public String getIcon() {
		return Messages.LAND;
	}
	public Boolean isInPosition(Position pos) {
		
		Boolean ok=true;
		if(this.pos.getCol()!= pos.getCol()|| this.pos.getRow()!= pos.getRow()) {
			ok=false;
		}
		return ok;
	}
}
