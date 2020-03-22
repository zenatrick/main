package seedu.address.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;

/**
 * Sorts your Fixed Expense List according to ascending or descending amount.
 */
public class SortFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "sortexpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts the fixed expense identified by the command"
            + " 0 or 1 in the displayed fixed expense list\n"
            + "0 sorts by ascending order while 1 sorts by descending order\n"
            + "Parameters : SORTIDENTIFIER (must be 0 or 1) "
            + "[AMOUNT]" + "[DESCRIPTION]" + "[CATEGORY]\n"
            + "Example: " + COMMAND_WORD + " 1 amount";

    public static final String MESSAGE_SORT_FIXEDEXPENSE_SUCCESS = "Sorting of FixedExpense successful :)";

    public static final String SORT_DESCENDING = "1";
    public static final String SORT_ASCENDING = "0";
    public static final String CATEGORY = "category";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";

    private final Integer sortIdentifier;
    private final String sortParameter;

    public SortFixedExpenseCommand(Integer sortIdentifier, String sortParameter) {
        this.sortIdentifier = sortIdentifier;
        this.sortParameter = sortParameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FixedExpense> lastShownList = model.getFilteredFixedExpenseList();

        if (lastShownList.size() < 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPTY_FIXEDEXPENSE_LIST);
        }

        switch (sortParameter) {
        case "category":
            if (sortIdentifier == 1) {
                model.sortFixedExpenseList((x, y) -> y.getCategory().toString().compareTo(
                        x.getCategory().toString()
                ));
                return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));

            } else {
                model.sortFixedExpenseList(Comparator.comparing(x -> x.getCategory().toString()));
                return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));
            }
        case "amount":
            if (sortIdentifier == 1) {
                model.sortFixedExpenseList((x, y) -> (int) Math.signum(Double.parseDouble(y.getAmount().value)
                        - Double.parseDouble(x.getAmount().value)));
                return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));

            } else {
                model.sortFixedExpenseList((Comparator.comparing(x -> x.getAmount().value)));
                return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));
            }

        case "description":
            if (sortIdentifier == 1) {
                model.sortFixedExpenseList((x, y) -> y.getDescription().toString().compareTo(
                        x.getDescription().toString()));
                return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));

            } else {
                model.sortFixedExpenseList(Comparator.comparing(x -> x.getDescription().toString()));
                return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));
            }

        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortFixedExpenseCommand.MESSAGE_USAGE));
        }

    }
}

