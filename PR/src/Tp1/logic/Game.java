package Tp1.logic;
import Tp1.logic.gameobjects.*;

public class Game {

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
    

	//TODO fill your code
	public Game(int nLevel) {	
		this.numLives=3;
		this.points=0;
		marioExited=false;
		exit=false;
		reset(nLevel);
	}
	
//--------------------------------------------------

	public String positionToString(int col, int row) {
			
		Position pos= new Position (row,col);
		
		 return gameObjects.positionToString(pos);	//Le pasa posicion al array de objetos que va a la clase y devuelve
		 //El objecto que haya en la posicion en forma de string
	}
//--------------------------------------------------


	public boolean playerWins() {
		return marioExited;
	}
	
//--------------------------------------------------

	public boolean playerLoses() {
		if(numLives<=0||remainingTime==0) {
			return true;
		}
		return false;
	}
//--------------------------------------------------

	public void exit(){
		this.exit=true;
	}
//--------------------------------------------------

	public void addPoints(int points){
		this.points+=points;
	}
//--------------------------------------------------

	public void reset(int nLevel) {
		switch(nLevel) {
		case 0:	initLevel0();
			break;
		case 1: initLevel1();
			break;
		default: initLevel1();
		}
	}

//--------------------------------------------------

	public int remainingTime() {
		return remainingTime;
	}
//--------------------------------------------------

	public int points() {
		return this.points;
	}
//--------------------------------------------------

	public void resetPoints() {
		this.points=0;
	}
//--------------------------------------------------

	public int numLives() {
		return this.numLives;
	}
//--------------------------------------------------

	public void resetLives() {
		this.numLives=3;
	}
//--------------------------------------------------

	public int level() {
		return nLevel;
	}
//--------------------------------------------------

	public void update(){
		this.remainingTime--;
		gameObjects.update();
	}
//--------------------------------------------------

	public void clearList() {
		mario.clearList();
	}
//--------------------------------------------------

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
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
			gameObjects.add(new Land(new Position(13,col)));
			gameObjects.add(new Land(new Position(14,col)));		
		}

		gameObjects.add(new Land(new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(new Position(9,2)));
		gameObjects.add(new Land(new Position(9,5)));
		gameObjects.add(new Land(new Position(9,6)));
		gameObjects.add(new Land(new Position(9,7)));
		gameObjects.add(new Land(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

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
		gameObjects.add(new Land(new Position(13,col)));
		gameObjects.add(new Land(new Position(14,col)));		
	}

	gameObjects.add(new Land(new Position(Game.DIM_Y-3,9)));
	gameObjects.add(new Land(new Position(Game.DIM_Y-3,12)));
	for(int col = 17; col < Game.DIM_X; col++) {
		gameObjects.add(new Land(new Position(Game.DIM_Y-2, col)));
		gameObjects.add(new Land(new Position(Game.DIM_Y-1, col)));		
	}

	gameObjects.add(new Land(new Position(9,2)));
	gameObjects.add(new Land(new Position(9,5)));
	gameObjects.add(new Land(new Position(9,6)));
	gameObjects.add(new Land(new Position(9,7)));
	gameObjects.add(new Land(new Position(5,6)));
	
	// Salto final
	int tamX = 8, tamY= 8;
	int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
	
	for(int col = 0; col < tamX; col++) {
		for (int fila = 0; fila < col+1; fila++) {
			gameObjects.add(new Land(new Position(posIniY- fila, posIniX+ col)));
		}
	}

	gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

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
//--------------------------------------------------
	public boolean isSolid(Position pos) {
	
		String es=gameObjects.whatIs(pos);
		if(es=="land") {
			return true;
		}
		return false;
	}	
//--------------------------------------------------
	public boolean positionIsIn(Position pos) {
		boolean ok=true;
		if((pos.getCol())<0 ||(pos.getCol())>DIM_X || (pos.getRow())<0 || (pos.getRow())>DIM_Y ) {
			ok=false;
		}
		return ok;
	}
//--------------------------------------------------

	public boolean isGoomba(Position pos) {
		String es=gameObjects.whatIs(pos);
		if(es=="goomba") {
			return true;
		}
		return false;
	}
//--------------------------------------------------

	public boolean isMario(Position pos) {
		String es=gameObjects.whatIs(pos);
		if(es=="mario") {
			return true;
		}
		return false;
	}
//--------------------------------------------------
	public void addAction(Action action) {
		mario.addAction(action);
	}
	public void marioDead() {
		numLives--;
		if(this.numLives>0) {
			reset(this.level());
		}
	}
//--------------------------------------------------

	public boolean isLand(Position pos) {
		String es=gameObjects.whatIs(pos);
		if(es=="land") {
			return true;
		}
		return false;
	}
//--------------------------------------------------

	public void marioExited() {
		addPoints(remainingTime*10);
		marioExited=true;
	}
//--------------------------------------------------
/*public void doInteractionsFrom(Mario mario) {
	this.gameObjects.doInteractionsFrom(Mario mario);
}*/

}
