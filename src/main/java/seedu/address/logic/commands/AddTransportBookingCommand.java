package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;

/**
 * Adds a TransportBooking to the TransportBookingManager.
 */
public class AddTransportBookingCommand extends Command {

    public static final String COMMAND_WORD = "addtransport";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transport booking. "
            + "Parameters: "
            + PREFIX_MODE + "MODE "
            + PREFIX_START_LOCATION + "START_LOCATION "
            + PREFIX_END_LOCATION + "END_LOCATION "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODE + "plane "
            + PREFIX_START_LOCATION + "Singapore "
            + PREFIX_END_LOCATION + "Japan "
            + PREFIX_START_TIME + "20-03-2020 1700 "
            + PREFIX_END_TIME + "30-03-2020 2200\n";

    public static final String MESSAGE_SUCCESS = "New transport booking added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRANSPORT_BOOKING = "This transport booking already exists in the "
            + "list";

    private final TransportBooking toAdd;

    /**
     * Creates an AddTransportBookingCommand to add the specified {@code TransportBooking}
     */
    public AddTransportBookingCommand(TransportBooking transportBooking) {
        requireNonNull(transportBooking);
        toAdd = transportBooking;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTransportBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRANSPORT_BOOKING);
        }

        model.addTransportBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTransportBookingCommand // instanceof handles nulls
                && toAdd.equals(((AddTransportBookingCommand) other).toAdd));
    }
}
