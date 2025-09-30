package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Game;



public class Mario {
	private Position pos;
	private Game game;
	private boolean left,right;
	public Mario(Game game,Position pos) {
		this.pos=pos;
		this.game=game;
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
	public boolean isInPosition(Position pos) {
		
		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
}
