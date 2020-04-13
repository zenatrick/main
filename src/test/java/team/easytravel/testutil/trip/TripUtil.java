package team.easytravel.testutil.trip;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRIP_BUDGET;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRIP_END_DATE;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRIP_EXCHANGE_RATE;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRIP_START_DATE;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_TRIP_TITLE;

import team.easytravel.logic.commands.trip.SetTripCommand;
import team.easytravel.model.trip.Trip;

/**
 * A utility class for trip.
 */
public class TripUtil {

    /**
     * Returns an add command string for adding the {@code Trip}.
     */
    public static String getAddCommand(Trip trip) {
        return SetTripCommand.COMMAND_WORD + " " + getTripDetails(trip);
    }

    /**
     * Returns the part of command string for the given {@code trip}'s details.
     */
    public static String getTripDetails(Trip trip) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TRIP_TITLE + trip.getTitle().value + " ");
        sb.append(PREFIX_TRIP_BUDGET + trip.getBudget().value.toString() + " ");
        sb.append(PREFIX_TRIP_EXCHANGE_RATE + trip.getExchangeRate().value.toString() + " ");
        sb.append(PREFIX_TRIP_START_DATE + trip.getStartDate().getStorageFormat() + " ");
        sb.append(PREFIX_TRIP_END_DATE + trip.getEndDate().getStorageFormat() + " ");
        return sb.toString();
    }
}
