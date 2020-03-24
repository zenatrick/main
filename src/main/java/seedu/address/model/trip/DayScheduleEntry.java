package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.time.Time;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.attributes.Title;
import seedu.address.model.util.uniquelist.UniqueListElement;

/**
 * Represents a DayScheduleEntry Object in the Trip
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DayScheduleEntry implements UniqueListElement {

    /**
     * Represents the type of schedule entry.
     * ACTIVITY represents a activity and TRANSPORT represents a transport booking.
     */
    private enum Type {
        ACTIVITY,
        TRANSPORT
    }

    private final Type type;
    private final Title title;
    private final Time startTime;
    private final Time endTime;
    private final long durationInHours;
    private final Location location;
    private final Optional<Activity> activityPointer;
    private final Optional<TransportBooking> transportBookingPointer;

    private DayScheduleEntry(
            Type type, Title title, Time startTime, Time endTime, long durationInHours, Location location,
            Optional<Activity> activityPointer, Optional<TransportBooking> transportBookingPointer) {
        this.type = type;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInHours = durationInHours;
        this.location = location;
        this.activityPointer = activityPointer;
        this.transportBookingPointer = transportBookingPointer;
    }

    /**
     * Returns a DayScheduleEntry given an activity and a startTime.
     */
    public static DayScheduleEntry fromActivity(Activity activity, Time startTime) {
        requireAllNonNull(activity, startTime);
        Time endTime = startTime.plusHours(activity.getDuration().value);
        return new DayScheduleEntry(
                Type.ACTIVITY, activity.getTitle(), startTime, endTime, activity.getDuration().value,
                activity.getLocation(), Optional.of(activity), Optional.empty());
    }

    /**
     * Returns a DayScheduleEntry given a transport booking.
     */
    public static DayScheduleEntry fromTransportBooking(TransportBooking transportBooking) {
        requireNonNull(transportBooking);
        Time startTime = transportBooking.getStartDateTime().getTime();
        Time endTime = transportBooking.getEndDateTime().getTime();
        long durationInHours = startTime.hoursUntilExclusive(endTime);
        return new DayScheduleEntry(Type.TRANSPORT, new Title(transportBooking.getMode().value), startTime, endTime,
                durationInHours, transportBooking.getStartLocation(), Optional.empty(), Optional.of(transportBooking));
    }

    public boolean isActivity() {
        return type.equals(Type.ACTIVITY);
    }

    public boolean isTransportBooking() {
        return type.equals(Type.TRANSPORT);
    }

    public Type getType() {
        return type;
    }

    public Title getTitle() {
        return title;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public long getDurationInHours() {
        return durationInHours;
    }

    public Location getLocation() {
        return location;
    }

    public Activity getActivityPointer() {
        assert isActivity();
        return activityPointer.get();
    }

    public TransportBooking getTransportBookingPointer() {
        assert isTransportBooking();
        return transportBookingPointer.get();
    }

    @Override
    public boolean isSame(UniqueListElement other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof DayScheduleEntry)) {
            return false;
        }

        DayScheduleEntry otherEntry = (DayScheduleEntry) o;
        return type.equals(otherEntry.type)
                && title.equals(otherEntry.title)
                && startTime.equals(otherEntry.startTime)
                && endTime.equals(otherEntry.endTime)
                && location.equals(otherEntry.location)
                && durationInHours == otherEntry.durationInHours
                && activityPointer.equals(otherEntry.activityPointer)
                && transportBookingPointer.equals(otherEntry.transportBookingPointer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, startTime, endTime, durationInHours, location, activityPointer,
                transportBookingPointer);
    }
}
