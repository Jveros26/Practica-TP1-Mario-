package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject{
	public Action direction;
	public boolean isFalling;
	
	public MovingObject(GameWorld game, Position pos,Action dir,boolean isFalling,String NAME){
		super(game,pos,NAME);
		this.direction=dir;
		this.isFalling=isFalling;
	}
	public MovingObject(Action dir,boolean isFalling,String NAME) {
		super(NAME);
		this.direction=dir;
		this.isFalling=isFalling;
	}
	public abstract String getIcon();

	public boolean isFalling() {return isFalling;}
	
	public void step() {	
			
		if(!((game.isSolid(pos.move(Action.LEFT)) && (game.isSolid(pos.move(Action.RIGHT)))))) {
			Position pa=pos.move(Action.LEFT);
			if(game.isSolid(pos.move(Action.LEFT)) || !game.positionIsIn(pa)){ 
				this.pos=pos.move(Action.RIGHT);
				this.direction=Action.RIGHT;

			}
			else {
				if(game.isSolid(pos.move(Action.RIGHT))){
					this.pos=pos.move(Action.LEFT);
					this.direction=Action.LEFT;

				}
				else {
					if(this.direction==Action.LEFT) {
						this.pos=pos.move(Action.LEFT);
						this.direction=Action.LEFT;

					}
					else {
						this.pos=pos.move(Action.RIGHT);
						this.direction=Action.RIGHT;

					}
				}
			}
		}
			
		}
	
}
