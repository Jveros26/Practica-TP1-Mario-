package tp1.control.commands;

import java.util.Arrays;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends AbstractCommand {

	private static final String NAME = Messages.COMMAND_AddAction_NAME;
    private static final String SHORTCUT = Messages.COMMAND_AddAction_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_AddAction_DETAILS;
    private static final String HELP = Messages.COMMAND_AddAction_HELP;
    private static String strsObject[];
    
    
    public AddObjectCommand() {
    	super(NAME,SHORTCUT,DETAILS,HELP);
    	
    }
    @Override
    public Command parse(String[] commandWords) {
       	if(commandWords.length<3) {	//Si es menos que tres no concuerda con AddObjectCommand que minimo necesita 3: nombre, posicion y objeto a añadir
			 return null;
		 }
		 else {
			 if(this.matchCommandName(commandWords[0])) {	//Veo si el primer argumento concuerda con la el nombre de la clase
				 
				 //-----CONVERSION (numero,numero) en numeronumero-----
				 String[] posParts = Arrays.copyOfRange(commandWords, 1, 2);	//Coge de la estructura "numero", ",", "numero"
				 String posString = String.join("", posParts); // "num,num"-> Creamos un string con las posiciones de los numeros position
				 posString = posString.replace(",", " ");	//"numnum"-->quitamos la coma
				 posString = posString.replace("(", "");	//"numnum"-->quitamos la coma
				 posString = posString.replace(")", "");	//"numnum"-->quitamos la coma


				 
				 
				 //------Copia array commandWords crea nuevo copia con numnum etc,....-----
				 StringBuilder comando=new StringBuilder();
				 comando.append(posString);	//Array que tiene numnum
				 comando.append(" ");
				 posParts=Arrays.copyOfRange(commandWords, 2, commandWords.length);	//Copia lo demas del array (el nombre dle objeto con su info)
				 posString=String.join(" ", posParts);	//Coge la info del objeto, crea un string con los elementos separados por espacios
				 comando.append(posString);	// contiene: numnum_Nombre/Shortcut_infoDeObjeto 
				 String comand=comando.toString();
				 String[] ArrayComando = comand.trim().split("\\s+");	//Cada palabra separada por espacios es un elemento del array word (String)
				 
				 
				 GameWorld game=new Game(1);	//Creamos un juego sin mas, para hacer el parse y ver si concuerda la estructura del Array con algun objeto de la factoria
				 //Inicializamos a null pq simplemente queremos ver si devuelve instancia, el juego da igual
				 GameObject obj=GameObjectFactory.parse(ArrayComando,game);
				 
				 if(obj!=null) {	//Si devuelve instancia el array si sirve y se asigna al array de la clase y se devuelve instancia
					 strsObject=ArrayComando;
					 return this;
				 }
				 else {	//Si devuelve null devolvemos null
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
		
		if(game.addObject(strsObject)) {	//Si consigue hacerlo muestra el juego 
			view.showGame();
		}
		else {	//Sino muestra error
			view.showError(Messages.INVALID_GAME_OBJECT);
		}
	}
	
}
