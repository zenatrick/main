package seedu.address.model.transportation;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a TransportBooking in the TransportBookingManager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TransportBooking {

    public static final String MESSAGE_LOCATION_CONSTRAINTS = "Start location cannot be equal to end location.";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Start time must come before end time.";

    //data
    private final Mode mode;
    private final Location startLocation;
    private final Location endLocation;
    private final Time startTime;
    private final Time endTime;

    /**
     * Every field must be present and not null.
     */
    public TransportBooking(Mode mode, Location startLocation, Location endLocation, Time startTime,
                            Time endTime) {
        requireAllNonNull(mode, startLocation, endLocation, startTime, endTime);
        checkArgument(areLocationsValid(startLocation, endLocation), MESSAGE_LOCATION_CONSTRAINTS);
        checkArgument(isTimeValid(startTime, endTime), MESSAGE_TIME_CONSTRAINTS);
        this.mode = mode;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Mode getMode() {
        return mode;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    /**
     * Returns true if the specified start and end location are different.
     */
    public boolean areLocationsValid(Location startLocation, Location endLocation) {
        return !startLocation.equals(endLocation);
    }

    /**
     * Returns true if the specified start time is before the specified end time..
     */
    public boolean isTimeValid(Time startTime, Time endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    /**
     * Returns true if both transport bookings have the same data fields.
     * This defines an equality between two transport bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TransportBooking)) {
            return false;
        }

        TransportBooking otherTransportBooking = (TransportBooking) other;
        return otherTransportBooking.getMode().equals(getMode())
                && otherTransportBooking.getStartLocation().equals(getStartLocation())
                && otherTransportBooking.getEndLocation().equals(getEndLocation())
                && otherTransportBooking.getStartTime().equals(getStartTime())
                && otherTransportBooking.getEndTime().equals(getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mode, startLocation, endLocation, startTime, endTime);
    }

    @Override
    public String toString() {
        return "TransportBooking - Mode: " + getMode()
                + " Start Location: " + getStartLocation()
                + " End Location: " + getEndLocation()
                + " Start Time: " + getStartTime()
                + " End Time: " + getEndTime();
    }

}
