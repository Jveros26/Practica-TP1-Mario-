//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.gameobjects.Mario;
import tp1.logic.Game;
import tp1.logic.GameItem;
import tp1.logic.Action;

public class Goombas extends MovingObject {
	private boolean Alive;
	private boolean isFalling;
	private static final String name="GOOMBA";
	private static final String shortcut="GOO";
//--------------------------------------------------

	public Goombas(Game game,Position pos) {
		super(game,pos,Action.RIGHT,false,name,shortcut);
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
		protected Goombas createInstance(Position pos, Game game) {
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
				this.direction=direction.opposite(direction);
			}
		}
	//--------------------------------------------------
		public  void receiveInteraction(ExitDoor obj) {
			if(obj.isInPosition(pos)) {
				this.direction=direction.opposite(direction);
			}
		}
	//--------------------------------------------------
		public  void receiveInteraction(Goombas obj) {	
			if(obj.isInPosition(pos)) {
				this.direction=direction.opposite(direction);
			}
		}
	//--------------------------------------------------
		public  boolean isSolid() {return false;}
	}