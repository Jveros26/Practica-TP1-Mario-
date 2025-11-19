//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

public interface GameWorld {

	public boolean isSolid(Position pos);
	public void addPoints(int p);
	public void marioExited();
	public boolean positionIsIn(Position pos);
	public void marioDead();
	public boolean addObject(String[] strsObject);
	public void doInteractions();
	public void addObjectDelayed(String[] objWords);
}
