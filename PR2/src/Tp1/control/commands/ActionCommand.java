package Tp1.control.commands;

import java.util.ArrayList;

import Tp1.logic.Action;
import Tp1.logic.gameobjects.Land;
import Tp1.view.Messages;

public class ActionCommand extends AbstractCommand {
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private ArrayList<Action> actionList;

    public ActionCommand( ArrayList<Action> actionList) {
    	this.actionList=new ArrayList<Action>(actionList);
    }
	 public ActionCommand() {
	 super(NAME,SHORTCUT,DETAILS,HELP);
	 }
	 public Command parse(String[] commandWords) {
		 if(commandWords.length<2) {
			 return null;
		 }
		 else {
			 
		 }
	}
			
}
