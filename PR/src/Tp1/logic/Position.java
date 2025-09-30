package Tp1.logic;

/**
 * 
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	public Position(int row,int col) {
		this.row=row;
		this.col=col;
	}
	
	public int getCol() {
		return this.col;
	}
	public int getRow() {
		return this.row;
	}
	public void setRow(int row) {
		this.row=row;
	}
	public void setCol(int col) {
		this.col=col;
	}
	public boolean equals(Object pos) {
		if(this==pos) return true;
		
		if(getClass()!=pos.getClass() || pos== null) 
			return false;
		
		Position a=(Position) pos;
		return this.row==a.row && this.col==a.col;
	}
}