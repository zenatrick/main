package team.easytravel.model.listmanagers;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.util.uniquelist.UniqueList;

/**
 * Wraps all data at the AccommodationBookingManager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AccommodationBookingManager implements ReadOnlyAccommodationBookingManager {
    private final UniqueList<AccommodationBooking> accommodationBookings;

    /**
     * Instantiates a new AccommodationBookingManager.
     */
    public AccommodationBookingManager() {
        accommodationBookings = new UniqueList<>();
    }

    /**
     * Creates an AccommodationBookingManager using the AccommodationBookings in the {@code toBeCopied}
     */
    public AccommodationBookingManager(ReadOnlyAccommodationBookingManager toBeCopied) {
        accommodationBookings = new UniqueList<>();
        resetData(toBeCopied);
    }

    // List overwrite operations

    /**
     * Replaces the contents of the accommodation booking list with {@code accommodation bookings}.
     * {@code accommodationBookings} must not contain duplicate accommodation bookings.
     */
    public void setAccommodationBookings(List<AccommodationBooking> accommodationBookings) {
        this.accommodationBookings.setElements(accommodationBookings);
    }

    /**
     * Resets the existing data of this {@code AccommodationBookingManager} with {@code newData}.
     */
    public void resetData(ReadOnlyAccommodationBookingManager newData) {
        requireNonNull(newData);
        setAccommodationBookings(newData.getAccommodationBookingList());
    }

    // AccommodationBooking-level operations

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

        accommodationBookings.setElement(target, editedAccommodationBooking);
    }

    public void sortAccommodationBookings() {
        accommodationBookings.sort((x,y) -> (x.getStartDay().value - y.getStartDay().value));
    }

    /**
     * Removes {@code key} from this {@code AccommodationBookingManager}.
     * {@code key} must exist in the AccommodationBookingManager.
     */
    public void removeAccommodationBooking(AccommodationBooking key) {
        accommodationBookings.remove(key);
    }

    // Util methods

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
