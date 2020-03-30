package team.easytravel.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * True when user sets trip.
     */
    private final boolean isSetTrip;

    /**
     * True when user deletes trip.
     */
    private final boolean isDeleteTrip;

    /**
     * True when user edits trip.
     */
    private final boolean isEditTrip;

    /**
     * User want to check status
     */
    private final boolean isCheckStatus;

    /**
     * Check Status Information should be shown to the user.
     */
    //private final String checkStatusString;

    private final List<String> checkStatus;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, List<String> checkStatus, boolean showHelp, boolean exit,
                         boolean isCheckStatus, boolean isSetTrip, boolean isDeleteTrip, boolean isEditTrip) {

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.checkStatus = checkStatus;
        this.showHelp = showHelp;
        this.exit = exit;
        this.isCheckStatus = isCheckStatus;
        this.isSetTrip = isSetTrip;
        this.isDeleteTrip = isDeleteTrip;
        this.isEditTrip = isEditTrip;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, List<String> checkStatus) {
        this(feedbackToUser, checkStatus, false, false, true, false, false, false);

    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isSetTrip() {
        return isSetTrip;
    }

    public boolean isEditTrip() {
        return isEditTrip;
    }

    public boolean isDeleteTrip() {
        return isDeleteTrip;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isCheckStatus() {
        return isCheckStatus;
    }

    public List<String> getStatusFeedback() {
        return checkStatus;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
