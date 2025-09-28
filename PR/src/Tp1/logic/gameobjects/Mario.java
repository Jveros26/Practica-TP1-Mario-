package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Game;

public class Mario {
	private Position pos;
	private boolean left,right;
	public Mario(Position pos) {
		this.pos.setCol(pos.getCol());
		this.pos.setRow(pos.getRow());
	}
	public String getIcon() {	//Va hacia la izq? Si-> devuelve imagen mario izq : No-> mario recto
		String pose=Messages.MARIO_STOP;
		if(left==true) {
			pose=Messages.MARIO_LEFT;
		}
		else {
			if(right==true) {
				pose=Messages.MARIO_RIGHT;
			}
		}
		return pose;
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
