package team.easytravel.logic.parser.activity;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.activity.DeleteActivityCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteActivityCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteActivityCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteActivityCommandParserTest {

    private DeleteActivityCommandParser parser = new DeleteActivityCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteActivityCommand() {
        assertParseSuccess(parser, "1", new DeleteActivityCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT,
                "activity"));
    }
}
