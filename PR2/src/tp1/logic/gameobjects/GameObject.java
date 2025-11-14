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
	private final String NAME;
	private final String SHORTCUT;
	
	private String getNAME() {return this.NAME;}
	private String getSHORCUT() {return this.SHORTCUT;}
	
	public GameObject(GameWorld game, Position pos,String NAME,String SHORTCUT) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		this.NAME=NAME;
		this.SHORTCUT=SHORTCUT;
		
	}
//--------------------------------------------------

	public GameObject(String NAME,String SHORTCUT) {
		this.NAME=NAME;
		this.SHORTCUT=SHORTCUT;
	}
//--------------------------------------------------
	
	public GameObject parse(String strsObject[],GameWorld game) {

		
		GameObject obj=null;
		if(strsObject.length>=2 && matchParseName(strsObject[2])) {	//Comprueba si la lista es mayor igual que dos y si concuerda con el nombre
			Position pos=new Position(strsObject[0],strsObject[1]);	//Crea posicion a partir del string
			if(game.positionIsIn(pos)) {	//Si esta dentro del tablero
				obj=this.createInstance(pos,game);	//Crea instancia de cada objeto que se ira casteando (o no)
			}
			else {	//Si no esta dentro del juego no se puede
				return null;
			}
		}
		return obj;
	}
	
//--------------------------------------------------

	protected abstract GameObject createInstance(Position pos,GameWorld game);

	
//--------------------------------------------------
	
	protected boolean matchParseName(String id) {
		return getNAME().equalsIgnoreCase(id) 
				|| getSHORCUT().equalsIgnoreCase(id);
	}
//--------------------------------------------------
	@Override
	public boolean isInPosition(Position p) {
		return isAlive && (this.pos.equals(p));
	}
//--------------------------------------------------
	@Override
	public boolean isAlive() {
		return isAlive;
	}
//--------------------------------------------------

	public void dead(){
		this.isAlive = false;
	}
//--------------------------------------------------
	@Override
	public abstract boolean isSolid();	//Abtracta pq cada una implementa su isSolid pq depende directamente del tipo
//--------------------------------------------------
	
	public  abstract void update();	//Abtracta tambien pq cada clase tiene su propio update
								//Aunque land y ExitDoor tmb tengan updates son vacios y no hace nada
//--------------------------------------------------

	public abstract String getIcon();
//--------------------------------------------------

	protected void move(Action dir) {	//Actualiza posicion en base a la direccion que quiera mover
		this.pos=pos.move(dir);
	}
//--------------------------------------------------
	
	public boolean isSolid(Position pos) {
		if(this.pos.equals(pos)) {
			return isSolid();
		}
		return false;
	}
	
//--------------------------------------------------

	public void marioExited() {
		game.marioExited();
	}

}