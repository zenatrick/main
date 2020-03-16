package seedu.address.model.accommodationbooking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.accommodationbooking.exceptions.DuplicateAccommodationBookingException;
import seedu.address.model.accommodationbooking.exceptions.AccommodationBookingNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of accommodations that enforces uniqueness between its elements and does not allow nulls.
 * An accommodation is considered unique by comparing using {@code AccommodationBooking#equals(Object)}. As such,
 * adding and updating of accommodation bookings uses AccommodationBooking#equals(Object) for equality so as to ensure
 * that the accommodation being added or updated is unique in terms of identity in the UniqueAccommodationList.
 *
 * Supports a minimal set of list operations.
 *
 * @see AccommodationBooking#equals(Object)
 */
public class UniqueAccommodationBookingList implements Iterable<AccommodationBooking> {

    private final ObservableList<AccommodationBooking> internalList = FXCollections.observableArrayList();
    private final ObservableList<AccommodationBooking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent accommodation booking as the given argument.
     */
    public boolean contains(AccommodationBooking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an accommodation booking to the list.
     * The accommodation booking must not already exist in the list.
     */
    public void add(AccommodationBooking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAccommodationBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the accommodation booking {@code target} in the list with {@code editedAccommodationBooking}.
     * {@code target} must exist in the list.
     * The accommodation booking identity of {@code editedAccommodationBooking} must not be the same as another
     * existing accommodation bookings in the list.
     */
    public void setAccommodationBooking(AccommodationBooking target, AccommodationBooking editedAccommodationBooking) {
        requireAllNonNull(target, editedAccommodationBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AccommodationBookingNotFoundException();
        }

        if (!target.equals(editedAccommodationBooking) && contains(editedAccommodationBooking)) {
            throw new DuplicateAccommodationBookingException();
        }

        internalList.set(index, editedAccommodationBooking);
    }

    /**
     * Removes the equivalent accommodation booking from the list.
     * The accommodation booking must exist in the list.
     */
    public void remove(AccommodationBooking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AccommodationBookingNotFoundException();
        }
    }

    public void setAccommodationBookings(UniqueAccommodationBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code accommodationBookingList}.
     * {@code accommodationBookingList} must not contain duplicate accommodation bookings.
     */
    public void setAccommodationBookings(List<AccommodationBooking> accommodationBookingList) {
        requireAllNonNull(accommodationBookingList);
        if (!transportBookingsAreUnique(accommodationBookingList)) {
            throw new DuplicateAccommodationBookingException();
        }

        internalList.setAll(accommodationBookingList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AccommodationBooking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<AccommodationBooking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAccommodationBookingList // instanceof handles nulls
                && internalList.equals(((UniqueAccommodationBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code accommodation} contains only unique accommodation.
     */
    private boolean transportBookingsAreUnique(List<AccommodationBooking> accommodationBookings) {
        for (int i = 0; i < accommodationBookings.size() - 1; i++) {
            for (int j = i + 1; j < accommodationBookings.size(); j++) {
                if (accommodationBookings.get(i).equals(accommodationBookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
