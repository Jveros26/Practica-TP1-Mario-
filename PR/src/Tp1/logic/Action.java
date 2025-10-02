package Tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0);
	
	private int x;
	private int y;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public static Action parse(String o) {
		Action a;
		switch(o.toLowerCase()) {
		case"left:":
		case"l":
			a=LEFT;
			
			break;
		case"right":
		case"r":
			a=RIGHT;
			break;
		case"down":
		case"d":
			a=DOWN;
			
			break;
		case "up":
		case"u":
			a=UP;
			break;
		case "stop":
		case"s":
			a=STOP;
			break;
			
		default:a=null;
		}
		
		return a;
	}
	
	
}