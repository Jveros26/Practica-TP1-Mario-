package Tp1.logic;
import Tp1.logic.gameobjects.*;
public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private int nLevel;
	private int remainingTime;
	private Mario mario;
    private GameObjectContainer gameObjects;

	//TODO fill your code
	public Game(int nLevel) {	
		
		this.gameObjects= new GameObjectContainer();	//Inicializa el contenedor de objetos a vacio
		if(nLevel==0) {
			initLevel0();
		}
		else {
		initLevel1();
		}
	}
	
	public String positionToString(int col, int row) {
			
		Position pos= new Position (row,col);
		
		 return gameObjects.positionToString(pos);	//Le pasa posicion al array de objetos que va a la clase y devuelve
		 //El objecto que haya en la posicion en forma de string
	}
	

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean playerLoses() {
		// TODO Auto-generated method stub
		return false;
	}


	public int remainingTime() {
		// TODO Auto-generated method stub
		return 100;
	}

	public int points() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}
	
	private void initLevel0() {  
		this.nLevel = 0;
		this.remainingTime = 100;
		
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
		this.mario = new Mario(this,new Position(Game.DIM_Y-3, 0));	//222666
		gameObjects.add(this.mario);

		gameObjects.add(new Goombas(this,new Position(0, 19)));
	}
	

private void initLevel1() {  
	this.nLevel = 1;
	this.remainingTime = 100;
	
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
	this.mario = new Mario(this,new Position(Game.DIM_Y-3, 0));	//222666
	gameObjects.add(this.mario);

	gameObjects.add(new Goombas(this,new Position(0, 19)));
	gameObjects.add(new Goombas(this,new Position(4,6)));
	gameObjects.add(new Goombas(this,new Position(12,6)));
	gameObjects.add(new Goombas(this,new Position(12,8)));
	gameObjects.add(new Goombas(this,new Position(10,10)));
	gameObjects.add(new Goombas(this,new Position(12,11)));
	gameObjects.add(new Goombas(this,new Position(12, 14)));
}

}
