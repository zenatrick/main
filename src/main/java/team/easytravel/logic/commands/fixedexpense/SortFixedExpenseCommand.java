package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;
import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;

/**
 * Sorts your Fixed Expense List according to ascending or descending amount.
 */
public class SortFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "sortexpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts the fixed expense identified by the command"
            + " asc or des in the displayed fixed expense list\n"
            + "asc sorts by ascending order while des sorts by descending order\n"
            + "Parameters : SORTIDENTIFIER (must be asc or des) "
            + "[AMOUNT]" + "[DESCRIPTION]" + "[CATEGORY]\n"
            + "Example: " + COMMAND_WORD + " asc amount";

    public static final String MESSAGE_SORT_FIXED_EXPENSE_SUCCESS = "Sorting of FixedExpense successful :)";

    public static final String SORT_DESCENDING = "des";
    public static final String SORT_ASCENDING = "asc";
    public static final String CATEGORY = "category";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";

    private final String sortIdentifier;
    private final String sortParameter;

    public SortFixedExpenseCommand(String sortIdentifier, String sortParameter) {
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
            if (sortIdentifier.equals("des")) {
                model.sortFixedExpenseList((x, y) -> y.getFixedExpenseCategory().toString().compareTo(
                        x.getFixedExpenseCategory().toString()
                ));
                return new CommandResult(MESSAGE_SORT_FIXED_EXPENSE_SUCCESS);

            } else {
                model.sortFixedExpenseList(Comparator.comparing(x -> x.getFixedExpenseCategory().toString()));
                return new CommandResult(MESSAGE_SORT_FIXED_EXPENSE_SUCCESS);
            }
        case "amount":
            if (sortIdentifier.equals("des")) {
                model.sortFixedExpenseList((x, y) -> (int) Math.signum(Double.parseDouble(y.getAmount().value)
                        - Double.parseDouble(x.getAmount().value)));
                return new CommandResult(MESSAGE_SORT_FIXED_EXPENSE_SUCCESS);

            } else {
                model.sortFixedExpenseList((Comparator.comparing(x -> x.getAmount().value)));
                return new CommandResult(MESSAGE_SORT_FIXED_EXPENSE_SUCCESS);
            }

        case "description":
            if (sortIdentifier.equals("des")) {
                model.sortFixedExpenseList((x, y) -> y.getDescription().toString().compareTo(
                        x.getDescription().toString()));
                return new CommandResult(MESSAGE_SORT_FIXED_EXPENSE_SUCCESS);

            } else {
                model.sortFixedExpenseList(Comparator.comparing(x -> x.getDescription().toString()));
                return new CommandResult(MESSAGE_SORT_FIXED_EXPENSE_SUCCESS);
            }

        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortFixedExpenseCommand.MESSAGE_USAGE));
        }

    }
}

