package seedu.address.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.fixedexpense.FixedExpense;

/**
 * Sorts your fixedexpenselist
 */
public class SortFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "sortexpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts the fixed expense identified by the command"
            + " low or high in the displayed fixed expense list\n"
            + "Example: " + COMMAND_WORD + " high";

    public static final String MESSAGE_SORT_FIXEDEXPENSE_SUCCESS = "Sorting of FixedExpense successful :)";

    //private final String highOrLow;

    //public SortFixedExpenseCommand(String highOrLow) {
    //this.highOrLow = highOrLow;
    //}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FixedExpense> lastShownList = model.getFilteredFixedExpenseList();

        if (lastShownList.size() < 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPTY_FIXEDEXPENSE_LIST);
        }

        model.getFixedExpenseManager().sortFixedExpenseList(new Comparator<FixedExpense>() {
            @Override
            public int compare(FixedExpense o1, FixedExpense o2) {
                return Integer.parseInt(o1.getAmount().value) - Integer.parseInt(o2.getAmount().value);
            }
        });


        // Means sort from high to low
        //if (highOrLow.contains("high")) {
        //    lastShownList
        //           .sort((x, y) -> Integer.parseInt(y.getAmount().value) - Integer.parseInt(x.getAmount().value));
        //} else if (highOrLow.contains("low")) {

        //}

        // model.updateFilteredFixedExpenseList(PREDICATE_SHOW_ALL_FIXED_EXPENSES);
        return new CommandResult(String.format(MESSAGE_SORT_FIXEDEXPENSE_SUCCESS));
    }

}
