package Tp1.logic.gameobjects;

import Tp1.logic.Position;
import Tp1.view.Messages;
import Tp1.logic.Action;
import Tp1.logic.Game;
import Tp1.logic.gameobjects.Goombas;
import Tp1.logic.ActionList;


public class Mario {
	private Action action;
	private ActionList actList;
	private Position pos;
	private Game game;
	private boolean isBig;
	private boolean left,right;
	private boolean alive;
	private boolean isFalling;
	private boolean isAscending;
	public Mario(Game game,Position pos,ActionList actL) {
		this.pos=pos;
		this.game=game;
		this.actList=actL;
		alive=true;
		isFalling=false;
		isAscending=false;
		action=Action.RIGHT;
	}
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
	public int getRow() {
		return pos.getRow();
	}
	public int getCol() {
		return pos.getCol();
	}
	public boolean isInPosition(Position pos) {
		
		return alive && (this.pos.equals(pos) || 
				this.isBig && pos.equals(this.pos.move(Action.UP)));
	}
	public Position posicion() {
		return this.pos;
	}
	
	
	public void update() {	
	if(actList.lenght()!=0) {	
		if(game.isSolid(pos.move(Action.DOWN))) {	//Aqui falta que muera y resetee
			if((game.isGoomba(pos.move(Action.LEFT))&&(action==Action.LEFT))) {
				dead();	//falta añadir en la funcion dead de game que reinicie
			}
			else {
				if((game.isGoomba(pos.move(Action.RIGHT))&&(action==Action.RIGHT))) {
					dead();
				}
				else {
					step();	
				}
			}
		}
		else {
		
			fall();
		}
	}
	else {
		//runActions();	//Tiene qu ehacer asi pero la funcion es booleana
	}
	if(!game.positionIsIn(pos)) {
		dead();
	}	
}
	
	
	
	
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
				
				if((pos.move(Action.LEFT).getCol())<0) {	//Si se va a salir dle tablero mario vuelve a girar
					pos.commute(Action.RIGHT);
					this.action=Action.RIGHT;
				}
				else {
					if(this.action==Action.LEFT) {
						pos.commute(Action.LEFT);
						//cada vez que hagamos commute
						//this.game.doInteractionsFrom(this);
						this.action=Action.LEFT;

					}
					else {
						pos.commute(Action.RIGHT);
						this.action=Action.RIGHT;

					}	
				}
			}
		}
		
	}
	public void fall() {
		pos.commute(Action.DOWN);
	}
	public void dead() {
		this.alive=false;
		game.marioDead();
	}
	
	/*private boolean runActions() {
		Position posIni=this.pos;
		for(int i=0;i<(actList.lenght());i++) {
			//String action=Action.toString(actList.get(i));
			//runAction(action);
		}
	}*/
	
	/*private void runAction(String action) {
		
		switch(action) {
		
		case
		
		
		
		}
	}*/
	
	
	
	
	/*public boolean Interactwith(Goombas other) {
		boolean canInteract=other.isInPosition(this.pos) ||
				this.isBig && other.isInPosition(this.pos.move(Action.UP));
		if(canInteract && !this.isFalling) {
			
		}
	}*/
}
