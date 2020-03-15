package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.listmanager.ReadOnlyTransportBookingManager;
import seedu.address.model.listmanager.TransportBookingManager;
import seedu.address.model.transportbooking.TransportBooking;

/**
 * An Immutable TransportBookingManager that is serializable to JSON format.
 */
@JsonRootName(value = "transportBookingManager")
class JsonSerializableTransportBookingManager {

    public static final String MESSAGE_DUPLICATE_TRANSPORT_BOOKING = "Transport booking list contains duplicate "
            + "transport booking(s).";

    private final List<JsonAdaptedTransportBooking> transportBookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTransportBookingManager} with the given transport bookings.
     */
    @JsonCreator
    public JsonSerializableTransportBookingManager(
            @JsonProperty("transportBookings") List<JsonAdaptedTransportBooking> transportBookings) {
        this.transportBookings.addAll(transportBookings);
    }

    /**
     * Converts a given {@code ReadOnlyTransportBookingManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTransportBookingManager}.
     */
    public JsonSerializableTransportBookingManager(ReadOnlyTransportBookingManager source) {
        transportBookings.addAll(
                source.getTransportBookings()
                        .stream()
                        .map(JsonAdaptedTransportBooking::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts this JsonSerializableTransportBookingManager into the model's {@code TransportBookingManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TransportBookingManager toModelType() throws IllegalValueException {
        TransportBookingManager transportBookingManager = new TransportBookingManager();
        for (JsonAdaptedTransportBooking jsonAdaptedTransportBooking : transportBookings) {
            TransportBooking transportBooking = jsonAdaptedTransportBooking.toModelType();
            if (transportBookingManager.hasTransportBooking(transportBooking)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSPORT_BOOKING);
            }
            transportBookingManager.addTransportBooking(transportBooking);
        }
        return transportBookingManager;
    }

}
