package seedu.address.logic.commands.fixedexpense;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.fixedexpense.FixedExpense;

public class AddFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "addexpense";

    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in the address book";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a fixed expense to the fixed expense list"
            + "Parameters: "
            + PREFIX_AMOUNT + "AMOUNT"
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + PREFIX_CATEGORY + "CATEGORY...\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "1500"
            + PREFIX_DESCRIPTION + "Plane Tickets"
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

        if(model.hasFixedExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        model.addFixedExpense(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

    }
}
