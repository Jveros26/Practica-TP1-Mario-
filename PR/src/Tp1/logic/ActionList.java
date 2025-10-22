//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

import java.util.ArrayList;

import tp1.logic.gameobjects.Land;

public class ActionList {

	private ArrayList<Action> actionList;
	private int l=0;
	private int r=0;
	private int u=0;
	private int d=0;
	
	public ActionList() {
		this.actionList=new ArrayList<Action>();
	}
//--------------------------------------------------

	public void clearList() {
		for(int i=0;i<actionList.size();i++) {
			Action accion=actionList.get(i);
				if(isOpposite(accion,i)){
					actionList.remove(i);
					i--; 
				}
				else {
					if(!itCan(accion)) {
						actionList.remove(i);
						i--;
					}
					else {
						counter(accion);
				}
			}
		}
	}
//--------------------------------------------------
	private void counter(Action a) {
	switch(a) {
	
	case Action.UP:
		u++;
		break;
	case Action.LEFT:
		l++;
		break;
	case Action.RIGHT:
		r++;
		break;
	case Action.DOWN:
		d++;
		break;
		}
	}
//--------------------------------------------------

	private void resetNum() {
		l=0;
		r=0;
		u=0;
		d=0;
	}
	
//--------------------------------------------------


	private boolean itCan(Action a) {
		boolean ok=true;
		if(a==Action.LEFT && l==4) {
			ok=false;
		}
		else {
			if(a==Action.RIGHT && r==4) {
				ok=false;
			}
			else {
				if(a==Action.UP && u==4) {
					ok=false;
				}
				else {
					if(a==Action.DOWN && d==4) {
						ok=false;
					}
				}
			}
		}
		return ok;
	}
//--------------------------------------------------

	private boolean isOpposite(Action a,int p) {
		boolean itIs=false;
		int i=p;
		while(!itIs && i>=0) {
			Action acc=actionList.get(i);
			if(a==Action.LEFT && acc==Action.RIGHT) {
				itIs=true;
			}
			else {
				if(a==Action.RIGHT && acc==Action.LEFT) {
					itIs=true;
				}
				else {
					if(a==Action.DOWN && acc==Action.UP) {
						itIs=true;
					}
					else {
						if(a==Action.UP && acc==Action.DOWN) {
							itIs=true;
						}
					}
				}
			}
			i--;
		}
		return itIs;
	}
//--------------------------------------------------

	public int lenght() {
		return actionList.size();
	}
//--------------------------------------------------

	public Action get(int i) {
		return actionList.get(i);
	}
//--------------------------------------------------

	public void add(Action acc) {
		actionList.add(acc);
	}
//--------------------------------------------------

	public void remove(int i) {
		actionList.remove(i);
	}
	
}
