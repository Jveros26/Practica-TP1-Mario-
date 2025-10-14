package Tp1.view;

import Tp1.logic.Game;

public abstract class GameView implements ViewInterface{

	protected Game game;
	
	public GameView(Game game) {
		this.game = game;
	}
	
}
