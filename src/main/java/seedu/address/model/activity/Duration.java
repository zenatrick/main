package seedu.address.model.activity;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Priority in an Activity.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(String)}
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Amount must be a valid number less than 32";

    // todo update regex
    // Done already, allows 10 digits, and at most 2 decimal places.
    public static final int VALIDATION_MAX_DAYS = 31;
    public static final String VALIDATION_REGEX = "\\d{2,}";

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
        try {
            boolean isValidRegex = test.matches(VALIDATION_REGEX);
            boolean isValidDays = Integer.parseInt(test) <= VALIDATION_MAX_DAYS;
            return isValidDays && isValidRegex;
        }
        catch (Exception e){
            return false;
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
