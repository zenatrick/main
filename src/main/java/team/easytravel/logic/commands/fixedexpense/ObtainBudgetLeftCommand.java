package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;

/**
 * Obtains the budget available.
 */
public class ObtainBudgetLeftCommand extends Command {

    public static final String COMMAND_WORD = "obtainbudget";

    public static final String MESSAGE_OBTAIN_BUDGET_LEFT_SUCCESS = "Your remaining budget is is ";

    public ObtainBudgetLeftCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return new CommandResult("" + MESSAGE_OBTAIN_BUDGET_LEFT_SUCCESS + model.getBudget());

    }
}
