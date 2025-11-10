package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mushroom extends MovingObject{
	private static final String NAME=Messages.MUSHROOM_NAME;
	private static final String SHORTCUT=Messages.MUSHROOM_SHORTCUT;
	
	public Mushroom(GameWorld game,Position pos) {
		super(game,pos,Action.RIGHT,false,NAME,SHORTCUT);	
		
	}
	public Mushroom(GameWorld game,Position pos,Action dir) {
		super(game,pos,Action.RIGHT,false,NAME,SHORTCUT);
		direction=dir;
		
	}
	
	public Mushroom() {
		super(Action.RIGHT,false,NAME,SHORTCUT);
	}

	@Override
	public String getIcon() {
		return Messages.MUSHROOM;
	}
	
	@Override
	protected GameObject createInstance(Position pos, GameWorld game) {
		return new Mushroom(game,pos);

	}
	protected GameObject createInstance(Position pos, GameWorld game,Action dir) {
		return new Mushroom(game,pos,dir);

	}
	
	@Override
	public  boolean isSolid() {return false;}
	
	@Override
	public boolean interactWith(GameItem item) {
		boolean canInteract=item.isInPosition(pos);
		if(canInteract && this.isAlive()) {
			item.receiveInteraction(this);
		}
		return canInteract;
	}
	@Override
	public  boolean receiveInteraction(Land obj) {
		return false;
	}
	
	@Override
	public  boolean receiveInteraction(ExitDoor obj) {
		return false;
	}
	
	@Override
	public  boolean receiveInteraction(Mario obj) {
		if(this.isAlive() && obj.receiveInteraction(this)) {
			return true;
		}
		else {
			return false;
		}

	}
	@Override
	public  boolean receiveInteraction(Goombas obj) {
		return false;
	}
	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}
	public void update() {

		if(game.isSolid(pos.move(Action.DOWN))){
			this.isFalling=false;
			step();	
		}
		else{
			this.isFalling=true;
			fall();
		}
		if(!game.positionIsIn(pos)) {
			dead();
		}
	}
//--------------------------------------------------	

	public void mushDead() {
		dead();
	}
//--------------------------------------------------	

	@Override
	public boolean receiveInteraction(Box obj) {
		return false;
	}
	
}
