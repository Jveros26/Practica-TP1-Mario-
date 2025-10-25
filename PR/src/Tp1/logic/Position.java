//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;


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
//--------------------------------------------------

	public boolean equals(Object pos) {
		if(this==pos) return true;
		
		if(getClass()!=pos.getClass() || pos== null) 
			return false;
		
		Position a=(Position) pos;
		return this.row==a.row && this.col==a.col;
	}
//--------------------------------------------------
	public Position move(Action action) {	//Coge x e y de action y dependiendo de la accion (enumerado) modifica la posicion
		return new Position(row+action.getY(),col+action.getX());
	}
//--------------------------------------------------

public boolean isInside(int maxRows, int maxCols) {
	    return row >= 0 && row < maxRows && col >= 0 && col < maxCols;
	}
}