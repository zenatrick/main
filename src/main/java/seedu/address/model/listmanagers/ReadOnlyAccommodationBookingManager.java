package seedu.address.model.listmanagers;

import javafx.collections.ObservableList;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;

/**
 * Unmodifiable view of a TransportBookingManager.
 */
public interface ReadOnlyAccommodationBookingManager {

    /**
     * Returns an unmodifiable view of the accommodation bookings list.
     * This list will not contain any duplicate accommodation bookings.
     */
    ObservableList<AccommodationBooking> getAccommodationBookingList();

}
