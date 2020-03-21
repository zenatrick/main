package seedu.address.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.TransportBookingManager;

/**
 * Clears the Transport Booking List
 */
public class ClearTransportBookingCommand extends Command {

    public static final String COMMAND_WORD = "cleartransport";
    public static final String MESSAGE_SUCCESS = "Transport Bookings has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTransportBookingManager(new TransportBookingManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
