package seedu.address.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;

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
            + " low or high in the displayed fixed expense list\n"
            + "Example: " + COMMAND_WORD + " high";

    public static final String MESSAGE_SORT_FIXEDEXPENSE_SUCCESS = "Sorting of FixedExpense successful :)";

    private final String highOrLow;

    public SortFixedExpenseCommand(String highOrLow) {
        this.highOrLow = highOrLow.toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FixedExpense> lastShownList = model.getFilteredFixedExpenseList();

        if (lastShownList.size() < 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPTY_FIXEDEXPENSE_LIST);
        }

        // Want to sort in descending order
        if (highOrLow.contains("high")) {
            model.sortFixedExpenseList((x, y) ->
                    Integer.parseInt(y.getAmount().value) - Integer.parseInt(x.getAmount().value));

        } else {
            // Sort in ascending order
            model.sortFixedExpenseList((x, y) ->
                    Integer.parseInt(x.getAmount().value) - Integer.parseInt(y.getAmount().value));

        }
        return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));
    }

}
