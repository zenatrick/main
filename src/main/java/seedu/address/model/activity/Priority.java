package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

/**
 * Represents a Activity's priority in numbers in the ActivityManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(Integer)}
 */
public class Priority {
    public static final String MESSAGE_CONSTRAINTS = "Priority must be a valid integer from 0 to 3";

    private static final int MAX_VALUE = 3;
    private static final int MIN_VALUE = 0;
    public static final Predicate<Integer> VALIDATION_PREDICATE = i -> i >= MIN_VALUE && i <= MAX_VALUE;

    public final Integer value;

    public Priority(Integer priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(Integer test) {
        return VALIDATION_PREDICATE.test(test);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
