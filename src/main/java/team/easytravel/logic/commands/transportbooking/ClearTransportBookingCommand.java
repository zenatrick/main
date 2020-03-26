package team.easytravel.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.TransportBookingManager;

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
