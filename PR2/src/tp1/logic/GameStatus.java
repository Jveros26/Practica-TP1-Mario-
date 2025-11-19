//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

public interface GameStatus {

	public String positionToString(int col, int row);
	public int points();
	public boolean playerWins();
	public int remainingTime();
	public int numLives();
	public boolean playerLoses();	
}