package seedu.address.logic.commands.activity;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.listmanagers.ActivityManager;

/**
 * Clears the address book.
 */
public class ClearActivityCommand extends Command {

    public static final String COMMAND_WORD = "clearactivity";
    public static final String MESSAGE_SUCCESS = "The activity List has been clear";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setActivityManager(new ActivityManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

