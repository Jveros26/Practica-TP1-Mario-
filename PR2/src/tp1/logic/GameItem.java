package tp1.logic;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goombas;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;

public  interface GameItem {
	public  boolean isSolid();
	public  boolean isAlive();
	public  boolean isInPosition(Position pos);

	public  boolean interactWith(GameItem item);

	public  void receiveInteraction(Land obj);
	public  void receiveInteraction(ExitDoor obj);
	public  void receiveInteraction(Mario obj);
	public  void receiveInteraction(Goombas obj);
}