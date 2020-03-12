package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.transportation.TransportBooking;
import seedu.address.model.transportation.UniqueTransportBookingList;

/**
 * Wraps all data at the TransportBookingManager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class TransportBookingManager implements ReadOnlyTransportBookingManager {

    private final UniqueTransportBookingList transportBookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        transportBookings = new UniqueTransportBookingList();
    }

    public TransportBookingManager() {
    }

    /**
     * Creates a TransportBookingManager using the TransportBookings in the {@code toBeCopied}
     */
    public TransportBookingManager(ReadOnlyTransportBookingManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the transport bookings list with {@code transport bookings}.
     * {@code transportBookings} must not contain duplicate transport bookings.
     */
    public void setTransportBookings(List<TransportBooking> transportBookings) {
        this.transportBookings.setTransportBookings(transportBookings);
    }

    /**
     * Resets the existing data of this {@code TransportBookingManager} with {@code newData}.
     */
    public void resetData(ReadOnlyTransportBookingManager newData) {
        requireNonNull(newData);

        setTransportBookings(newData.getTransportBookings());
    }

    //// TransportBooking-level operations

    /**
     * Returns true if a transport booking with the same identity as {@code transportBooking} exists in the
     * TransportBookingManager.
     */
    public boolean hasTransportBooking(TransportBooking transportBooking) {
        requireNonNull(transportBooking);
        return transportBookings.contains(transportBooking);
    }

    /**
     * Adds a transport booking to the TransportBookingManager.
     * The transport booking must not already exist in the TransportBookingManager.
     */
    public void addTransportBooking(TransportBooking transportBooking) {
        transportBookings.add(transportBooking);
    }

    /**
     * Replaces the given transport booking {@code target} in the list with {@code editedTransportBooking}.
     * {@code target} must exist in the TransportBookingManager.
     * The transport booking identity of {@code editedTransportBooking} must not be the same as another existing
     * transport booking in the TransportBookingManager.
     */
    public void setTransportBooking(TransportBooking target, TransportBooking editedTransportBooking) {
        requireNonNull(editedTransportBooking);

        transportBookings.setTransportBooking(target, editedTransportBooking);
    }

    /**
     * Removes {@code key} from this {@code TransportBookingManager}.
     * {@code key} must exist in the TransportBookingManager.
     */
    public void removeTransportBooking(TransportBooking key) {
        transportBookings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return transportBookings.asUnmodifiableObservableList().size() + " transport bookings";
        // TODO: refine later
    }

    @Override
    public ObservableList<TransportBooking> getTransportBookings() {
        return transportBookings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TransportBookingManager // instanceof handles nulls
                && transportBookings.equals(((TransportBookingManager) other).transportBookings));
    }

    @Override
    public int hashCode() {
        return transportBookings.hashCode();
    }
}
