package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject{
	protected Action direction;
	protected boolean isFalling;
	
	public MovingObject(GameWorld game, Position pos,Action dir,boolean isFalling,String NAME,String SHORTCUT){
		super(game,pos,NAME,SHORTCUT);
		this.direction=dir;
		this.isFalling=isFalling;
	}
//--------------------------------------------------

	public MovingObject(Action dir,boolean isFalling,String NAME,String SHORTCUT) {
		super(NAME,SHORTCUT);
		this.direction=dir;
		this.isFalling=isFalling;
	}
//--------------------------------------------------
	@Override
	public abstract String getIcon();
//--------------------------------------------------

	public boolean isFalling() {return isFalling;}
//--------------------------------------------------

	public void step() {		
		if(!((game.isSolid(pos.move(Action.LEFT)) && (game.isSolid(pos.move(Action.RIGHT)))))) {
			Position pa=pos.move(Action.LEFT);
			if(game.isSolid(pos.move(Action.LEFT)) || !game.positionIsIn(pa)){ 
				this.pos=pos.move(Action.RIGHT);
				this.direction=Action.RIGHT;

			}
			else {
				if(game.isSolid(pos.move(Action.RIGHT))){
					this.pos=pos.move(Action.LEFT);
					this.direction=Action.LEFT;

				}
				else {
					if(this.direction==Action.LEFT) {
						this.pos=pos.move(Action.LEFT);
						this.direction=Action.LEFT;

					}
					else {
						this.pos=pos.move(Action.RIGHT);
						this.direction=Action.RIGHT;

					}
				}
			}
		}
			
		}
//--------------------------------------------------
	protected abstract GameObject createInstance(Position pos,GameWorld game,Action dir);

	@Override
	public GameObject parse(String strsObject[],GameWorld game) {//CASTEAR OBJ PARA PONER LA ACCION Y EN MARIO IGUAL
		GameObject obj;
		obj=super.parse(strsObject, game);	//LLamamos al parse de arriba para comprobar que lo primero del array es un GameObject
		if(obj!=null) {	//Si la instancia devuelta por el super es null es que no concuerda con la estructura GameObject
			String p=strsObject[3].toLowerCase();
			Action acc=Action.parse(p);
			if(acc!=null){	//Si el string concuerda con que es una accion devuelve instancia
				Position po=new Position(strsObject[0],strsObject[1]);
				obj= this.createInstance(po,game,acc);
			}
			else {
				obj=null;
			}
		}
		return obj;
	}
	
	public void fall() {
		this.pos=pos.move(Action.DOWN);
	}
	
}
