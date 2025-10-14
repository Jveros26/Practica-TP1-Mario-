package Tp1.control.commands;

import Tp1.logic.Game;
import Tp1.view.GameView;

public interface Command {

	public void execute(Game game, GameView view);	  
	public Command parse(String[] commandWords);

	public String helpText();
}
