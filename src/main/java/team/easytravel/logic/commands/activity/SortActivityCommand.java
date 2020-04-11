package team.easytravel.logic.commands.activity;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_EMPTY_LIST_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_SORT_SUCCESS_FORMAT;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_ACTIVITY;

import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.trip.TripManager;

/**
 * Sorts the displayed activities.
 */
public class SortActivityCommand extends Command {
    public static final String COMMAND_WORD = "sortactivity";

    public static final String CRITERIA_TITLE = "title";
    public static final String CRITERIA_LOCATION = "location";
    public static final String CRITERIA_DURATION = "duration";

    public static final String MESSAGE_CRITERIA_CONSTRAINTS = String.format("Criteria must be one of the following: "
            + "\"%s\", \"%s\", \"%s\".", CRITERIA_TITLE, CRITERIA_LOCATION, CRITERIA_DURATION);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the displayed activities by the given "
            + "criteria and order.\n"
            + "Parameters : CRITERIA "
            + " ORDER\n"
            + "Example: " + COMMAND_WORD + " " + CRITERIA_TITLE + " " + SortCommandOrder.ASCENDING;

    private final SortCommandOrder order;
    private final String criteria;

    public SortActivityCommand(SortCommandOrder order, String criteria) {
        this.order = order;
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        if (model.getFilteredActivityList().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST_FORMAT, "activities"));
        }

        Comparator<Activity> cmp;
        switch (criteria) {
        case CRITERIA_TITLE:
            cmp = Comparator.comparing(entry -> entry.getTitle().value.toLowerCase());
            break;
        case CRITERIA_LOCATION:
            cmp = Comparator.comparing(entry -> entry.getLocation().value.toLowerCase());
            break;
        case CRITERIA_DURATION:
            cmp = Comparator.comparingInt(entry -> entry.getDuration().value);
            break;
        default:
            throw new AssertionError("Illegal Criteria given.");
        }

        if (order.isAscending()) {
            model.sortActivityList(cmp);
        } else if (order.isDescending()) {
            model.sortActivityList(cmp.reversed());
        } else {
            throw new AssertionError("Illegal SortCommandOrder given.");
        }

        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS_FORMAT, "activities", criteria, order),
                SWITCH_TAB_ACTIVITY);

    }
}
