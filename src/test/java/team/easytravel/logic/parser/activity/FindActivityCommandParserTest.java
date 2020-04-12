package team.easytravel.logic.parser.activity;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.activity.FindActivityCommand;
import team.easytravel.model.listmanagers.activity.ActivityContainKeywordPredicate;

public class FindActivityCommandParserTest {

    private FindActivityCommandParser parser = new FindActivityCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindActivityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindActivityCommand() {
        // no leading and trailing whitespaces
        FindActivityCommand expectedFindActivityCommand =
                new FindActivityCommand(new ActivityContainKeywordPredicate(Arrays.asList("Cheese", "Japan")));
        assertParseSuccess(parser, "Cheese Japan", expectedFindActivityCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Cheese \n \t Japan  \t", expectedFindActivityCommand);
    }

}

