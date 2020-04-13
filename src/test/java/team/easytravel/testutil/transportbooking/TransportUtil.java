package team.easytravel.testutil.transportbooking;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRANSPORT_END_DATE_TIME;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRANSPORT_END_LOCATION;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRANSPORT_MODE;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRANSPORT_START_DATE_TIME;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRANSPORT_START_LOCATION;

import team.easytravel.logic.commands.transportbooking.AddTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand.EditTransportBookingDescriptor;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;


/**
 * A utility class for TransportBooking.
 */
public class TransportUtil {

    /**
     * Returns an add command string for adding the {@code transportBooking}.
     */
    public static String getAddCommand(TransportBooking transportBooking) {
        return AddTransportBookingCommand.COMMAND_WORD + " " + getTransportBookingDetails(transportBooking);
    }

    /**
     * Returns the part of command string for the given {@code transportBooking}'s details.
     */
    public static String getTransportBookingDetails(TransportBooking transportBooking) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TRANSPORT_MODE + transportBooking.getMode().value + " ");
        sb.append(PREFIX_TRANSPORT_START_LOCATION + transportBooking.getStartLocation().value + " ");
        sb.append(PREFIX_TRANSPORT_END_LOCATION + transportBooking.getEndLocation().value + " ");
        sb.append(PREFIX_TRANSPORT_START_DATE_TIME + transportBooking.getStartDateTime().getStorageFormat() + " ");
        sb.append(PREFIX_TRANSPORT_END_DATE_TIME + transportBooking.getEndDateTime().getStorageFormat() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTransportBookingDescriptor}'s details.
     */
    public static String getEditTransportBookingDescriptorDetails(EditTransportBookingDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getMode().ifPresent(name -> sb.append(PREFIX_TRANSPORT_MODE).append(name.value).append(" "));
        descriptor.getStartLocation().ifPresent(location -> sb.append(PREFIX_TRANSPORT_START_LOCATION)
                .append(location.value)
                .append(" "));
        descriptor.getEndLocation().ifPresent(location -> sb.append(PREFIX_TRANSPORT_END_LOCATION)
                .append(location.value)
                .append(" "));
        descriptor.getStartDateTime().ifPresent(start -> sb.append(PREFIX_TRANSPORT_START_DATE_TIME)
                .append(start.getStorageFormat())
                .append(" "));
        descriptor.getEndDateTime().ifPresent(end -> sb
                .append(PREFIX_TRANSPORT_END_DATE_TIME).append(end.getStorageFormat())
                .append(" "));

        return sb.toString();
    }
}
