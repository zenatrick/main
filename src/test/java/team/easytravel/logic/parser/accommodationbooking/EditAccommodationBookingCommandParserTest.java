package team.easytravel.logic.parser.accommodationbooking;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT;
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
import static team.easytravel.logic.commands.CommandTestUtil.REMARK_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.REMARK_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.START_DAY_DESC_HOTEL;
import static team.easytravel.logic.commands.CommandTestUtil.START_DAY_DESC_RITZ;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_END_DAY;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_LOCATION;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_REMARK;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_HOTEL_START_DAY;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_RITZ_END_DAY;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_RITZ_LOCATION;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_RITZ_NAME;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_RITZ_REMARK;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACCOMMODATION_RITZ_START_DAY;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.accommodationbooking.EditAccommodationBookingCommand;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationName;
import team.easytravel.model.listmanagers.accommodationbooking.Day;
import team.easytravel.model.listmanagers.accommodationbooking.Remark;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.testutil.accommodationbooking.EditAccommodationBookingDescriptorBuilder;

class EditAccommodationBookingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAccommodationBookingCommand.MESSAGE_USAGE);

    private static final String MESSAGE_INVALID_INDEX = String.format(MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
            "accommodation booking");

    private EditAccommodationBookingCommandParser parser = new EditAccommodationBookingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ACCOMMODATION_RITZ_NAME, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAccommodationBookingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_ACCOMMODATION_RITZ_NAME, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_ACCOMMODATION_RITZ_NAME, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_NAME_DESC,
                AccommodationName.MESSAGE_CONSTRAINTS); // invalid accommodation name
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_LOCATION_DESC,
                Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_START_DAY_DESC,
                Day.MESSAGE_CONSTRAINTS); // invalid start day
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_END_DAY_DESC,
                Day.MESSAGE_CONSTRAINTS); // invalid end day
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_REMARK_DESC,
                Remark.MESSAGE_CONSTRAINTS); // invalid remark

        // invalid location followed by valid start day
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_LOCATION_DESC + START_DAY_DESC_RITZ,
                Location.MESSAGE_CONSTRAINTS);

        // valid location followed by invalid location. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + LOCATION_DESC_HOTEL + INVALID_ACCOMMODATION_LOCATION_DESC,
                Location.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ACCOMMODATION_NAME_DESC + INVALID_ACCOMMODATION_LOCATION_DESC
                        + INVALID_ACCOMMODATION_START_DAY_DESC + INVALID_ACCOMMODATION_END_DAY_DESC,
                AccommodationName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + LOCATION_DESC_RITZ + REMARK_DESC_RITZ
                + START_DAY_DESC_RITZ + END_DAY_DESC_RITZ + ACCOMMODATION_NAME_DESC_RITZ;

        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder().withAccommodationName(VALID_ACCOMMODATION_RITZ_NAME)
                        .withLocation(VALID_ACCOMMODATION_RITZ_LOCATION)
                        .withStartDay(VALID_ACCOMMODATION_RITZ_START_DAY)
                        .withEndDay(VALID_ACCOMMODATION_RITZ_END_DAY)
                        .withRemark(VALID_ACCOMMODATION_RITZ_REMARK).build();

        EditAccommodationBookingCommand expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + LOCATION_DESC_HOTEL + REMARK_DESC_RITZ;


        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder().withLocation(VALID_ACCOMMODATION_HOTEL_LOCATION)
                        .withRemark(VALID_ACCOMMODATION_RITZ_REMARK).build();

        EditAccommodationBookingCommand expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // accommodation name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + ACCOMMODATION_NAME_DESC_RITZ;
        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder()
                        .withAccommodationName(VALID_ACCOMMODATION_RITZ_NAME).build();
        EditAccommodationBookingCommand expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_RITZ;
        descriptor = new EditAccommodationBookingDescriptorBuilder().withLocation(VALID_ACCOMMODATION_RITZ_LOCATION)
                .build();
        expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start day
        userInput = targetIndex.getOneBased() + START_DAY_DESC_RITZ;
        descriptor = new EditAccommodationBookingDescriptorBuilder().withStartDay(VALID_ACCOMMODATION_RITZ_START_DAY)
                .build();
        expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end day
        userInput = targetIndex.getOneBased() + END_DAY_DESC_RITZ;
        descriptor = new EditAccommodationBookingDescriptorBuilder().withEndDay(VALID_ACCOMMODATION_RITZ_END_DAY)
                .build();
        expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark
        userInput = targetIndex.getOneBased() + REMARK_DESC_RITZ;
        descriptor = new EditAccommodationBookingDescriptorBuilder().withRemark(VALID_ACCOMMODATION_RITZ_REMARK)
                .build();
        expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + LOCATION_DESC_RITZ + START_DAY_DESC_RITZ + END_DAY_DESC_RITZ
                + REMARK_DESC_RITZ + LOCATION_DESC_RITZ + START_DAY_DESC_RITZ + END_DAY_DESC_RITZ + REMARK_DESC_RITZ
                + LOCATION_DESC_HOTEL + START_DAY_DESC_HOTEL + END_DAY_DESC_HOTEL + REMARK_DESC_HOTEL;

        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder().withLocation(VALID_ACCOMMODATION_HOTEL_LOCATION)
                        .withStartDay(VALID_ACCOMMODATION_HOTEL_START_DAY).withEndDay(VALID_ACCOMMODATION_HOTEL_END_DAY)
                        .withRemark(VALID_ACCOMMODATION_HOTEL_REMARK).build();
        EditAccommodationBookingCommand expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_ACCOMMODATION_LOCATION_DESC
                + LOCATION_DESC_HOTEL;
        EditAccommodationBookingCommand.EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder().withLocation(VALID_ACCOMMODATION_HOTEL_LOCATION)
                        .build();
        EditAccommodationBookingCommand expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + START_DAY_DESC_HOTEL + INVALID_ACCOMMODATION_LOCATION_DESC
                + END_DAY_DESC_HOTEL + LOCATION_DESC_HOTEL;
        descriptor = new EditAccommodationBookingDescriptorBuilder().withLocation(VALID_ACCOMMODATION_HOTEL_LOCATION)
                .withStartDay(VALID_ACCOMMODATION_HOTEL_START_DAY).withEndDay(VALID_ACCOMMODATION_HOTEL_END_DAY)
                .build();
        expectedCommand = new EditAccommodationBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
