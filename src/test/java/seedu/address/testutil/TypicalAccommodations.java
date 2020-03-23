package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.listmanagers.AccommodationBookingManager;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationName;
import seedu.address.model.listmanagers.accommodationbooking.Day;
import seedu.address.model.listmanagers.accommodationbooking.Remark;
import seedu.address.model.util.attributes.Location;

public class TypicalAccommodations {

    public static final AccommodationBooking ACCOMMODATION_BOOKING_HOTEL = new AccommodationBooking(
            new AccommodationName("Hotel"), new Location("Singapore"),
            new Day(1), new Day(3), new Remark("2 nights stay"));

    public static final AccommodationBooking ACCOMMODATION_BOOKING_HOSTEL = new AccommodationBooking(
            new AccommodationName("Hostel"), new Location("Singapore"),
            new Day(3), new Day(4), new Remark("1 night stay"));

    public static final AccommodationBooking ACCOMMODATION_BOOKING_BACKPACKER = new AccommodationBooking(
            new AccommodationName("Backpacker"), new Location("Singapore"),
            new Day(5), new Day(7), new Remark("2 nights stay"));

    private TypicalAccommodations() {}

    /**
     * Returns an {@code TypicalAccommodations} with all the typical Accommodations.
     */

    public static AccommodationBookingManager getTypicalAccommodationManager() {
        AccommodationBookingManager am = new AccommodationBookingManager();
        for(AccommodationBooking ab : getTypicalAccommodations()) {
            am.addAccommodationBooking(ab);
        }
        return am;
    }

    public static List<AccommodationBooking> getTypicalAccommodations() {
        return new ArrayList<>(Arrays.asList(ACCOMMODATION_BOOKING_HOTEL,
                ACCOMMODATION_BOOKING_BACKPACKER
                , ACCOMMODATION_BOOKING_HOSTEL));
    }

}
