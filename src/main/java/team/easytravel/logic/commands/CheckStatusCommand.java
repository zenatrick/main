package team.easytravel.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;

/**
 * Adds a person to the address book.
 */
public class CheckStatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Check the preparedness of trip. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Check Status window is opened.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<String> statusMessage = model.getStatus();

        return new CommandResult(MESSAGE_SUCCESS, statusMessage);
    }

}
