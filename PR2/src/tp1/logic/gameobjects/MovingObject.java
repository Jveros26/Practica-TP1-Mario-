package Tp1.logic.gameobjects;

import Tp1.logic.Action;
import Tp1.logic.Game;
import Tp1.logic.Position;

public abstract class MovingObject extends GameObject{
	public Action direction;
	public boolean isFalling;
	
	public MovingObject(Game game, Position pos,Action dir,boolean isFalling){
		super(game,pos);
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
