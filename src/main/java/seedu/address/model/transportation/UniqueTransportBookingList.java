package seedu.address.model.transportation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transportation.exceptions.DuplicateTransportBookingException;
import seedu.address.model.transportation.exceptions.TransportBookingNotFoundException;

/**
 * A list of transport bookings that enforces uniqueness between its elements and does not allow nulls.
 * A transport booking is considered unique by comparing using {@code TransportBooking#equals(Object)}. As such,
 * adding, updating and removal of transport bookings uses TransportBooking#equals(Object) for equality so as to ensure
 * that the transport booking being added or updated is unique in terms of identity in the UniqueTransportBookingList.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see TransportBooking#equals(Object)
 */
public class UniqueTransportBookingList implements Iterable<TransportBooking> {

    private final ObservableList<TransportBooking> internalList = FXCollections.observableArrayList();
    private final ObservableList<TransportBooking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent transport booking as the given argument.
     */
    public boolean contains(TransportBooking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a transport booking to the list.
     * The transport booking must not already exist in the list.
     */
    public void add(TransportBooking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTransportBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the transport booking {@code target} in the list with {@code editedTransportBooking}.
     * {@code target} must exist in the list.
     * The transport booking identity of {@code editedTransportBooking} must not be the same as another existing
     * transport bookings in the list.
     */
    public void setTransportBooking(TransportBooking target, TransportBooking editedTransportBooking) {
        requireAllNonNull(target, editedTransportBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TransportBookingNotFoundException();
        }

        if (!target.equals(editedTransportBooking) && contains(editedTransportBooking)) {
            throw new DuplicateTransportBookingException();
        }

        internalList.set(index, editedTransportBooking);
    }

    /**
     * Removes the equivalent transport booking from the list.
     * The transport booking must exist in the list.
     */
    public void remove(TransportBooking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TransportBookingNotFoundException();
        }
    }

    public void setTransportBookings(UniqueTransportBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code transportBookingList}.
     * {@code transportBookingList} must not contain duplicate transport bookings.
     */
    public void setTransportBookings(List<TransportBooking> transportBookingList) {
        requireAllNonNull(transportBookingList);
        if (!transportBookingsAreUnique(transportBookingList)) {
            throw new DuplicateTransportBookingException();
        }

        internalList.setAll(transportBookingList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TransportBooking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TransportBooking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTransportBookingList // instanceof handles nulls
                && internalList.equals(((UniqueTransportBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean transportBookingsAreUnique(List<TransportBooking> transportBookings) {
        for (int i = 0; i < transportBookings.size() - 1; i++) {
            for (int j = i + 1; j < transportBookings.size(); j++) {
                if (transportBookings.get(i).equals(transportBookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
