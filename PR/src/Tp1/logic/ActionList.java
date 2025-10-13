//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1.logic;

import java.util.ArrayList;

import Tp1.logic.gameobjects.Land;

public class ActionList {

	private ArrayList<Action> actionList;
	
	public ActionList() {
		this.actionList=new ArrayList<Action>();
	}
	public int lenght() {
		return actionList.size();
	}
	public Action get(int i) {
		return actionList.get(i);
	}
	public void add(Action acc) {
		actionList.add(acc);
	}
	public void remove(int i) {
		actionList.remove(i);
	}
	
}
