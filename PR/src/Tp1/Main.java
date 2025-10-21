//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio
package Tp1;

import java.util.Locale;

import Tp1.logic.Game;
import Tp1.view.ConsoleColorsView;
import Tp1.view.ConsoleView;
import Tp1.view.GameView;
import Tp1.view.Messages;
import Tp1.control.Controller;

public class Main {

	
	public static void main(String[] args) {
		// Required to avoid issues with tests
        Locale.of("es", "ES");
		
		try {
			
			int nLevel = 1;
			if (args.length != 0) nLevel = Integer.parseInt(args[0]);

            Game game = new Game(nLevel);
            GameView view = args.length>1 ? new ConsoleView(game): new ConsoleColorsView(game);
            Controller controller = new Controller(game, view);
					
			controller.run();

		} catch (NumberFormatException e) {
			System.out.println(String.format(Messages.LEVEL_NOT_A_NUMBER_ERROR, args[0]));
		}
	}
}
