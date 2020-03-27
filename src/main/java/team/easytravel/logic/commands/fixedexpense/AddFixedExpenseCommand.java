package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static team.easytravel.commons.util.CollectionUtil.requireAllNonNull;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;

/**
 * Adds a Fixed Expense to the Fixed Expense manager.
 */
public class AddFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "addexpense";

    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in the address book";
    public static final String MESSAGE_EXCEED_BUDGET = "Take note, you have exceeded your budget!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a fixed expense to the fixed expense list"
            + "Parameters: "
            + PREFIX_AMOUNT + "AMOUNT"
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + PREFIX_CATEGORY + "CATEGORY...\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "1500" + " "
            + PREFIX_DESCRIPTION + "Plane Tickets" + " "
            + PREFIX_CATEGORY + "Flights";

    public static final String MESSAGE_SUCCESS = "New Fixed Expense added: %1$s";
    private final FixedExpense toAdd;
    private boolean checkOverseasAmount;

    /**
     * Creates an AddFixedExpenseCommand to the specied {@code FixedExpense}
     */
    public AddFixedExpenseCommand(FixedExpense toAdd, boolean checkOverseasAmount) {
        requireAllNonNull(toAdd, checkOverseasAmount);
        this.toAdd = toAdd;
        this.checkOverseasAmount = checkOverseasAmount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFixedExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        if (checkOverseasAmount) {
            double exchangeRate = model.getExchangeRate();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Double amountInSgd = Double.parseDouble(toAdd.getAmount().value) * exchangeRate;
            System.out.println(amountInSgd);
            // JPY -> SGD
            FixedExpense editedFixedExpense = new FixedExpense(new Amount(decimalFormat.format(amountInSgd)),
                    toAdd.getDescription(),
                    toAdd.getFixedExpenseCategory());
            model.addFixedExpense(editedFixedExpense);
        } else {
            model.addFixedExpense(toAdd);
        }

        int currentBudget = model.getBudget();


        if (currentBudget < 1) {
            FixedExpense highestExpense = Collections.max(model.getFilteredFixedExpenseList(),
                    Comparator.comparingDouble(x -> Double.parseDouble(x.getAmount().value)));

            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd + "\n"
                    + MESSAGE_EXCEED_BUDGET + "\n Currently, your Highest expense is "
                    + highestExpense));
        }


        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd + "\n"
                + "Your budget left is now " + currentBudget));
    }
}
