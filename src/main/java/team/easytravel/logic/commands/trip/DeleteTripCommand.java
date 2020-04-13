package team.easytravel.logic.commands.trip;

import static java.util.Objects.requireNonNull;
import static team.easytravel.logic.commands.CommandResult.Action.TRIP_DELETE;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.trip.TripManager;

/**
 * Deletes the Trip and reset the whole application.
 */
public class DeleteTripCommand extends Command {

    public static final String COMMAND_WORD = "deletetrip";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the entire trip and all the app data.";
    public static final String MESSAGE_SUCCESS = "Trip has been deleted. All lists have been cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        model.deleteTrip();
        return new CommandResult(MESSAGE_SUCCESS, TRIP_DELETE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTripCommand); // state check
    }
}
