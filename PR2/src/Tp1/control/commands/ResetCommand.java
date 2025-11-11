package tp1.control.commands;

import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {
	private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    private int level;
    
    public ResetCommand() {
   	 super(NAME,SHORTCUT,DETAILS,HELP);
   	 }
    @Override
    public Command parse(String[] commandWords) {
    	
    	if(commandWords.length==2) {	//Si la lista tiene tamaño 2--> reset + nivel y coincide con el nombre y atajo reset
    		if(this.matchCommandName(commandWords[0])) {
    		this.level=Integer.parseInt(commandWords[1]);	//Le adjudico el nivel de reset  y devuelvo la clase reset con el nivel
    		return this;
    		}
    		else {
    			return null;	//Sino devuelve null
    		}
    	}
    	else {
    		if(commandWords.length==1) {	//Si la lista solo contiene el nombre o atajo reset
    			if(this.matchCommandName(commandWords[0])) {
        			return this;	//Si no tiene nivel le devolvemos tal cual
        		}
    			else {	//Sino null
    				return null;
    			}
    		}
    		else {
    			return null;
    		}
    	}
    	
    	
    }
    @Override
	public void execute(GameModel game, GameView view) {
		game.resetLives();
		game.resetPoints();
		if(game.reset(level)) {	//Si se intenta resetear con un nivel no existente devuelve error y no muestra juego
			view.showGame();
		}
	}

	
	
}
