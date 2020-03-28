package team.easytravel.logic.commands.activity;

import static java.util.Objects.requireNonNull;

import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.logic.commands.exceptions.CommandException;
import team.easytravel.model.Model;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.trip.TripManager;

/**
 * Deletes an activity in the list.
 */
public class DeleteActivityCommand extends Command {

    public static final String COMMAND_WORD = "deleteactivity";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the activity identified by the index number used in the displayed activity list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACTIVITY_SUCCESS = "Deleted Activity: %1$s";

    private final Index targetIndex;

    public DeleteActivityCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTrip()) {
            throw new CommandException(TripManager.MESSAGE_ERROR_NO_TRIP);
        }

        List<Activity> lastShownList = model.getFilteredActivityList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteActivity(activityToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ACTIVITY_SUCCESS, activityToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteActivityCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteActivityCommand) other).targetIndex)); // state check
    }
}
