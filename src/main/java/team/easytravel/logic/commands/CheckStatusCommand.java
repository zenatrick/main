package team.easytravel.logic.commands;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;

/**
 * Adds a person to the address book.
 */
public class CheckStatusCommand extends Command {

    public static final String COMMAND_WORD = "checkstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Check the preparedness of trip. "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_READY = "You are prepared, everything has been checked";
    public static final String MESSAGE_UNREADY = "You are not %d%% ready for the trip";
    public static final String MESSAGE_SUCCESS = "Check status has commence";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isTripPrepared()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS), String.format(MESSAGE_UNREADY));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS), String.format(MESSAGE_READY));
    }

}
