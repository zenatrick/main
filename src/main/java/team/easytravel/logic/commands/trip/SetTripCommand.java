package team.easytravel.logic.commands.trip;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.parser.CliSyntax;
import team.easytravel.model.Model;
import team.easytravel.model.trip.Trip;

/**
 * Adds a trip to Eztravel.
 */
public class SetTripCommand extends Command {

    public static final String COMMAND_WORD = "settrip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a trip. "
            + "Parameters: "
            + CliSyntax.PREFIX_TRIP_TITLE + "TITLE "
            + CliSyntax.PREFIX_TRIP_BUDGET + "BUDGET "
            + CliSyntax.PREFIX_TRIP_START_DATE + "START_DATE "
            + CliSyntax.PREFIX_TRIP_END_DATE + "END_DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_TRIP_TITLE + "Graduation Trip "
            + CliSyntax.PREFIX_TRIP_BUDGET + "5000 "
            + CliSyntax.PREFIX_TRIP_START_DATE + "28-09-2020 "
            + CliSyntax.PREFIX_TRIP_END_DATE + "05-10-2020";

    public static final String MESSAGE_SUCCESS = "Trip is successfully set: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "Trip is already set. You cannot set more than one trip!";

    private final Trip toAdd;

    /**
     * Creates an SetTripCommand to add the specified {@code Trip}
     */
    public SetTripCommand(Trip trip) {
        requireNonNull(trip);
        toAdd = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTrip()) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        model.setTrip(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
