package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.budget.Amount;
import seedu.address.model.budget.Budget;

/**
 * Allows the user to set the budget for the trip.
 * Uses budget set/AMOUNT.
 */

public class BudgetSetCommand extends Command {

    public static final String COMMAND_WORD = "setbudget";
    public static final String MESSAGE_SUCCESS = "New budget added: %1$s";
    public static final String MESSAGE_FAILURE = "Please enter a suitable format for your budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a new budget for the trip. "
            + "Must be a positive integer. by the index number used in the displayed person list.\n "
            + "Parameters: AMOUNT (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " set/20000 ";


    // Put the amount entered as a trip as an String for now.
    // Will create a amount class to convert from String to integer.

    private final Budget budget;

    public BudgetSetCommand(Budget budget) {
        //Make sure there is not a null budget
        requireAllNonNull(budget);
        this.budget = budget;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BudgetSetCommand)) {
            return false;
        }

        Amount amount = (Amount) obj;
        return this.equals(amount);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        Integer a = (Integer) budget.getFinalBudgetAmount();
        System.out.println(a);
        if (a == 0) {
            return new CommandResult(String.format(MESSAGE_FAILURE));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, budget.getFinalBudgetAmount()));

    }
}
