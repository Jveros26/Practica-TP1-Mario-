package Tp1.control.commands;

import Tp1.logic.Game;
import Tp1.view.GameView;
import Tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {

    private static final String NAME = Messages.COMMAND_HELP_NAME;
    private static final String SHORTCUT = Messages.COMMAND_HELP_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_HELP_DETAILS;
    private static final String HELP = Messages.COMMAND_HELP_HELP;

    public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public void execute(Game game, GameView view) {
		
		view.showMessage(CommandGenerator.commandHelp());
	}

}