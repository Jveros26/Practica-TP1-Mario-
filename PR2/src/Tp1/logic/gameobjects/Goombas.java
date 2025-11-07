//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.gameobjects.Mario;
import tp1.logic.Game;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Action;

public class Goombas extends MovingObject {
	private boolean Alive;
	private boolean isFalling;
	private static final String NAME=Messages.GOOMBA_NAME;
	private static final String SHORTCUT=Messages.GOOMBA_SHORTCUT;

//--------------------------------------------------

	public Goombas(GameWorld game,Position pos) {
		super(game,pos,Action.RIGHT,false,NAME,SHORTCUT);
	}
	
	public Goombas() {
		super(Action.STOP,false,NAME,SHORTCUT);
	}
	//--------------------------------------------------
	@Override
		public String getIcon() {
			return Messages.GOOMBA;
		}
	//--------------------------------------------------

		@Override
		public boolean isInPosition(Position pos) {
			if(this.pos.equals(pos)) {
				return true;
			}
			return false;
		}
	//--------------------------------------------------
		@Override
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
		@Override
		public boolean receiveInteraction(Mario obj) {	//Muere si mario es grande
				if(obj.isFalling()) {	//Si mario esta en la posicion del goomba y resulta que estaba cayendo el goomba muere
					dead();
					game.addPoints(100);
				}
				else {
					if(obj.isBIG()) {	//Si mario no estaba cayendo pero se encuentra al goomba este muere
						dead();
						game.addPoints(100);
						obj.shrink();
					}
					else {
						this.game.addPoints(100);	//Se suman puntos
						game.marioDead();		//Mario muere y se reinicia todo
						
					}
				}
				return true;
		}
	//--------------------------------------------------
		@Override
		public boolean interactWith(GameItem other) {
			boolean canInteract=other.isInPosition(pos);
			if(canInteract && this.isAlive()) {
				other.receiveInteraction(this);
			}
			return canInteract;
		}
	//--------------------------------------------------
		@Override
		public  boolean receiveInteraction(Land obj) {
			return false;
		}
	//--------------------------------------------------
		@Override
		public  boolean receiveInteraction(ExitDoor obj) {
			return false;
		}
	//--------------------------------------------------
		@Override
		public  boolean receiveInteraction(Goombas obj) {	
			return false;
		}
	//--------------------------------------------------
		@Override
		public boolean receiveInteraction(Mushroom obj) {
			return false;
		}
	//--------------------------------------------------
		@Override
		public  boolean isSolid() {return false;}
	//--------------------------------------------------	
		@Override
		protected GameObject createInstance(Position pos, GameWorld game) {
			return new Goombas(game,pos);

		}


	}