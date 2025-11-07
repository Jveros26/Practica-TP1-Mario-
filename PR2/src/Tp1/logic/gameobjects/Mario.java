//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

import java.util.ArrayList;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.gameobjects.Goombas;
import tp1.logic.ActionList;
import tp1.view.Messages;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;


public class Mario extends MovingObject{
	private ActionList actList;
	private boolean isBig;
	private boolean left,right;
	private boolean onAir;
	private boolean isAscending;
	private static final String NAME=Messages.MARIO_NAME;
	private static final String SHORTCUT=Messages.MARIO_SHORTCUT;

//--------------------------------------------------

	public Mario(GameWorld game,Position pos) {
		super(game,pos,Action.STOP,false,NAME,SHORTCUT);	
		isBig=true;
		isAscending=false;
		actList=new ActionList();
	}
	
	public Mario() {
		super(Action.STOP,false,NAME,SHORTCUT);
	}

//--------------------------------------------------
	@Override
	public String getIcon() {	//Va hacia la izq? Si-> devuelve imagen mario izq : No-> mario recto
		String pose=Messages.MARIO_STOP;
		if(direction==Action.LEFT) {
			pose=Messages.MARIO_LEFT;
		}
		else {
			if(direction==Action.RIGHT) {
				pose=Messages.MARIO_RIGHT;
			}
		}
		return pose;
	}
//--------------------------------------------------

	public Position marioPos() {
		return this.pos;
	}
//--------------------------------------------------
	@Override
	public boolean isInPosition(Position pos) {
		
		return isAlive() && (this.pos.equals(pos) || 
				this.isBig && pos.equals(this.pos.move(Action.UP)));
	}
//--------------------------------------------------

	public Position posicion() {
		return this.pos;
	}
	
//--------------------------------------------------
	@Override
	public void update() {	

	if(game.isSolid(pos.move(Action.DOWN))) {	
		this.onAir=false;
		this.isAscending=false;
		this.isFalling=false;
		if(actList.lenght()==0) {
			direction=Action.RIGHT;
			step();	
		}
		else {
			runActions();
		}
	}
	else {
		this.onAir=true;
		this.isFalling=true;
		if(actList.lenght()>0) {
			fall();
			runActions();
		}
		else {
				this.direction=Action.STOP;
				fall();
			}
		}
			
	if(!game.positionIsIn(pos)) {
		game.marioDead();
	}	
}	
//--------------------------------------------------

	/*STEP() IMPLEMENTADA EN MOVING OBJECT*/

//--------------------------------------------------

	public void fall() {
		this.pos=pos.move(Action.DOWN);
	}

//--------------------------------------------------
	
	public void addAction(Action action) {
		actList.add(action);
	}

	
	public void shrink() {
		isBig=false;
	}
//--------------------------------------------------


	private void runActions() {
		actList.clearList();	//Limpio la lista de movimientos que no se pueden hacer o contrarios
		
		for(int i=0;i<(actList.lenght());i++) {	//Recorremos la lista de acciones
			Position pa=pos.move(Action.LEFT);
			Action acc= actList.get(i);	//Obtengo la accion que toca ahora
			if(i>0) {	//Si ya se puede mirar la posicion anterior
				if(canMove(acc)) {	//Si no se choca con una pared deja moverse
						if(!((acc==Action.LEFT) && !game.positionIsIn(pa))) {	//Mientras se vaya a mover a la izquierda y la posicion de la izquierda este fuera del tablero no se realiza la accion
							runAction(acc);
						}
				}
				else {
					actList.remove(i);
					i--;	//En caso de que borre lo que hago es retroceder un turno y asi no llega al contador
				}
			}
			else {
				if(canMove(acc)) {	//Si no se choca con una pared deja moverse
					if(!((acc==Action.LEFT)  && !game.positionIsIn(pa))) {	
						runAction(acc);
					}
				}
			}
		}
		
}
//--------------------------------------------------

	
	private void runAction(Action action) {
		Position p=this.pos.move(Action.UP);	//Posicion de arriba de mario si este es grande
		switch(action) {
		
		case Action.LEFT:
			if(isBig) {
				if(!game.isSolid(p.move(Action.LEFT)) && !game.isSolid(this.pos.move(Action.LEFT))) {
					this.pos=pos.move(Action.LEFT);
					this.direction=Action.LEFT;
				}
				
			}
			else {
				if(!game.isSolid(pos.move(Action.LEFT))) {
					this.pos=pos.move(Action.LEFT);
					this.direction=Action.LEFT;
				}
				
			}
			break;
			
		case Action.RIGHT:
			
			if(isBig) {
				if(!game.isSolid(p.move(Action.RIGHT)) && !game.isSolid(this.pos.move(Action.RIGHT))) {
					this.pos=pos.move(Action.RIGHT);
					this.direction=Action.RIGHT;
				}
				
			}
			else {
				if(!game.isSolid(pos.move(Action.RIGHT))) {
					this.pos=pos.move(Action.RIGHT);
					this.direction=Action.RIGHT;
				}
				
			}
			
			break;
			
		case Action.UP:				
			if(isBig) {
				if(!game.isSolid(p.move(Action.UP))) {
					this.pos=pos.move(Action.UP);
					this.isAscending=true;
					this.isFalling=false;
				}
					
			}
			else {
				if(!game.isSolid(pos.move(Action.UP))) {
					this.pos=pos.move(Action.UP);
					this.isAscending=true;
					this.isFalling=false;
				}
					
			}
			break;
		
		case Action.DOWN:
			boolean isDead=false;
			while(!game.isSolid(pos.move(Action.DOWN)) && !isDead) {
				if(!game.positionIsIn(pos)) {
					dead();
					isDead=true;
				}
				else {
				this.pos=pos.move(Action.DOWN);
				this.isFalling=true;
				}
			}
			this.direction=Action.STOP;
			

			break;
		}
	}
//--------------------------------------------------

