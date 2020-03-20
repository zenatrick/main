package seedu.address.logic.parser.packinglist;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.packinglist.DeleteItemCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The type Delete item parser.
 */
public class DeleteItemParser implements Parser<DeleteItemCommand> {

    @Override
    public DeleteItemCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteItemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format
                    (MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE), pe);
        }
    }
}
