package seedu.address.logic.commands.accommodationbooking;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.AccommodationBookingManager;

/**
 * Clears the Accommodation Booking List.
 */
public class ClearAccommodationBookingCommand extends Command {

    public static final String COMMAND_WORD = "clearacc";
    public static final String MESSAGE_SUCCESS = "Accommodation Bookings has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAccommodationBookingManager(new AccommodationBookingManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
