package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.view.Messages;

public abstract class GameObject implements GameItem{ 

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected GameWorld game; //Como los objetos solo perciben esa parte pequeña de game se le asigna GameWorld en vez de game
	private final String name;
	private final String shortcut;
	
	private String getShortcut() {return shortcut;}
	private String getName() {return name;}
	
	public GameObject(GameWorld game, Position pos,String name,String shortcut) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		this.name=name;
		this.shortcut=shortcut;
		
	}
	
	public GameObject parse(String strsObject[],Game game ) {
		GameObject obj=null;
		
		if(strsObject.length>=2 && matchParseName(strsObject[1])) {
			Position pos=new Position(strsObject[0]);
			
			if(game.positionIsIn(pos)) {
				obj=this.createInstance(pos,game);
			}
		}
		return obj;
	}
	
	protected abstract GameObject createInstance(Position pos,GameWorld game);
	
	
	protected boolean matchParseName(String name) {
		return getShortcut().equalsIgnoreCase(name) || 
				getName().equalsIgnoreCase(name);
	}
	
	
	public boolean isInPosition(Position p) {
		return isAlive && (this.pos.equals(p));
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	// TODO implement and decide, Which one is abstract?
	public abstract boolean isSolid();
	
	public void update() {
		if(!isSolid()) {
			
		}
		
	}
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {	//Actualiza posicion en base a la direccion que quiera mover
		this.pos=pos.move(dir);
	}
	
	public boolean isSolid(Position pos) {
		if(this.pos.equals(pos)) {
			return isSolid();
		}
		return false;
	}
	public void marioExited() {
		game.marioExited();
	}

}