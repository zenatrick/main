package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

//import static seedu.address.commons.util.AppUtil.checkArgument;
//
///**
// * Represents a Transportation's start or end time in the Transportation manager.
// * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
// */

/**
 * Represents a Transportation's start or end time in the Transportation manager.
 * Guarantees: immutable
 */
public class Time {

    // todo check time is within trip's start and end time

    //public static final String MESSAGE_CONSTRAINTS = "Time must be within the trip's start and end "
    //        + "time.";

    public final LocalDateTime value;

    /**
     * Constructs  {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(LocalDateTime time) {
        requireNonNull(time);
        //checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        //assume value to be valid
        value = time;
    }

    ///**
    // * Returns true if a given string is a valid start time, within the trip's start and end time.
    // */
    //public static boolean isValidStartTime(LocalDateTime tripStartTime, LocalDateTime tripEndTime) {
    //    return value.compareTo(tripStartTime) >= 0 && value.compareTo(tripEndTime) <= 0;
    //}

    public int compareTo(Time otherTime) {
        return value.compareTo(otherTime.value);
    }

    @Override
    public String toString() {
        return value.toString();
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
