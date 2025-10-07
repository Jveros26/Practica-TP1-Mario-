package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;

import java.util.ArrayList;

import Tp1.logic.Action;
import Tp1.logic.Game;
import Tp1.logic.gameobjects.Goombas;
import Tp1.logic.ActionList;
import Tp1.view.Messages;



public class Mario {
	private Action action;
	private ActionList actList;
	private Position pos;
	private Game game;
	private boolean isBig;
	private boolean left,right;
	private boolean alive;
	private boolean isFalling;
	private boolean onAir;
	private boolean isAscending;
	private int r,l,d,u;
//--------------------------------------------------

	public Mario(Game game,Position pos) {
		this.pos=pos;
		this.game=game;
		alive=true;
		isFalling=false;
		isAscending=false;
		action=Action.RIGHT;	
		actList=new ActionList();
	}
//--------------------------------------------------

	public String getIcon() {	//Va hacia la izq? Si-> devuelve imagen mario izq : No-> mario recto
		String pose=Messages.MARIO_STOP;
		if(action==Action.LEFT) {
			pose=Messages.MARIO_LEFT;
		}
		else {
			if(action==Action.RIGHT) {
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

	public boolean isInPosition(Position pos) {
		
		return alive && (this.pos.equals(pos) || 
				this.isBig && pos.equals(this.pos.move(Action.UP)));
	}
//--------------------------------------------------

	public Position posicion() {
		return this.pos;
	}
	
//--------------------------------------------------

	public void update() {	

	if(game.isSolid(pos.move(Action.DOWN))) {	
		this.onAir=false;
		if(actList.lenght()==0) {
			step();	
		}
		else {
			runActions();
		}
	}
	else {
		this.onAir=true;
		if(actList.lenght()>0) {
			fall();
			runActions();
		}
		else {
				this.action=Action.STOP;
				fall();
			}
		}
			
	if(!game.positionIsIn(pos)) {
		dead();
	}	
}	
//--------------------------------------------------

	
	public void step() {
		if(game.isLand(pos.move(Action.LEFT))){
			pos.commute(Action.RIGHT);
			this.action=Action.RIGHT;
		}
		else {
			if(game.isLand(pos.move(Action.RIGHT))){
				pos.commute(Action.LEFT);
				this.action=Action.LEFT;
				
			}
			else {
				
				if((pos.move(Action.LEFT).getCol())<0) {	//Si se va a salir dle tablero mario vuelve a girar
					pos.commute(Action.RIGHT);
					this.action=Action.RIGHT;
				}
				else {
					if(this.action==Action.LEFT && !(game.isGoomba(pos.move(Action.LEFT)))) {
						pos.commute(Action.LEFT);
						//cada vez que hagamos commute
						//this.game.doInteractionsFrom(this);
						this.action=Action.LEFT;

					}
					else {
						if(!(game.isGoomba(pos.move(Action.RIGHT)))) {
						pos.commute(Action.RIGHT);
						this.action=Action.RIGHT;
						}

					}	
				}
			}
		}
		
	}
//--------------------------------------------------

	public void fall() {
		pos.commute(Action.DOWN);
	}
//--------------------------------------------------

	public void dead() {
		this.alive=false;
		game.marioDead();
	}
//--------------------------------------------------
	
	public void addAction(Action action) {
		actList.add(action);
	}
//--------------------------------------------------
	public void clearList() {
		for(int i=actList.lenght()-1;i>=0;i--) {
			actList.remove(i);
		}
	}
	
//--------------------------------------------------

	private void /*boolean*/ runActions() {
		//Position posIni=this.pos;
		this.u=0;
		this.d=0;
		this.r=0;
		this.l=0;
		for(int i=0;i<(actList.lenght());i++) {	//Recorremos la lista de acciones
			Action acc= actList.get(i);
			if(i>0) {	//Si ya se puede mirar la posicion anterior
				Action acc0= actList.get(i-1);	//Cogemos la anterior
				if(!acc0.isOpposite(acc)) {	//Comprobamos si la anterior es la opuesta a la de ahora
					if(canMove(acc)) {	//Si no se choca con una pared deja moverse
						String a=acc.toString();	//Si no lo es ejecutamos la accion de ahora
						counter(a);	//Suma el contador de acciones con el que corresponda
							if(itCan(a)) {	//Si puede hacer el movimeinto lo hace sino lo ignora
								runAction(a);
							}
							else {
								actList.remove(i);
								i--;	//En caso de que borre lo que hago es retroceder un turno y asi no llega al contador
							}
					}
				}
				else {	//Si es opuesta solo la borramos del array
					actList.remove(i);
					i--; //En caso de que borre lo que hago es retroceder un turno y asi no llega al contador
				}
			}
			else {
				if(canMove(acc)) {	//Si no se choca con una pared deja moverse
				String a=acc.toString();	//Si no lo es ejecutamos la accion de ahora
				counter(a);	
				runAction(a);
				}
			}
		
		}
	}
//--------------------------------------------------

	
	private void runAction(String action) {
		
		switch(action) {
		
		case "left":
			pos.commute(Action.LEFT);
			this.action=Action.LEFT;

			break;
			
		case "right":
			pos.commute(Action.RIGHT);
			this.action=Action.RIGHT;

			break;
			
		case"up":
			if(!this.onAir) {
			pos.commute(Action.UP);
			this.isAscending=true;
			//this.action=Action.STOP;
			}

			break;
		
		case"down":
			pos.commute(Action.DOWN);
			this.action=Action.STOP;
			this.isFalling=false;

			break;
		}
	}
//--------------------------------------------------

	public void counter(String action) {
		switch(action) {
		case "left":
			this.l++;
			break;
			
		case "right":
			this.r++;
			break;
			
		case"up":
			this.u++;
			break;
		
		case"down":
			this.d++;
			break;
		}
	}
//--------------------------------------------------

	public boolean itCan(String a) {
		boolean ok=true;
		if(a=="left" && this.l>4) {
			ok=false;
		}
		else {
			if(a=="right" && this.r>4) {
				ok=false;
			}
			else {
				if(a=="up" && this.u>4) {
					ok=false;
				}
				else {
					if(a=="down" && this.d>4) {
						ok=false;
					}
				}
			}
		}
		return ok;
	}
//--------------------------------------------------

	public boolean canMove(Action acc) {
		boolean yes=true;
		switch(acc) {
		case LEFT:
			if(game.isLand(pos.move(Action.LEFT))) {
				yes=false;
			}
			break;
		case RIGHT:
			if(game.isLand(pos.move(Action.RIGHT))) {
				yes=false;
			}
			break;
		case DOWN:
			if(game.isLand(pos.move(Action.DOWN))) {
				yes=false;
			}
			
			break;
		case UP:
			if(game.isLand(pos.move(Action.UP))) {
				yes=false;
			}

			break;
		}
		return yes;
	}
//--------------------------------------------------
	public boolean interactWith(ExitDoor other) {
		Position posit=other.exitDoorPos();
		
		boolean isInDoor=false;
		if(this.pos.equals(posit)) {	//Comprueba si la posicion de la exitdoor y la de mario es la misma 
			isInDoor=true;	//si lo es devuelve true sino false
			game.marioExited();
		}
		return isInDoor;
	}
		
}


	
	

//--------------------------------------------------
	
	/*public boolean Interactwith(Goombas other) {
		boolean canInteract=other.isInPosition(this.pos) ||
				this.isBig && other.isInPosition(this.pos.move(Action.UP));
		if(canInteract && !this.isFalling) {
			
		}
	}*/



/*if(this.action==Action.RIGHT) {
if(actList.lenght()>0) {
	runActions();
}
else {
pos.commute(Action.RIGHT);
fall();
}
}
else {
this.action=Action.STOP;
fall();
}*/

