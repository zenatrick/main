package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.accommodationbooking.AccommodationBooking;

/**
 * Unmodifiable view of a TransportBookingManager.
 */
public interface ReadOnlyAccommadationBookingManager {

    /**
     * Returns an unmodifiable view of the accommodation bookings list.
     * This list will not contain any duplicate accommodation bookings.
     */
    ObservableList<AccommodationBooking> getAccommodationBookingList();

}
