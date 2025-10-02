package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Game;
import Tp1.logic.Action;

public class Goombas {
	private Position pos;
	private Game game;
	private boolean Alive;
	//private Action action;
	public Goombas(Game game,Position pos) {
		this.pos=pos;
		this.game=game;
		this.Alive=true;
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
	public void update() {
		
		if(game.isSolid(pos.move(Action.DOWN))) {
			step();
		}
		else {
			fall();
		}
		
		if(!game.positionIsIn(pos)) {
			dead();
		}
		
	}
	public void dead() {
		Alive=false;
	}
	public boolean Alive() {
		return this.Alive;
	}
	public void step() {
		if(game.isSolid(pos.move(Action.LEFT))){
			pos.move(Action.RIGHT);
		}
		else {
			if(game.isSolid(pos.move(Action.RIGHT))){
				pos.move(Action.LEFT);
			}
			else {
				//Si no cambia el movimiento como sabemos a donde se mueve?
				//Idea de poner Action como atributo de la clase
			}
		}
		
	}
	public void fall() {
		pos.move(Action.DOWN);
	}
}
