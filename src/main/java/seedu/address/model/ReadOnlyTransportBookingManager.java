package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * Unmodifiable view of a TransportBookingManager.
 */
public interface ReadOnlyTransportBookingManager {

    /**
     * Returns an unmodifiable view of the transport bookings list.
     * This list will not contain any duplicate transport bookings.
     */
    ObservableList<TransportBooking> getTransportBookings();

}
