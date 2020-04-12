package team.easytravel.testutil.transportbooking;

import team.easytravel.commons.core.time.DateTime;
import team.easytravel.model.listmanagers.transportbooking.Mode;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.util.attributes.Location;

/**
 * A utility class to help with building Transport Booking objects.
 */
public class TransportBookingBuilder {


    public static final String DEFAULT_MODE = "plane";
    public static final String DEFAULT_START_LOCATION = "Singapore";
    public static final String DEFAULT_END_LOCATION = "Japan";
    public static final String DEFAULT_START_DATE_TIME = DateTime.fromString("28-09-2020 18:00").getStorageFormat();
    public static final String DEFAULT_END_DATE_TIME = DateTime.fromString("30-09-2020 23:00").getStorageFormat();


    private Mode mode;
    private Location startLocation;
    private Location endLocation;
    private DateTime startDateTime;
    private DateTime endDateTime;

    public TransportBookingBuilder() {
        mode = new Mode(DEFAULT_MODE);
        startLocation = new Location(DEFAULT_START_LOCATION);
        endLocation = new Location(DEFAULT_END_LOCATION);
        startDateTime = DateTime.fromString(DEFAULT_START_DATE_TIME);
        endDateTime = DateTime.fromString(DEFAULT_END_DATE_TIME);
    }

    /**
     * Initializes the TransportBookingBuilder with the data of {@code transportBooking}.
     */
    public TransportBookingBuilder(TransportBooking transportBooking) {
        mode = transportBooking.getMode();
        startLocation = transportBooking.getStartLocation();
        endLocation = transportBooking.getEndLocation();
        startDateTime = transportBooking.getStartDateTime();
        endDateTime = transportBooking.getEndDateTime();
    }

    /**
     * Parses the {@code Mode} of the {@code TransportBooking} that we are building.
     */
    public TransportBookingBuilder withMode(String mode) {
        this.mode = new Mode(mode);
        return this;
    }

    /**
     * Parses the {@code startLocation} of the {@code TransportBooking} that we are building.
     */
    public TransportBookingBuilder withStartLocation(String startLocation) {
        this.startLocation = new Location(startLocation);
        return this;
    }

    /**
     * Parses the {@code endLocation} of the {@code TransportBooking} that we are building.
     */
    public TransportBookingBuilder withEndLocation(String endLocation) {
        this.endLocation = new Location(endLocation);
        return this;
    }

    /**
     * Parses the {@code startDateTime} of the {@code TransportBooking} that we are building.
     */
    public TransportBookingBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = DateTime.fromString(startDateTime);
        return this;
    }

    /**
     * Parses the {@code endDateTime} of the {@code TransportBooking} that we are building.
     */
    public TransportBookingBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = DateTime.fromString(endDateTime);
        return this;
    }


    public TransportBooking build() {
        return new TransportBooking(mode, startLocation, endLocation, startDateTime, endDateTime);
    }



}
