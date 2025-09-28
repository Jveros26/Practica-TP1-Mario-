package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;

public class Goombas {
	private Position pos;
	public Goombas(Position pos) {
		this.pos.setCol(pos.getCol());
		this.pos.setRow(pos.getRow());
	}
	public String getIcon() {
		return Messages.GOOMBA;
	}
	public int getRow() {
		return pos.getRow();
	}
	public int getCol() {
		return pos.getCol();
	}
	public Boolean isInPosition(Position pos) {
		
		Boolean ok=true;
		if(this.pos.getCol()!= pos.getCol()|| this.pos.getRow()!= pos.getRow()) {
			ok=false;
		}
		return ok;
	}
}
