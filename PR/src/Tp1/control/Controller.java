package Tp1.control;

import Tp1.logic.Game;
import Tp1.view.GameView;
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
		
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		view.showGame();
		
		view.showEndMessage();
	}

	public void Command() {
		show_commands();
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Command>");
		String c=scanner.nextLine();	//cin>>c;
		
		
		
		 scanner.close();
	}
	public void show_commands() {
		System.out.println("Available commands:");
		System.out.println("	   [a]ction [[R]IGHT | [L]EFT | [U]P | [D]OWN | [S]TOP]+: user performs actions");
		System.out.println("	   [u]pdate | \"\": user does not perform any action\r\n"
				+ "			   [r]eset [numLevel]: reset the game to initial configuration if not numLevel else load the numLevel map\r\n"
				+ "			   [h]elp: print this help message\r\n"
				+ "			   [e]xit: exits the game");
	}
	
}
