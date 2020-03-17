package seedu.address.model.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a AccommodationBooking's start or end day in the AccommodationBookingManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day {

    public static final String MESSAGE_CONSTRAINTS = "Day should be a positive integer.";

    public static final String VALIDATION_REGEX = "^\\d+$";

    public final String value;

    /**
     * Constructs an {@code Day}.
     *
     * @param day A valid day within the length of the trip.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        value = day;
    }

    // TODO: Find a way to ensure day does not exceed the number of days of a trip

    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
