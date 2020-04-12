package team.easytravel.logic.parser.activity;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.activity.FindActivityTagCommand;
import team.easytravel.model.listmanagers.activity.ActivityTagContainsPredicate;

public class FindActivityTagCommandParserTest {

    private FindActivityTagCommandParser parser = new FindActivityTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindActivityTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindActivityTagCommand() {
        // no leading and trailing whitespaces
        FindActivityTagCommand expectedFindActivityTagCommand =
                new FindActivityTagCommand(new ActivityTagContainsPredicate(Arrays.asList("Cheese", "Japan")));
        assertParseSuccess(parser, "Cheese Japan", expectedFindActivityTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Cheese \n \t Japan  \t", expectedFindActivityTagCommand);
    }

}
