package seedu.address.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;

/**
 * Deletes a TransportBooking identified using it's displayed index from the TransportBookingManager.
 */
public class DeleteTransportBookingCommand extends Command {

    public static final String COMMAND_WORD = "deletetransport";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the transport booking identified by the index number used in the displayed transport "
            + "booking list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TRANSPORT_BOOKING_SUCCESS = "Deleted Transport Booking: %1$s";

    private final Index targetIndex;

    public DeleteTransportBookingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TransportBooking> lastShownList = model.getFilteredTransportBookingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
                    "Transport Booking"));
        }

        TransportBooking transportBookingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTransportBooking(transportBookingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TRANSPORT_BOOKING_SUCCESS, transportBookingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTransportBookingCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTransportBookingCommand) other).targetIndex)); // state check
    }
}
