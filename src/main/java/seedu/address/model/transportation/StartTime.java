package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

//import static seedu.address.commons.util.AppUtil.checkArgument;
//
///**
// * Represents a Transportation's start time in the Transportation manager.
// * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
// */

/**
 * Represents a Transportation's start time in the Transportation manager.
 * Guarantees: immutable
 */
public class StartTime {

    // todo check start time is within trip's start and end time

    //public static final String MESSAGE_CONSTRAINTS = "Start time be a time that is within the trip's start and end "
    //        + "time.";

    public final LocalDateTime value;

    /**
     * Constructs  {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(LocalDateTime startTime) {
        requireNonNull(startTime);
        //checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        //assume value to be valid
        value = startTime;
    }

    ///**
    // * Returns true if a given string is a valid start time, within the trip's start and end time.
    // */
    //public static boolean isValidStartTime(LocalDateTime tripStartTime, LocalDateTime tripEndTime) {
    //    return value.compareTo(tripStartTime) >= 0 && value.compareTo(tripEndTime) < 0;
    //}

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && value.equals(((StartTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
