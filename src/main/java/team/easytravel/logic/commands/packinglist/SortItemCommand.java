package team.easytravel.logic.commands.packinglist;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_EMPTY_LIST_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_SORT_SUCCESS_FORMAT;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_PACKING_LIST;

import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.trip.TripManager;

/**
 * Sorts the displayed packing list items.
 */
public class SortItemCommand extends Command {
    public static final String COMMAND_WORD = "sortitem";

    public static final String CRITERIA_CATEGORY = "category";
    public static final String CRITERIA_NAME = "name";
    public static final String CRITERIA_QUANTITY = "quantity";

    public static final String MESSAGE_CRITERIA_CONSTRAINTS = String.format("Criteria must be one of the following: "
                    + "\"%s\", \"%s\", \"%s\".", CRITERIA_CATEGORY, CRITERIA_NAME, CRITERIA_QUANTITY);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the displayed transport bookings by the given "
            + "criteria and order.\n"
            + "Parameters : CRITERIA "
            + " ORDER\n"
            + "Example: " + COMMAND_WORD + " " + CRITERIA_CATEGORY + " " + SortCommandOrder.ASCENDING;

    private final SortCommandOrder order;
    private final String criteria;

    public SortItemCommand(SortCommandOrder order, String criteria) {
        this.order = order;
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        if (model.getFilteredPackingList().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST_FORMAT, "packing"));
        }

        Comparator<PackingListItem> cmp;
        switch (criteria) {
        case CRITERIA_CATEGORY:
            cmp = Comparator.comparing(entry -> entry.getItemCategory().value.toLowerCase());
            break;
        case CRITERIA_NAME:
            cmp = Comparator.comparing(entry -> entry.getItemName().value.toLowerCase());
            break;
        case CRITERIA_QUANTITY:
            cmp = Comparator.comparingInt(entry -> entry.getQuantity().value);
            break;
        default:
            throw new AssertionError("Illegal Criteria given.");
        }

        if (order.isAscending()) {
            model.sortPackingList(cmp);
        } else if (order.isDescending()) {
            model.sortPackingList(cmp.reversed());
        } else {
            throw new AssertionError("Illegal SortCommandOrder given.");
        }

        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS_FORMAT, "packing", criteria, order),
                SWITCH_TAB_PACKING_LIST);
    }
}
