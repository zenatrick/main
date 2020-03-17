package seedu.address.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.listmanager.FixedExpenseManager;

/**
 * Clears the fixed expense list
 */
public class ClearFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "clearexpense";
    public static final String MESSAGE_SUCCESS = "Fixed Expenses has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFixedExpense(new FixedExpenseManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
