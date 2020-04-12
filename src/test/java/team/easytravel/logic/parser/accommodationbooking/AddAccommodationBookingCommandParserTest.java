package team.easytravel.logic.parser.accommodationbooking;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.ACCOMMODATION_NAME_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.ACCOMMODATION_NAME_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.END_DAY_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.END_DAY_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_END_DAY_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_LOCATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_NAME_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_REMARK_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_ACCOMMODATION_START_DAY_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.LOCATION_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.LOCATION_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static team.easytravel.logic.commands.CommandTestUtil.REMARK_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.REMARK_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.START_DAY_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.START_DAY_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_END_DAY;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_LOCATION;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_NAME;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_START_DAY;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.accommodationbooking.AddAccommodationBookingCommand;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationName;
import team.easytravel.model.listmanagers.accommodationbooking.Day;
import team.easytravel.model.listmanagers.accommodationbooking.Remark;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.testutil.accommodationbooking.AccommodationBookingBuilder;

class AddAccommodationBookingCommandParserTest {

    private AddAccommodationBookingCommandParser parser = new AddAccommodationBookingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AccommodationBooking expectedAccommodationBooking =
                new AccommodationBookingBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL
                + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));

        // multiple accommodation names - last accommodation name accepted
        assertParseSuccess(parser, ACCOMMODATION_NAME_DESC_RITZ + ACCOMMODATION_NAME_DESC_HOTEL
                        + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));

        // multiple locations - last location accepted
        assertParseSuccess(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_RITZ + LOCATION_DESC_HOTEL
                        + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));

        // multiple start days - last start day accepted
        assertParseSuccess(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_RITZ
                        + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));

        // multiple end days - last end day accepted
        assertParseSuccess(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                        + END_DAY_DESC_RITZ + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                        + END_DAY_DESC_HOTEL + REMARK_DESC_RITZ + REMARK_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no remark
        AccommodationBooking expectedAccommodationBooking =
                new AccommodationBookingBuilder(true).build(true);
        assertParseSuccess(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                        + END_DAY_DESC_HOTEL,
                new AddAccommodationBookingCommand(expectedAccommodationBooking));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAccommodationBookingCommand.MESSAGE_USAGE);

        // missing accommonation name prefix
        assertParseFailure(parser, VALID_ACCOMMODATION_HOTEL_NAME + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                + END_DAY_DESC_HOTEL, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + VALID_ACCOMMODATION_HOTEL_LOCATION
                + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL, expectedMessage);

        // missing start day prefix
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL
                + VALID_ACCOMMODATION_HOTEL_START_DAY + END_DAY_DESC_HOTEL, expectedMessage);

        // missing end day prefix
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                + VALID_ACCOMMODATION_HOTEL_END_DAY, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ACCOMMODATION_HOTEL_NAME + VALID_ACCOMMODATION_HOTEL_LOCATION
                + VALID_ACCOMMODATION_HOTEL_START_DAY + VALID_ACCOMMODATION_HOTEL_END_DAY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid accommodation name
        assertParseFailure(parser, INVALID_ACCOMMODATION_NAME_DESC + LOCATION_DESC_HOTEL
                + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL, AccommodationName.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + INVALID_ACCOMMODATION_LOCATION_DESC
                + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL, Location.MESSAGE_CONSTRAINTS);

        // invalid start day
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL
                + INVALID_ACCOMMODATION_START_DAY_DESC + END_DAY_DESC_HOTEL
                + REMARK_DESC_HOTEL, Day.MESSAGE_CONSTRAINTS);

        // invalid end day
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                + INVALID_ACCOMMODATION_END_DAY_DESC + REMARK_DESC_HOTEL, Day.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL
                + END_DAY_DESC_HOTEL + INVALID_ACCOMMODATION_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ACCOMMODATION_NAME_DESC + LOCATION_DESC_HOTEL
                + START_DAY_DESC_HOTEL + INVALID_ACCOMMODATION_END_DAY_DESC, AccommodationName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ACCOMMODATION_NAME_DESC_HOTEL + LOCATION_DESC_HOTEL
                        + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAccommodationBookingCommand.MESSAGE_USAGE));
    }

}
