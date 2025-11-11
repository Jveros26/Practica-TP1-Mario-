package tp1.logic.gameobjects;


import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Box extends GameObject {

	private static final String NAME=Messages.BOX_NAME;
	private static final String SHORTCUT=Messages.BOX_SHORTCUT;
	boolean isOpened;
	
	public Box() {
		super(NAME,SHORTCUT);
		isOpened=false;
	}
	public Box(GameWorld game,Position pos) {
		super(game,pos,NAME,SHORTCUT);
		isOpened=false;
	}
	public Box(GameWorld game,Position pos,boolean opened) {
		super(game,pos,NAME,SHORTCUT);
		isOpened=opened;
	}
	@Override
	public String getIcon() {
		if(isOpened) {
			return Messages.OPENED_BOX;
		}
		else {
			return Messages.NOT_OPENED_BOX;
		}
	}
	@Override
	public  boolean isSolid() {return true;}
	private boolean isStatus(String o) {
		if("full".equals(o)||"f".equals(o)) {
			return true;
		}
		else {
			if("empty".equals(o)||"e".equals(o)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	private boolean Status(String o) {
		if("full".equals(o)||"f".equals(o)) {
			return false;
		}
		else {
			if("empty".equals(o)||"e".equals(o)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	@Override
	public GameObject parse(String strsObject[],GameWorld game) {
		GameObject obj=super.parse(strsObject, game);
		if(obj!=null) {
			if(strsObject.length>3) {
				if(isStatus(strsObject[3].toLowerCase())) {
					Position p=new Position(strsObject[0],strsObject[1]);
					return this.createInstance(p, game,Status(strsObject[3].toLowerCase()));
				}
				else {
					return null;
				}	
			}
			else {
				Position p=new Position(strsObject[0],strsObject[1]);
				return this.createInstance(p, game,false);
			}
			
		}
		else {
			return null;
		}
	}
	public boolean isOpened() {
		return this.isOpened;
	}
	@Override
	protected Box createInstance(Position pos, GameWorld game) {
		return new Box(game,pos);
	}
	protected Box createInstance(Position pos, GameWorld game,boolean opened ) {
		return new Box(game,pos,opened);
	}
	//--------------------------------------------------
	@Override
		public boolean interactWith(GameItem other) {
			if (!this.isOpened) {	//Comprueba si la caja ya se abrio
				return  other.receiveInteraction(this);	//Si todavia no se abrio comprueba interaccion con elementos
			}
			else {	//Si ya se abrio no realiza ninguna interaccion mas (comportamiento Land)
				return false;
			}
		}
	//--------------------------------------------------
	@Override
		public boolean receiveInteraction(Land obj) {
			return false;
		}
	//--------------------------------------------------
	@Override
		public  boolean receiveInteraction(ExitDoor obj) {
			return false;
		}
	//--------------------------------------------------
	@Override
		public boolean  receiveInteraction(Mario obj) {
			if(!this.isOpened() && obj.receiveInteraction(this)) {
				addMushroom();
				return true;
			}
			else {
				return false;	
			}
			
		}
	//--------------------------------------------------
	@Override
		public boolean receiveInteraction(Goombas obj) {
			return false;
			
		}
	//--------------------------------------------------
	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}
	@Override
	public boolean receiveInteraction(Box obj) {
		return false;
	}
	public void addMushroom() {
		StringBuilder build=new StringBuilder();
		Position posUp=pos.move(Action.UP);	//Coje la posicion de arriba de la box
		build.append(posUp.toString());	//La pasa a string formato "num num"
		build.append(" ");	//Crea estructura comando
		build.append("MUSHROOM");
		build.append(" ");
		build.append("RIGHT");	
		String b=build.toString();
		String[] addMushroom = b.trim().split("\\s+");	//Genera el array de Strings
		this.isOpened=true;
		game.addPoints(50);
		game.addObjectDelayed(addMushroom);	//Añade al juego el mushroom
	}
	
	@Override
	public void update() {
	}


}
