//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.control.commands.CommandGenerator;
import tp1.logic.Action;
import tp1.control.commands.Command;
import tp1.logic.GameModel;

import java.util.Scanner;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private GameModel game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}

	public void run() {
		view.showWelcome();	
		view.showGame();	
		while(!game.isFinished()) {
			String [] userWords=view.getPrompt();
			Command command=CommandGenerator.parse(userWords);
			
			if(command!=null) {
				command.execute(game, view);
			}
			else {
				view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", userWords)));
			}
		}
		view.showEndMessage();	//Muestra el final
	}

}



