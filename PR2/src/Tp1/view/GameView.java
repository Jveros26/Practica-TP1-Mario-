package tp1.view;

import tp1.logic.Game;
import tp1.logic.GameStatus;

public abstract class GameView implements ViewInterface{

	protected GameStatus game;
	
	public GameView(Game game) {
		this.game = game;
	}

	
}
