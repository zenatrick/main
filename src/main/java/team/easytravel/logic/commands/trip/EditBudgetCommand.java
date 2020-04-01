package team.easytravel.logic.commands.trip;

import static java.util.Objects.requireNonNull;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.trip.Budget;
import team.easytravel.model.trip.TripManager;

/**
 * Edits the details of an existing budget.
 */
public class EditBudgetCommand extends Command {

    public static final String COMMAND_WORD = "editbudget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the budget of the current trip."
            + "Parameter: AMOUNT (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1000";

    public static final String MESSAGE_EDIT_BUDGET_SUCCESS = "Edited Budget: %1$s";
    public static final String MESSAGE_BUDGET_TOO_LOW = "Edited Budget should be more than expenses";

    private final String newBudget;

    /**
     * @param budget details to edit the current budget with
     */
    public EditBudgetCommand(String budget) {
        requireNonNull(budget);
        this.newBudget = budget.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        Integer amount;
        try {
            amount = Integer.parseInt(newBudget);
        } catch (NumberFormatException e) {
            return new CommandResult(MESSAGE_USAGE);
        }

        if (!Budget.isValidBudget(amount)) {
            return new CommandResult(Budget.MESSAGE_CONSTRAINTS);
        }

        if (amount < model.getTotalExpense()) {
            throw new CommandException(String.format(MESSAGE_EDIT_BUDGET_SUCCESS, amount) + "\n"
                    + MESSAGE_BUDGET_TOO_LOW);
        }

        Budget editedBudget = new Budget(amount);
        model.setBudget(editedBudget);
        return new CommandResult(String.format(MESSAGE_EDIT_BUDGET_SUCCESS, amount) ,
                null,
                false,
                false,
                false,
                false,
                false,
                true,
                false);
    }


}
