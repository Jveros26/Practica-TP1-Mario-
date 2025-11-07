//package tp1.logic.gameobjects;
//
//import tp1.logic.Action;
//import tp1.logic.GameItem;
//import tp1.logic.GameWorld;
//import tp1.logic.Position;
//import tp1.view.Messages;
//
//public class Box extends GameObject {
//
//	private static final String NAME=Messages.BOX_NAME;
//	private static final String SHORTCUT=Messages.BOX_SHORTCUT;
//	boolean isOpened;
//	
//	public Box() {
//		super(NAME,SHORTCUT);
//		isOpened=false;
//	}
//	public Box(GameWorld game,Position pos) {
//		super(game,pos,NAME,SHORTCUT);
//		isOpened=false;
//	}
//	@Override
//	public String getIcon() {
//		if(isOpened) {
//			return Messages.OPENED_BOX;
//		}
//		else {
//			return Messages.NOT_OPENED_BOX;
//		}
//	}
//	@Override
//	public  boolean isSolid() {return true;}
//	private boolean isStatus(String o) {
//		o=o.toLowerCase();
//		if(o=="full"||o=="f") {
//			this.isOpened=true;
//			return true;
//		}
//		else {
//			if(o=="empty"||o=="e") {
//				this.isOpened=false;
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
//	}
//	@Override
//	public GameObject parse(String strsObject[],GameWorld game) {
//		GameObject obj=super.parse(strsObject, game);
//		if(obj!=null) {
//			if(isStatus(strsObject[2])) {
//				return this.createInstance(pos, game);
//			}
//			else {
//				return null;
//			}
//			
//		}
//		else {
//			return null;
//		}
//	}
//	public boolean isOpened() {
//		return this.isOpened;
//	}
//	@Override
//	protected Box createInstance(Position pos, GameWorld game) {
//		return new Box(game,pos);
//	}
//	//--------------------------------------------------
//	@Override
//		public boolean interactWith(GameItem other) {
//			boolean canInteract=other.isInPosition(pos);
//			if(canInteract && this.isAlive()) {
//				other.receiveInteraction(this);
//			}
//			return canInteract;
//		}
//	//--------------------------------------------------
//	@Override
//		public boolean receiveInteraction(Land obj) {
//			return false;
//		}
//	//--------------------------------------------------
//	@Override
//		public  boolean receiveInteraction(ExitDoor obj) {
//			return false;
//		}
//	//--------------------------------------------------
//	@Override
//		public boolean  receiveInteraction(Mario obj) {
//			if(!this.isOpened() && obj.receiveInteraction(this)) {
//				Position posUp=pos.move(Action.UP);
//				String posup=posUp.toString();
//				//game.addObject-->supongo que habra que añadirlo a la interfaz
//				//Añadir seta en la pos de arriba
//				return true;
//			}
//			else {
//				return false;	
//			}
//
//		}
//	//--------------------------------------------------
//	@Override
//		public boolean receiveInteraction(Goombas obj) {
//			return false;
//			
//		}
//	//--------------------------------------------------
//		@Override
//		public boolean receiveInteraction(Mushroom obj) {
//			return false;
//		}
//
//
//}
