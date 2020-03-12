package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

//import static seedu.address.commons.util.AppUtil.checkArgument;
//
///**
// * Represents a Transportation's end time in the Transportation manager.
// * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
// */

/**
 * Represents a Transportation's end time in the Transportation manager.
 * Guarantees: immutable
 */
public class EndTime {

    // todo check end time is within trip's start and end time

    //public static final String MESSAGE_CONSTRAINTS = "End time be a time that is within the trip's start and end "
    //        + "time.";

    public final LocalDateTime value;

    /**
     * Constructs  {@code StartTime}.
     *
     * @param endTime A valid end time.
     */
    public EndTime(LocalDateTime endTime) {
        requireNonNull(endTime);
        //checkArgument(isValidStartTime(endTime), MESSAGE_CONSTRAINTS);
        //assume value to be valid
        value = endTime;
    }

    ///**
    // * Returns true if a given string is a valid end time, within the trip's start and end time.
    // */
    //public static boolean isValidEndTime(LocalDateTime tripStartTime, LocalDateTime tripEndTime) {
    //    return value.compareTo(tripStartTime) > 0 && value.compareTo(tripEndTime) <= 0;
    //}

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && value.equals(((EndTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
