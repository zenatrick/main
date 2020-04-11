package team.easytravel.logic.parser.packinglist;

import java.util.List;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.packinglist.CheckItemCommand;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * The type Check item parser.
 */
public class CheckItemCommandParser implements Parser<CheckItemCommand> {
    @Override
    public CheckItemCommand parse(String userInput) throws ParseException {
        try {
            List<Index> indexes = ParserUtil.parseMultipleIndex(userInput);
            return new CheckItemCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT, "item"), pe);
        }
    }
}
