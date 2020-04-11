package team.easytravel.logic.commands.trip;

import static java.util.Objects.requireNonNull;
import static team.easytravel.logic.commands.CommandResult.Action.STATUS;

import java.util.List;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.trip.TripManager;

/**
 * Checks the progress of the plan for the trip.
 */
public class CheckStatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks the progress of the plan for your trip.";

    public static final String MESSAGE_SUCCESS = "Progress checker window is opened.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        List<String> statusMessage = model.getStatus();

        return new CommandResult(MESSAGE_SUCCESS, statusMessage, STATUS);
    }

}
