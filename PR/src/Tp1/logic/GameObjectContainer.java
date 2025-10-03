package Tp1.logic;

import Tp1.logic.Position;
import Tp1.logic.gameobjects.Land;
import Tp1.logic.gameobjects.Goombas;
import Tp1.logic.gameobjects.ExitDoor;
import Tp1.logic.gameobjects.Mario;
import Tp1.logic.gameobjects.Goombas;
import java.util.ArrayList;

public class GameObjectContainer {
	private ArrayList<Land> lands;
	private ArrayList<Goombas> goombas;
	private ExitDoor exitdoor;
	private Mario mario;
	
	public GameObjectContainer() {
		lands=new ArrayList<Land>();			//Inicializar el array de los objetos
		goombas=new ArrayList<Goombas>();
	}
	public void add(Land land) {	//Añade en el arraylist lands el argumento land
		lands.add(land);
	}
	public void add(Goombas goomba) {
		goombas.add(goomba);
	}
	public void add(ExitDoor exitdoor) {
		this.exitdoor=exitdoor;
	}
	public void add(Mario mario) {
		this.mario=mario;
	}
	public Mario getMario() {
		return this.mario;
	}
	public ExitDoor getExitDoor() {
		return this.exitdoor;
	}
//--------------------------------------------------

	public String positionToString(Position pos) { 
		StringBuilder buffer=new StringBuilder();
		for(Land land:lands) {
			if(land.isInPosition(pos)) buffer.append(land.getIcon());
		}
		for(Goombas goombas:goombas) {
			if(goombas.isInPosition(pos)) buffer.append(goombas.getIcon());
		}
		if(mario.isInPosition(pos)) buffer.append(mario.getIcon());
		if(exitdoor.isInPosition(pos)) buffer.append(exitdoor.getIcon());
		
		
		return buffer.toString();
	}
//--------------------------------------------------

	public void update() {
		
		mario.update();	//actualiza mario--->aqui como avisa que mario muere
		for(Goombas goomba:goombas) {	//Actualiza goombas
			goomba.update();
		}
		
		clear();	//limpia goombas muertos
	}
//--------------------------------------------------

	public String whatIs(Position pos) {
		
		String es="";
		for(Land land:lands) {
			if(land.isInPosition(pos)){
				es="land";
				break;
			}
		}
		if(es!="land") {
			for(Goombas goomba:goombas) {
				if(goomba.isInPosition(pos)){
					es="goomba";
					break;
				}
			}
		}
		if(es!="land"&&es!="goomba") {
			if(mario.isInPosition(pos)){
				es="mario";
			}
		}
		if(es!="land" && es!="goomba" && es!="mario") {
			if(exitdoor.isInPosition(pos)){
				es="exitdoor";
			}
		}
		
		return es;
	}
//--------------------------------------------------

	private void clear() {
		for(int i=goombas.size()-1;i>=0;i--) {
			Goombas goomba=goombas.get(i);
			if(!goomba.Alive()) {
				goombas.remove(i);
			}
		}
	}
}

