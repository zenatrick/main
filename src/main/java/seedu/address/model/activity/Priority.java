package seedu.address.model.activity;



import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Priority in numbers in an Activity.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {
    public static final String MESSAGE_CONSTRAINTS = "Priority must be a valid number and not more than 3";

    // todo update regex
    // Done already, allows 10 digits, and at most 2 decimal places.
    public static final int VALIDATION_MAX_DAYS = 3;
    public static final String VALIDATION_REGEX = "\\d{1,}";

    private String value;

    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is valid days.
     */
    public static boolean isValidPriority(String test) {
        try {
            boolean isValidRegex = test.matches(VALIDATION_REGEX);
            boolean isValidPriority = Integer.parseInt(test) <= VALIDATION_MAX_DAYS;
            return isValidPriority && isValidRegex;
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
                || (other instanceof seedu.address.model.activity.Priority // instanceof handles nulls
                && value.equals(((seedu.address.model.activity.Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
