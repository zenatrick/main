package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.FixedExpenseManager;

/**
 * Clears the fixed expense list
 */
public class ClearFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "clearexpense";
    public static final String MESSAGE_SUCCESS = "Fixed Expenses has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFixedExpenseManager(new FixedExpenseManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
