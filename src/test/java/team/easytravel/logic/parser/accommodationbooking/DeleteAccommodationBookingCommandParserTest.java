package team.easytravel.logic.parser.accommodationbooking;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.accommodationbooking.DeleteAccommodationBookingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteAccommodationBookingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteAccommodationBookingCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
class DeleteAccommodationBookingCommandParserTest {

    private DeleteAccommodationBookingCommandParser parser = new DeleteAccommodationBookingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteAccommodationBookingCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
                "accommodation booking"));
    }

}
