//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Game;
import tp1.logic.Action;

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


	public boolean isInPosition(Position pos) {
		if(this.pos.equals(pos)) {
			return true;
		}
		return false;
	}
//--------------------------------------------------

	public void update() {
		
		if(game.isSolid(pos.move(Action.DOWN)) || game.isGoomba(pos.move(Action.DOWN))){
			this.isFalling=false;
			step();	
		}
		else{
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
		
	if(!((game.isSolid(pos.move(Action.LEFT))|| game.isGoomba(pos.move(Action.LEFT))) && (game.isSolid(pos.move(Action.RIGHT))||game.isGoomba(pos.move(Action.RIGHT))))) {
		Position pa=pos.move(Action.LEFT);
		if(game.isSolid(pos.move(Action.LEFT))|| game.isGoomba(pos.move(Action.LEFT)) || !game.positionIsIn(pa)){ 
			this.pos=pos.move(Action.RIGHT);
			this.action=Action.RIGHT;

		}
		else {
			if(game.isSolid(pos.move(Action.RIGHT))||game.isGoomba(pos.move(Action.RIGHT))){
				this.pos=pos.move(Action.LEFT);
				this.action=Action.LEFT;

			}
			else {
				if(this.action==Action.LEFT) {
					this.pos=pos.move(Action.LEFT);
					this.action=Action.LEFT;

				}
				else {
					this.pos=pos.move(Action.RIGHT);
					this.action=Action.RIGHT;

				}
			}
		}
	}
		
	}
//--------------------------------------------------

	public void fall() {
		this.pos=pos.move(Action.DOWN);
	}
//--------------------------------------------------

	public Action accionG() {
		return this.action;
	}
//--------------------------------------------------

	public void receiveInteraction(Mario other) {
		if(other.isFalling()) {	//Si mario esta en la posicion del goomba y resulta que estaba cayendo el goomba muere
			dead();
		}
		else {
			if(other.isBIG()) {	//Si mario no estaba cayendo pero se encuentra al goomba este muere
				dead();
			}
			else {
				this.game.addPoints(100);	//Se suman puntos
				game.marioDead();		//Mario muere y se reinicia todo
			}
		}
	}

}
