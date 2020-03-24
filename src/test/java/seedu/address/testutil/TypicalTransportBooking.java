package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.time.DateTime;
import seedu.address.model.listmanagers.TransportBookingManager;
import seedu.address.model.listmanagers.transportbooking.Mode;
import seedu.address.model.listmanagers.transportbooking.TransportBooking;
import seedu.address.model.util.attributes.Location;

public class TypicalTransportBooking {

    public static final TransportBooking TRANSPORT_BOOKING_TRAIN = new TransportBooking(
            new Mode("train"), new Location("Singapore"), new Location("Malaysia"),
            DateTime.fromString("20-01-2020 22:00"), DateTime.fromString("20-01-2020 23:00")
    );

    public static final TransportBooking TRANSPORT_BOOKING_PLANE = new TransportBooking(
            new Mode("plane"), new Location("Singapore"), new Location("Hong Kong"),
            DateTime.fromString("23-03-2020 00:15"), DateTime.fromString("23-03-2020 05:15")
    );

    public static final TransportBooking TRANSPORT_BOOKING_BUS = new TransportBooking(
            new Mode("bus"), new Location("Hong Kong"), new Location("Macau"),
            DateTime.fromString("24-03-2020 19:00"), DateTime.fromString("24-03-2020 20:50")
    );

    private TypicalTransportBooking() {}

    /**
     * Returns an {@code TypicalTransportBooking} with all the typical TransportBooking.
     */

    public static TransportBookingManager getTypicalTransportBookingManager() {
        TransportBookingManager tb = new TransportBookingManager();
        for(TransportBooking transportBooking: getTypicalTransportBookings()) {
            tb.addTransportBooking(transportBooking);
        }
        return tb;
    }

    public static List<TransportBooking> getTypicalTransportBookings() {
        return new ArrayList<>(Arrays.asList(TRANSPORT_BOOKING_TRAIN, TRANSPORT_BOOKING_PLANE,
                TRANSPORT_BOOKING_BUS));
    }

}
