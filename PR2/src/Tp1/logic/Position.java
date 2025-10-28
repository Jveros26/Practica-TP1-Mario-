//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

import tp1.logic.Action;
import tp1.logic.Position;

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
	public Position(String pos) {
		int p=Integer.parseInt(pos);
		int a=p%10;
		int k= (p-a)/10;
		this.row=k;
		this.col=a;
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