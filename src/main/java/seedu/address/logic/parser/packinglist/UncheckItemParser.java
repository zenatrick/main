package seedu.address.logic.parser.packinglist;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.packinglist.UncheckItemCommand;
import seedu.address.logic.commands.packinglist.UncheckItemCommand.UncheckItemDescriptor;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The type Uncheck item parser.
 */
public class UncheckItemParser implements Parser<UncheckItemCommand> {
    @Override
    public UncheckItemCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            UncheckItemDescriptor uncheckItemDescriptor = new UncheckItemDescriptor();
            return new UncheckItemCommand(index, uncheckItemDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UncheckItemCommand.MESSAGE_USAGE), pe);
        }
    }
}
