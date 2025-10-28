package Tp1.logic.gameobjects;

import Tp1.logic.Action;
import Tp1.logic.Game;
import Tp1.logic.Position;
import Tp1.logic.GameItem;
import Tp1.logic.GameWorld;
import Tp1.view.Messages;

public abstract class GameObject implements GameItem,GameWorld{ 

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected Game game; 
	private final String name;
	private final String shortcut;
	
	private String getShortcut() {return shortcut;}
	private String getName() {return name;}
	
	public GameObject(Game game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public GameObject parse(String strsObject[],GameWorld game ) {
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
	
	public void update() {};
	
	public boolean isInPosition(Position p) {
		// TODO fill your code here, it should depends on the status of the object
		return isAlive && (this.pos.equals(p));
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	// TODO implement and decide, Which one is abstract?
	// public boolean isSolid()
	// public void update()
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {	//Actualiza posicion en base a la direccion que quiera mover
		this.pos=pos.move(dir);
	}
}