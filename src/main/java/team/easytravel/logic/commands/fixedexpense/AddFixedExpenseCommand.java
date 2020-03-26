package team.easytravel.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Collections;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
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

    /**
     * Creates an AddFixedExpenseCommand to the specied {@code FixedExpense}
     */
    public AddFixedExpenseCommand(FixedExpense toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFixedExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        model.addFixedExpense(toAdd);
        int currentBudget = model.getBudget();


        if(currentBudget < 1) {
            FixedExpense highestExpense = Collections.max(model.getFilteredFixedExpenseList(),
                    (x,y)-> Integer.parseInt(y.getAmount().value)
                            - Integer.parseInt(x.getAmount().value));
            
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd + "\n"
            + "You have spent more than your budget! Currently, your Highest expense is " +
                    highestExpense));
        }


        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd + "\n" +
                "Your budget left is now " + currentBudget ));
    }
}
