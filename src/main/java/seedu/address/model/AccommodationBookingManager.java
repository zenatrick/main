package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.accommodationbooking.AccommodationBooking;
import seedu.address.model.accommodationbooking.UniqueAccommodationBookingList;

/**
 * Wraps all data at the AccommodationBookingManager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AccommodationBookingManager implements ReadOnlyAccommodationBookingManager {

    private final UniqueAccommodationBookingList accommodationBookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        accommodationBookings = new UniqueAccommodationBookingList();
    }

    public AccommodationBookingManager() {}

    /**
     * Creates an AccommodationBookingManager using the AccommodationBookings in the {@code toBeCopied}
     */
    public AccommodationBookingManager(ReadOnlyAccommodationBookingManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the accommodation bookings list with {@code accommodation bookings}.
     * {@code accommodationBookings} must not contain duplicate accommodation bookings.
     */
    public void setAccommodationBookings(List<AccommodationBooking> accommodationBookings) {
        this.accommodationBookings.setAccommodationBookings(accommodationBookings);
    }

    /**
     * Resets the existing data of this {@code AccommodationBookingManager} with {@code newData}.
     */
    public void resetData(ReadOnlyAccommodationBookingManager newData) {
        requireNonNull(newData);

        setAccommodationBookings(newData.getAccommodationBookingList());
    }

    //// AccommodationBooking-level operations

    /**
     * Returns true if a accommodation booking with the same identity as {@code accommodationBooking} exists in the
     * AccommodationBookingManager.
     */
    public boolean hasAccommodationBooking(AccommodationBooking accommodationBooking) {
        requireNonNull(accommodationBooking);
        return accommodationBookings.contains(accommodationBooking);
    }

    /**
     * Adds a accommodation booking to the AccommodationBookingManager.
     * The accommodation booking must not already exist in the AccommodationBookingManager.
     */
    public void addAccommodationBooking(AccommodationBooking accommodationBooking) {
        accommodationBookings.add(accommodationBooking);
    }

    /**
     * Replaces the given accommodation booking {@code target} in the list with {@code editedAccommodationBooking}.
     * {@code target} must exist in the AccommodationBookingManager.
     * The accommodation booking identity of {@code editedAccommodationBooking} must not be the same as another existing
     * accommodation booking in the AccommodationBookingManager.
     */
    public void setAccommodationBooking(AccommodationBooking target, AccommodationBooking editedAccommodationBooking) {
        requireNonNull(editedAccommodationBooking);

        accommodationBookings.setAccommodationBooking(target, editedAccommodationBooking);
    }

    /**
     * Removes {@code key} from this {@code AccommodationBookingManager}.
     * {@code key} must exist in the AccommodationBookingManager.
     */
    public void removeAccommodationBooking(AccommodationBooking key) {
        accommodationBookings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return accommodationBookings.asUnmodifiableObservableList().size() + " accommodation bookings";
        // TODO: refine later
    }

    @Override
    public ObservableList<AccommodationBooking> getAccommodationBookingList() {
        return accommodationBookings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccommodationBookingManager // instanceof handles nulls
                && accommodationBookings.equals(((AccommodationBookingManager) other).accommodationBookings));
    }

    @Override
    public int hashCode() {
        return accommodationBookings.hashCode();
    }

}
