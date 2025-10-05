package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Game;
import Tp1.logic.Action;

public class Goombas {
	private Position pos;
	private Game game;
	private boolean Alive;
	private Action action;
	private boolean isFalling;
//--------------------------------------------------

	public Goombas(Game game,Position pos) {
		this.pos=pos;
		this.game=game;
		this.Alive=true;
		action=Action.LEFT;
	}
//--------------------------------------------------

	public String getIcon() {
		return Messages.GOOMBA;
	}
//--------------------------------------------------

	public int getRow() {
		return pos.getRow();
	}
//--------------------------------------------------

	public int getCol() {
		return pos.getCol();
	}
//--------------------------------------------------


	public boolean isInPosition(Position pos) {
		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
//--------------------------------------------------

	public void update() {
		
		if(game.isSolid(pos.move(Action.DOWN))) {
			this.isFalling=false;
			if((game.isMario(pos.move(Action.LEFT))&&(action==Action.LEFT))) {
				game.marioDead();	
			}
			else {
				if((game.isMario(pos.move(Action.RIGHT))&&(action==Action.RIGHT))) {
					game.marioDead();
				}
				else {
					step();	
				}
			}
		}
		else {
			this.isFalling=true;
			fall();
		}
		
		if(!game.positionIsIn(pos)) {
			dead();
		}
		
	}
//--------------------------------------------------

	public void dead() {
		Alive=false;
	}
//--------------------------------------------------

	public boolean Alive() {
		return this.Alive;
	}
//--------------------------------------------------

	public void step() {
		if(game.isSolid(pos.move(Action.LEFT))){
			pos.commute(Action.RIGHT);
			this.action=Action.RIGHT;

		}
		else {
			if(game.isSolid(pos.move(Action.RIGHT))){
				pos.commute(Action.LEFT);
				this.action=Action.LEFT;

			}
			else {
				if(this.action==Action.LEFT) {
					pos.commute(Action.LEFT);
					this.action=Action.LEFT;

				}
				else {
					pos.commute(Action.RIGHT);
					this.action=Action.RIGHT;

				}
			}
		}
		
	}
//--------------------------------------------------

	public void fall() {
		pos.commute(Action.DOWN);
	}
//--------------------------------------------------

	public Action accionG() {
		return this.action;
	}
}
