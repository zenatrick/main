package seedu.address.model.listmanagers;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.util.uniquelist.UniqueList;

/**
 * Wraps all data at the TransportBookingManager level
 * Duplicates are not allowed (by .isSame comparison)
 */
public class TransportBookingManager implements ReadOnlyTransportBookingManager {
    private final UniqueList<TransportBooking> transportBookings;

    /**
     * Instantiates a new TransportBookingManager.
     */
    public TransportBookingManager() {
        transportBookings = new UniqueList<>();
    }

    /**
     * Creates a TransportBookingManager using the TransportBookings in the {@code toBeCopied}
     */
    public TransportBookingManager(ReadOnlyTransportBookingManager toBeCopied) {
        transportBookings = new UniqueList<>();
        resetData(toBeCopied);
    }

    // List overwrite operations

    /**
     * Replaces the contents of the transport booking list with {@code transportBookings}.
     * {@code transportBookings} must not contain duplicate transport bookings.
     */
    public void setTransportBookings(List<TransportBooking> transportBookings) {
        this.transportBookings.setElements(transportBookings);
    }

    /**
     * Resets the existing data of this {@code TransportBookingManager} with {@code newData}.
     */
    public void resetData(ReadOnlyTransportBookingManager newData) {
        requireNonNull(newData);
        setTransportBookings(newData.getTransportBookings());
    }

    // TransportBooking-level operations

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

        transportBookings.setElement(target, editedTransportBooking);
    }

    /**
     * Removes {@code key} from this {@code TransportBookingManager}.
     * {@code key} must exist in the TransportBookingManager.
     */
    public void removeTransportBooking(TransportBooking key) {
        transportBookings.remove(key);
    }

    // Util methods

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
