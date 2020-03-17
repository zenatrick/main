package seedu.address.storage.accommodationbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.listmanagers.AccommodationBookingManager;
import seedu.address.model.listmanagers.ReadOnlyAccommodationBookingManager;
import seedu.address.model.listmanagers.accommodationbooking.AccommodationBooking;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "accommodationBookingManager")
public class JsonSerializableAccommodationBookingManager {

    public static final String MESSAGE_DUPLICATE_ACCOMMODATION_BOOKING = "Accommodation booking list contains "
            + "duplicate accommodation(s).";

    private final List<JsonAdaptedAccommodationBooking> accommodationBookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAccommodationBookingManager} with the given accommodation bookings.
     */
    @JsonCreator
    public JsonSerializableAccommodationBookingManager(
            @JsonProperty("accommodationBookings") List<JsonAdaptedAccommodationBooking> accommodationBookings) {
        this.accommodationBookings.addAll(accommodationBookings);
    }

    /**
     * Converts a given {@code ReadOnlyAccommodationBookingManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the
     *               created {@code JsonSerializableAccommodationBookingManager}.
     */
    public JsonSerializableAccommodationBookingManager(ReadOnlyAccommodationBookingManager source) {
        accommodationBookings.addAll(
                source.getAccommodationBookingList()
                        .stream()
                        .map(JsonAdaptedAccommodationBooking::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this accommodation booking manager into the model's {@code AccommodationBookingManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AccommodationBookingManager toModelType() throws IllegalValueException {
        AccommodationBookingManager accommodationBookingManager = new AccommodationBookingManager();
        for (JsonAdaptedAccommodationBooking jsonAdaptedAccommodationBooking : accommodationBookings) {
            AccommodationBooking accommodationBooking = jsonAdaptedAccommodationBooking.toModelType();
            if (accommodationBookingManager.hasAccommodationBooking(accommodationBooking)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACCOMMODATION_BOOKING);
            }
            accommodationBookingManager.addAccommodationBooking(accommodationBooking);
        }
        return accommodationBookingManager;
    }

}
