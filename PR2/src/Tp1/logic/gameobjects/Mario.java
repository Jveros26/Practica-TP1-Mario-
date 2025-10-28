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
	private Position pos;
	private Game game;
	private boolean isBig;
	private boolean left,right;
	private boolean onAir;
	private boolean isAscending;
	private static final String name="MARIO";
	private static final String shortcut="MA";
	private int r,l,d,u;
//--------------------------------------------------

	public Mario(Game game,Position pos) {
		super(game,pos,Action.STOP,false,name,shortcut);	
		isBig=true;
		isAscending=false;
		actList=new ActionList();
	}

//--------------------------------------------------

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

	public boolean isInPosition(Position pos) {
		
		return isAlive && (this.pos.equals(pos) || 
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
		this.isAscending=false;
		this.isFalling=false;
		if(actList.lenght()==0) {
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

	/*public void dead() {
		this.alive=false;
		game.marioDead();
	}*/
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

	private void runActions() {
		//Position posIni=this.pos;
		this.u=0;
		this.d=0;
		this.r=0;
		this.l=0;
		for(int i=0;i<(actList.lenght());i++) {	//Recorremos la lista de acciones
			Position pa=pos.move(Action.LEFT);
			Action acc= actList.get(i);	//Obtengo la accion que toca ahora
			if(i>0) {	//Si ya se puede mirar la posicion anterior
				if(!isOpposite(acc,i)) {	//Comprobamos si no hay ninguna accion anterior que sea opuesta
					if(canMove(acc)) {	//Si no se choca con una pared deja moverse
						String a=acc.toString();	//Si no lo es ejecutamos la accion de ahora
						counter(a);	//Suma el contador de acciones con el que corresponda
							if(itCan(a)) {	//Si puede hacer el movimeinto lo hace sino lo ignora
								if(!((acc==Action.LEFT) && !game.positionIsIn(pa))) {	//Mientras se vaya a mover a la izquierda y la posicion de la izquierda este fuera del tablero no se realiza la acion
									runAction(a);
								}
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
					if(!((acc==Action.LEFT)  && !game.positionIsIn(pa))) {
						String a=acc.toString();	//Si no lo es ejecutamos la accion de ahora
						counter(a);	
						runAction(a);
					}
				}
			}
		
		}
	}
//--------------------------------------------------

	
	private void runAction(String action) {
		Position p=this.pos.move(Action.UP);	//Posicion de arriba de mario si este es grande
		switch(action) {
		
		case "left":
			if(isBig) {
				if(!game.isSolid(p.move(Action.LEFT)) && !game.isSolid(this.pos.move(Action.LEFT))) {
					this.pos=pos.move(Action.LEFT);
					this.action=Action.LEFT;
				}
				
			}
			else {
				if(!game.isSolid(pos.move(Action.LEFT))) {
					this.pos=pos.move(Action.LEFT);
					this.action=Action.LEFT;
				}
				
			}
			break;
			
		case "right":
			
			if(isBig) {
				if(!game.isSolid(p.move(Action.RIGHT)) && !game.isSolid(this.pos.move(Action.RIGHT))) {
					this.pos=pos.move(Action.RIGHT);
					this.action=Action.RIGHT;
				}
				
			}
			else {
				if(!game.isSolid(pos.move(Action.RIGHT))) {
					this.pos=pos.move(Action.RIGHT);
					this.action=Action.RIGHT;
				}
				
			}
			
			break;
			
		case"up":
			if(!this.onAir) {
				
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

			}
			else {
				this.isAscending=false;
				this.isFalling=true;
			}

			break;
		
		case"down":
	
			boolean isDead=false;
			while(!game.isSolid(pos.move(Action.DOWN)) && !isDead) {
				if(!game.positionIsIn(pos)) {
					dead();
					isDead=true;
				}
				else {
					this.pos=pos.move(Action.DOWN);
				this.action=Action.STOP;
				this.isFalling=true;
				}
			}
			

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
	public boolean interactWith(ExitDoor other) {
		
		if(other.isInPosition(this.pos)) {
			return true;
		}
		return false;
	}
//--------------------------------------------------

public void Interactwith(Goombas obj) {
	boolean canInteract=obj.isInPosition(this.pos) ||	//si el gomba y mario estan en la misma pos o si mario es grande y el goomba esta en la posicion de abajo o arriba
			this.isBig && obj.isInPosition(this.pos.move(Action.UP));
	if(canInteract && !this.isFalling) {
		this.crash(obj);	//Si no cae mario se choca y o muere o mata al goomba y deja de ser grande
	}
	else {
		if(canInteract && this.isFalling) {
			obj.receiveInteraction(this);	//Gomba muere
			game.addPoints(100);
		}
	}
	
}
//--------------------------------------------------

private void crash(Goombas other) {
	
	if(this.isBig) {
		other.receiveInteraction(this);	//El goomba muere
		this.isBig=false;	//Deja de ser grande
		game.addPoints(100);
	}
	else {
		this.game.addPoints(100);	//Se suman puntos
		other.receiveInteraction(this);
	}
}
//--------------------------------------------------

public boolean isBIG() {
	return this.isBig;
	
}
//--------------------------------------------------

public boolean isFalling() {
	return this.isFalling;
}
//--------------------------------------------------

	public boolean isOpposite(Action a,int p) {
		boolean itIs=false;
		int i=p;
		while(!itIs && i>=0) {
			Action acc=actList.get(i);
			if(a==Action.LEFT && acc==Action.RIGHT) {
				itIs=true;
			}
			else {
				if(a==Action.RIGHT && acc==Action.LEFT) {
					itIs=true;
				}
				else {
					if(a==Action.DOWN && acc==Action.UP) {
						itIs=true;
					}
					else {
						if(a==Action.UP && acc==Action.DOWN) {
							itIs=true;
						}
					}
				}
			}
			i--;
		}
		return itIs;
	}
//--------------------------------------------------

	public  boolean isSolid() {return false;}

//--------------------------------------------------
	public boolean interactWith(GameItem item) {
		boolean canInteract=item.isInPosition(pos);
		if(canInteract) {
			item.recieveInteraction(this);
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
				game.marioExited();
			}
		}
//--------------------------------------------------
		public void  receiveInteraction(Mario obj) {
		}
//--------------------------------------------------
		public  void receiveInteraction(Goombas obj) {
			
		}

		@Override
		public void addPoints(int p) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected GameObject createInstance(Position pos, GameWorld game) {
			return new Mario(game,pos);

		}

	

}