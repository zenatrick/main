package team.easytravel.logic.commands.accommodationbooking;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.AccommodationBookingManager;

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
