package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.budget.FixedCostBook;
import seedu.address.model.budget.FixedCostModel;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(FixedCostModel model) {
        requireNonNull(model);
        model.setFixedCostBook((new FixedCostBook()));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
