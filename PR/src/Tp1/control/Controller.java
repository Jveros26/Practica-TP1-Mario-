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
		view.showWelcome();	//Enseña el titulo
		Scanner scanner = new Scanner(System.in);	//Crea el scanner
		view.showGame();	//Muestra el tablero
		System.out.println();
		System.out.println("Command>");
		String command=scanner.nextLine();	//Pido comando por pantalla
		while(!(command.equalsIgnoreCase("exit")||command.equalsIgnoreCase("e"))) {	//Mientras que el texto sea distinto de exit...
			if(command.equalsIgnoreCase("help")||command.equalsIgnoreCase("h")) {
				show_commands();	//Si pide ayuda muestro comandos por pantalla
			}
			else {
				if(command.equalsIgnoreCase("update")||command.equalsIgnoreCase("u")||command.isEmpty()) {	//Actualiza juego
					//game.update();
					/*Supongo que en la funcion update hay que modificar la posicion de mario en el juego y cambiarlo en el tablero*/
					view.showGame();
				}
				else {
					if(command.equalsIgnoreCase("reset")||command.equalsIgnoreCase("r")) {	//Resetea el juego
						//game.reset();
						view.showGame();
					}
					else {
						System.out.println("Error: Unknown command: comandoTecleadoPorElUsuario.");	//No existe el comando
					}
				}
			}
			System.out.println();
			System.out.println("Command>");
			command=scanner.nextLine();	//Pido comando por pantalla
		}
		view.showEndMessage();	//Muestra el final
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
