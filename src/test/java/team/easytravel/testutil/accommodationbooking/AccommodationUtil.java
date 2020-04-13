package team.easytravel.testutil.accommodationbooking;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_END_DAY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_LOCATION;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_NAME;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_REMARK;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACCOMMODATION_START_DAY;

import team.easytravel.logic.commands.accommodationbooking.AddAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.EditAccommodationBookingCommand.EditAccommodationBookingDescriptor;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;

/**
 * A utility class for Accommodation.
 */
public class AccommodationUtil {

    /**
     * Returns an add command string for adding the {@code Accommodation}.
     */
    public static String getAddCommand(AccommodationBooking accommodation) {
        return AddAccommodationBookingCommand.COMMAND_WORD + " " + getAccommodationDetails(accommodation);
    }

    /**
     * Returns the part of command string for the given {@code accommodation}'s details.
     */
    public static String getAccommodationDetails(AccommodationBooking accommodation) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ACCOMMODATION_NAME + accommodation.getAccommodationName().value + " ");
        sb.append(PREFIX_ACCOMMODATION_LOCATION + accommodation.getLocation().value + " ");
        sb.append(PREFIX_ACCOMMODATION_START_DAY + accommodation.getStartDay().value.toString() + " ");
        sb.append(PREFIX_ACCOMMODATION_END_DAY + accommodation.getEndDay().value.toString() + " ");
        sb.append(PREFIX_ACCOMMODATION_REMARK + accommodation.getRemark().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAccommodationDescriptor}'s details.
     */
    public static String getEditAccommodationDescriptorDetails(EditAccommodationBookingDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getAccommodationName().ifPresent(name -> sb.append(PREFIX_ACCOMMODATION_NAME)
                .append(name.value).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_ACCOMMODATION_LOCATION).append(location.value)
                .append(" "));
        descriptor.getStartDay().ifPresent(duration -> sb.append(PREFIX_ACCOMMODATION_START_DAY).append(duration.value)
                .append(" "));
        descriptor.getEndDay().ifPresent(duration -> sb.append(PREFIX_ACCOMMODATION_END_DAY).append(duration.value)
                .append(" "));
        descriptor.getRemark().ifPresent(duration -> sb.append(PREFIX_ACCOMMODATION_REMARK).append(duration.value)
                .append(" "));
        return sb.toString();
    }
}
