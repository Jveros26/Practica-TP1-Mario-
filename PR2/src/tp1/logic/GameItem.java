package Tp1.logic;

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