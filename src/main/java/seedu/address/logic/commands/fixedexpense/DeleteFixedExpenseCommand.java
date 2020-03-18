package seedu.address.logic.commands.fixedexpense;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class DeleteFixedExpenseCommand extends Command {

    public static final String COMMAND_WORD = "deleteexpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the fixed expense identified by the index number used in the displayed fixed expense list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FIXEDEXPENSE_SUCCESS = "Deleted Fixed Expense: %1$s";

    private final Index targetIndex;

    public DeleteFixedExpenseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
