package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Game;

public class Goombas {
	private Position pos;
	private Game game;
	public Goombas(Game game,Position pos) {
		this.pos=pos;
		this.game=game;
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
	public boolean isInPosition(Position pos) {
		

		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
}
