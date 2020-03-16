package seedu.address.model.accommodationbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a TransportBooking's start or end day in the AccommodationBookingManager.
 * Guarantees: immutable
 */
public class Day {

    public static final String MESSAGE_CONSTRAINTS = "Day has to be within the length of the trip and cannot be"
            + "a negative number.";

    public static final String VALIDATION_REGEX = "^\\d+$";

    public final String accommodationDay;

    /**
     * Constructs an {@code Day}.
     *
     * @param day A valid day within the length of the trip.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        accommodationDay = day;
    }

    // todo Find a way to ensure day does not exceed the number of days of a trip

    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(accommodationDay);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.accommodationbooking.Day // instanceof handles nulls
                && accommodationDay.equals(((seedu.address.model.accommodationbooking.Day) other)
                .accommodationDay)); // state check
    }

    @Override
    public int hashCode() {
        return accommodationDay.hashCode();
    }

}
