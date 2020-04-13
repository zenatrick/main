package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.packinglist.DeleteItemCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteItemCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteItemCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteItemCommandParserTest {
    private DeleteItemCommandParser parser = new DeleteItemCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteItemCommand() {
        assertParseSuccess(parser, "1", new DeleteItemCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "a", expectedMessage);
    }
}
