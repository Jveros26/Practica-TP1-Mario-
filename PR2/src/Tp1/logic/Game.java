//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;
import tp1.logic.gameobjects.*;
import tp1.view.GameView;
import tp1.view.Messages;

import java.util.ArrayList;

import tp1.logic.Position;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private int remainingTime;
	private Mario mario;
	public int nLevel;
    private GameObjectContainer gameObjects;
    private int numLives;
    private boolean exit;
    private boolean marioExited;
    private int points;
    

	public Game(int nLevel) {	
		this.numLives=3;
		this.points=0;
		marioExited=false;
		exit=false;
		reset(nLevel);
	}
//--------------------------------------------------

	@Override
	public void reset() {
		this.exit=true;
	}
//--------------------------------------------------
	@Override
	public String positionToString(int col, int row) {
			
		Position pos= new Position (row,col);
		
		 return gameObjects.positionToString(pos);	//Le pasa posicion al array de objetos que va a la clase y devuelve
		 //El objecto que haya en la posicion en forma de string
	}
//--------------------------------------------------

	@Override
	public boolean playerWins() {
		return marioExited;
	}
	
//--------------------------------------------------
	@Override
	public boolean playerLoses() {
		if(numLives<=0||remainingTime==0) {
			return true;
		}
		return false;
	}
//--------------------------------------------------
	@Override
	public void exit(){
		this.exit=true;
	}
//--------------------------------------------------
	@Override
	public void addPoints(int points){
		this.points+=points;
	}
//--------------------------------------------------
	@Override
	public boolean reset(int nLevel) {
		switch(nLevel) {
		case -1:
				initLevelMinus1();
				return true;
		case 0:	initLevel0();
				return true;
			
		case 1: initLevel1();
				return true;
		default: /*reset(this.nLevel;*/ System.out.println(Messages.INVALID_LEVEL_NUMBER);
				return false;
		}
	}

//--------------------------------------------------
	@Override
	public int remainingTime() {
		return remainingTime;
	}
//--------------------------------------------------
	@Override
	public int points() {
		return this.points;
	}
//--------------------------------------------------
	@Override
	public void resetPoints() {
		this.points=0;
	}
//--------------------------------------------------
	@Override
	public int numLives() {
		return this.numLives;
	}
//--------------------------------------------------
	@Override
	public void resetLives() {
		this.numLives=3;
	}
//--------------------------------------------------

	public int level() {
		return nLevel;
	}
//--------------------------------------------------
	@Override
	public void update(){
		this.remainingTime--;
		gameObjects.update();
	}
//--------------------------------------------------
	@Override
	public void clearList() {
		mario.clearListM();
	}
//--------------------------------------------------

	@Override
	public String toString() {
		return "TODO: Hola soy el game";
	}
//--------------------------------------------------

	public boolean isFinished() {
		if(this.playerLoses() || this.playerWins()||this.exit) {
			return true;
		}
		return false;
	}
//--------------------------------------------------

	private void initLevel0() {  
		this.nLevel = 0;
		this.remainingTime = 100;
		this.gameObjects= new GameObjectContainer();
		
		// 1. Mapa
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(this,new Position(13,col)));
			gameObjects.add(new Land(this,new Position(14,col)));		
		}

		gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(this,new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(this,new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(this,new Position(9,2)));
		gameObjects.add(new Land(this,new Position(9,5)));
		gameObjects.add(new Land(this,new Position(9,6)));
		gameObjects.add(new Land(this,new Position(9,7)));
		gameObjects.add(new Land(this,new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(this,new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(this,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this,new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);

		gameObjects.add(new Goombas(this,new Position(0, 19)));
	}
	
//--------------------------------------------------

private void initLevel1() {  
	this.nLevel = 1;
	this.remainingTime = 100;
	this.gameObjects= new GameObjectContainer();
	
	// 1. Mapa
	for(int col = 0; col < 15; col++) {
		gameObjects.add(new Land(this,new Position(13,col)));
		gameObjects.add(new Land(this,new Position(14,col)));		
	}

	gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,9)));
	gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,12)));
	for(int col = 17; col < Game.DIM_X; col++) {
		gameObjects.add(new Land(this,new Position(Game.DIM_Y-2, col)));
		gameObjects.add(new Land(this,new Position(Game.DIM_Y-1, col)));		
	}

	gameObjects.add(new Land(this,new Position(9,2)));
	gameObjects.add(new Land(this,new Position(9,5)));
	gameObjects.add(new Land(this,new Position(9,6)));
	gameObjects.add(new Land(this,new Position(9,7)));
	gameObjects.add(new Land(this,new Position(5,6)));
	
	// Salto final
	int tamX = 8, tamY= 8;
	int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
	
	for(int col = 0; col < tamX; col++) {
		for (int fila = 0; fila < col+1; fila++) {
			gameObjects.add(new Land(this,new Position(posIniY- fila, posIniX+ col)));
		}
	}

	gameObjects.add(new ExitDoor(this,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

	// 3. Personajes
	this.mario = new Mario(this,new Position(Game.DIM_Y-3, 0));	
	gameObjects.add(this.mario);
	
	gameObjects.add(new Goombas(this,new Position(0, 19)));
	gameObjects.add(new Goombas(this,new Position(4,6)));
	gameObjects.add(new Goombas(this,new Position(12,6)));
	gameObjects.add(new Goombas(this,new Position(12,8)));
	gameObjects.add(new Goombas(this,new Position(10,10)));
	gameObjects.add(new Goombas(this,new Position(12,10)));
	gameObjects.add(new Goombas(this,new Position(12, 14)));
}
private void initLevelMinus1(){
	this.nLevel = 0;
	this.remainingTime = 100;
	this.gameObjects= new GameObjectContainer();
}

//--------------------------------------------------
	@Override
	public boolean isSolid(Position pos) {
	
	return gameObjects.isSolid(pos);	//Comprueba en objectContainer si la pos contiene un objeto solido (Land)
}	
//--------------------------------------------------
	
	@Override
	public boolean positionIsIn(Position pos) {
	    return pos.isInside(DIM_Y, DIM_X);
	}
//--------------------------------------------------
	public void addAction(Action action) {
		mario.addAction(action);
	}
//--------------------------------------------------

	@Override
	public void marioDead() {
		numLives--;
		if(this.numLives>0) {
			reset(this.level());
		}
	}
//--------------------------------------------------
	@Override
	public void marioExited() {
		addPoints(remainingTime*10);
		marioExited=true;
	}
//--------------------------------------------------
	@Override
	public boolean addObject(String[] strsObject) {
		GameObject obj;
		if(this.mario!=null) {
			obj=this.mario.parse(strsObject, this);
			if(obj!=null) {
				this.mario=(Mario) obj;
			}
			else {
				obj=GameObjectFactory.parse(strsObject,this);
			}
			if(obj!=null) this.gameObjects.add(obj);
			return obj!=null;
		}
		else {
			obj=GameObjectFactory.parse(strsObject,this);
			if(obj!=null) this.gameObjects.add(obj);
			return obj!=null;
		}
		
		
	}
	@Override
	public void addObjectDelayed(String[] objWords) {
	    GameObject obj =GameObjectFactory.parse(objWords,this);
	    if(obj!=null) {
	    	gameObjects.addDelayed(obj);
	    }
	}
	@Override
	public void doInteractions() {
		gameObjects.doInteractions();
	}
}
