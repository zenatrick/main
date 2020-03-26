package team.easytravel.logic.commands.accommodationbooking;

import static java.util.Objects.requireNonNull;

import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;

/**
 * Deletes an Accommodation Booking from the Accommodation Booking Manager.
 */
public class DeleteAccommodationBookingCommand extends Command {

    public static final String COMMAND_WORD = "deleteacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the accommodation booking identified by the index number used in the displayed accommodation "
            + "booking list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACCOMMODATION_BOOKING_SUCCESS = "Deleted Accommodation Booking: %1$s";

    private final Index targetIndex;

    public DeleteAccommodationBookingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<AccommodationBooking> lastShownList = model.getFilteredAccommodationBookingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACCOMMODATION_BOOKING_DISPLAYED_INDEX);
        }

        AccommodationBooking accommodationBookingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAccommodationBooking(accommodationBookingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ACCOMMODATION_BOOKING_SUCCESS,
                accommodationBookingToDelete));
    }

}
