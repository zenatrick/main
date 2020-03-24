package seedu.address.logic.commands.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIP_TITLE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.trip.Trip;

/**
 * Adds a trip to Eztravel.
 */
public class SetTripCommand extends Command {

    public static final String COMMAND_WORD = "settrip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a trip. "
            + "Parameters: "
            + PREFIX_TRIP_TITLE + "TITLE "
            + PREFIX_TRIP_BUDGET + "BUDGET "
            + PREFIX_TRIP_START_DATE + "STARTDATE "
            + PREFIX_TRIP_END_DATE + "ENDDATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TRIP_TITLE + "Graduation Trip "
            + PREFIX_TRIP_BUDGET + "5000 "
            + PREFIX_TRIP_START_DATE + "28-09-20 "
            + PREFIX_TRIP_END_DATE + "5-10-20 ";

    public static final String MESSAGE_SUCCESS = "Trip creation success";
    public static final String MESSAGE_DUPLICATE_TRIP = "You cannot have more than one trip";

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
