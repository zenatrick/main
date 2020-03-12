package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transportation's start or end location in the Transportation manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS = "Location must be alphanumeric words not more than 50 "
            + "characters long";

    /*
     * Location must be made up of alphanumeric words not more than 50 characters long.
     */
    public static final String VALIDATION_REGEX = "^[^\\s][\\w\\s]{1,50}";

    public final String value;

    /**
     * Constructs  {@code Location}.
     *
     * @param startLocation A valid location.
     */
    public Location(String startLocation) {
        requireNonNull(startLocation);
        checkArgument(isValidLocation(startLocation), MESSAGE_CONSTRAINTS);
        value = startLocation;
    }

    /**
     * Returns true if a given string is a valid start location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && value.equals(((Location) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
