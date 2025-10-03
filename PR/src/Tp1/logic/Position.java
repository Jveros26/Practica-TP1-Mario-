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
	public void setRow(int row) {
		this.row=row;
	}
	public void setCol(int col) {
		this.col=col;
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
	public boolean equals(Object pos) {
		if(this==pos) return true;
		
		if(getClass()!=pos.getClass() || pos== null) 
			return false;
		
		Position a=(Position) pos;
		return this.row==a.row && this.col==a.col;
	}
	public Position move(Action accion) {
		Position p=new Position(this);
		switch(accion) {
		case DOWN:
			p.down();
			break;
			
		case UP:
			p.up();
			break;
			
		case LEFT:
			p.left();
			break;
			
		case RIGHT:
			p.right();;
			break;
		}
		return p;
	}
	public void commute(Action accion) {
		switch(accion) {
		case DOWN:
			this.down();
			break;
			
		case UP:
			this.up();
			break;
			
		case LEFT:
			this.left();
			break;
			
		case RIGHT:
			this.right();;
			break;
		}
	}
}