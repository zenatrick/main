package seedu.address.model.transportbooking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a TransportBooking's start or end time in the TransportBookingManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)} )}
 */
public class Time {

    // TODO: Check time is within trip's start and end time
    public static final String MESSAGE_CONSTRAINTS = "Time must be within the trip's start and end time.";

    public final String value;

    /**
     * Constructs  {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns true if a given string is a valid time, within the trip's start and end time.
     */
    public static boolean isValidTime(String test) {
        return true;
    }

    public int compareTo(Time otherTime) {
        return value.compareTo(otherTime.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