	public boolean canMove(Action acc) {
		boolean yes=true;
		switch(acc) {
		case LEFT:
			if(game.isSolid(pos.move(Action.LEFT))) {
				yes=false;
			}
			break;
		case RIGHT:
			if(game.isSolid(pos.move(Action.RIGHT))) {
				yes=false;
			}
			break;
		case DOWN:
			if(game.isSolid(pos.move(Action.DOWN))) {
				yes=false;
			}
			
			break;
		case UP:
			if(game.isSolid(pos.move(Action.UP))) {
				yes=false;
			}

			break;
		}
		return yes;
	}
//--------------------------------------------------

	public void clearListM() {
		for(int i=actList.lenght()-1;i>=0;i--) {
			actList.remove(i);
		}
	}

//--------------------------------------------------

//public void Interactwith(Goombas obj) {
//	boolean canInteract=obj.isInPosition(this.pos) ||	//si el gomba y mario estan en la misma pos o si mario es grande y el goomba esta en la posicion de abajo o arriba
//			this.isBig && obj.isInPosition(this.pos.move(Action.UP));
//	if(canInteract && !this.isFalling) {
//		this.crash(obj);	//Si no cae mario se choca y o muere o mata al goomba y deja de ser grande
//	}
//	else {
//		if(canInteract && this.isFalling) {
//			obj.receiveInteraction(this);	//Gomba muere
//			game.addPoints(100);
//		}
//	}
//	
//}

//--------------------------------------------------

public boolean isBIG() {
	return this.isBig;
	
}
//--------------------------------------------------
@Override
public boolean isFalling() {
	return this.isFalling;
}

//--------------------------------------------------
	@Override
	public  boolean isSolid() {return false;}

//--------------------------------------------------
	@Override
	public boolean interactWith(GameItem item) {
		Position posUp=pos.move(Action.UP);
		boolean canInteract=item.isInPosition(pos) || (this.isBIG() && item.isInPosition(posUp));	//Comprueba el mario de arriba y abajo
		if(canInteract && this.isAlive()) {
			item.receiveInteraction(this);
		}
		return canInteract;
	}
//--------------------------------------------------
		@Override
		public  boolean receiveInteraction(Land obj) {
			return false;
		}
//		@Override
//		public  boolean receiveInteraction(Box obj) {
//			Position posUp=pos.move(Action.UP);
//			Position posUpUp=posUp.move(Action.UP);
//
//			boolean canInteract;
//			if((this.direction==Action.UP && obj.isInPosition(posUp))||
//					(this.direction==Action.UP && this.isBIG() && obj.isInPosition(posUpUp))) {
//				canInteract=true;
//			}
//			else {
//				canInteract=false;
//			}
//			if(this.isAlive() && canInteract) {
//				return true;
//			}
//			else {
//				return false;
//			}
//			
//		}
//--------------------------------------------------
		@Override
		public  boolean receiveInteraction(ExitDoor obj) {
			Position posUp=pos.move(Action.UP);
			boolean canInteract= obj.isInPosition(pos) || this.isBIG() && obj.isInPosition(posUp);
			if(canInteract && this.isAlive() && obj.receiveInteraction(this)) {
				game.marioExited();
				return true;
			}
			else {
				return false;
			}
		}
//--------------------------------------------------
		@Override
		public boolean  receiveInteraction(Mario obj) {
			return false;
		}
//--------------------------------------------------
		@Override
		public  boolean receiveInteraction(Goombas obj) {
			Position posUp=pos.move(Action.UP);
			boolean canInteract= obj.isInPosition(pos) || this.isBIG() && obj.isInPosition(posUp);	//Comprueba si mario se choca con algun goomba en pequeño o si es grande arriba y abajo
			if(canInteract && this.isAlive()) {
				return obj.receiveInteraction(this);
			}
			return false;
		}
//--------------------------------------------------
		@Override
		public boolean receiveInteraction(Mushroom obj) {
			Position posUp=pos.move(Action.UP);
			boolean canInteract= obj.isInPosition(pos) || this.isBIG() && obj.isInPosition(posUp);
			if(canInteract && this.isAlive() && !this.isBIG()) {	//Comprueba que concuerde la pos con la del la seta, que mario esta vivo y que no es grande
					this.isBig=true;
					return true;
			}
			else {
				return false;
			}
			
		}
//--------------------------------------------------

		@Override
		protected GameObject createInstance(Position pos, GameWorld game) {
			return new Mario(game,pos);

		}
//--------------------------------------------------

		private boolean isStatus(String p) {
			p.toLowerCase();
			if(p=="big"|| p=="p") {
				return true;
			}
			else {
				if(p=="small"||p=="s") {
					return true;
				}
			}
			return false;
		}
//--------------------------------------------------

		@Override
	public GameObject parse(String objWords[],GameWorld game) {
		GameObject obj;
		obj=super.parse(objWords, game);
		if(obj!=null) {	//Si lo devuelto del super no concuerda con GameObject o MovingObject no es un elemento movible y no concuerda con mario
			if(isStatus(objWords[3])) {	//Concuerda con que el ultimo elemento es Big o Small para mario
				obj=this.createInstance(pos,game);	//Si es un GameObject, MovingObject y concuerda con estructura mario devuelve estancia
			}
			else {
				return null;
			}
		}
		return obj;
	}

		
}