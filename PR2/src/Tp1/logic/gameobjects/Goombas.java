//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.gameobjects.Mario;
import Tp1.logic.Game;
import Tp1.logic.GameItem;
import Tp1.logic.Action;

public class Goombas extends MovingObject {
	private boolean Alive;
	private boolean isFalling;
//--------------------------------------------------

	public Goombas(Game game,Position pos) {
		super(game,pos,Action.RIGHT,false);
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
			//if(game.isSolid(pos.move(Action.DOWN)) || game.isGoomba(pos.move(Action.DOWN))){

			if(game.isSolid(pos.move(Action.DOWN))){
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

		/*STEP() IMPLEMENTADA EN MOVING OBJECT*/

	//--------------------------------------------------

		public void fall() {
			this.pos=pos.move(Action.DOWN);
		}

	//--------------------------------------------------

		public void receiveInteraction(Mario obj) {
			if(obj.isFalling()) {	//Si mario esta en la posicion del goomba y resulta que estaba cayendo el goomba muere
				dead();
			}
			else {
				if(obj.isBIG()) {	//Si mario no estaba cayendo pero se encuentra al goomba este muere
					dead();
				}
				else {
					this.game.addPoints(100);	//Se suman puntos
					game.marioDead();		//Mario muere y se reinicia todo
				}
			}
		}
	//--------------------------------------------------
		public  boolean isSolid() {return true;}
	//--------------------------------------------------
		protected Goombas createInstance(Position pos, GameWorld game) {
			return new Goombas(game,pos);
		}
	//--------------------------------------------------

		public boolean interactWith(GameItem other) {
			boolean canInteract=other.isInPosition(pos);
			if(canInteract) {
				other.recieveInteraction(this);
			}
			return canInteract;
		}
	//--------------------------------------------------
		public  void receiveInteraction(Land obj) {
			if(obj.isInPosition(pos)) {
				dead();
			}
		}
	//--------------------------------------------------
		public  void receiveInteraction(ExitDoor obj) {
			if(obj.isInPosition(pos)) {
				dead();
			}
		}
	//--------------------------------------------------
		public  void receiveInteraction(Goombas obj) {	
			if(obj.isInPosition(pos)) {
				this.direction=direction.opposite(direction);
			}
		}
	}