package tp1.logic;

import javax.swing.Box;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goombas;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Mushroom;

public  interface GameItem {
	public  boolean isSolid();
	public  boolean isAlive();
	public  boolean isInPosition(Position pos);

	public  boolean interactWith(GameItem item);

	public  boolean receiveInteraction(Land obj);
	public  boolean receiveInteraction(ExitDoor obj);
	public  boolean receiveInteraction(Mario obj);
	public  boolean receiveInteraction(Goombas obj);
	public boolean receiveInteraction(Mushroom obj);
	public boolean receiveInteraction(Box obj);
}