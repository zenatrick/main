package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Duration in an Activity.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(String)}
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Time (Hours) must be a valid number less than 24";

    // todo update regex
    public static final int VALIDATION_MAX_HOURS = 24;
    public static final int VALIDATION_MIN_HOURS = 1;

    private String value;

    public Duration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        value = duration;
    }

    /**
     * Returns true if a given string is valid days.
     */
    public static boolean isValidDuration(String test) {
        if (test == null) {
            throw new NullPointerException();
        }

        try {
            boolean isValidHour = Integer.parseInt(test) <= VALIDATION_MAX_HOURS
                    &&
                    Integer.parseInt(test) >= VALIDATION_MIN_HOURS;
            return isValidHour;
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.activity.Duration // instanceof handles nulls
                && value.equals(((seedu.address.model.activity.Duration) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
