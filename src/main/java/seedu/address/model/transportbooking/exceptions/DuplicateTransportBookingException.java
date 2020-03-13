package seedu.address.model.transportbooking.exceptions;

/**
 * Signals that the operation will result in duplicate TransportBookings.
 * TransportBookings are considered duplicates if they are equal. (Checked using TransportBooking#equals(Object))
 */
public class DuplicateTransportBookingException extends RuntimeException {
    public DuplicateTransportBookingException() {
        super("Operation would result in duplicate transport bookings");
    }
}
