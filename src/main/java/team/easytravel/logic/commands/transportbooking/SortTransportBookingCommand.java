package team.easytravel.logic.commands.transportbooking;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_EMPTY_LIST_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_SORT_SUCCESS_FORMAT;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_TRANSPORT;

import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.TripManager;

/**
 * Sorts the displayed transport bookings.
 */
public class SortTransportBookingCommand extends Command {
    public static final String COMMAND_WORD = "sorttransport";

    public static final String CRITERIA_MODE = "mode";
    public static final String CRITERIA_START_LOCATION = "startloc";
    public static final String CRITERIA_END_LOCATION = "endloc";
    public static final String CRITERIA_START_TIME = "starttime";
    public static final String CRITERIA_END_TIME = "endtime";

    public static final String MESSAGE_CRITERIA_CONSTRAINTS = String.format("Criteria must be one of the following: "
                    + "\"%s\", \"%s\", \"%s\", \"%s\", \"%s\".", CRITERIA_MODE, CRITERIA_START_LOCATION,
            CRITERIA_END_LOCATION, CRITERIA_START_TIME, CRITERIA_END_TIME);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the displayed transport bookings by the given "
            + "criteria and order.\n"
            + "Parameters : CRITERIA "
            + " ORDER\n"
            + "Example: " + COMMAND_WORD + " " + CRITERIA_MODE + " " + SortCommandOrder.ASCENDING;

    private final SortCommandOrder order;
    private final String criteria;

    public SortTransportBookingCommand(SortCommandOrder order, String criteria) {
        this.order = order;
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        if (model.getFilteredTransportBookingList().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST_FORMAT, "transport booking"));
        }

        Comparator<TransportBooking> cmp;
        switch (criteria) {
        case CRITERIA_MODE:
            cmp = Comparator.comparing(entry -> entry.getMode().value);
            break;
        case CRITERIA_START_LOCATION:
            cmp = Comparator.comparing(entry -> entry.getStartLocation().value.toLowerCase());
            break;
        case CRITERIA_END_LOCATION:
            cmp = Comparator.comparing(entry -> entry.getEndLocation().value.toLowerCase());
            break;
        case CRITERIA_START_TIME:
            cmp = Comparator.comparing(TransportBooking::getStartDateTime);
            break;
        case CRITERIA_END_TIME:
            cmp = Comparator.comparing(TransportBooking::getEndDateTime);
            break;
        default:
            throw new AssertionError("Illegal Criteria given.");
        }

        if (order.isAscending()) {
            model.sortTransportList(cmp);
        } else if (order.isDescending()) {
            model.sortTransportList(cmp.reversed());
        } else {
            throw new AssertionError("Illegal SortCommandOrder given.");
        }

        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS_FORMAT, "transport bookings", criteria, order),
                SWITCH_TAB_TRANSPORT);
    }
}
