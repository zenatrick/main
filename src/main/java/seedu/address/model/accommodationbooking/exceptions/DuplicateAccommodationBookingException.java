package seedu.address.model.accommodationbooking.exceptions;

/**
 * Signals that the operation will result in duplicate AccommodationBookings.
 * AccommodationBookings are considered duplicates if they are equal.
 * (Checked using AccommodationBooking#equals(Object))
 */
public class DuplicateAccommodationBookingException extends RuntimeException {

    public DuplicateAccommodationBookingException() {
        super("Operation would result in duplicate Accommodation bookings");
    }

}
