package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transportation's end location in the Transportation manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndLocation(String)}
 */
public class EndLocation {

    public static final String MESSAGE_CONSTRAINTS = "End location must be alphanumeric words not more than 50 "
            + "characters long";

    /*
     * End location must be made up of alphanumeric words not more than 50 characters long.
     */
    public static final String VALIDATION_REGEX = "^[^\\s][\\w\\s]{1,50}";

    public final String value;

    /**
     * Constructs  {@code EndLocation}.
     *
     * @param endLocation A valid end location.
     */
    public EndLocation(String endLocation) {
        requireNonNull(endLocation);
        checkArgument(isValidEndLocation(endLocation), MESSAGE_CONSTRAINTS);
        value = endLocation;
    }

    /**
     * Returns true if a given string is a valid end location.
     */
    public static boolean isValidEndLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndLocation // instanceof handles nulls
                && value.equals(((EndLocation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
