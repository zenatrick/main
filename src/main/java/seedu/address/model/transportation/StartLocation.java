package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transportation's start location in the Transportation manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartLocation(String)}
 */
public class StartLocation {

    public static final String MESSAGE_CONSTRAINTS = "Start location must be alphanumeric words not more than 50 "
            + "characters long";

    /*
     * Start location must be made up of alphanumeric words not more than 50 characters long.
     */
    public static final String VALIDATION_REGEX = "^[^\\s][\\w\\s]{1,50}";

    public final String value;

    /**
     * Constructs  {@code StartLocation}.
     *
     * @param startLocation A valid start location.
     */
    public StartLocation(String startLocation) {
        requireNonNull(startLocation);
        checkArgument(isValidStartLocation(startLocation), MESSAGE_CONSTRAINTS);
        value = startLocation;
    }

    /**
     * Returns true if a given string is a valid start location.
     */
    public static boolean isValidStartLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartLocation // instanceof handles nulls
                && value.equals(((StartLocation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
