package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Action;
import Tp1.logic.Game;



public class Mario {
	private Position pos;
	private Game game;
	private boolean isBig;
	private boolean left,right;
	//private Action action;
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
	public Position posicion() {
		return this.pos;
	}
public void update() {	//
		
	if(game.isSolid(pos.move(Action.DOWN))) {
			step();
		}
		else {
			fall();
		}
		
		if(!game.positionIsIn(pos)) {
			//Muere, resetea nivel,baja una vida y resetea el tiempo---> llama a game notificando de bajar una vida
		}
	
	
		
	}
	public void step() {
		if(game.isSolid(pos.move(Action.LEFT))){
			pos.move(Action.RIGHT);
			right=true;
			left=false;
		}
		else {
			if(game.isSolid(pos.move(Action.RIGHT))){
				pos.move(Action.LEFT);
				right=false;
				left=true;
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
