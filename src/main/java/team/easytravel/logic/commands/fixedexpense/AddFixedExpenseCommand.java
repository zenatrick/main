package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.util.CollectionUtil.requireAllNonNull;
import static team.easytravel.logic.commands.CommandResult.Action.SWITCH_TAB_FIXED_EXPENSE;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_AMOUNT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_CURRENCY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_EXPENSE_DESCRIPTION;

import java.util.Collections;
import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.trip.TripManager;

/**
 * Adds a Fixed Expense to the Fixed Expense manager.
 */
public class AddFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "addexpense";

    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in the Fixed Expense List";
    public static final String MESSAGE_EXCEED_BUDGET = "Take note, you have exceeded your budget!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a fixed expense to the fixed expense list\n"
            + "Parameters: "
            + PREFIX_EXPENSE_AMOUNT + "AMOUNT "
            + PREFIX_EXPENSE_CURRENCY + "CURRENCY "
            + PREFIX_EXPENSE_DESCRIPTION + "DESCRIPTION "
            + PREFIX_EXPENSE_CATEGORY + "CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EXPENSE_AMOUNT + "1500 "
            + PREFIX_EXPENSE_CURRENCY + "sgd "
            + PREFIX_EXPENSE_DESCRIPTION + "Plane Tickets "
            + PREFIX_EXPENSE_CATEGORY + "transport";

    public static final String MESSAGE_SUCCESS = "New Fixed Expense added: %1$s";

    private FixedExpense toAdd;
    private boolean isOverseasAmount;

    /**
     * Creates an AddFixedExpenseCommand to the specified {@code FixedExpense}
     */
    public AddFixedExpenseCommand(FixedExpense toAdd, boolean isOverseasAmount) {
        requireAllNonNull(toAdd, isOverseasAmount);
        this.toAdd = toAdd;
        this.isOverseasAmount = isOverseasAmount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        if (model.hasFixedExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        if (!isOverseasAmount) {
            model.addFixedExpense(toAdd);
        } else {
            double exchangeRate = model.getExchangeRate();
            double amountInOther = Double.parseDouble(toAdd.getAmount().value);
            double amountInSgd = amountInOther * exchangeRate;
            String amountInSgdString = String.format("%.2f", amountInSgd);
            checkString(amountInSgdString);
            toAdd = new FixedExpense(new Amount(amountInSgdString),
                    toAdd.getDescription(),
                    toAdd.getFixedExpenseCategory());
            model.addFixedExpense(toAdd);
        }


        int remainingBudget = (int) (model.getBudget() - model.getTotalExpense());

        boolean isAboveBudget = checkBudget(remainingBudget);

        if (isAboveBudget) {
            FixedExpense highestExpense = Collections.max(model.getFilteredFixedExpenseList(),
                    Comparator.comparingDouble(x -> Double.parseDouble(x.getAmount().value)));

            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd + "\n"
                    + MESSAGE_EXCEED_BUDGET + "\nCurrently, your Highest expense is "
                    + highestExpense), SWITCH_TAB_FIXED_EXPENSE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd + "\n"
                + "Your budget left is " + remainingBudget), SWITCH_TAB_FIXED_EXPENSE);
    }

    private boolean checkBudget(int remainingBudget) {
        return remainingBudget < 1;
    }

    private void checkString(String amountInSgdString) throws CommandException {
        if (!Amount.isValidAmount(amountInSgdString)) {
            throw new CommandException(Amount.MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFixedExpenseCommand // instanceof handles nulls
                && toAdd.equals(((AddFixedExpenseCommand) other).toAdd)
                && isOverseasAmount == ((AddFixedExpenseCommand) other).isOverseasAmount);
    }
}
