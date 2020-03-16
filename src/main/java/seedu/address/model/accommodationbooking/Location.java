package seedu.address.model.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an AccommodationBooking's location in the AccommodationBookingManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS = "Location of accommodation must be alphanumeric words not "
            + "more than 50 characters long";

    // todo update regex to match constraints
    // Allows for 50 alphanumeric characters.
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[A-Za-z0-9\\s]{1,50}+";

    /**
     * The accommodation location.
     */
    public final String accommodationLocation;

    /**
     * Constructs  {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        accommodationLocation = location;
    }

    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return accommodationLocation;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.accommodationbooking.Location // instanceof handles nulls
                && accommodationLocation.equals(((seedu.address.model.accommodationbooking.Location) other)
                .accommodationLocation)); // state check
    }

    @Override
    public int hashCode() {
        return accommodationLocation.hashCode();
    }

}
