package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Amount;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Allows the user to set the budget for the trip.
 * Uses budget set/AMOUNT.
 */

public class BudgetCommand extends Command {

    public static final String COMMAND_WORD = "budget";
    // Put the amount entered as a trip as an String for now.
    // Will create a amount class to convert from String to integer.
    private static int amount;
    private String input;

    // To show budgets, as there is no Cli Syntax like "/r" required.
    // TODO: I am assuming that there is only one budget.

    //This is the case where its show/remove
    public BudgetCommand(String input, String amount) {
        this.input = input;
        if (input.contains("remove")) {
            BudgetCommand.amount = 0;
        }
    }

    //This is the case where its "set/" or "edit/"
    public BudgetCommand(String amount) {
        requireAllNonNull(amount);
        Amount budgetInString = new Amount(amount);
        BudgetCommand.amount = budgetInString.getAmountOutput();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("The budget is " + amount);

    }
}
