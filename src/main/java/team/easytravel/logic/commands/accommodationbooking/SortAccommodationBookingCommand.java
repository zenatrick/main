package team.easytravel.logic.commands.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_EMPTY_LIST_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_SORT_SUCCESS_FORMAT;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_ACCOMMODATION;

import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.trip.TripManager;

/**
 * Sorts the displayed accommodation bookings.
 */
public class SortAccommodationBookingCommand extends Command {

    public static final String COMMAND_WORD = "sortacc";

    public static final String CRITERIA_NAME = "name";
    public static final String CRITERIA_LOCATION = "location";
    public static final String CRITERIA_DAY = "day";

    public static final String MESSAGE_CRITERIA_CONSTRAINTS = String.format("Criteria must be one of the following: "
            + "\"%s\", \"%s\", \"%s\".", CRITERIA_NAME, CRITERIA_LOCATION, CRITERIA_DAY);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the displayed accommodation bookings by the "
            + " given criteria and order.\n"
            + "Parameters : CRITERIA "
            + " ORDER\n"
            + "Example: " + COMMAND_WORD + " " + CRITERIA_NAME + " " + SortCommandOrder.ASCENDING;

    private final SortCommandOrder order;
    private final String criteria;

    public SortAccommodationBookingCommand(SortCommandOrder order, String criteria) {
        this.order = order;
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        if (model.getFilteredAccommodationBookingList().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST_FORMAT, "accommodation booking"));
        }

        Comparator<AccommodationBooking> cmp;
        switch (criteria) {
        case CRITERIA_NAME:
            cmp = Comparator.comparing(entry -> entry.getAccommodationName().value.toLowerCase());
            break;
        case CRITERIA_LOCATION:
            cmp = Comparator.comparing(entry -> entry.getLocation().value.toLowerCase());
            break;
        case CRITERIA_DAY:
            cmp = Comparator.comparingInt(entry -> entry.getStartDay().value);
            break;
        default:
            throw new AssertionError("Illegal Criteria given.");
        }

        if (order.isAscending()) {
            model.sortAccommodationList(cmp);
        } else if (order.isDescending()) {
            model.sortAccommodationList(cmp.reversed());
        } else {
            throw new AssertionError("Illegal SortCommandOrder given.");
        }

        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS_FORMAT, "accommodation bookings", criteria, order),
                SWITCH_TAB_ACCOMMODATION);
    }
}

