package team.easytravel.storage.trip;

import static team.easytravel.storage.trip.JsonAdaptedTrip.MISSING_FIELD_MESSAGE_FORMAT;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.trip.TypicalTrip.TRIP_CHEESE;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.time.Date;
import team.easytravel.commons.exceptions.IllegalValueException;
import team.easytravel.model.trip.Budget;
import team.easytravel.model.trip.ExchangeRate;
import team.easytravel.model.util.attributes.Title;

class JsonAdaptedTripTest {

    public static final String INVALID_TITLE = "G@ADU!T!O&TR!P";
    public static final String INVALID_START_DATE = "IUH!IUH#J@";
    public static final String INVALID_END_DATE = "Testing123";
    public static final Integer INVALID_BUDGET = -1000;
    public static final Double INVALID_EXCHANGE_RATE = 10000000.00;

    public static final String VALID_TITLE = TRIP_CHEESE.getTitle().value;
    public static final String VALID_START_DATE = TRIP_CHEESE.getStartDate().getStorageFormat();
    public static final String VALID_END_DATE = TRIP_CHEESE.getEndDate().getStorageFormat();
    public static final Integer VALID_BUDGET = TRIP_CHEESE.getBudget().value;
    public static final Double VALID_EXCHANGE_RATE = TRIP_CHEESE.getExchangeRate().value;

    @Test
    public void toModelType_invalidTripTitle_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(INVALID_TITLE, VALID_START_DATE, VALID_END_DATE, VALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullTripTitle_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(null, VALID_START_DATE, VALID_END_DATE, VALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidTripStartDate_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, INVALID_START_DATE, VALID_END_DATE, VALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullTripStartDate_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, null, VALID_END_DATE, VALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidTripEndDateCategory_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, VALID_START_DATE, INVALID_END_DATE, VALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullTripEndDate_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, VALID_START_DATE, null, VALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidTripBudget_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, VALID_START_DATE, VALID_END_DATE, INVALID_BUDGET, VALID_EXCHANGE_RATE);
        String expectedMessage = Budget.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullTripBudget_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, VALID_START_DATE, VALID_END_DATE, null, VALID_EXCHANGE_RATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Budget.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_invalidExchangeRate_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, VALID_START_DATE, VALID_END_DATE, VALID_BUDGET, INVALID_EXCHANGE_RATE);
        String expectedMessage = ExchangeRate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }

    @Test
    public void toModelType_nullExchangeRate_throwsIllegalValueException() {
        JsonAdaptedTrip trip =
                new JsonAdaptedTrip(VALID_TITLE, VALID_START_DATE, VALID_END_DATE, VALID_BUDGET, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ExchangeRate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, trip::toModelType);
    }
}
