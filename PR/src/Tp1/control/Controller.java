//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.Action;

import java.util.Scanner;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		view.showWelcome();	
		view.showGame();	
		while(!game.isFinished()) {
			String [] words=view.getPrompt();
			execute(words);
			
		}
		view.showEndMessage();	//Muestra el final
	}
//--------------------------------------------------

	private void execute(String[] words) {
		
		switch(words[0].toLowerCase()) {
		
		case "action":
		case"a":
				if(words.length<2) {	//Pq tiene que tener dos elementos action+lo que quieres hacer
					view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				}
				else {
					for(int i=1;i<words.length;i++) {
						Action action= Action.parse(words[i]);
						if(action !=null){
						game.addAction(action);

						}
						else {
							view.showError(Messages.UNKNOWN_ACTION.formatted(words[i])); //Si la accion no es valida
						}
					}
					this.game.update();
					this.game.clearList();
					view.showGame();
				}
				break;
		case"update":
		case"u":
		case"":
			if(words.length!=1) {
				view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			else {
				this.game.update();
				view.showGame();
			}
			break;
		case"reset":
		case"r":
			if(words.length==2) {
				this.game.resetLives();
				this.game.resetPoints();
				this.game.reset(Integer.parseInt(words[1]));
				view.showGame();
			}
			else {
				if(words.length==1) {
					this.game.resetLives();
					this.game.resetPoints();
					this.game.reset(-1);	//Reinicia al nivel 1 pq no exite nivel -1
					view.showGame();
				}
				else {
					view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				}
			}
			break;
		case"help":
		case"h":
			if(words.length!=1) {
				view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			else {
				view.showMessage(Messages.HELP);
			}
			break;
		case"exit":
		case"e":
			if(words.length!=1) {
				view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			else {
				this.game.exit();
			}
			break;
		default: view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0]));	//Si el commando no es valido
		
		}
	}

}



