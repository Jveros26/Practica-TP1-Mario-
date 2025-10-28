//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.logic;

import tp1.logic.Position;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Goombas;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Goombas;
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
//--------------------------------------------------

	public void add(Land land) {	//Añade en el arraylist lands el argumento land
		lands.add(land);
	}
//--------------------------------------------------

	public void add(Goombas goomba) {
		goombas.add(goomba);
	}
//--------------------------------------------------

	public void add(ExitDoor exitdoor) {
		this.exitdoor=exitdoor;
	}
//--------------------------------------------------

	public void add(Mario mario) {
		this.mario=mario;
	}
//--------------------------------------------------

	public Mario getMario() {
		return this.mario;
	}
//--------------------------------------------------

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

	public void update() {	//Hacemos doble clear e interactWith para que mario interaccione si es grande y asciende con los goombas que cae
							//Asi cuando el goomba cae en el mario grande ya se limpia del juego y desaparece
		
		mario.update();	//actualiza mario--->aqui como avisa que mario muere
		if(!checkMarioInExit()) {	//Si mario esta en la puerta pasa de actualizar esto
			doInteractionsFrom(mario);
			clear();
			for(Goombas goomba:goombas) {	//Actualiza goombas
				goomba.update();
			}
			doInteractionsFrom(mario);
		
			clear();	//limpia goombas muertos
		}
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
//--------------------------------------------------	
	
	private boolean checkMarioInExit() {
		return mario.interactWith(exitdoor);
	}
//--------------------------------------------------
	
	private void doInteractionsFrom(Mario mario) {
		for(Goombas goomba:goombas) {
			mario.Interactwith(goomba);
		}
	}
//--------------------------------------------------
	public boolean isSolid(Position pos) {
		boolean issolid=false;
		for(Land land:lands) {	//Recorre todos los land comprueba posiciones
			if(land.isInPosition(pos)){	//Si en la posicion hay un land devuelve true
				issolid=true;
				break;
			}
		}
		//Aqui estaba el for de goombas 
		
		return issolid;	//Devuelve si hay un goomba/land-->objetos solidos
	}
}

