package team.easytravel.logic.commands;

import static java.util.Objects.requireNonNull;

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
     * User want to check status
     */
    private final boolean isCheckStatus;

    /**
     * Check Status Information should be shown to the user.
     */
    private final String checkStatusString;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, String checkStatusString, boolean showHelp, boolean exit,
                         boolean isCheckStatus, boolean isSetTrip, boolean isDeleteTrip) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.checkStatusString = checkStatusString;
        this.showHelp = showHelp;
        this.exit = exit;
        this.isCheckStatus = isCheckStatus;
        this.isSetTrip = isSetTrip;
        this.isDeleteTrip = isDeleteTrip;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, String checkStatusString) {
        this(feedbackToUser, checkStatusString, false, false, true, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isSetTrip() {
        return isSetTrip;
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

    public String getStatusFeedback() {
        return checkStatusString;
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
