package Tp1.control.commands;

import java.util.ArrayList;

import Tp1.logic.Action;
import Tp1.logic.gameobjects.Land;
import Tp1.view.GameView;
import Tp1.view.Messages;
import Tp1.logic.ActionList;
import Tp1.logic.Game;

public class ActionCommand extends AbstractCommand {
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private ArrayList<Action> actionList;

    public ActionCommand( ArrayList<Action> actionList) {	//Llamamos al consturctor super
    	super(NAME,SHORTCUT,DETAILS,HELP);
    	this.actionList=actionList;	//Inicializamos el actionList con lo que dan
    }
	 public ActionCommand() {
	 super(NAME,SHORTCUT,DETAILS,HELP);
	 }
	 public Command parse(String[] commandWords) {
		 if(commandWords.length<2) {
			 return null;
		 }
		 else {
			 if(this.matchCommandName(commandWords[0])) {	//Veo si el primer argumento concuerda con la el nombre d ela clase
				 for(int i=1;i<commandWords.length;i++) {	//Recorro las pocisiones haciendo parse y añado a lista
					 Action acc=Action.parse(commandWords[i]);
					 actionList.add(acc);
				 }
				 return this;	//al terminar
			 	}
			 else {
				 return null;
			 }
		 }
	}
	 public void execute(Game game, GameView view) {
		game.update();
		game.clearList();
		view.showGame();
	 }
			
}
