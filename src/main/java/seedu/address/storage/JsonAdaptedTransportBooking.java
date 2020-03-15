package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transportbooking.Location;
import seedu.address.model.transportbooking.Mode;
import seedu.address.model.transportbooking.Time;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * Jackson-friendly version of {@link TransportBooking}.
 */
class JsonAdaptedTransportBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TransportBooking's %s field is missing!";

    private final String mode;
    private final String startLocation;
    private final String endLocation;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedTransportBooking} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTransportBooking(@JsonProperty("mode") String mode,
                                       @JsonProperty("startLocation") String startLocation,
                                       @JsonProperty("endLocation") String endLocation,
                                       @JsonProperty("startTime") String startTime,
                                       @JsonProperty("endTime") String endTime) {
        this.mode = mode;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code TransportBooking} into this class for Jackson use.
     */
    public JsonAdaptedTransportBooking(TransportBooking source) {
        mode = source.getMode().value;
        startLocation = source.getStartLocation().value;
        endLocation = source.getEndLocation().value;
        startTime = source.getStartTime().value;
        endTime = source.getEndTime().value;
    }

    /**
     * Converts this Jackson-friendly adapted transport booking object into the model's {@code TransportBooking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public TransportBooking toModelType() throws IllegalValueException {
        if (mode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Mode.class.getSimpleName()));
        }
        if (!Mode.isValidMode(mode)) {
            throw new IllegalValueException(Mode.MESSAGE_CONSTRAINTS);
        }
        final Mode modelMode = new Mode(mode);

        if (startLocation == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(startLocation)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelStartLocation = new Location(startLocation);

        if (endLocation == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(endLocation)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelEndLocation = new Location(endLocation);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(startTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        Time modelStartTime = new Time(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(endTime)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        Time modelEndTime = new Time(endTime);

        return new TransportBooking(modelMode, modelStartLocation, modelEndLocation, modelStartTime, modelEndTime);
    }

}
