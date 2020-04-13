package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.packinglist.FindItemCategoryCommand;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategoryContainsKeywordsPredicate;

public class FindItemCategoryCommandParserTest {
    private FindItemCategoryCommandParser parser = new FindItemCategoryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCategoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindItemCategoryCommand() {
        // no leading and trailing whitespaces
        FindItemCategoryCommand expectedFindItemCategoryCommand =
                new FindItemCategoryCommand(new ItemCategoryContainsKeywordsPredicate(Arrays.asList("clothes",
                        "international")));
        assertParseSuccess(parser, "clothes international", expectedFindItemCategoryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n clothes \n \t international  \t", expectedFindItemCategoryCommand);
    }
}
