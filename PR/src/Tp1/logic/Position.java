//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1.logic;


public class Position {

	private int col;
	private int row;

	public Position(int row,int col) {
		this.row=row;
		this.col=col;
	}
	public Position(Position pos) {
		this.col=pos.col;
		this.row=pos.row;
	}
	
	public int getCol() {
		return this.col;
	}
	public int getRow() {
		return this.row;
	}
	public void up() {
		this.row--;
	}
	public void down() {
		this.row++;
	}
	public void left() {
		this.col--;
	}
	public void right() {
		this.col++;
	}
//--------------------------------------------------

	public boolean equals(Object pos) {
		if(this==pos) return true;
		
		if(getClass()!=pos.getClass() || pos== null) 
			return false;
		
		Position a=(Position) pos;
		return this.row==a.row && this.col==a.col;
	}
//--------------------------------------------------
	public Position move(Action action) {
	    switch (action) {
	        case UP:
	            return new Position(row - 1, col);
	        case DOWN:
	            return new Position(row + 1, col);
	        case LEFT:
	            return new Position(row, col - 1);
	        case RIGHT:
	            return new Position(row, col + 1);
	        default:
	            return this;
	    }
	}
//--------------------------------------------------

public boolean isInside(int maxRows, int maxCols) {
	    return row >= 0 && row < maxRows && col >= 0 && col < maxCols;
	}
}