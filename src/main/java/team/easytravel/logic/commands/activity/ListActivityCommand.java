package team.easytravel.logic.commands.activity;

import static java.util.Objects.requireNonNull;
import static team.easytravel.model.Model.PREDICATE_SHOW_ALL_ACTIVITIES;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;

/**
 * Lists all activity list items to the user.
 */
public class ListActivityCommand extends Command {

    public static final String COMMAND_WORD = "listactivity";

    public static final String MESSAGE_SUCCESS = "Listed all activity";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredActivityList(PREDICATE_SHOW_ALL_ACTIVITIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
