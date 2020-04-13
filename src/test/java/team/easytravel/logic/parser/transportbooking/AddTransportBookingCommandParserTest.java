package team.easytravel.logic.parser.transportbooking;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.DEFAULT_END_DATE_TIME;
import static team.easytravel.logic.commands.CommandTestUtil.DEFAULT_END_LOCATION;
import static team.easytravel.logic.commands.CommandTestUtil.DEFAULT_START_DATE_TIME;
import static team.easytravel.logic.commands.CommandTestUtil.DEFAULT_START_LOCATION;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TRANSPORT_END_DATE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TRANSPORT_END_LOCATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TRANSPORT_MODE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TRANSPORT_START_DATE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TRANSPORT_START_LOCATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TRANSPORT_END_DATE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TRANSPORT_END_LOCATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TRANSPORT_MODE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TRANSPORT_MODE_PLANE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TRANSPORT_START_DATE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TRANSPORT_START_LOCATION_DESC;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.time.DateTime;
import team.easytravel.logic.commands.transportbooking.AddTransportBookingCommand;
import team.easytravel.model.listmanagers.transportbooking.Mode;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.testutil.transportbooking.TransportBookingBuilder;

public class AddTransportBookingCommandParserTest {
    private AddTransportBookingCommandParser parser = new AddTransportBookingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TransportBooking expectedTransportBooking = new TransportBookingBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + VALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC + VALID_TRANSPORT_END_DATE_DESC,
                new AddTransportBookingCommand(expectedTransportBooking));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddTransportBookingCommand.MESSAGE_USAGE);

        // missing MODE prefix
        assertParseFailure(parser, VALID_TRANSPORT_MODE_PLANE + VALID_TRANSPORT_START_LOCATION_DESC
                        + VALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                        + VALID_TRANSPORT_END_DATE_DESC, expectedMessage);

        // missing LOCATION prefix
        assertParseFailure(parser, VALID_TRANSPORT_MODE_DESC
                        + DEFAULT_START_LOCATION
                        + VALID_TRANSPORT_END_LOCATION_DESC
                        + VALID_TRANSPORT_START_DATE_DESC + VALID_TRANSPORT_END_DATE_DESC, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, VALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + DEFAULT_END_LOCATION
                + VALID_TRANSPORT_START_DATE_DESC + VALID_TRANSPORT_END_DATE_DESC, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TRANSPORT_MODE_PLANE
                + DEFAULT_START_LOCATION
                + DEFAULT_END_LOCATION
                + DEFAULT_START_DATE_TIME + DEFAULT_END_DATE_TIME, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid MODE
        assertParseFailure(parser, INVALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + VALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                + VALID_TRANSPORT_END_DATE_DESC, Mode.MESSAGE_CONSTRAINTS);

        // invalid LOCATION
        assertParseFailure(parser, VALID_TRANSPORT_MODE_DESC
                + INVALID_TRANSPORT_START_LOCATION_DESC
                + VALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                + VALID_TRANSPORT_END_DATE_DESC, Location.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, VALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + INVALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                + VALID_TRANSPORT_END_DATE_DESC, Location.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, VALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + VALID_TRANSPORT_END_LOCATION_DESC + INVALID_TRANSPORT_START_DATE_DESC
                + VALID_TRANSPORT_END_DATE_DESC, DateTime.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, VALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + VALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                + INVALID_TRANSPORT_END_DATE_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + INVALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                + VALID_TRANSPORT_END_DATE_DESC, Mode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_TRANSPORT_MODE_DESC
                + VALID_TRANSPORT_START_LOCATION_DESC
                + VALID_TRANSPORT_END_LOCATION_DESC + VALID_TRANSPORT_START_DATE_DESC
                + VALID_TRANSPORT_END_DATE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransportBookingCommand.MESSAGE_USAGE));
    }
}
