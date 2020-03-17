package seedu.address.model.listmanager;

import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.UniqueActivityList;
import seedu.address.model.transportbooking.TransportBooking;

import java.util.List;

import static java.util.Objects.requireNonNull;

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
