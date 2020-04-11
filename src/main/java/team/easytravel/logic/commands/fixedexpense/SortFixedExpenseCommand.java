package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_EMPTY_LIST_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_SORT_SUCCESS_FORMAT;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_FIXED_EXPENSE;

import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.logic.commands.util.SortCommandOrder;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.trip.TripManager;

/**
 * Sorts the displayed fixed expenses.
 */
public class SortFixedExpenseCommand extends Command {
    public static final String COMMAND_WORD = "sortexpense";

    public static final String CRITERIA_CATEGORY = "category";
    public static final String CRITERIA_DESCRIPTION = "description";
    public static final String CRITERIA_AMOUNT = "amount";

    public static final String MESSAGE_CRITERIA_CONSTRAINTS = String.format("Criteria must be one of the following: "
            + "\"%s\", \"%s\", \"%s\".", CRITERIA_CATEGORY, CRITERIA_DESCRIPTION, CRITERIA_AMOUNT);

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the displayed transport bookings by the given "
            + "criteria and order.\n"
            + "Parameters : CRITERIA "
            + " ORDER\n"
            + "Example: " + COMMAND_WORD + " " + CRITERIA_CATEGORY + " " + SortCommandOrder.ASCENDING;

    private final SortCommandOrder order;
    private final String criteria;

    public SortFixedExpenseCommand(SortCommandOrder order, String criteria) {
        this.order = order;
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        if (model.getFilteredFixedExpenseList().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST_FORMAT, "fixed expense"));
        }

        Comparator<FixedExpense> cmp;
        switch (criteria) {
        case CRITERIA_CATEGORY:
            cmp = Comparator.comparing(entry -> entry.getFixedExpenseCategory().value.toLowerCase());
            break;
        case CRITERIA_DESCRIPTION:
            cmp = Comparator.comparing(entry -> entry.getDescription().value.toLowerCase());
            break;
        case CRITERIA_AMOUNT:
            cmp = Comparator.comparingDouble(entry -> Double.parseDouble(entry.getAmount().value));
            break;
        default:
            throw new AssertionError("Illegal Criteria given.");
        }

        if (order.isAscending()) {
            model.sortFixedExpenseList(cmp);
        } else if (order.isDescending()) {
            model.sortFixedExpenseList(cmp.reversed());
        } else {
            throw new AssertionError("Illegal SortCommandOrder given.");
        }

        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS_FORMAT, "fixed expense", criteria, order),
                SWITCH_TAB_FIXED_EXPENSE);
    }
}

