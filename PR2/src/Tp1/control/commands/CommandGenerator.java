package Tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import Tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
	        new ActionCommand(),
	        new UpdateCommand(),
	        new ResetCommand(),
	        new HelpCommand(),
	        new ExitCommand()
	   );
	public static Command parse(String[] commandWords) {		
		for (Command c: AVAILABLE_COMMANDS) {
			//TODO fill with your code
		}	
		return null;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c:AVAILABLE_COMMANDS) {
			//TODO fill with your code
		}
		
		return commands.toString();
	}

}
