package seedu.address.storage.accommodationbooking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.accommodationbooking.AccommodationBooking;
import seedu.address.model.accommodationbooking.Day;
import seedu.address.model.accommodationbooking.accommodationName;
import seedu.address.model.accommodationbooking.Remark;
import seedu.address.model.transportbooking.Location;

/**
 * Jackson-friendly version of {@link AccommodationBooking}.
 */
public class JsonAdaptedAccommodationBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Accommodation Booking's %s field is missing!";

    private final String name;
    private final String location;
    private final String startDay;
    private final String endDay;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedAccommodationBooking} with the given details.
     */
    @JsonCreator
    public JsonAdaptedAccommodationBooking(@JsonProperty("name") String name, @JsonProperty("location") String location,
                             @JsonProperty("startDay") String startDay, @JsonProperty("endDay") String endDay,
                             @JsonProperty("remark") String remark) {
        this.name = name;
        this.location = location;
        this.startDay = startDay;
        this.endDay = endDay;
        this.remark = remark;
    }

    /**
     * Converts a given {@code AccommodationBooking} into this class for Jackson use.
     */
    public JsonAdaptedAccommodationBooking(AccommodationBooking source) {
        name = source.getAccommodationName().accommodationName;
        location = source.getLocation().value;
        startDay = source.getStartDay().accommodationDay;
        endDay = source.getEndDay().accommodationDay;
        remark = source.getRemark().toString();
    }

    /**
     * Converts this Jackson-friendly adapted accommodation booking object into the
     * model's {@code AccommodationBooking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public AccommodationBooking toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, accommodationName.class.getSimpleName()));
        }
        if (!accommodationName.isValidName(name)) {
            throw new IllegalValueException(accommodationName.MESSAGE_CONSTRAINTS);
        }
        final accommodationName modelAccommodationName = new accommodationName(name);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (startDay == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(startDay)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelStartDay = new Day(startDay);

        if (endDay == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(endDay)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelEndDay = new Day(endDay);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        final Remark modelRemark = new Remark(remark);

        return new AccommodationBooking(modelAccommodationName, modelLocation, modelStartDay, modelEndDay, modelRemark);
    }

}
