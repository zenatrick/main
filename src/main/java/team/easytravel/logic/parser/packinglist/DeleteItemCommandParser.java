package team.easytravel.logic.parser.packinglist;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.packinglist.DeleteItemCommand;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * The type Delete item parser.
 */
public class DeleteItemCommandParser implements Parser<DeleteItemCommand> {

    @Override
    public DeleteItemCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteItemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT, "item"), pe);
        }
    }
}
