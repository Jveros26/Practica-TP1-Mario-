package Tp1.view;

import Tp1.logic.Game;
import Tp1.logic.GameStatus;

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
