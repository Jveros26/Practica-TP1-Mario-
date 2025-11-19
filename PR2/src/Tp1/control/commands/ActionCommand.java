//Grupo 6: Jorge Veros Moreno y Álvaro Rocha del Barrio

package tp1.control.commands;

import java.util.ArrayList;

import tp1.logic.Action;
import tp1.logic.gameobjects.Land;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.logic.GameModel;

public class ActionCommand extends AbstractCommand {
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private ArrayList<Action> actionList;
    private static String error;
    private static String Err[];


    public ActionCommand( ArrayList<Action> actionList) {	//Llamamos al consturctor super
    	super(NAME,SHORTCUT,DETAILS,HELP);
    	this.actionList=new ArrayList<Action>();
    	this.actionList=actionList;	//Inicializamos el actionList con lo que dan
    }
	 public ActionCommand() {
	 super(NAME,SHORTCUT,DETAILS,HELP);
	 this.actionList=new ArrayList<Action>();
	 }
	 @Override
	 public Command parse(String[] commandWords) {
		 if(commandWords.length<2) {
			 return null;
		 }
		 else {
			 if(this.matchCommandName(commandWords[0])) {	//Veo si el primer argumento concuerda con la el nombre d ela clase
					 for(int i=1;i<commandWords.length;i++) {	//Recorro las pocisiones haciendo parse y añado a lista
						 Action acc=Action.parse(commandWords[i]);
						 if(acc!=null) {
							 actionList.add(acc);
							 error="ok";
						 }
						 else {//Si da error en la accion guarda el comando q dio error y el mensaje de error
							 Err=commandWords;
							 error="error";
							 return this;
						 }
					 }
				 return this;	//al terminar
			 	}
			 else {
				 return null;
			 }
		 }
	}
	 private void clearList() {
		 for(int i=actionList.size()-1;i>=0;i--) {
				actionList.remove(i);
			}
	 }
	 @Override
	 public void execute(GameModel game, GameView view) {
		 if(error!="error") {	//Si no dio error carga la lista en game con la que teniamos
			 for(int i=0;i<actionList.size();i++) {
				 Action acc=actionList.get(i);
				 game.addAction(acc);
			 }
			 game.update();	//Updateo
			 game.clearList();	//Limpio la lista del game
			 this.clearList();	//Limpio esta lista
			 view.showGame();	//Muestro juego
		 }
		 else {
			 view.showError(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", Err)));	//Muestro el comando que dio error
			 game.update();	//updateo el juego para que se mueva mario
			 view.showGame();	//Muestra juego
		 }
	 }
			
}
