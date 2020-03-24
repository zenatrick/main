package seedu.address.model.trip;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.time.Date;
import seedu.address.commons.core.time.DateTime;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.trip.exception.IllegalOperationException;
import seedu.address.model.util.attributes.Title;

/**
 * Manages the trip of the application.
 */
public class TripManager {

    private static final String MESSAGE_ERROR_SCHEDULE = "The ending time of the scheduled activity cannot go beyond "
            + "the scheduled day";
    private static final String MESSAGE_ERROR_SET_TRIP = "Trip has already been set.";
    private static final String MESSAGE_ERROR_NO_TRIP = "Trip has not been set.";

    private boolean hasTrip;
    private Trip trip;
    private List<DaySchedule> daySchedules;

    /**
     * Instantiates a new trip manager.
     */
    public TripManager() {
        hasTrip = false;
        trip = null;
        daySchedules = new ArrayList<>();
    }

    /**
     * Creates an TripManager using the Trip in the {@code} toBeCopied}
     */
    public TripManager(TripManager toBeCopied) {
        daySchedules = new ArrayList<>();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code TripManager} with {@code newData}
     */
    public void resetData(TripManager newData) {
        requireNonNull(newData);
        hasTrip = newData.hasTrip;
        trip = newData.trip;
        daySchedules.addAll(newData.daySchedules);
    }

    /**
     * Set a new trip with the given {@code trip}.
     */
    public void setTrip(Trip trip) {
        if (hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_SET_TRIP);
        }
        hasTrip = true;
        this.trip = trip;
        daySchedules = IntStream.range(0, trip.getNumDays())
                .mapToObj(x -> new DaySchedule())
                .collect(Collectors.toList());
    }

    public Title getTripTitle() {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return trip.getTitle();
    }

    public int getTripNumDays() {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return trip.getNumDays();
    }

    public Budget getTripBudget() {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return trip.getBudget();
    }

    public Date getTripStartDate() {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return trip.getStartDate();
    }

    public Date getTripEndDate() {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return trip.getEndDate();
    }

    public ObservableList<DayScheduleEntry> getDayScheduleEntryList(int dayIndex) {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return daySchedules.get(dayIndex).getDayScheduleList();
    }

    public List<ObservableList<DayScheduleEntry>> getDayScheduleEntryLists() {
        if (!hasTrip) {
            throw new IllegalOperationException(MESSAGE_ERROR_NO_TRIP);
        }
        return daySchedules.stream().map(DaySchedule::getDayScheduleList).collect(Collectors.toList());
    }

    /**
     * Returns true if there exist a trip
     */
    public boolean hasTrip() {
        return hasTrip;
    }

    /**
     * Schedule an activity into a given day with a specified start time.
     */
    public void scheduleActivity(int dayIndex, DateTime startDateTime, Activity activityToSchedule) {
        DaySchedule daySchedule = daySchedules.get(dayIndex);
        DayScheduleEntry entry = DayScheduleEntry.fromActivity(activityToSchedule, startDateTime);
        DateTime endDateTime = entry.getEndDateTime();
        if (startDateTime.getDate().daysUntilInclusive(endDateTime.getDate()) > 0) {
            throw new IllegalOperationException(MESSAGE_ERROR_SCHEDULE);
        }
        daySchedule.addScheduleEntry(entry);
    }

    /**
     * Unschedule an activity from a given day.
     */
    public void unscheduleActivity(int dayIndex, DayScheduleEntry toDelete) {
        DaySchedule daySchedule = daySchedules.get(dayIndex);
        daySchedule.removeScheduleEntry(toDelete);
    }

    /**
     * Schedule a transport booking into the schedule.
     */
    public void scheduleTransportBooking(TransportBooking transportBookingToSchedule) {
        DateTime startDateTime = transportBookingToSchedule.getStartDateTime();
        DateTime endDateTime = transportBookingToSchedule.getEndDateTime();
        int dayIndex = getTripStartDate().daysUntilInclusive(startDateTime.getDate());
        DaySchedule daySchedule = daySchedules.get(dayIndex);
        DayScheduleEntry entry = DayScheduleEntry.fromTransportBooking(transportBookingToSchedule);
        daySchedule.addScheduleEntry(entry);
    }

    /**
     * Unschedule transport booking from the schedule.
     */
    public void unscheduleTransportBooking(DayScheduleEntry toDelete) {
        DateTime startDateTime = toDelete.getStartDateTime();
        int dayIndex = getTripStartDate().daysUntilInclusive(startDateTime.getDate());
        DaySchedule daySchedule = daySchedules.get(dayIndex);
        daySchedule.removeScheduleEntry(toDelete);
    }

    @Override
    public String toString() {
        return "TripManager - hasTrip: " + hasTrip + " " + trip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasTrip, trip, daySchedules);
    }
}

