package tp1.view;

import tp1.logic.Game;
import tp1.logic.GameStatus;

public abstract class GameView implements ViewInterface,GameStatus{

	protected Game game;
	
	public GameView(Game game) {
		this.game = game;
	}
	public int poins() {
		return game.points();
	}
	public boolean playerWins() {
		return game.playerWins();
	}
}
